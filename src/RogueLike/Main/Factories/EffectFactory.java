package RogueLike.Main.Factories;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.ExtraColors;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Line;
import RogueLike.Main.Point;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.applicationMain;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.AcidDamage;
import RogueLike.Main.Damage.ChaosDamage;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Damage.FireDamage;
import RogueLike.Main.Damage.FrostDamage;
import RogueLike.Main.Damage.MagicDamage;
import RogueLike.Main.Damage.PhysicalDamage;
import RogueLike.Main.Damage.PoisonDamage;
import RogueLike.Main.Damage.ShockDamage;

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
		Effect missile = new Effect(1, null, true, reference){
			public void start(Creature creature) {
				creature.doAction("get hit with a magic missile!");
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
					Damage manaRestore = new Damage(manaAmount, false, false, null, null, false);
					reference.modifyMana(manaRestore);
				}
	        	Damage damage = new MagicDamage(damageAmount, false, getThis(), true);
	        	creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.sparkle(ExtraColors.lilac, 2), creature.x(), creature.y(), creature.z());
				creature.modifyHP(damage, String.format("Killed by %s using Magic Missile", reference.name()));
			}
		};
		missile.setShowInMenu(false);
		return missile;
	}
	
	public Effect forceBlast(Creature reference) {
		Effect repel = new Effect(1, null, true, reference){
			public void start(Creature creature) {
				int px = reference.x();
				int py = reference.y();
				int cx = creature.x();
				int cy = creature.y();
				if(creature == reference) {
					double tempAmount = (ExtraMaths.diceRoll(1, 12));
					int amount = (int) Math.round(tempAmount);
					Damage damage = new PhysicalDamage(amount, false, getThis(), true);
					creature.doAction("get crushed!");
					creature.modifyHP(damage, "Killed by kinetic energy");
				}else {
					int push = 0;
					for(int i = 0; i < 3; i++) { //TODO apply evocation
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
						creature.doAction("get thrown back!");
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
							Damage manaRestore = new Damage(manaAmount, false, false, null, null, false);
							reference.modifyMana(manaRestore);
						}
						if(attackRoll >= creature.armorClass()) {
							Damage damage = new MagicDamage(amount, false, getThis(), false);
							creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.blast(ExtraColors.lilac, 2), creature.x(), creature.y(), creature.z());
							creature.modifyHP(damage, "Killed by kinetic energy");
						}
					}
				}
			}
        };
        return repel;
	}
	
	public Effect archmagesAegis(Creature reference) {
		Effect archmagesAegis = new Effect(1, "Archmage's Aegis", false, reference) {
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
				for(int x = -2; x < 3; x++) {
					for(int y = -2; y < 3; y++) {
						if(reference.creature(x, y, reference.z()) != null && reference.creature(x, y, reference.z()) != reference) {
							reference.creature(x, y, reference.z()).addEffect((Effect) confused_.clone());
							creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtraColors.lilac, 2), x, y, creature.z());
						}
					}
				}
			}
        };
        archmagesAegis.setShowInMenu(false);
		return archmagesAegis;
	}
	
	public Effect findTraps() {
		Effect findTraps = new Effect(0, null, false, null) {
			public void start(Creature creature) {
				int count = 0;
				for(int x = -creature.visionRadius(); x < creature.visionRadius()+1; x++) {
					for(int y = -creature.visionRadius(); y < creature.visionRadius()+1; y++) {
						if(x < 0 || y < 0 || x > creature.world().width() || y > creature.world().height()) {
							continue;
						}
						if(creature.world().item(x, y, creature.z()) != null && creature.world().item(x, y, creature.z()).isTrap()) {
							count++;
						}
					}
				}
				if(count > 0) {
					String trap = "trap";
					if(count > 1) {
						trap = "traps";
					}
					creature.notify(String.format("You sense %d %s nearby.", count, trap));
				}else {
					creature.notify("You do not sense any traps nearby");
				}
			}
		};
		return findTraps;
	}
	
	//Pyromancy Spells
	public Effect firebolt(Creature reference) {
		Effect firebolt = new Effect(1, "Firebolt", true, null) {
        	public void start(Creature creature) {
				Damage damage = new FireDamage(Dice.d8.roll()+reference.intelligenceModifier(), false, getThis(), true);
				if(reference.intelligenceRoll() >= creature.armorClass()) {
					creature.doAction("get hit with a bolt of fire!");
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.fire(ExtraColors.orange, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Firebolt", reference.name()));
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
		Effect flashfire = new Effect(1, "Flashfire", true, null) {
        	public void start(Creature creature) {
				Damage damage = new FireDamage(Dice.d4.roll()+reference.intelligenceModifier(), false, getThis(), true);
				if(creature.affectedBy(Effect.ignited)) {
					int amount = reference.intelligenceModifier();
					Effect temp = null;
					for(int i = 0; i < creature.effects().size(); i++) {
						if(creature.effects().get(i).type() == Effect.ignited) {
							temp = creature.effects().get(i);
						}
					}
					for(int i = 0; i < temp.duration(); i++) {
						amount += Dice.d4.roll();
					}
					damage = new FireDamage(amount, false, getThis(), false);
				}
				if(creature.dexterityRoll() >= reference.intelligenceSaveDC()) {
					creature.doAction("get consumed by searing flames!");
					creature.setLastHit(reference);
					if(creature.affectedBy(Effect.ignited)) {
						creature.cureEffectOfType(Effect.ignited);
					}
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.fire(ExtraColors.orange, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Flashfire", reference.name()));
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
		Effect brazierBarrier = new Effect(1, "Brazier Barrier", false, reference) {
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
		Effect pyrotechnics = new Effect(1, null, true, reference) {
			public void start(Creature creature){
                for (int ox = -2; ox < 3; ox++){
                    for (int oy = -2; oy < 3; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if(creature.tile(nx, ny, creature.z()).canHaveGas()) {
                        	creature.world().changeGasTile(nx, ny, creature.z(), Tile.SMOKE);
                        }
                        if (creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        int duration_ = 5;
                        if(reference.pyromancyLevel() >= 1) {
                        	duration_ += reference.proficiencyBonus();
                        }
                        if(reference.pyromancyLevel() >= 2) {
                        	duration_ += reference.proficiencyBonus();
                        }
                        Effect smoke = blinded(duration_);
                        creature.creature(nx, ny, creature.z).addEffect(smoke);
                    }
                }
            }
		};
		return pyrotechnics;
	}
	
	//Cryomancy Spells
	public Effect flashFreeze(Creature player) {
		Effect freeze = new Effect(1, null, true, player) {
			public void start(Creature creature) {
				
				int savingThrow = creature.frostSave();
				int saveTarget = player.intelligenceSaveDC();
				int duration = 2+Dice.d4.roll(); //TODO apply cryomancy
				if(savingThrow < saveTarget) {
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtraColors.water, 2), creature.x(), creature.y(), creature.z());
					creature.addEffect(frozen(duration));
				}else {
					creature.doAction("resist the spell!");
				}
			}
		};
		return freeze;
	}
	
	public Effect iceKnife(Creature reference) {
		Effect iceKnife = new Effect(1, "Ice Knife", true, null) {
        	public void start(Creature creature) {
				Damage damage = new FrostDamage(Dice.d6.roll()+reference.intelligenceModifier(), false, getThis(), true);
				if(reference.intelligenceRoll() >= creature.armorClass()) {
					creature.doAction("get hit with a blade of ice!");
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtraColors.water, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Ice Knife", reference.name()));
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
				damage = new FrostDamage(Dice.d4.roll()+reference.intelligenceModifier(), false, getThis(), true);
				if(creature.dexterityRoll() < reference.intelligenceSaveDC()) {
					creature.notify("You feel a biting frost creep over you!");
					reference.notify(String.format("The %s is frostbitten by your spell!", creature.name()));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtraColors.water, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Ice Knife", reference.name()));
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
		Effect glaciate = new Effect(1, "Glaciate", false, reference) {
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
		Effect wall = new Effect(1, null, false, player){
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
	public Effect chainLightning(Creature player) { //TODO CLEANUP
		Effect lightning = new Effect(7, null, true, player){
			public void start(Creature creature){
				int playerDC = player.intelligenceSaveDC();
				int creatureSave = creature.shockSave();
				int damageCeiling = 8+player.intelligenceModifier();
				
				int tempAmount = (ExtraMaths.diceRoll(1, damageCeiling));
				int amountHalf = (int) Math.round(tempAmount/2);
				int amount = tempAmount;
				boolean saved = false;
				
				if(creatureSave >= playerDC) {
					amount = amountHalf;
					saved = true;
				}
				if(amount < 1) {
					amount = 1;
				}
				creature.doAction("get a shock!");
				creature.setLastHit(player);
				creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtraColors.paralyzed, 2), creature.x(), creature.y(), creature.z());
				Damage damage = new ShockDamage(amount, false, getThis(), true);
				creature.modifyHP(damage, "Killed by lightning magic");
				
				if(saved == false) {
	                for (int ox = -2; ox < 3; ox++){
	                    for (int oy = -2; oy < 3; oy++){
	                        int nx = creature.x + ox;
	                        int ny = creature.y + oy;
	                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null || creature.creature(nx, ny, creature.z) == player) {
	                            continue;
	                        }
	                        
	                        
	                        Creature target = creature.creature(nx, ny, creature.z);
	                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtraColors.paralyzed, 2), target.x(), target.y(), target.z());
	                        int amountChain = (ExtraMaths.diceRoll(1, damageCeiling));
	        				
	                        target.doAction("get a shock!");
	                        target.setLastHit(player);
	                        Damage damageChain = new ShockDamage(amountChain, false, getThis(), true);
	        				target.modifyHP(damageChain, "Killed by lightning magic");
	
	                    }
	                }
				}
            }
        };
        return lightning;
	}
	
	public Effect lightningLance(Creature reference) {
		Effect lightningLance = new Effect(1, "Lightning Lance", false, reference) {
        	public void start(Creature creature) {
        		Line l = new Line(reference.x(), reference.y(), creature.x(), creature.y());
        		ArrayList<Creature> targets = new ArrayList<Creature>();
        		for(Point p : l) {
        			//applicationMain.terminal.write((char)15, p.x, p.y, ExtraColors.paralyzed);
        			if(creature.creature(p.x, p.y, creature.z()) != null && (p.x != reference.x() && p.y != reference.y())) {
        				targets.add(creature.creature(p.x, p.y, creature.z()));
        				creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtraColors.paralyzed, 2), p.x, p.y, creature.z());
        			}
        			if(creature.creature(p.x, p.y, creature.z()) == null) {
        				creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtraColors.paralyzed, 2), p.x, p.y, creature.z());
        			}
        			
        		}
        		for(Creature c : targets) {
        			int amount = Dice.d8.roll()+reference.intelligenceModifier();
    				Damage damage = new ShockDamage(amount, false, creature.ai().factory.effectFactory, true);
    				c.modifyHP(damage, String.format("Killed by %s using Lightning Lance", reference.name()));
        		}
			}
        };
        lightningLance.setShowInMenu(false);
		return lightningLance;
	}
	
	public Effect staticSurge(Creature reference) {
		Effect staticSurge = new Effect(1, "Static Surge", false, reference) {
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
		Effect hasteSpell = new Effect(1, "Haste", false, reference) {
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
		Effect acidBlast = new Effect(1, "Acid Blast", true, null) {
        	public void start(Creature creature) {
				Damage damage = new AcidDamage(Dice.d4.roll()+reference.intelligenceModifier(), false, getThis(), true);
				if(creature.affectedBy(Effect.corroded)) {
					damage = new AcidDamage(Dice.d10.roll()+reference.intelligenceModifier(), false, getThis(), false);
				}
				if(reference.intelligenceRoll() >= creature.armorClass()) {
					creature.doAction("get hit with a blast of acid!");
					creature.setLastHit(reference);
					if(creature.affectedBy(Effect.corroded)) {
						creature.cureEffectOfType(Effect.corroded);
					}
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtraColors.lime, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Acid Blast", reference.name()));
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
		Effect toxicTransfusion = new Effect(1, "Toxic Transfusion", false, reference) {
        	public void start(Creature creature) {
        		int duration_ = 10;
        		if(reference.alchemancyLevel() >= 1) {
        			duration_ += reference.proficiencyBonus();
        		}
        		if(reference.alchemancyLevel() >= 2) {
        			duration_ += reference.proficiencyBonus();
        		}
        		Effect venomousWard_ = reference.ai().factory.effectFactory.venomousWard(duration_);
        		Effect poisoned_ = reference.ai().factory.effectFactory.poisoned(duration_/2);
        		Effect corroded_ = reference.ai().factory.effectFactory.corroded(duration_/2);
				reference.addEffect((Effect) venomousWard_.clone());
				Damage damage = new PoisonDamage(Dice.d6.roll()+reference.intelligenceModifier(), false, getThis(), false);

				if(reference.intelligenceRoll() >= creature.armorClass()) {
					creature.doAction("get hit with a splash of poison!");
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtraColors.magenta, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Toxic Transfusion", reference.name()));
				}else {
					creature.notify(String.format("The %s's spell misses you.", reference.name()));
					reference.notify(String.format("Your spell misses the %s.", creature.name()));
				}
				if(creature.strengthRoll() < reference.intelligenceSaveDC()) {
					creature.notify("You feel a searing toxin flood your veins!");
					reference.notify(String.format("The %s is afflicted by your spell!", creature.name()));
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtraColors.magenta, 2), creature.x(), creature.y(), creature.z());
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
		Effect refluxBarrier = new Effect(1, "Reflux Barrier", false, reference) {
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
		Effect lifetap = new Effect(1, "Lifetap", false, reference) {
        	public void start(Creature creature) {
        		int amount_ = (int) creature.hp() / 2;
        		Damage damage = new Damage(amount_, false, false, null, null, false);
        		creature.modifyHP(damage, null);
        		if(reference.alchemancyLevel() >= 1) {
        			amount_ += reference.proficiencyBonus();
        		}
        		if(reference.alchemancyLevel() >= 2) {
        			amount_ += reference.proficiencyBonus();
        		}
        		Damage manaRestore = new Damage(amount_, true, false, null, null, false);
        		creature.modifyMana(manaRestore);
			}
        };
        lifetap.setShowInMenu(false);
		return lifetap;
	}
	
	//Ferromancy Spells
	public Effect armorStorm(Creature reference) {
		Effect armorStorm = new Effect(1, "Armor Storm", false, reference) {
        	public void start(Creature creature) {
        		int stacks_ = reference.armorClass()-10;
        		if(stacks_ <= 0) {
        			stacks_ = 1;
        		}
        		int amount = 0;
        		for(int i = 0; i < stacks_+1; i++) {
        			amount += Dice.d4.roll();
        		}
        		if(reference.ferromancyLevel() >= 1) {
        			amount += reference.proficiencyBonus();
        		}
        		if(reference.ferromancyLevel() >= 2) {
        			amount += reference.proficiencyBonus();
        		}
        		Damage damage = new PhysicalDamage(amount, false, reference.ai().factory.effectFactory, true);
        		creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.blast(ExtraColors.white, 2), creature.x(), creature.y(), creature.z());
        		creature.modifyHP(damage, String.format("Killed by %s using Armor Storm", reference.name()));
        		creature.setLastHit(reference);
        		Effect sundered_ = reference.ai().factory.effectFactory.sundered(stacks_);
				reference.addEffect((Effect) sundered_.clone());
			}
        };
        armorStorm.setShowInMenu(false);
		return armorStorm;
	}
	
	public Effect weaponBolt(Creature reference) {
		Effect weaponBolt = new Effect(1, "Weapon Bolt", false, reference) {
        	public void start(Creature creature) {
        		int amount = 0;
        		if(reference.weapon() != null) {
        			if(reference.weapon().isVersatile()) {
        				amount = reference.weapon().versatileDamageDice().roll();
        			}else if(reference.weapon().isRangedWeapon()){
        				amount = reference.weapon().rangedDamageDice().roll();
        			}else {
        				amount = reference.weapon().damageDice().roll();
        			}
        			if(reference.weapon().upgradeLevel() > 0) {
        				amount += reference.weapon().upgradeLevel();
        			}
        		}
        		int attackRoll = reference.intelligenceSaveDC();
        		
        		if(reference.ferromancyLevel() >= 1) {
        			attackRoll += reference.proficiencyBonus();
        		}
        		if(reference.ferromancyLevel() >= 2) {
        			amount += reference.proficiencyBonus();
        		}
        		Damage damage = new MagicDamage(amount, false, reference.ai().factory.effectFactory, true);
        		if(attackRoll >= creature.armorClass()) {
					creature.doAction("get hit with a spectral weapon!");
					creature.setLastHit(reference);
					creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.blast(ExtraColors.white, 2), creature.x(), creature.y(), creature.z());
					creature.modifyHP(damage, String.format("Killed by %s using Weapon Bolt", reference.name()));
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
		Effect bladsWard = new Effect(1, "Blad's Ward", false, reference) {
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
		Effect infuseUpgrade = new Effect(1, "Infuse Upgrade", false, reference) {
        	public void start(Creature creature) {
        		int chance = reference.mana();
        		Damage chanceMana = new Damage(chance, false, false, null, null, false);
        		creature.modifyMana(chanceMana);
        		
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
	
	
	
	
	
	
	
	public Effect maxHealth() {
		Effect maxHealth = new Effect(1, null, false, null) {
			public void start(Creature creature) {
				if(creature.hp() == creature.maxHP()) {
					return;
				}
				Damage damage = new Damage((creature.maxHP() - creature.hp()), true, false, null, getThis(), false);
				creature.modifyHP(damage, "");
				//creature.doAction("look healthier");
			}
		};
		return maxHealth;
	}
	
	public Effect maxMana() {
		Effect maxMana = new Effect(1, null, false, null) {
			public void start(Creature creature) {
				if(creature.mana() == creature.maxMana()) {
					return;
				}
				Damage damage = new Damage((creature.maxMana() - creature.mana()), true, false, null, getThis(), false);
				creature.modifyMana(damage);
				creature.doAction("look energised");
			}
		};
		return maxMana;
	}
	
	public Effect poisoned(int duration) {
		Effect poisoned = new Effect(duration, "Poisoned", true, null, Effect.poisoned) { //10
			public void start(Creature creature) {
				creature.doAction("look unwell");
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new PoisonDamage(Dice.d4.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Killed by poison");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.poisoned)) {
					creature.doAction("recover from poison");
				}

			}
		};
		return poisoned;
	}
	
	public Effect bleeding(int duration) {
		Effect bleeding = new Effect(duration, "Bleeding", true, null, Effect.bleeding) { //10
			public void start(Creature creature) {
				creature.doAction("begin to bleed");
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new PhysicalDamage(Dice.d4.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Killed by bleeding");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.bleeding)) {
					creature.doAction("stem the bleeding");
				}

			}
		};
		return bleeding;
	}
	
	public Effect giantStrength(int duration) {
		Effect giantStrength = new Effect(duration, "Giant Strength", false, null, Effect.giantStrength) {
			public void start(Creature creature) {
				creature.doAction("swell with power");
			}
			public void end(Creature creature) {
				creature.doAction("return to normal strength");
			}
		};
		return giantStrength;
	}
	
	public Effect beastForm(int duration) {
		Effect beastForm = new Effect(duration, "Beast Form", false, null, Effect.beastForm) {
			public void start(Creature creature) {
				creature.doAction("appear more bestial");
			}
			public void end(Creature creature) {
				creature.doAction("return to a normal appearance");
			}
		};
		return beastForm;
	}
	
	public Effect corroded(int duration) {
		Effect corroded = new Effect(duration, "Corroded", true, null, Effect.corroded) {
        	public void start(Creature creature) {
        		//creature.modifyDefenseValue(-6);
				creature.doAction("get coated in biting acid");
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new AcidDamage(Dice.d4.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Killed by acid");
				creature.notify("The acid burns you!");	
			}
			public void end(Creature creature) {
				//creature.modifyDefenseValue(6);
				if(!creature.affectedBy(Effect.corroded) == false) {
    				creature.doAction("recover from the acid burns");
				}
				
				
			}
        };
		return corroded;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Effect causticVapor(int duration) {
		Effect causticVapor = new Effect(1, null, true, null) {
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
		Effect mindVision = new Effect(duration, "Mind Vision", false, null, Effect.mindVision){
            public void start(Creature creature){
                creature.doAction("look far off into the distance");
            }
            public void end(Creature creature){
            }
        };
        return mindVision;
	}
	
	public Effect restoration() {
		Effect restoration = new Effect(1, null, false, null) {
			public void start(Creature creature) {
				creature.doAction("look refreshed");
				creature.cureNegativeEffects();
			}
		};
		return restoration;
	}
	
	public Effect blinded(int duration) {
		Effect blinded = new Effect(duration, "Blinded", true, null, Effect.blinded) {
			public void start(Creature creature) {
				creature.doAction("become blinded!");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.blinded) == false) {
					creature.doAction("return to seeing normally");
				}

			}
		};
		return blinded;
	}
	
	public Effect illuminated(int duration) {
		Effect illuminated = new Effect(duration, "Illuminated", false, null, Effect.illuminated) {
			public void start(Creature creature) {
				creature.doAction("glow with a bright light!");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.illuminated) == false) {
					creature.doAction("stop glowing");
				}

			}
		};
		return illuminated;
	}
	
	public Effect haste(int duration) {
		Effect haste = new Effect(duration, "Haste", false, null, Effect.haste) {
			public void start(Creature creature) {
				creature.doAction("begin to vibrate with energy!");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.haste) == false) {
					creature.doAction("slow down to normal speed");
				}

			}
		};
		return haste; //TODO finish + implement
	}
	
	public Effect electrocharged(int duration) {
		Effect electrocharged = new Effect(duration, "Electrocharged", false, null, Effect.electrocharged) {
			public void start(Creature creature) {
				creature.doAction("become infused with electricity!");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.electrocharged) == false) {
					creature.doAction("feel the electrical infusion fade away");
				}

			}
		};
		return electrocharged;
	}
	
	public Effect levitating(int duration) {
		Effect levitating = new Effect(duration, "Levitating", false, null, Effect.levitating){
            public void start(Creature creature){
                creature.doAction("float into the air!");
            }
            public void end(Creature creature){
            	creature.doAction("float back down to the floor");
            }
        };
		return levitating;
	}
	
	public Effect devoured(int duration) {
		Effect devoured = new Effect(duration, "Devoured", true, null, Effect.devoured) {
			public void start(Creature creature) {
				creature.doAction("writhe in agony");
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new ChaosDamage(Dice.d4.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Killed by a devouring curse");
				Damage damage2 = new Damage(Dice.d4.roll(), false, false, null, getThis(), false);
				creature.modifyMana(damage2);
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.devoured)) {
					creature.doAction("escape a devouring curse");
				}

			}
		};
		return devoured;
	}
	
	public Effect confused(int duration) {
		Effect confused = new Effect(duration, "Confused", true, null, Effect.confused) {
			public void start(Creature creature) {
				creature.doAction("become confused!");
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction("wander around in confusion!");
				//creature.ai().wander();
				
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.confused)) {
					creature.doAction("stop being confused");
				}

			}
		};
		return confused;
	}
	
	public Effect bounce() {
		Effect bounce = new Effect(7, null, false, null){
			public void start(Creature creature){
				creature.doAction("let out a wave of force!");

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
                				target.doAction("get thrown back!");
                			}
                		}
                	}
                }
            };
        };
		return bounce;
	}
	
	public Effect arcaneWard(int duration) {
		Effect arcaneWard = new Effect(duration, "Arcane Ward", false, null, Effect.arcaneWard){
			public void start(Creature creature){
				creature.doAction("raise a magical shield!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtraColors.lilac, 2), target.x(), target.y(), target.z());
                        Effect magicBurst = blink();
                		target.addEffect(magicBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("let the shielding magic dissipate");
			}
        };
		return arcaneWard;
	}
	
	public Effect venomousWard(int duration) {
		Effect venomousWard = new Effect(duration, "Venomous Ward", false, null, Effect.venomousWard){
			public void start(Creature creature){
				creature.doAction("become coated in protective poison!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtraColors.magenta, 2), target.x(), target.y(), target.z());
                        Effect venomBurst = poisoned((int)duration/2);
                		target.addEffect(venomBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("feel the poisonous barrier fade");
			}
        };
		return venomousWard;
	}
	
	public Effect eldritchWard(int duration) {
		Effect eldritchWard = new Effect(duration, "Eldritch Ward", false, null, Effect.eldritchWard){
			public void start(Creature creature){
				creature.doAction("become shrouded by chaotic energy!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtraColors.green, 2), target.x(), target.y(), target.z());
                        Effect chaosBurst = devoured((int)duration/2);
                		target.addEffect(chaosBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("feel the chaotic energy fade away");
			}
        };
		return eldritchWard;
	}
	
	public Effect bladeWard(int duration) {
		Effect bladeWard = new Effect(duration, "Blade Ward", false, null, Effect.bladeWard){
			public void start(Creature creature){
				creature.doAction("become surrounded by metal shards!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.droplet(ExtraColors.red, 2), target.x(), target.y(), target.z());
                        Effect bladeBurst = bleeding((int)duration/2);
                		target.addEffect(bladeBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("return the metal shards to the earth");
			}
        };
		return bladeWard;
	}
	
	public Effect causticWard(int duration) {
		Effect causticWard = new Effect(duration, "Venomous Ward", false, null, Effect.causticWard){
			public void start(Creature creature){
				creature.doAction("become veiled in protective acid!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.crossbones(ExtraColors.lime, 2), target.x(), target.y(), target.z());
                        Effect acidBurst = corroded((int)duration/2);
                		target.addEffect(acidBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("feel the acidic veil fade");
			}
        };
		return causticWard;
	}
	
	public Effect chillWard(int duration) {
		Effect chillWard = new Effect(duration, "Chill Ward", false, null, Effect.chillWard){
			public void start(Creature creature){
				creature.doAction("become wreathed in freezing air!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.frost(ExtraColors.water, 2), target.x(), target.y(), target.z());
                        Effect chillBurst = frozen((int)duration/2);
                		target.addEffect(chillBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("feel the freezing winds fade away");
			}
        };
		return chillWard;
	}
	
	public Effect magmaWard(int duration) {
		Effect magmaWard = new Effect(duration, "Magma Ward", false, null, Effect.magmaWard){
			public void start(Creature creature){
				creature.doAction("become shielded by flames!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.fire(ExtraColors.orange, 2), target.x(), target.y(), target.z());
                        Effect magmaBurst = ignited((int)duration/2);
                        
                		target.addEffect(magmaBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("feel the flaming shield burn out");
			}
        };
		return magmaWard;
	}
	
	public Effect fireball(int duration) {
		Effect fireball = new Effect(1, null, true, null) {
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
		Effect overgrow = new Effect(1, null, true, null){
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
		Effect arcWard = new Effect(duration, "Arc Ward", false, null, Effect.arcWard){
			public void start(Creature creature){
				creature.doAction("become shrouded in lightning!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.shock(ExtraColors.paralyzed, 2), target.x(), target.y(), target.z());
                        
                        //int amountChain = Math.max(0, 5-target.shockDefenseValue());
                        
                        double tempAmountChain = (ExtraMaths.d4());
        				int amountChain = (int) Math.round(tempAmountChain);
        				
                        target.doAction("get a shock!");
                        target.setLastHit(creature);
                        Damage damage = new ShockDamage(amountChain, false, getThis(), true);
        				target.modifyHP(damage, "Killed by lightning magic");
        				target.modifyMana(damage);

                    }
                }
            }
			public void end(Creature creature) {
				creature.doAction("feel the lightning shroud dissipate");
			}
        };
		return arcWard;
	}
	
	public Effect invisible(int duration) {
		Effect invisible = new Effect(duration, "Invisible", false, null, Effect.invisible) {
			public void start(Creature creature) {
				creature.changeColor(ExtraColors.invisible);
				creature.doAction("become transparent");
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.changeColor(ExtraColors.invisible);
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.invisible)) {
					creature.changeColor(creature.defaultColor());
					creature.doAction("become visible");
				}
				
			}
		};
		return invisible;
	}
	
	public Effect paralysed(int duration) {
		Effect paralyzed = new Effect(duration, "Paralysed", true, null, Effect.paralysed) {
			public void start(Creature creature) {
				creature.doAction("seize up!");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.paralysed)) {
					creature.doAction("become mobile again");
				}
			}
		};
		return paralyzed;
	}
	
	public Effect blink() {
		Effect blink = new Effect(8, null, false, null) {
			public void start(Creature creature){
	            creature.doAction("fade out");
	            creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtraColors.pink, 2), creature.x(), creature.y(), creature.z());
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
	            
	            creature.doAction("fade in");
	            creature.world().setParticleAtLocation(creature.ai().factory.particleFactory.vortex(ExtraColors.pink, 2), creature.x(), creature.y(), creature.z());
	        }
		};
		return blink;
	}
	
	public Effect pitFall() {
		Effect pit = new Effect(8, null, true, null) {
			public void start(Creature creature){
	            creature.doAction("fall into a pit!");
	            
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
	            
	            
	            creature.doAction("hit the ground");
	        }
		};
		return pit;
	}
	
	public Effect frozen(int duration) {
		Effect frozen = new Effect(duration, "Frozen", true, null, Effect.frozen){
			public void start(Creature creature) {
				creature.doAction("freeze solid!");
				Damage damage = new FrostDamage(Dice.d8.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Killed by icy magic");
			}
			
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.frozen)) {
					creature.doAction("defrost");
				}
			}};
		return frozen;
	}
	
	public Effect sundered(int duration) {
		Effect sundered = new Effect(duration, "Sundered", true, null, Effect.sundered){
			public void start(Creature creature) {
				creature.doAction("become defenseless!");
			}
			
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.frozen)) {
					creature.doAction("recover defenses");
				}
			}};
		return sundered;
	}
	
	public Effect electrified(int duration) {
		Effect electrified = new Effect(duration, "Electrified", true, null, Effect.electrified) {
			public void start(Creature creature) {
				creature.doAction("get a shock!");
				Damage damage = new ShockDamage(Dice.d6.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Killed by shocking magic");
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(Dice.d4.roll(), false, false, null, getThis(), false);
				creature.modifyMana(damage);
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.electrified)) {
					creature.doAction("recover from a shock");
				}
			}};
		return electrified;
	}
	
	public Effect ignited(int duration) {
		Effect ignited = new Effect(duration, "Ignited", true, null, Effect.ignited) {
			public void start(Creature creature) {
				creature.doAction("burst into flames!");
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction("burn up!");
				Damage damage = new FireDamage(Dice.d4.roll(), false, getThis(), false);
				creature.modifyHP(damage, "Burned to death");
			}
			public void end(Creature creature) {
				if(!creature.affectedBy(Effect.ignited)) {
					creature.doAction("put the flames out");
				}

			}};
		return ignited;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	

	public Effect enchantScroll() {
		Effect enchant = new Effect(0, null, false, null) {
			public void start(Creature creature) {
				creature.setIsReadingEnchantment(true);
			}
		};
		return enchant;
	}
	
	public Effect removeCurseScroll() {
		Effect remove = new Effect(0, null, false, null) {
			public void start(Creature creature) {
				creature.setIsReadingRemoveCurse(true);
			}
		};
		return remove;
	}
	
	public Effect upgradeScroll() {
		Effect upgrade = new Effect(0, null, false, null) {
			public void start(Creature creature) {
				creature.setIsReadingUpgrade(true);
			}
		};
		return upgrade;
	}
	
	
	
	public Effect summonMonstersScroll(Creature player) {
		Effect summon = new Effect(1, null, false, null) {
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
                        bat.world().setParticleAtLocation(bat.ai().factory.particleFactory.vortex(ExtraColors.white, 2), bat.x(), bat.y(), bat.z());
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
		Effect identify = new Effect(0, null, false, null) {
			public void start(Creature creature) {
				creature.setIsReadingIdentify(true);
			}
		};
		return identify;
	}
	
	public Effect magicMappingScroll() {
		Effect map = new Effect(2, null, false, null) {
			public void start(Creature creature) {
				creature.setIsReadingMagicMapping(true);
			}
			public void end(Creature creature) {
				creature.setIsReadingMagicMapping(false);

			}
		};
		return map;
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
