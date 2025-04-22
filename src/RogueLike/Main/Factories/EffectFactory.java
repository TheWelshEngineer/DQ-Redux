package RogueLike.Main.Factories;

import java.util.ArrayList;
import java.util.Objects;

import RogueLike.Main.AoE.InstantiatedAoE;
import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Screens.TerminalText;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Particle;
import RogueLike.Main.Tile;
import RogueLike.Main.Utils.PointShapes.Cone;
import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Square;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Utils.PointShapes.Point;

public class EffectFactory {
	
	public ObjectFactory objectFactory;
	
	public EffectFactory getThis() {
		return this;
	}
	
	public EffectFactory(ObjectFactory factory) {
		this.objectFactory = factory;
	}
	
	//Spell Effects
	
	//Evocation Spells
	public Effect magicMissile(Creature reference) {
		Effect missile = new Effect(1, null, true, reference, ' ', null){
			public void start(Creature creature) {
				creature.doAction(new TerminalText("get hit with a magic missile!"));
				creature.setLastHit(reference);
				int critCheck = Dice.d20.roll()+reference.intelligenceModifier();
				int damageAmount = Dice.d6.roll()+reference.intelligenceModifier();
				if(reference.evocationLevel() >= 1) {
					critCheck += reference.proficiencyBonus();
				}
				if(reference.evocationLevel() >= 2) {
					damageAmount += reference.proficiencyBonus();
				}
				if(reference.evocationLevel() >= 3 && critCheck >= 20) {
					int manaAmount = (int)Math.ceil(reference.maxMana()/2);
					reference.gainMana(manaAmount, false);
				}
	        	Damage damage = new Damage(damageAmount, false, DamageType.MAGIC, getThis(), true);
	        	creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.sparkle(ExtendedAsciiPanel.lilac, 2), creature.x(), creature.y(), creature.z());
				creature.damage(damage, String.format("Killed by %s using Magic Missile", reference.name()));
			}
		};
		missile.setShowInMenu(false);
		return missile;
	}
	
	public Effect forceBlast(Creature reference) {
		Effect repel = new Effect(1, null, true, reference, ' ', null){
			public void start(Creature creature) {
				int px = reference.x();
				int py = reference.y();
				int cx = creature.x();
				int cy = creature.y();
				if(creature == reference) {
					double tempAmount = (ExtraMaths.diceRoll(1, 12));
					int amount = (int) Math.round(tempAmount);
					Damage damage = new Damage(amount, false, DamageType.PHYSICAL, getThis(), true);
					creature.doAction(new TerminalText("get crushed!"));
					creature.damage(damage, "Killed by kinetic energy");
				}else {
					int push = 0;
					for(int i = 0; i < 3; i++) {
						if(cx > px && cy < py) {
							creature.moveBy(1, -1, 0, false);
							push++;
						}
						if(cx > px && cy > py) {
							creature.moveBy(1, 1, 0, false);
							push++;
						}
						if(cx < px && cy < py) {
							creature.moveBy(-1, -1, 0, false);
							push++;
						}
						if(cx < px && cy > py) {
							creature.moveBy(-1, 1, 0, false);
							push++;
						}
						if(cx < px && cy == py) {
							creature.moveBy(-1, 0, 0, false);
							push++;
						}
						if(cx > px && cy == py) {
							creature.moveBy(1, 0, 0, false);
							push++;
						}
						if(cx == px && cy > py) {
							creature.moveBy(0, 1, 0, false);
							push++;
						}
						if(cx == px && cy < py) {
							creature.moveBy(0, -1, 0, false);
							push++;
						}
						creature.doAction(new TerminalText("get thrown back!"));
						int amount = 0;
						for(int j = 0; j < push+1; j++) {
							amount += Dice.d4.roll();
						}
						
						int attackRoll = Dice.d20.roll() + reference.intelligenceModifier();
						if(reference.evocationLevel() >= 1) {
							attackRoll += reference.proficiencyBonus();
						}
						if(reference.evocationLevel() >= 2) {
							amount += reference.proficiencyBonus();
						}
						if(reference.evocationLevel() >= 3 && attackRoll >= 20) {
							int manaAmount = (int)Math.ceil(reference.maxMana()/2);
							reference.gainMana(manaAmount, false);
						}
						if(attackRoll >= creature.armorClass()) {
							Damage damage = new Damage(amount, false, DamageType.MAGIC, getThis(), false);
							creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.blast(ExtendedAsciiPanel.lilac, 2), creature.x(), creature.y(), creature.z());
							creature.damage(damage, "Killed by kinetic energy");
						}
					}
				}
			}
        };
        return repel;
	}
	
	public Effect archmagesAegis(Creature reference) {
		Effect archmagesAegis = new Effect(1, "Archmage's Aegis", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.evocationLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.evocationLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect arcaneWard_ = reference.ai().factory.effectFactory.arcaneWard(duration_);
        		Effect confused_ = reference.ai().factory.effectFactory.confused(duration_);
				creature.addEffect((Effect) arcaneWard_.clone());

				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 2), creature.world())
					.affectedCreaturesExcept(reference)
					.forEach(c -> {
						c.addEffect((Effect) confused_.clone());
						c.world().setParticleAtLocation(c.ai().factory.particleFactory.vortex(ExtendedAsciiPanel.lilac, 2), c.x(), c.y(), c.z());
					});
			}
        };
        archmagesAegis.setShowInMenu(false);
		return archmagesAegis;
	}
	
	public Effect findTraps() {
		Effect findTraps = new Effect(0, null, false, null, ' ', null) {
			public void start(Creature creature) {
				int count = 0;
				for(Point p : new Square(creature.x(), creature.y(), creature.z(), creature.visionRadius())) {
					if(p.x < 0 || p.y < 0 || p.x > creature.world().width() || p.y > creature.world().height()) {
						continue;
					}
					if (creature.world().entity(p.x, p.y, p.z) instanceof Trap) {
						count++;
					}
				}
				if(count > 0) {
					String trap = "trap";
					if(count > 1) {
						trap = "traps";
					}
					creature.notify(String.format("You sense %d %s nearby.", count, trap));
				}else {
					creature.notify("You do not sense any traps nearby.");
				}
			}
		};
		return findTraps;
	}
	
	//Pyromancy Spells
	public Effect firebolt(Creature reference) {
		Effect firebolt = new Effect(1, "Firebolt", true, null, ' ', null) {
        	public void start(Creature creature) {
        		int attackRoll = reference.intelligenceRoll();
        		if(reference.pyromancyLevel() >= 1) {
        			attackRoll += reference.proficiencyBonus();
        		}
        		int damageAmount = Dice.d8.roll()+reference.intelligenceModifier();
        		if(reference.pyromancyLevel() >= 2) {
        			damageAmount += reference.proficiencyBonus();
        		}
        		if(attackRoll >= 20) {
        			damageAmount *= 2;
        		}
				Damage damage = new Damage(damageAmount, false, DamageType.FIRE, getThis(), true);
				if(attackRoll >= creature.armorClass() || attackRoll >= 20) {
					creature.doAction(new TerminalText("get hit with a bolt of fire!"));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.fire(ExtendedAsciiPanel.orange, 2), creature.x(), creature.y(), creature.z());
					creature.damage(damage, String.format("Killed by %s using Firebolt", reference.name()));
					if(attackRoll >= 20 && reference.pyromancyLevel() >= 3) {
						creature.addEffect((Effect) reference.ai().factory.effectFactory.ignited(reference.proficiencyBonus()*2).clone());
					}
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
			}
        };
        firebolt.setShowInMenu(false);
		return firebolt;
	}
	
	public Effect flashfire(Creature reference) {
		Effect flashfire = new Effect(1, "Flashfire", true, null, ' ', null) {
        	public void start(Creature creature) {
        		int damageAmount = Dice.d4.roll()+reference.intelligenceModifier();
        		
				
				if(creature.affectedBy(Effect.ignited)) {
					damageAmount = reference.intelligenceModifier();
					Effect temp = null;
					for(int i = 0; i < creature.effects().size(); i++) {
						if(creature.effects().get(i).type() == Effect.ignited) {
							temp = creature.effects().get(i);
						}
					}
					for(int i = 0; i < temp.duration(); i++) {
						damageAmount += Dice.d4.roll();
					}
					
				}
				
				if(reference.pyromancyLevel() >= 2) {
					damageAmount += reference.proficiencyBonus();
				}

				Damage damage = new Damage(damageAmount, false, DamageType.FIRE, getThis(), true);

				int saveDC = reference.intelligenceSaveDC();
				if(creature.dexterityRoll() < saveDC) {
					creature.doAction(new TerminalText("get consumed by searing flames!"));
					creature.setLastHit(reference);
					if(creature.affectedBy(Effect.ignited)) {
						creature.cureEffectOfType(Effect.ignited);
					}
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.fire(ExtendedAsciiPanel.orange, 2), creature.x(), creature.y(), creature.z());
					creature.damage(damage, String.format("Killed by %s using Flashfire", reference.name()));
				}else {
					creature.notify(String.format("You dodge the %s's spell.", reference.name()));
					reference.notify(String.format("The %s dodges your spell.", creature.name()));
				}
			}
        };
        flashfire.setShowInMenu(false);
		return flashfire;
	}
	
	public Effect brazierBarrier(Creature reference) {
		Effect brazierBarrier = new Effect(1, "Brazier Barrier", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.pyromancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.pyromancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect magmaWard_ = reference.ai().factory.effectFactory.magmaWard(duration_);
        		Effect illuminated_ = reference.ai().factory.effectFactory.illuminated(duration_);
				creature.addEffect((Effect) magmaWard_.clone());
				creature.addEffect((Effect) illuminated_.clone());
			}
        };
        brazierBarrier.setShowInMenu(false);
		return brazierBarrier;
	}
	
	public Effect pyrotechnics(Creature reference) {
		Effect pyrotechnics = new Effect(1, null, true, reference, ' ', null) {
			public void start(Creature creature){
				for(Point p : new Square(creature.x(), creature.y(), creature.z(), 3)) {
					if(creature.tile(p.x, p.y, p.z).canHaveGas()) {
                    	creature.world().changeGasTile(p.x, p.y, p.z, Tile.SMOKE);
                    }
				}
				int duration_ = 5;
                if(reference.pyromancyLevel() >= 1) {
                	duration_ += reference.proficiencyBonus();
                }
                if(reference.pyromancyLevel() >= 2) {
                	duration_ += reference.proficiencyBonus();
                }
				// Blind all creatures within radius 3, except the caster
				int blindedDuration = duration;
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 3), creature.world())
					.affectedCreaturesExcept(reference)
					.forEach(c -> c.addEffect(blinded(blindedDuration)));
            }
		};
		return pyrotechnics;
	}

	public Effect dragonsBreath(Creature player) {
		return new Effect(1, null, true, player, ' ', null){
			public void start(Creature creature) {
				player.notify("You breath a gout of roaring flames!");
				Point source = player.location();
				Point target = creature.location();
				player.world().remove(creature); // Remove the temporary marker creature

				// Calculate damage
				int damageAmount = Dice.d6x2.roll() + player.intelligenceModifier();
				if (player.pyromancyLevel() >= 2) {
					damageAmount += player.proficiencyBonus();
				}
				if (player.pyromancyLevel() >= 3) {
					damageAmount += player.proficiencyBonus();
				}
				Damage damage = new Damage(damageAmount, false, DamageType.FIRE, player.world().factory().effectFactory, true);
				Damage halfDamage = new Damage(damageAmount / 2,  false, DamageType.FIRE, player.world().factory().effectFactory, true);

				// Apply damage to all creatures in the area of effect
				InstantiatedAoE aoe = new InstantiatedAoE(new Cone(source, target, 5, 60.0), player.world());
				aoe.affectedCreaturesExcept(player)
					.forEach(
						c -> {
							if (c.dexterityRoll() >= player.intelligenceSaveDC()) {
								c.doAction(new TerminalText("avoid the worst of the flames!"));
								c.damage(halfDamage, String.format("Killed by %s using Dragon's Breath", player.name()));
							} else {
								c.doAction(new TerminalText("get seared by the scorching flames!"));
								c.damage(damage, String.format("Killed by %s using Dragon's Breath", player.name()));
							}
						}
					);

				// Set fire to terrain in the area of effect
				aoe.points.forEach(
					p -> {
						if (player.world().tile(p.x, p.y, p.z).canHaveSubtiles() && Dice.d6.roll() > 2) { // 2 in 3 chance to set tile on fire
							player.world().changeSubTile(p.x, p.y, p.z, Tile.FIRE);
						}
					}
				);
			}
		};
	}

	//Cryomancy Spells
	public Effect flashFreeze(Creature reference) {
		Effect freeze = new Effect(1, null, true, reference, ' ', null) {
			public void start(Creature creature) {
				
				int savingThrow = creature.dexterityRoll();
				int saveTarget = reference.intelligenceSaveDC();
				int damageAmount = Dice.d8.roll()+reference.intelligenceModifier();
				if(reference.cryomancyLevel() >= 2) {
					damageAmount += reference.proficiencyBonus();
				}
				int duration = 2;
				if(reference.cryomancyLevel() >= 1) {
					duration += reference.proficiencyBonus();
				}
				if(reference.cryomancyLevel() >= 2) {
					duration += reference.proficiencyBonus();
				}
				if(savingThrow < saveTarget) {
					creature.doAction(new TerminalText("get blasted by freezing air!"));
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtendedAsciiPanel.water, 2), creature.x(), creature.y(), creature.z());
					Damage damage = new Damage(damageAmount, false, DamageType.FROST, getThis(), false);
					creature.damage(damage, "Killed by icy magic");
					creature.addEffect(frozen(duration));
				}else {
					creature.notify(String.format("You dodge the %s's spell.", reference.name()));
					reference.notify(String.format("The %s dodges your spell.", creature.name()));
				}
			}
		};
		return freeze;
	}
	
	public Effect iceKnife(Creature reference) {
		Effect iceKnife = new Effect(1, "Ice Knife", true, null, ' ', null) {
        	public void start(Creature creature) {
        		int attackRoll = reference.intelligenceRoll();
        		if(reference.cryomancyLevel() >= 1) {
        			attackRoll += reference.proficiencyBonus();
        		}
        		int hitDamageAmount = Dice.d6.roll()+reference.intelligenceModifier();
				Damage hitDamage = new Damage(hitDamageAmount, false, DamageType.FROST, getThis(), true);
				
				int splashDamageAmount = Dice.d4.roll()+reference.intelligenceModifier();
				Damage splashDamage = new Damage(splashDamageAmount, false, DamageType.FROST, getThis(), true);
				if(reference.cryomancyLevel() >= 2) {
					hitDamageAmount += reference.proficiencyBonus();
					splashDamageAmount += reference.proficiencyBonus();
				}
				if(attackRoll >= 20) {
					hitDamageAmount *= 2;
				}
				
				if(attackRoll >= creature.armorClass() || attackRoll >= 20) {
					creature.doAction(new TerminalText("get hit with a blade of ice!"));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtendedAsciiPanel.water, 2), creature.x(), creature.y(), creature.z());
					creature.damage(hitDamage, String.format("Killed by %s using Ice Knife", reference.name()));
					if(attackRoll >= 20 && reference.cryomancyLevel() >= 3) {
						Effect cryoCrit = creature.ai().factory.effectFactory.frozen(reference.proficiencyBonus());
						creature.addEffect((Effect) cryoCrit.clone());
						creature.ai().factory.effectFactory.spreadEffect(creature, cryoCrit, 1);
					}
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
				if(creature.dexterityRoll() < reference.intelligenceSaveDC()) {
					creature.notify("You feel a biting frost creep over you!");
					reference.notify(String.format("The %s is frostbitten by your spell!", creature.name()));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtendedAsciiPanel.water, 2), creature.x(), creature.y(), creature.z());

					// Damage all adjacent creatures (including target) with splash damage
					new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 3), creature.world())
						.affectedCreatures()
						.forEach(c -> {
							c.damage(splashDamage, String.format("Killed by %s using Ice Knife", reference.name()));
							c.world().setParticleAtLocation(c.ai().factory.particleFactory.frost(ExtendedAsciiPanel.water, 2), c.x(), c.y(), c.z());
						});
				}else {
					creature.notify(String.format("You dodge the %s's spell.", reference.name()));
					reference.notify(String.format("The %s dodges your spell.", creature.name()));
				}
			}
        };
        iceKnife.setShowInMenu(false);
		return iceKnife;
	}
	
	public Effect glaciate(Creature reference) {
		Effect glaciate = new Effect(1, "Glaciate", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.cryomancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.cryomancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect chillWard_ = reference.ai().factory.effectFactory.chillWard(duration_);
				creature.addEffect((Effect) chillWard_.clone());
				for(int x = -1; x < 2; x++) {
					for(int y = -1; y < 2; y++) {
						if((creature.creature(creature.x()+x, creature.y()+y, creature.z()) == null)) {
							Creature newWall = objectFactory.creatureFactory.newIceWall(0, reference, false);
							creature.ai().world.addCreatureAtLocation(newWall, creature.x()+x, creature.y()+y, creature.z());
						}
					}
				}
				creature.notify("You summon a ring of ice!");
			}
        };
        glaciate.setShowInMenu(false);
		return glaciate;
	}
	
	public Effect iceWall(Creature player) {
		Effect wall = new Effect(1, null, false, player, ' ', null){
			public void start(Creature creature) {
				player.notify("You summon a wall of ice!");
				Creature iceWall = objectFactory.creatureFactory.newIceWall(0, player, false);
				creature.become(iceWall);

				int px = player.x();
				int py = player.y();
				int cx = creature.x();
				int cy = creature.y();

				if(cx > px && cy > py) {
					//south west
					//creature.moveBy(1, 1, 0);
					if((creature.creature(cx+1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy, creature.z());
					}
					if((creature.creature(cx+2, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy-1, creature.z());
					}
					if((creature.creature(cx, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy+1, creature.z());
					}
					if((creature.creature(cx-1, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy+2, creature.z());
					}

					if((creature.creature(cx-1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy+1, creature.z());
					}
					if((creature.creature(cx-2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy+2, creature.z());
					}

					if((creature.creature(cx+1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy-1, creature.z());
					}
					if((creature.creature(cx+2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy-2, creature.z());
					}
				}

				if(cx < px && cy < py) {
					//north east
					//creature.moveBy(-1, -1, 0);
					if((creature.creature(cx-1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy, creature.z());
					}
					if((creature.creature(cx-2, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy+1, creature.z());
					}
					if((creature.creature(cx, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy-1, creature.z());
					}
					if((creature.creature(cx+1, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy-2, creature.z());
					}

					if((creature.creature(cx-1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy+1, creature.z());
					}
					if((creature.creature(cx-2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy+2, creature.z());
					}

					if((creature.creature(cx+1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy-1, creature.z());
					}
					if((creature.creature(cx+2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy-2, creature.z());
					}
				}

				if((cx > px && cy < py)) {
					//north west
					//creature.moveBy(1, -1, 0);
					if((creature.creature(cx+1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy, creature.z());
					}
					if((creature.creature(cx+2, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy+1, creature.z());
					}
					if((creature.creature(cx, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy-1, creature.z());
					}
					if((creature.creature(cx-1, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy-2, creature.z());
					}

					if((creature.creature(cx+1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy+1, creature.z());
					}
					if((creature.creature(cx+2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy+2, creature.z());
					}

					if((creature.creature(cx-1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy-1, creature.z());
					}
					if((creature.creature(cx-2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy-2, creature.z());
					}
				}

				if((cx < px && cy > py)) {
					//south east
					//creature.moveBy(-1, 1, 0);
					if((creature.creature(cx-1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy, creature.z());
					}
					if((creature.creature(cx-2, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy-1, creature.z());
					}
					if((creature.creature(cx, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy+1, creature.z());
					}
					if((creature.creature(cx+1, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy+2, creature.z());
					}

					if((creature.creature(cx+1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy+1, creature.z());
					}
					if((creature.creature(cx+2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy+2, creature.z());
					}

					if((creature.creature(cx-1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy-1, creature.z());
					}
					if((creature.creature(cx-2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy-2, creature.z());
					}
				}

				if((cx > px && cy == py)||(cx < px && cy == py)) {
					//creature.moveBy(1, 0, 0);
					if((creature.creature(cx, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy+1, creature.z());
					}
					if((creature.creature(cx, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy-1, creature.z());
					}

					if((creature.creature(cx, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy+2, creature.z());
					}
					if((creature.creature(cx, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx, cy-2, creature.z());
					}
				}


				if((cx == px && cy < py)||(cx == px && cy > py)) {
					//creature.moveBy(0, -1, 0);
					if((creature.creature(cx+1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+1, cy, creature.z());
					}
					if((creature.creature(cx-1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-1, cy, creature.z());
					}

					if((creature.creature(cx+2, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx+2, cy, creature.z());
					}
					if((creature.creature(cx-2, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.creatureFactory.newIceWall(0, player, false);
						creature.ai().world.addCreatureAtLocation(newWall, cx-2, cy, creature.z());
					}
				}
			}
		};
		return wall;
	}
	
	//Electromancy Spells
	public Effect chainLightning(Creature reference) {
		Effect lightning = new Effect(7, null, true, reference, ' ', null){
			public void start(Creature creature){
				int saveDC = reference.intelligenceSaveDC();
				int creatureSave = creature.dexterityRoll();
				int damageAmount = Dice.d8.roll()+reference.intelligenceModifier();
				if(reference.electromancyLevel() >= 2) {
					damageAmount += reference.proficiencyBonus();
				}
				boolean saved = false;
				
				if(creatureSave >= saveDC) {
					damageAmount /= 2;
					saved = true;
				}
				if(damageAmount < 1) {
					damageAmount = 1;
				}
				creature.doAction(new TerminalText("get a shock!"));
				creature.setLastHit(reference);
				creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtendedAsciiPanel.paralyzed, 2), creature.x(), creature.y(), creature.z());
				Damage damage = new Damage(damageAmount, false, DamageType.SHOCK, getThis(), true);
				creature.damage(damage, String.format("Killed by %s using Chain Lightning", reference.name()));
				
				if(!saved) {
					new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 2), creature.world())
						.affectedCreaturesExcept(creature)
						.forEach(c -> {
							c.damage(damage, String.format("Killed by %s using Chain Lightning", reference.name()));
							c.world().setParticleAtLocation(c.ai().factory.particleFactory.shock(ExtendedAsciiPanel.paralyzed, 2), c.x(), c.y(), c.z());
						});
				}
            }
        };
        return lightning;
	}
	
	public Effect lightningLance(Creature reference) {
		Effect lightningLance = new Effect(1, "Lightning Lance", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		Line l = new Line(reference.x(), reference.y(), creature.x(), creature.y());
        		ArrayList<Creature> targets = new ArrayList<Creature>();
        		for(Point p : l) {
        			//applicationMain.terminal.write((char)15, p.x, p.y, ExtraColors.paralyzed);
        			if(creature.creature(p.x, p.y, creature.z()) != null && (p.x != reference.x() && p.y != reference.y())) {
        				targets.add(creature.creature(p.x, p.y, creature.z()));
        				creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtendedAsciiPanel.paralyzed, 2), p.x, p.y, creature.z());
        			}
        			if(creature.creature(p.x, p.y, creature.z()) == null) {
        				creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtendedAsciiPanel.paralyzed, 2), p.x, p.y, creature.z());
        			}
        			
        		}
        		for(Creature c : targets) {
        			int amount = Dice.d8.roll()+reference.intelligenceModifier();
        			if(reference.electromancyLevel() >= 2) {
        				amount += reference.proficiencyBonus();
        			}
    				Damage damage = new Damage(amount, false, DamageType.SHOCK, creature.ai().factory.effectFactory, true);
    				if(c.dexterityRoll() < reference.intelligenceSaveDC()) {
    					c.doAction(new TerminalText("get hit with a lance of electricity!"));
    					c.damage(damage, String.format("Killed by %s using Lightning Lance", reference.name()));
    				}
    				
        		}
			}
        };
        lightningLance.setShowInMenu(false);
		return lightningLance;
	}
	
	public Effect staticSurge(Creature reference) {
		Effect staticSurge = new Effect(1, "Static Surge", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.electromancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.electromancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect arcWard_ = reference.ai().factory.effectFactory.arcWard(duration_);
        		Effect electrocharged_ = reference.ai().factory.effectFactory.electrocharged(duration_);
				creature.addEffect((Effect) arcWard_.clone());
				creature.addEffect((Effect) electrocharged_.clone());
			}
        };
        staticSurge.setShowInMenu(false);
		return staticSurge;
	}
	
	public Effect hasteSpell(Creature reference) {
		Effect hasteSpell = new Effect(1, "Haste", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.electromancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.electromancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect haste_ = reference.ai().factory.effectFactory.haste(duration_);
				creature.addEffect((Effect) haste_.clone());
			}
        };
        hasteSpell.setShowInMenu(false);
		return hasteSpell;
	}
	
	//Alchemancy Spells
	public Effect acidBlast(Creature reference) {
		Effect acidBlast = new Effect(1, "Acid Blast", true, null, ' ', null) {
        	public void start(Creature creature) {
        		int attackRoll = reference.intelligenceRoll();
        		if(reference.alchemancyLevel() >= 1) {
        			attackRoll += reference.proficiencyBonus();
        		}
        		
        		int damageAmount = Dice.d4.roll()+reference.intelligenceModifier();
				
				if(creature.affectedBy(Effect.corroded)) {
					damageAmount = Dice.d10.roll()+reference.intelligenceModifier(); 
				}
				
				if(reference.alchemancyLevel() >= 2) {
					damageAmount += reference.proficiencyBonus();
				}
				
				if(attackRoll >= 20) {
					damageAmount *= 2;
				}
				
				Damage damage = new Damage(damageAmount, false, DamageType.ACID, getThis(), true);
				if(attackRoll >= creature.armorClass() || attackRoll >= 20) {
					creature.doAction(new TerminalText("get hit with a blast of acid!"));
					creature.setLastHit(reference);
					if(creature.affectedBy(Effect.corroded)) {
						creature.cureEffectOfType(Effect.corroded);
					}
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtendedAsciiPanel.lime, 2), creature.x(), creature.y(), creature.z());
					creature.damage(damage, String.format("Killed by %s using Acid Blast", reference.name()));
					if(attackRoll >= 20 && reference.alchemancyLevel() >= 3) {
						reference.addEffect(reference.ai().factory.effectFactory.restoration());
						reference.heal(5 * reference.proficiencyBonus());
					}
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
			}
        };
        acidBlast.setShowInMenu(false);
		return acidBlast;
	}
	
	public Effect toxicTransfusion(Creature reference) {
		Effect toxicTransfusion = new Effect(1, "Toxic Transfusion", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int attackRoll = reference.intelligenceRoll();
        		int damageAmount = Dice.d6.roll()+reference.intelligenceModifier();
        		int duration_ = 10;
        		if(reference.alchemancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        			attackRoll += reference.proficiencyBonus();
        		}
        		if(reference.alchemancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        			damageAmount += reference.proficiencyBonus();
        		}
        		if(attackRoll >= 20) {
        			damageAmount *= 2;
        		}
        		Effect venomousWard_ = reference.ai().factory.effectFactory.venomousWard(duration_);
        		Effect poisoned_ = reference.ai().factory.effectFactory.poisoned(duration_/2);
        		Effect corroded_ = reference.ai().factory.effectFactory.corroded(duration_/2);
				reference.addEffect((Effect) venomousWard_.clone());
				
				
				Damage damage = new Damage(damageAmount, false, DamageType.POISON, getThis(), false);

				if(attackRoll >= creature.armorClass() || attackRoll >= 20) {
					creature.doAction(new TerminalText("get hit with a splash of poison!"));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtendedAsciiPanel.magenta, 2), creature.x(), creature.y(), creature.z());
					creature.damage(damage, String.format("Killed by %s using Toxic Transfusion", reference.name()));
					if(attackRoll >= 20 && reference.alchemancyLevel() >= 3) {
						reference.addEffect(reference.ai().factory.effectFactory.restoration());
						reference.heal(5 * reference.proficiencyBonus());
					}
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
				if(creature.strengthRoll() < reference.intelligenceSaveDC()) {
					creature.notify("You feel a searing toxin flood your veins!");
					reference.notify(String.format("The %s is afflicted by your spell!", creature.name()));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtendedAsciiPanel.magenta, 2), creature.x(), creature.y(), creature.z());
					creature.addEffect((Effect) poisoned_.clone());
					creature.addEffect((Effect) corroded_.clone());
				}else {
					creature.notify(String.format("You resist the %s's spell.", reference.name()));
					reference.notify(String.format("The %s resists your spell.", creature.name()));
				}
			}
        };
        toxicTransfusion.setShowInMenu(false);
		return toxicTransfusion;
	}
	
	public Effect refluxBarrier(Creature reference) {
		Effect refluxBarrier = new Effect(1, "Reflux Barrier", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.alchemancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.alchemancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect causticWard_ = reference.ai().factory.effectFactory.causticWard(duration_);
        		Effect restoration_ = reference.ai().factory.effectFactory.restoration();
				creature.addEffect((Effect) causticWard_.clone());
				creature.addEffect((Effect) restoration_.clone());
			}
        };
        refluxBarrier.setShowInMenu(false);
		return refluxBarrier;
	}
	
	public Effect lifetap(Creature reference) {
		Effect lifetap = new Effect(1, "Lifetap", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int amount_ = (int) creature.hp() / 2;
        		Damage drainDamage = new Damage(amount_, false, DamageType.TRUE, null, false);
        		creature.damage(drainDamage, "Killed by your own ambition");
        		if(reference.alchemancyLevel() >= 1) {
        			amount_ += reference.proficiencyBonus();
        		}
        		if(reference.alchemancyLevel() >= 2) {
        			amount_ += reference.proficiencyBonus();
        		}
        		creature.gainMana(amount_, true);
			}
        };
        lifetap.setShowInMenu(false);
		return lifetap;
	}
	
	//Ferromancy Spells
	public Effect armorStorm(Creature reference) {
		Effect armorStorm = new Effect(1, "Armor Storm", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int attackRoll = reference.intelligenceRoll();
        		int stacks_ = reference.armorClass()-10;
        		if(stacks_ <= 0) {
        			stacks_ = 1;
        		}
        		int damageAmount = 0;
        		for(int i = 0; i < stacks_+1; i++) {
        			damageAmount += Dice.d4.roll();
        		}
        		if(reference.ferromancyLevel() >= 1) {
        			attackRoll += reference.proficiencyBonus();
        		}
        		if(reference.ferromancyLevel() >= 2) {
        			damageAmount += reference.proficiencyBonus();
        		}
        		if(attackRoll >= 20) {
        			damageAmount *= 2;
        		}
        		
        		if(attackRoll >= creature.armorClass() || attackRoll >= 20) {
        			Damage damage = new Damage(damageAmount, false, DamageType.PHYSICAL, reference.ai().factory.effectFactory, true);
            		creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.blast(ExtendedAsciiPanel.white, 2), creature.x(), creature.y(), creature.z());
            		creature.doAction(new TerminalText("get torn apart by metal shards!"));
            		creature.damage(damage, String.format("Killed by %s using Armor Storm", reference.name()));
            		creature.setLastHit(reference);
            		Effect sundered_ = reference.ai().factory.effectFactory.sundered(stacks_);
    				reference.addEffect((Effect) sundered_.clone());
    				if(attackRoll >= 20 && reference.ferromancyLevel() >= 3) {
    					Effect giantStrength_ = reference.ai().factory.effectFactory.giantStrength(reference.proficiencyBonus());
        				reference.addEffect((Effect) giantStrength_.clone());
    				}
        		}else {
        			creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
        		}
			}
        };
        armorStorm.setShowInMenu(false);
		return armorStorm;
	}
	
	public Effect weaponBolt(Creature reference) {
		Effect weaponBolt = new Effect(1, "Weapon Bolt", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int damageAmount = 0;
        		if(reference.weapon() != null) {
        			if(reference.weapon().isVersatile()) {
        				damageAmount = reference.weapon().versatileDamageDice().roll();
        			}else if(reference.weapon().isRangedWeapon()){
        				damageAmount = reference.weapon().rangedDamageDice().roll();
        			}else {
        				damageAmount = reference.weapon().damageDice().roll();
        			}
        			if(reference.weapon().upgradeLevel() > 0) {
        				damageAmount += reference.weapon().upgradeLevel();
        			}
        		}
        		int attackRoll = reference.intelligenceSaveDC();
        		
        		if(reference.ferromancyLevel() >= 1) {
        			attackRoll += reference.proficiencyBonus();
        		}
        		if(reference.ferromancyLevel() >= 2) {
        			damageAmount += reference.proficiencyBonus();
        		}
        		if(attackRoll >= 20) {
        			damageAmount *= 2;
        		}
        		
        		Damage damage = new Damage(damageAmount, false, DamageType.MAGIC, reference.ai().factory.effectFactory, true);
        		if(attackRoll >= creature.armorClass() || attackRoll >= 20) {
    				TerminalText newNotification = new TerminalText();
    				newNotification.append("get hit with a spectral ");
    				newNotification.append(reference.weapon().name(), reference.weapon().color());
    				newNotification.append("!");
    				creature.doAction(newNotification);
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.blast(ExtendedAsciiPanel.white, 2), creature.x(), creature.y(), creature.z());
					creature.damage(damage, String.format("Killed by %s using Weapon Bolt", reference.name()));
					if(attackRoll >= 20 && reference.ferromancyLevel() >= 3) {
    					Effect giantStrength_ = reference.ai().factory.effectFactory.giantStrength(reference.proficiencyBonus());
        				reference.addEffect((Effect) giantStrength_.clone());
    				}
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
			}
        };
        weaponBolt.setShowInMenu(false);
		return weaponBolt;
	}
	
	public Effect bladsWard(Creature reference) {
		Effect bladsWard = new Effect(1, "Blad's Ward", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.ferromancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.ferromancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect bladeWard_ = reference.ai().factory.effectFactory.bladeWard(duration_);
        		Effect giantStrength_ = reference.ai().factory.effectFactory.giantStrength(duration_);
				creature.addEffect((Effect) bladeWard_.clone());
				creature.addEffect((Effect) giantStrength_.clone());
			}
        };
        bladsWard.setShowInMenu(false);
		return bladsWard;
	}
	
	public Effect infuseUpgrade(Creature reference) {
		Effect infuseUpgrade = new Effect(1, "Infuse Upgrade", false, reference, ' ', null) {
        	public void start(Creature creature) {
        		int chance = reference.mana();
        		creature.loseMana(chance, false);
        		
        		if(reference.ferromancyLevel() >= 1) {
        			chance += reference.proficiencyBonus();
        		}
        		if(reference.ferromancyLevel() >= 2) {
        			chance += reference.proficiencyBonus();
        		}
        		
        		if(Dice.d100.roll() <= chance) {
        			creature.notify("You call forth upgrading magic!");
        			Effect upgradeScroll_ = reference.ai().factory.effectFactory.upgradeScroll();
    				creature.addEffect((Effect) upgradeScroll_.clone());
        		}else {
        			creature.notify("You fail to call forth upgrading magic..");
        		}
			}
        };
        infuseUpgrade.setShowInMenu(false);
		return infuseUpgrade;
	}
	
	//General Effects
	
	public Effect maxHealth() {
		Effect maxHealth = new Effect(1, null, false, null, ' ', null) {
			public void start(Creature creature) {
				if(creature.hp() == creature.maxHP()) {
					return;
				}
				creature.heal(creature.maxHP() - creature.hp());
				//creature.doAction(new TerminalText("look healthier"));
			}
		};
		return maxHealth;
	}
	
	public Effect maxMana() {
		Effect maxMana = new Effect(1, null, false, null, ' ', null) {
			public void start(Creature creature) {
				if(creature.mana() == creature.maxMana()) {
					return;
				}
				creature.gainMana(creature.maxMana() - creature.mana(), false);
				creature.doAction(new TerminalText("look energised"));
			}
		};
		return maxMana;
	}
	
	public Effect poisoned(int duration) {
		Effect poisoned = new Effect(duration, "Poisoned", true, null, Effect.poisoned, ExtendedAsciiPanel.getGlyphFromPage(165, 2), ExtendedAsciiPanel.magenta) { //10
			public void start(Creature creature) {
				creature.doAction(new TerminalText("look unwell"));
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(Dice.d4.roll(), false, DamageType.POISON, getThis(), false);
				creature.damage(damage, "Killed by poison");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.poisoned)) {
					creature.doAction(new TerminalText("recover from poison"));
				}
			}
		};
		poisoned.setDescription("You have been poisoned - you take 1d4 Poison damage each turn you are affected.");
		return poisoned;
	}
	
	public Effect bleeding(int duration) {
		Effect bleeding = new Effect(duration, "Bleeding", true, null, Effect.bleeding, ExtendedAsciiPanel.getGlyphFromPage(161, 2), ExtendedAsciiPanel.red) { //10
			public void start(Creature creature) {
				creature.doAction(new TerminalText("begin to bleed"));
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(Dice.d4.roll(), false, DamageType.PHYSICAL, getThis(), false);
				creature.damage(damage, "Killed by bleeding");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.bleeding)) {
					creature.doAction(new TerminalText("stem the bleeding"));
				}
			}
		};
		bleeding.setDescription("You are bleeding - you take 1d4 Physical damage each turn you are affected.");
		return bleeding;
	}
	
	public Effect giantStrength(int duration) {
		Effect giantStrength = new Effect(duration, "Giant Strength", false, null, Effect.giantStrength, ExtendedAsciiPanel.getGlyphFromPage(3, 1), ExtendedAsciiPanel.orange) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("swell with power"));
			}
			public void end(Creature creature) {
				creature.doAction(new TerminalText("return to normal strength"));
			}
		};
		giantStrength.setDescription("The strength of giants flows through your muscles, temporarily granting you a +4 bonus to your Strength and Armor Class.");
		return giantStrength;
	}
	
	public Effect beastForm(int duration) {
		Effect beastForm = new Effect(duration, "Beast Form", false, null, Effect.beastForm, ExtendedAsciiPanel.getGlyphFromPage(3, 1), ExtendedAsciiPanel.green) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("appear more bestial"));
			}
			public void end(Creature creature) {
				creature.doAction(new TerminalText("return to a normal appearance"));
			}
		};
		beastForm.setDescription("The ferocity of beasts suffuses your body and mind, temporarily granting you a +4 bonus to your Dexterity and Vision Radius.");
		return beastForm;
	}
	
	public Effect corroded(int duration) {
		Effect corroded = new Effect(duration, "Corroded", true, null, Effect.corroded, ExtendedAsciiPanel.getGlyphFromPage(161, 2), ExtendedAsciiPanel.lime) {
        	public void start(Creature creature) {
        		//creature.modifyDefenseValue(-6);
				creature.doAction(new TerminalText("get coated in biting acid"));
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(Dice.d4.roll(), false, DamageType.ACID, getThis(), false);
				creature.damage(damage, "Killed by acid");
				creature.notify("The acid burns you!");	
			}
			public void end(Creature creature) {
				//creature.modifyDefenseValue(6);
				if(!creature.affectedBy(Effect.corroded) == false) {
    				creature.doAction(new TerminalText("recover from the acid burns"));
				}
			}
        };
		corroded.setDescription("You are coated in acid - you take 1d4 Acid damage each turn you are affected and suffer a -2 penalty to your Armor Class whilst affected.");
		return corroded;
	}
	
	
	public Effect causticVapor(int duration) {
		Effect causticVapor = new Effect(1, null, true, null, ' ', null) {
			public void start(Creature creature){
                for (int ox = -2; ox < 3; ox++){
                    for (int oy = -2; oy < 3; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if(creature.tile(nx, ny, creature.z()).canHaveGas()) {
                        	creature.world().changeGasTile(nx, ny, creature.z(), Tile.ACID_GAS);
                        }
                        if (creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        Effect gas = corroded(duration);
                        creature.creature(nx, ny, creature.z).addEffect(gas);
                    }
                }
            }
		};
		return causticVapor;
	}

	
	public Effect mindVision(int duration) {
		Effect mindVision = new Effect(duration, "Mind Vision", false, null, Effect.mindVision, ExtendedAsciiPanel.getGlyphFromPage(163, 2), ExtendedAsciiPanel.pink){
            public void start(Creature creature){
                creature.doAction(new TerminalText("look far off into the distance"));
            }
            public void end(Creature creature){
            }
        };//TODO update effects
		mindVision.setDescription("You can sense the presence of minds, temporarily allowing you to see creatures at any distance regardless of intervening obstacles.");
        return mindVision;
	}
	
	public Effect restoration() {
		Effect restoration = new Effect(1, null, false, null, ' ', null) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("look refreshed"));
				creature.cureNegativeEffects();
			}
		};
		return restoration;
	}
	
	public Effect blinded(int duration) {
		Effect blinded = new Effect(duration, "Blinded", true, null, Effect.blinded, ExtendedAsciiPanel.getGlyphFromPage(15, 1), ExtendedAsciiPanel.invisible) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("become blinded!"));
			}
			public void end(Creature creature) {
				if(creature.affectedBy(Effect.blinded)) {
					creature.doAction(new TerminalText("return to seeing normally"));
				}

			}
		};
		blinded.setDescription("You've been temporarily blinded, setting your base Vision Radius to 2.");
		return blinded;
	}
	
	public Effect illuminated(int duration) {
		Effect illuminated = new Effect(duration, "Illuminated", false, null, Effect.illuminated, ExtendedAsciiPanel.getGlyphFromPage(15, 1), ExtendedAsciiPanel.brightYellow) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("glow with a bright light!"));
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.illuminated) == false) {
					creature.doAction(new TerminalText("stop glowing"));
				}
			}
		};
		illuminated.setDescription("You are glowing with a bright light, illuminating your surroundings and granting a +10 bonus to your Vision Radius.");
		return illuminated;
	}
	
	public Effect haste(int duration) {
		Effect haste = new Effect(duration, "Haste", false, null, Effect.haste, ExtendedAsciiPanel.getGlyphFromPage(164, 2), ExtendedAsciiPanel.brightGreen) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("begin to vibrate with energy!"));
			}
			public void end(Creature creature) {
				if(creature.affectedBy(Effect.haste)) {
					creature.doAction(new TerminalText("slow down to normal speed"));
				}
			}
		};
		haste.setDescription("Wild energy flows through you, doubling the speed at which you move, attack, and take actions.");
		return haste;
	}
	
	public Effect electrocharged(int duration) {
		Effect electrocharged = new Effect(duration, "Electrocharged", false, null, Effect.electrocharged, ExtendedAsciiPanel.getGlyphFromPage(164, 2), ExtendedAsciiPanel.brightCyan) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("become infused with electricity!"));
			}
			public void end(Creature creature) {
				if(creature.affectedBy(Effect.electrocharged)) {
					creature.doAction(new TerminalText("feel the electrical infusion fade away"));
				}
			}
		};
		electrocharged.setDescription("You are infused with electrical energy, dealing additional Shock damage to your targets on successful attacks.");
		return electrocharged;
	}
	
	public Effect levitating(int duration) {
		Effect levitating = new Effect(duration, "Levitating", false, null, Effect.levitating, ExtendedAsciiPanel.getGlyphFromPage(162, 2), ExtendedAsciiPanel.brightWhite){
            public void start(Creature creature){
                creature.doAction(new TerminalText("float into the air!"));
            }
            public void end(Creature creature){
            	creature.doAction(new TerminalText("float back down to the floor"));
            }
        };
		levitating.setDescription("You are floating in the air, allowing you to glide across pits and trapped floors safely.");
		return levitating;
	}
	
	public Effect devoured(int duration) {
		Effect devoured = new Effect(duration, "Devoured", true, null, Effect.devoured, ExtendedAsciiPanel.getGlyphFromPage(162, 2), ExtendedAsciiPanel.brightMagenta) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("writhe in agony"));
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(Dice.d4.roll(), false, DamageType.CHAOS, getThis(), false);
				creature.damage(damage, "Killed by a devouring curse");
				creature.loseMana(Dice.d4.roll(), false);
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.devoured)) {
					creature.doAction(new TerminalText("escape a devouring curse"));
				}

			}
		};
		devoured.setDescription("A devouring curse is eating away at your life force, draining 1d4 Health and Mana each turn you are affected.");
		return devoured;
	}
	
	public Effect confused(int duration) {
		Effect confused = new Effect(duration, "Confused", true, null, Effect.confused, ExtendedAsciiPanel.getGlyphFromPage(162, 2), ExtendedAsciiPanel.lilac) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("become confused!"));
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction(new TerminalText("wander around in confusion!"));
				//creature.ai().wander();
				
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.confused)) {
					creature.doAction(new TerminalText("stop being confused"));
				}

			}
		};
		confused.setDescription("You are disoriented, and can't control the direction you move or attack in.");
		return confused;
	}
	
	public Effect bounce() {
		Effect bounce = new Effect(7, null, false, null, ' ', null){
			public void start(Creature creature){
				creature.doAction(new TerminalText("let out a wave of force!"));

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                     
                		int px = creature.x();
                		int py = creature.y();
                		int cx = target.x();
                		int cy = target.y();
                		
                		int push = 0;
                		for(int i = 0; i < 3; i++) {
                			if(cx > px && cy < py) {
                				target.moveBy(1, -1, 0, false);
                				push++;
                			}
                			if(cx > px && cy > py) {
                				target.moveBy(1, 1, 0, false);
                				push++;
                			}
                			if(cx < px && cy < py) {
                				target.moveBy(-1, -1, 0, false);
                				push++;
                			}
                			if(cx < px && cy > py) {
                				target.moveBy(-1, 1, 0, false);
                				push++;
                			}
                			if(cx < px && cy == py) {
                				target.moveBy(-1, 0, 0, false);
                				push++;
                			}
                			if(cx > px && cy == py) {
                				target.moveBy(1, 0, 0, false);
                				push++;
                			}
                			if(cx == px && cy > py) {
                				target.moveBy(0, 1, 0, false);
                				push++;
                			}
                			if(cx == px && cy < py) {
                				target.moveBy(0, -1, 0, false);
                				push++;
                			}
                			if(push > 2) {
                				target.doAction(new TerminalText("get thrown back!"));
                			}
                		}
                	}
                }
            };
        };
		return bounce;
	}
	
	public Effect arcaneWard(int duration) {
		Effect arcaneWard = new Effect(duration, "Arcane Ward", false, null, Effect.arcaneWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.lilac){
			public void start(Creature creature){
				creature.doAction(new TerminalText("raise a magical shield!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 1), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						c.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtendedAsciiPanel.lilac, 2), c.x(), c.y(), c.z());
						c.addEffect(blink());
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("let the shielding magic dissipate"));
			}
        };
		arcaneWard.setDescription("An arcane shield surrounds you, immediately teleporting adjacent foes away from you and granting you Resistance to Magic damage for the duration.");
		return arcaneWard;
	}
	
	public Effect venomousWard(int duration) {
		Effect venomousWard = new Effect(duration, "Venomous Ward", false, null, Effect.venomousWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.magenta){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become coated in protective poison!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 1), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						c.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtendedAsciiPanel.magenta, 2), c.x(), c.y(), c.z());
						c.addEffect(poisoned((int)duration/2));
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("feel the poisonous barrier fade"));
			}
        };
		venomousWard.setDescription("A shield of protective poison surrounds you, immediately applying Poisoned to adjacent foes and granting you Resistance to Poison damage for the duration.");
		return venomousWard;
	}
	
	public Effect eldritchWard(int duration) {
		Effect eldritchWard = new Effect(duration, "Eldritch Ward", false, null, Effect.eldritchWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.brightMagenta){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become shrouded by chaotic energy!"));
                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtendedAsciiPanel.green, 2), target.x(), target.y(), target.z());
                        Effect chaosBurst = devoured((int)duration/2);
                		target.addEffect(chaosBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("feel the chaotic energy fade away"));
			}
        };
		eldritchWard.setDescription("A shroud of chaotic energy protects you, immediately applying Devoured to adjacent foes and granting you Resistance to Chaos damage for the duration.");
		return eldritchWard;
	}
	
	public Effect bladeWard(int duration) {
		Effect bladeWard = new Effect(duration, "Blade Ward", false, null, Effect.bladeWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.white){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become surrounded by metal shards!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 1), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						c.world().setParticleAtLocation(c.ai().factory.particleFactory.droplet(ExtendedAsciiPanel.red, 2), c.x(), c.y(), c.z());
						c.addEffect(bleeding((int)duration/2));
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("return the metal shards to the earth"));
			}
        };
		bladeWard.setDescription("A storm of metal shards shields you, immediately applying Bleeding to adjacent foes and granting you Resistance to Physical damage for the duration.");
		return bladeWard;
	}
	
	public Effect causticWard(int duration) {
		Effect causticWard = new Effect(duration, "Caustic Ward", false, null, Effect.causticWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.lime){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become veiled in protective acid!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 1), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						c.world().setParticleAtLocation(c.ai().factory.particleFactory.crossbones(ExtendedAsciiPanel.lime, 2), c.x(), c.y(), c.z());
						c.addEffect(corroded((int)duration/2));
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("feel the acidic veil fade"));
			}
        };
		causticWard.setDescription("A veil of protective acid shields you, immediately applying Corroded to adjacent foes and granting you Resistance to Acid damage for the duration.");
		return causticWard;
	}
	
	public Effect chillWard(int duration) {
		Effect chillWard = new Effect(duration, "Chill Ward", false, null, Effect.chillWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.water){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become wreathed in freezing air!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 2), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						c.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtendedAsciiPanel.water, 2), c.x(), c.y(), c.z());
						c.addEffect(frozen((int)duration/2));
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("feel the freezing winds fade away"));
			}
        };
		chillWard.setDescription("You are shielded by freezing winds, immediately applying Frozen to adjacent foes and granting you Resistance to Frost damage for the duration.");
		return chillWard;
	}
	
	public Effect magmaWard(int duration) {
		Effect magmaWard = new Effect(duration, "Magma Ward", false, null, Effect.magmaWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.orange){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become shielded by flames!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 2), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						creature.world().setParticleAtLocation(c.ai().factory.particleFactory.fire(ExtendedAsciiPanel.orange, 2), c.x(), c.y(), c.z());
						c.addEffect(ignited((int)duration/2));
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("feel the flaming shield burn out"));
			}
        };
		magmaWard.setDescription("A shield of flames surrounds you, immediately applying Ignited to adjacent foes and granting you Resistance to Fire damage for the durration");
		return magmaWard;
	}
	
	public Effect fireball(int duration) {
		Effect fireball = new Effect(1, null, true, null, ' ', null) {
			public void start(Creature creature){
                for (int ox = -2; ox < 3; ox++){
                    for (int oy = -2; oy < 3; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if((creature.tile(nx, ny, creature.z()).canHaveSubtiles()) && creature.item(nx, ny, creature.z()) == null) {
                        	if(ExtraMaths.d10() > 5) {
                        		creature.world().changeSubTile(nx, ny, creature.z(), Tile.FIRE);
                        		if(creature.creature(nx, ny, creature.z()) != null) {
                        			creature.creature(nx, ny, creature.z()).notify("Roaring flames ignite beneath you!");
                        		}
                        	}
                        }
                        if (creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        Effect gas = ignited(duration);
                        creature.creature(nx, ny, creature.z).addEffect(gas);
                       
                    }
                }
            }
		};
		return fireball;
	}
	
	public Effect overgrow(int duration) {
		Effect overgrow = new Effect(1, null, true, null, ' ', null){
			public void start(Creature creature) {
				
				for (int ox = -2; ox < 3; ox++){
                    for (int oy = -2; oy < 3; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;

                        if(creature.tile(nx, ny, creature.z()) == Tile.FLOOR && creature.item(nx, ny, creature.z()) == null) {
                        	if(ExtraMaths.d10() > 4) {
                        		creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
                        		if(creature.creature(nx, ny, creature.z()) != null) {
                        			creature.creature(nx, ny, creature.z()).notify("Thick foliage springs up around you!");
                        			creature.creature(nx, ny, creature.z()).addEffect(paralysed(duration));
                        		}
                        	}
                        }
                    }
                }
			}
        };
        return overgrow;
	}
	
	public Effect arcWard(int duration) {
		Effect arcWard = new Effect(duration, "Arc Ward", false, null, Effect.arcWard, ExtendedAsciiPanel.getGlyphFromPage(4, 1), ExtendedAsciiPanel.brightCyan){
			public void start(Creature creature){
				creature.doAction(new TerminalText("become shrouded in lightning!"));
				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 1), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> {
						c.world().setParticleAtLocation(c.ai().factory.particleFactory.shock(ExtendedAsciiPanel.paralyzed, 2), c.x(), c.y(), c.z());
						c.doAction(new TerminalText("get a shock!"));
						c.setLastHit(creature);
						c.damage(new Damage(Dice.d4.roll(), false, DamageType.SHOCK, getThis(), true), "Killed by lightning magic");
						c.loseMana(Dice.d4.roll(), false);
					});
            }
			public void end(Creature creature) {
				creature.doAction(new TerminalText("feel the lightning shroud dissipate"));
			}
        };
		arcWard.setDescription("A shroud of crackling lightning protects you, immediately dealing 1d4 Shock damage and draining 1d4 Mana from adjacent foes and granting you Resistance to Shock damage for the duration.");
		return arcWard;
	}
	
	public Effect invisible(int duration) {
		Effect invisible = new Effect(duration, "Invisible", false, null, Effect.invisible, ExtendedAsciiPanel.getGlyphFromPage(163, 2), ExtendedAsciiPanel.invisible) {
			public void start(Creature creature) {
				creature.changeColor(ExtendedAsciiPanel.invisible);
				creature.doAction(new TerminalText("become transparent"));
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.changeColor(ExtendedAsciiPanel.invisible);
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.invisible)) {
					creature.changeColor(creature.defaultColor());
					creature.doAction(new TerminalText("become visible"));
				}
			}
		};
		invisible.setDescription("You have become transparent, making you much harder to target. Attacking or casting a spell will break this effect, however.");
		return invisible;
	}
	
	public Effect paralysed(int duration) {
		Effect paralyzed = new Effect(duration, "Paralysed", true, null, Effect.paralysed, ExtendedAsciiPanel.getGlyphFromPage(165, 2), ExtendedAsciiPanel.paralyzed) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("seize up!"));
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.paralysed)) {
					creature.doAction(new TerminalText("become mobile again"));
				}
			}
		};
		paralyzed.setDescription("Your muscles have seized up, preventing you from moving.");
		return paralyzed;
	}
	
	public Effect blink() {
		Effect blink = new Effect(8, null, false, null, ' ', null) {
			public void start(Creature creature){
	            creature.doAction(new TerminalText("fade out"));
	            creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtendedAsciiPanel.pink, 2), creature.x(), creature.y(), creature.z());
	            int mx = 0;
	            int my = 0;
	            
	            do
	            {
	                mx = (int)(Math.random() * 6) - 2;
	                my = (int)(Math.random() * 6) - 2;
	            }
	            while (!creature.canEnter(creature.x+mx, creature.y+my, creature.z)
	                    && creature.canSee(creature.x+mx, creature.y+my, creature.z));
	            
	            creature.moveBy(mx, my, 0, false);
	            
	            creature.doAction(new TerminalText("fade in"));
	            creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtendedAsciiPanel.pink, 2), creature.x(), creature.y(), creature.z());
	        }
		};
		return blink;
	}
	
	public Effect pitFall() {
		Effect pit = new Effect(8, null, true, null, ' ', null) {
			public void start(Creature creature){
	            creature.doAction(new TerminalText("fall into a pit!"));
	            
	            int mx = 0;
	            int my = 0;
	            
	            do
	            {
	                mx = ExtraMaths.diceRoll(0, creature.world().width());
	                my = ExtraMaths.diceRoll(0, creature.world().height());
	            }
	            while (creature.realTile(mx, my, creature.z+1) != Tile.FLOOR
	            		//&& (creature.creature(mx, my, creature.z+1) != null)
	            		//&& 
	            		//creature.realTile(mx, my, creature.z+1) == Tile.WALL
	            		
	            		
	            		
	            		);
	            if(creature.creature(mx, my, creature.z+1) != null) {
	            	creature.creature(mx, my, creature.z+1).ai().wander();
	            }
	            creature.overrideX(mx);
	            creature.overrideY(my);
	            creature.overrideZ(creature.z+1);
	            
	            
	            creature.doAction(new TerminalText("hit the ground"));
	        }
		};
		return pit;
	}
	
	public Effect frozen(int duration) {
		Effect frozen = new Effect(duration, "Frozen", true, null, Effect.frozen, ExtendedAsciiPanel.getGlyphFromPage(166, 2), ExtendedAsciiPanel.water){
			public void start(Creature creature) {
				creature.doAction(new TerminalText("freeze solid!"));
			}
			
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.frozen)) {
					creature.doAction(new TerminalText("defrost"));
				}
			}
		};
		frozen.setDescription("You are frozen solid, and are completely unable to move.");
		return frozen;
	}
	
	public Effect sundered(int duration) {
		Effect sundered = new Effect(duration, "Sundered", true, null, Effect.sundered, ExtendedAsciiPanel.getGlyphFromPage(160, 2), ExtendedAsciiPanel.white){
			public void start(Creature creature) {
				creature.doAction(new TerminalText("become defenseless!"));
			}
			
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.sundered)) {
					creature.doAction(new TerminalText("recover defenses"));
				}
			}
		};
		sundered.setDescription("Your armor has been sundered, limiting your Armor Class to a maximum of 10 for the duration.");
		return sundered;
	}
	
	public Effect electrified(int duration) {
		Effect electrified = new Effect(duration, "Electrified", true, null, Effect.electrified, ExtendedAsciiPanel.getGlyphFromPage(164, 2), ExtendedAsciiPanel.paralyzed) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("get a shock!"));
				Damage damage = new Damage(Dice.d6.roll(), false, DamageType.SHOCK, getThis(), false);
				creature.damage(damage, "Killed by electrocution");
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.loseMana(Dice.d4.roll(), false);
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.electrified)) {
					creature.doAction(new TerminalText("recover from a shock"));
				}
			}
		};
		electrified.setDescription("An electric shock has disrupted your magical reserves - you immediately take 1d6 Shock damage, then lose 1d4 Mana each turn you are affected.");
		return electrified;
	}
	
	public Effect ignited(int duration) {
		Effect ignited = new Effect(duration, "Ignited", true, null, Effect.ignited, ExtendedAsciiPanel.getGlyphFromPage(167, 2), ExtendedAsciiPanel.orange) {
			public void start(Creature creature) {
				creature.doAction(new TerminalText("burst into flames!"));
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction(new TerminalText("burn up!"));
				Damage damage = new Damage(Dice.d4.roll(), false, DamageType.FIRE, getThis(), false);
				creature.damage(damage, "Burned to death");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.ignited)) {
					creature.doAction(new TerminalText("put the flames out"));
				}

			}
		};
		ignited.setDescription("You have been set on fire, taking 1d4 Fire damage each turn you are affected.");
		return ignited;
		
	}


	public Effect enchantScroll() {
		Effect enchant = new Effect(0, null, false, null, ' ', null) {
			public void start(Creature creature) {
				creature.setIsReadingEnchantment(true);
			}
		};
		return enchant;
	}
	
	public Effect removeCurseScroll() {
		Effect remove = new Effect(0, null, false, null, ' ', null) {
			public void start(Creature creature) {
				creature.setIsReadingRemoveCurse(true);
			}
		};
		return remove;
	}
	
	public Effect upgradeScroll() {
		Effect upgrade = new Effect(0, null, false, null, ' ', null) {
			public void start(Creature creature) {
				creature.setIsReadingUpgrade(true);
			}
		};
		return upgrade;
	}
	
	
	public Effect summonMonstersScroll(Creature player) {
		Effect summon = new Effect(1, null, false, null, ' ', null) {
			public void start(Creature creature){
                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) != null) {
                            continue;
                        }
                        Creature bat = objectFactory.randomLesserMonster(0, player, false);
                        if (!bat.canEnter(nx, ny, creature.z)){
                        	objectFactory.world.remove(bat);
                            continue;
                        }
                        
                        if (creature.creature(nx, ny, creature.z) != null){
                        	objectFactory.world.remove(bat);
                            continue;
                        }
                        
                        if (ExtraMaths.d10() < 4){
                        	objectFactory.world.remove(bat);
                            continue;
                        }
                        
                        bat.x = nx;
                        bat.y = ny;
                        bat.z = creature.z;
                        
                        creature.summon(bat);
                        bat.world().setParticleAtLocation(bat.ai().factory.particleFactory.vortex(ExtendedAsciiPanel.white, 2), bat.x(), bat.y(), bat.z());
                    }
                }
                if(creature.isTileSpell()) {
                	creature.ai().world.remove(creature);
                }
            }
		};
		return summon;
	}

	public Effect identifyScroll() {
		Effect identify = new Effect(0, null, false, null, ' ', null) {
			public void start(Creature creature) {
				creature.setIsReadingIdentify(true);
			}
		};
		return identify;
	}
	
	public Effect magicMappingScroll() {
		Effect map = new Effect(2, null, false, null, ' ', null) {
			public void start(Creature creature) {
				creature.setIsReadingMagicMapping(true);
			}
			public void end(Creature creature) {
				creature.setIsReadingMagicMapping(false);

			}
		};
		return map;
	}
	
	public Effect smokeTrap() {
		Effect pyrotechnics = new Effect(1, null, true, null, ' ', null) {
			public void start(Creature creature){
				for(Point p : new Square(creature.x(), creature.y(), creature.z(), 3)) {
					if(creature.tile(p.x, p.y, p.z).canHaveGas()) {
                    	creature.world().changeGasTile(p.x, p.y, p.z, Tile.SMOKE);
                    }
				}
				int duration_ = 10;

				new InstantiatedAoE(new Square(creature.x(), creature.y(), creature.z(), 3), creature.world())
					.affectedCreaturesExcept(creature)
					.forEach(c -> c.addEffect(blinded(duration_)));
            }
		};
		return pyrotechnics;
	}
	
	//Helper Methods
	public void spreadEffect(Creature centre, Effect effect, int radius) {
		for(int x = centre.x()-radius; x <= centre.x()+radius; x++) {
			for(int y = centre.y()-radius; y <= centre.y()+radius; y++) {
				if(centre.creature(x, y, centre.z()) != null && (x != centre.x() && y != centre.y())) {
					centre.creature(x, y, centre.z()).addEffect((Effect) effect.clone());
				}
			}
		}
	}
	
	public void spreadDamage(Creature centre, Damage damage, String cause, int radius, Particle particle) {
		for(int x = centre.x()-radius; x <= centre.x()+radius; x++) {
			for(int y = centre.y()-radius; y <= centre.y()+radius; y++) {
				if(centre.creature(x, y, centre.z()) != null && (x != centre.x() && y != centre.y())) {
					centre.world().setParticleAtLocation(particle, x, y, centre.z());
					centre.creature(x, y, centre.z()).damage(damage, cause);
				}
			}
		}
	}
	
	//Generation Tables
	public Effect corpseEffect() {
		switch(Dice.d6.roll()) {
		case 1: return paralysed(5);
		case 2: return frozen(5);
		case 3: return corroded(5);
		case 4: return poisoned(5);
		case 5: return bleeding(5);
		case 6: return blinded(5);
		default: return paralysed(5);
		}
	}
}
