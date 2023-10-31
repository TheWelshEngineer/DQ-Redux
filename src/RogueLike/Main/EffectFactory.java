package RogueLike.Main;

import RogueLike.Main.Damage.Damage;

public class EffectFactory {
	
	public ObjectFactory objectFactory;
	
	public EffectFactory getThis() {
		return this;
	}
	
	public EffectFactory(ObjectFactory factory) {
		this.objectFactory = factory;
	}
	
	public Effect maxHealth() {
		Effect maxHealth = new Effect(1, null, false, null) {
			public void start(Creature creature) {
				if(creature.hp() == creature.maxHP()) {
					return;
				}
				Damage damage = new Damage((creature.maxHP() - creature.hp()), true, false, null, getThis());
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
				Damage damage = new Damage((creature.maxMana() - creature.mana()), true, false, null, getThis());
				creature.modifyMana(damage);
				creature.doAction("look energised");
			}
		};
		return maxMana;
	}
	
	public Effect poisoned() {
		Effect poisoned = new Effect(10, "Poisoned", true, null) { //10
			public void start(Creature creature) {
				creature.doAction("look unwell");
				creature.setIsPoisoned(true);
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(Dice.d4.roll(), false, false, Damage.poison, getThis());
				creature.modifyHP(damage, "Killed by poison");
			}
			public void end(Creature creature) {
				creature.setIsPoisoned(false);
				if(creature.isPoisoned() == false) {
					creature.doAction("recover from poison");
				}

			}
		};
		poisoned.setIsPoison(true);
		poisoned.setIsNegative(true);
		return poisoned;
	}
	
	public Effect giantStrength() {
		Effect giantStrength = new Effect(15, "Giant Strength", false, null) {
			public void start(Creature creature) {
				creature.setHasGiantStrength(true);
				creature.doAction("swell with power");
			}
			public void end(Creature creature) {
				creature.setHasGiantStrength(false);
				creature.doAction("return to normal strength");
			}
		};
		giantStrength.setIsGiantStrength(true);
		return giantStrength;
	}
	
	public Effect beastForm() {
		Effect beastForm = new Effect(15, "Beast Form", false, null) {
			public void start(Creature creature) {
				creature.setHasBeastForm(true);
				creature.doAction("appear more bestial");
			}
			public void end(Creature creature) {
				creature.setHasBeastForm(false);
				creature.doAction("return to a normal appearance");
			}
		};
		beastForm.setIsBeastForm(true);
		return beastForm;
	}
	
	public Effect corroded() {
		Effect corroded = new Effect(5, "Corroded", true, null) {
        	public void start(Creature creature) {
        		creature.setIsCorroded(true);
        		//creature.modifyDefenseValue(-6);
				creature.doAction("get coated in caustic vapour");
				//creature.learnName(item);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(ExtraMaths.diceRoll(1, 3), false, false, Damage.acid, getThis());
				creature.modifyHP(damage, "Killed by caustic gas");
				creature.notify("The vapour burns you!");	
			}
			public void end(Creature creature) {
				creature.setIsCorroded(false);
				//creature.modifyDefenseValue(6);
				if(creature.isCorroded() == false) {
    				creature.doAction("recover from the corrosion");
				}
				
				
			}
        };
        corroded.setIsNegative(true);
		corroded.setIsCorrosion(true);
		return corroded;
	}
	
	public Effect firebolt(Creature reference) {
		Effect firebolt = new Effect(1, "Firebolt", true, null) {
        	public void start(Creature creature) {
        		creature.notify("The firebolt splashes over your body!");
        		Damage damage = new Damage(Dice.d8.roll()+reference.intelligenceModifier(), false, false, Damage.fire, getThis());
				creature.modifyHP(damage, "Killed by fire magic");
			}
        };
		return firebolt;
	}
	
	public Effect causticVapor() {
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
                        Effect gas = corroded();
                        creature.creature(nx, ny, creature.z).addEffect(gas);
                    }
                }
            }
		};
		return causticVapor;
	}
	
	public Effect mindVision() {
		Effect mindVision = new Effect(5, "Mind Vision", false, null){
            public void start(Creature creature){
                creature.doAction("look far off into the distance");
                creature.setHasMindVision(true);
            }
            public void end(Creature creature){
                creature.setHasMindVision(false);
            }
        };
        mindVision.setIsMindVision(true);
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
	
	public Effect blinded() {
		Effect blinded = new Effect(5, "Blinded", true, null) {
			public void start(Creature creature) {
				creature.doAction("become blinded!");
				creature.setIsBlinded(true);
			}
			public void end(Creature creature) {
				creature.setIsBlinded(false);
				if(creature.isBlinded() == false) {
					creature.doAction("return to seeing normally");
				}

			}
		};
		blinded.setIsNegative(true);
		blinded.setIsBlinded(true);
		return blinded;
	}
	
	public Effect levitating() {
		Effect levitating = new Effect(5, "Levitating", false, null){
            public void start(Creature creature){
                creature.doAction("float into the air!");
                creature.setIsLevitating(true);
            }
            public void end(Creature creature){
            	creature.doAction("float back down to the floor");
                creature.setIsLevitating(false);
            }
        };
		levitating.setIsLevitating(true);
		return levitating;
	}
	
	public Effect devoured() {
		Effect devoured = new Effect(5, "Devoured", true, null) {
			public void start(Creature creature) {
				creature.doAction("writhe in agony");
				creature.setIsDevoured(true);
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(ExtraMaths.diceRoll(1, 3), false, false, Damage.chaos, getThis());
				creature.modifyHP(damage, "Killed by a devouring curse");
				Damage damage2 = new Damage(ExtraMaths.diceRoll(1, 3), false, false, null, getThis());
				creature.modifyMana(damage2);
			}
			public void end(Creature creature) {
				creature.setIsDevoured(false);
				if(creature.isDevoured() == false) {
					creature.doAction("escape a devouring curse");
				}

			}
		};
		devoured.setIsNegative(true);
		devoured.setIsDevoured(true);
		return devoured;
	}
	
	public Effect updateTest(Creature reference) {
		Effect test = new Effect(25, "Test", false, null) {
			public void start(Creature creature) {
				creature.doAction(String.format("%d %d", reference.x(), reference.y()));
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction(String.format("%d %d", reference.x(), reference.y()));
			}
			public void end(Creature creature) {
				creature.doAction(String.format("%d %d", reference.x(), reference.y()));
			}
		};

		return test;
	}
	
	public Effect confused() {
		Effect confused = new Effect(5, "Confused", true, null) {
			public void start(Creature creature) {
				creature.doAction("become confused!");
				creature.setIsConfused(true);
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction("wander around in confusion!");
				//creature.ai().wander();
				
			}
			public void end(Creature creature) {
				creature.setIsConfused(false);
				if(creature.isConfused() == false) {
					creature.doAction("stop being confused");
				}

			}
		};
		confused.setIsNegative(true);
		confused.setIsConfused(true);
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
	
	public Effect frostWard() {
		Effect chillWard = new Effect(5, "Frost Ward", false, null){
			public void start(Creature creature){
				creature.setHasChillWard(true);
				creature.doAction("become wreathed in freezing air!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        
                        Effect chillBurst = frozen();
                		target.addEffect(chillBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.setHasChillWard(false);
				creature.doAction("feel the freezing winds fade away");
			}
        };
        chillWard.setIsChillWard(true);
		return chillWard;
	}
	
	public Effect magmaWard() {
		Effect magmaWard = new Effect(5, "Magma Ward", false, null){
			public void start(Creature creature){
				creature.setHasMagmaWard(true);
				creature.doAction("become shielded by flames!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        
                        Effect magmaBurst = ignited();
                        
                		target.addEffect(magmaBurst);

                    }
                }
            }
			public void end(Creature creature) {
				creature.setHasMagmaWard(false);
				creature.doAction("feel the flaming shield burn out");
			}
        };
        magmaWard.setIsMagmaWard(true);
		return magmaWard;
	}
	
	public Effect fireball() {
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
                        Effect gas = ignited();
                        creature.creature(nx, ny, creature.z).addEffect(gas);
                       
                    }
                }
            }
		};
		return fireball;
	}
	
	public Effect overgrow() {
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
                        			creature.creature(nx, ny, creature.z()).notify("Thick grass springs up around you!");
                        			creature.creature(nx, ny, creature.z()).addEffect(paralyzed());
                        		}
                        	}
                        }
                    }
                }
			}
        };
        return overgrow;
	}
	
	public Effect arcWard() {
		Effect arcWard = new Effect(5, "Arc Ward", false, null){
			public void start(Creature creature){
				creature.setHasArcWard(true);
				creature.doAction("become shrouded in lightning!");

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
                            continue;
                        }
                        
                        
                        Creature target = creature.creature(nx, ny, creature.z);
                        
                        //int amountChain = Math.max(0, 5-target.shockDefenseValue());
                        
                        double tempAmountChain = (ExtraMaths.d4());
        				int amountChain = (int) Math.round(tempAmountChain);
        				
                        target.doAction("get a shock!");
                        target.setLastHit(creature);
                        Damage damage = new Damage(amountChain, false, false, Damage.shock, getThis());
        				target.modifyHP(damage, "Killed by lightning magic");
        				target.modifyMana(damage);

                    }
                }
            }
			public void end(Creature creature) {
				creature.setHasArcWard(false);
				creature.doAction("feel the lightning shroud dissipate");
			}
        };
        arcWard.setIsArcWard(true);
		return arcWard;
	}
	
	public Effect invisible() {
		Effect invisible = new Effect(5, "Invisible", false, null) {
			public void start(Creature creature) {
				creature.setIsInvisible(true);
				creature.changeColor(ExtraColors.invisible);
				creature.doAction("become transparent");
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.changeColor(ExtraColors.invisible);
			}
			public void end(Creature creature) {
				creature.setIsInvisible(false);
				if(creature.isInvisible() == false) {
					creature.changeColor(creature.defaultColor());
					creature.doAction("become visible");
				}
				
			}
		};
		invisible.setIsInvisible(true);
		return invisible;
	}
	
	public Effect paralyzed() {
		Effect paralyzed = new Effect(5, "Paralyzed", true, null) {
			public void start(Creature creature) {
				creature.setIsParalyzed(true);
				creature.doAction("seize up!");
			}
			public void end(Creature creature) {
				creature.setIsParalyzed(false);
				if(creature.isParalyzed() == false) {
					creature.doAction("become mobile again");
				}
			}
		};
		paralyzed.setIsNegative(true);
		paralyzed.setIsParalysis(true);
		return paralyzed;
	}
	
	public Effect blink() {
		Effect blink = new Effect(8, null, false, null) {
			public void start(Creature creature){
	            creature.doAction("fade out");
	            
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
	
	public Effect frozen() {
		Effect frozen = new Effect(5, "Frozen", true, null){
			public void start(Creature creature) {
				
				double tempAmount = (ExtraMaths.d8());
				int amount = (int) Math.round(tempAmount);
				if(amount < 1) {
					amount = 1;
				}
				
				creature.setIsFrozen(true);
				creature.doAction("freeze solid!");
				Damage damage = new Damage(amount, false, false, Damage.frost, getThis());
				creature.modifyHP(damage, "Killed by icy magic");

			}
			
			public void end(Creature creature) {
				creature.setIsFrozen(false);
				if(creature.isFrozen() == false) {
					creature.doAction("defrost");
				}
			}};
		frozen.setIsNegative(true);
		frozen.setIsFrozen(true);
		return frozen;
	}
	
	public Effect electrified() {
		Effect electrified = new Effect(5, "Electrified", true, null) {
			public void start(Creature creature) {
				
				double tempAmount = (ExtraMaths.d4());
				int amount = (int) Math.round(tempAmount);
				
				creature.setIsShocked(true);
				creature.doAction("get a shock!");
				Damage damage = new Damage(amount, false, false, Damage.shock, getThis());
				creature.modifyHP(damage, "Killed by shocking magic");
			}
			public void update(Creature creature) {
				super.update(creature);
				Damage damage = new Damage(1, false, false, null, getThis());
				creature.modifyMana(damage);
			}
			public void end(Creature creature) {
				creature.setIsShocked(false);
				if(creature.isShocked() == false) {
					creature.doAction("recover from a shock");
				}
			}};
		electrified.setIsNegative(true);
		electrified.setIsElectrified(true);
		return electrified;
	}
	
	public Effect ignited() {
		Effect ignited = new Effect(5, "Ignited", true, null) {
			public void start(Creature creature) {
				creature.doAction("burst into flames!");
				creature.setIsIgnited(true);
			}
			public void update(Creature creature) {
				super.update(creature);
				creature.doAction("burn up!");
				Damage damage = new Damage(Dice.d4.roll(), false, false, Damage.fire, getThis());
				creature.modifyHP(damage, "Killed by fire magic");
			}
			public void end(Creature creature) {
				creature.setIsIgnited(false);
				if(creature.isIgnited() == false) {
					creature.doAction("put the flames out");
				}

			}};
		ignited.setIsNegative(true);
		ignited.setIsIgnited(true);
		return ignited;
		
	}
	
	public Effect magicMissile(Creature player) {
		Effect missile = new Effect(1, null, true, player){
			public void start(Creature creature) {
				//int amount = Math.max(0, 7-creature.defenseValue());
				
				double tempAmount = (ExtraMaths.d8()+(player.intelligenceModifier()));
				int amount = (int) Math.round(tempAmount);
				int attackRoll = player.intelligenceRoll();
				if(attackRoll >= creature.armorClass()) {
					creature.doAction("get hit with magic missiles!");
					creature.setLastHit(player);
					Damage damage = new Damage(amount, false, false, Damage.magic, getThis());
					creature.modifyHP(damage, "Killed by magic");
				}else {
					creature.doAction("resist the spell!");
				}
				
				//creature.learnName(item);
			}
		};
		return missile;
	}
	
	public Effect iceWall(Creature player) {
		Effect wall = new Effect(1, null, false, player){
			public void start(Creature creature) {
				player.notify("You summon a wall of ice!");
				Creature iceWall = objectFactory.newIceWall(0, player, 0);
				creature.become(iceWall);

				int px = player.x();
				int py = player.y();
				int cx = creature.x();
				int cy = creature.y();

				if(cx > px && cy > py) {
					//south west
					//creature.moveBy(1, 1, 0);
					if((creature.creature(cx+1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy, creature.z());
					}
					if((creature.creature(cx+2, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy-1, creature.z());
					}
					if((creature.creature(cx, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy+1, creature.z());
					}
					if((creature.creature(cx-1, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy+2, creature.z());
					}

					if((creature.creature(cx-1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy+1, creature.z());
					}
					if((creature.creature(cx-2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy+2, creature.z());
					}

					if((creature.creature(cx+1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy-1, creature.z());
					}
					if((creature.creature(cx+2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy-2, creature.z());
					}
				}

				if(cx < px && cy < py) {
					//north east
					//creature.moveBy(-1, -1, 0);
					if((creature.creature(cx-1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy, creature.z());
					}
					if((creature.creature(cx-2, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy+1, creature.z());
					}
					if((creature.creature(cx, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy-1, creature.z());
					}
					if((creature.creature(cx+1, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy-2, creature.z());
					}

					if((creature.creature(cx-1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy+1, creature.z());
					}
					if((creature.creature(cx-2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy+2, creature.z());
					}

					if((creature.creature(cx+1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy-1, creature.z());
					}
					if((creature.creature(cx+2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy-2, creature.z());
					}
				}

				if((cx > px && cy < py)) {
					//north west
					//creature.moveBy(1, -1, 0);
					if((creature.creature(cx+1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy, creature.z());
					}
					if((creature.creature(cx+2, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy+1, creature.z());
					}
					if((creature.creature(cx, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy-1, creature.z());
					}
					if((creature.creature(cx-1, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy-2, creature.z());
					}

					if((creature.creature(cx+1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy+1, creature.z());
					}
					if((creature.creature(cx+2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy+2, creature.z());
					}

					if((creature.creature(cx-1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy-1, creature.z());
					}
					if((creature.creature(cx-2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy-2, creature.z());
					}
				}

				if((cx < px && cy > py)) {
					//south east
					//creature.moveBy(-1, 1, 0);
					if((creature.creature(cx-1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy, creature.z());
					}
					if((creature.creature(cx-2, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy-1, creature.z());
					}
					if((creature.creature(cx, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy+1, creature.z());
					}
					if((creature.creature(cx+1, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy+2, creature.z());
					}

					if((creature.creature(cx+1, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy+1, creature.z());
					}
					if((creature.creature(cx+2, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy+2, creature.z());
					}

					if((creature.creature(cx-1, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy-1, creature.z());
					}
					if((creature.creature(cx-2, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy-2, creature.z());
					}
				}

				if((cx > px && cy == py)||(cx < px && cy == py)) {
					//creature.moveBy(1, 0, 0);
					if((creature.creature(cx, cy+1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy+1, creature.z());
					}
					if((creature.creature(cx, cy-1, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy-1, creature.z());
					}

					if((creature.creature(cx, cy+2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy+2, creature.z());
					}
					if((creature.creature(cx, cy-2, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx, cy-2, creature.z());
					}
				}


				if((cx == px && cy < py)||(cx == px && cy > py)) {
					//creature.moveBy(0, -1, 0);
					if((creature.creature(cx+1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+1, cy, creature.z());
					}
					if((creature.creature(cx-1, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-1, cy, creature.z());
					}

					if((creature.creature(cx+2, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx+2, cy, creature.z());
					}
					if((creature.creature(cx-2, cy, creature.z()) == null)) {
						Creature newWall = objectFactory.newIceWall(0, player, 0);
						creature.ai().world.addAtGivenLocation(newWall, cx-2, cy, creature.z());
					}
				}

			}




		};
		return wall;
	}

	public Effect chainLightning(Creature player) {
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
				Damage damage = new Damage(amount, false, false, Damage.shock, getThis());
				creature.modifyHP(damage, "Killed by lightning magic");
				
				if(saved == false) {
	                for (int ox = -2; ox < 3; ox++){
	                    for (int oy = -2; oy < 3; oy++){
	                        int nx = creature.x + ox;
	                        int ny = creature.y + oy;
	                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) == null) {
	                            continue;
	                        }
	                        
	                        
	                        Creature target = creature.creature(nx, ny, creature.z);
	                        
	                        int amountChain = (ExtraMaths.diceRoll(1, damageCeiling));
	        				
	                        target.doAction("get a shock!");
	                        target.setLastHit(player);
	                        Damage damageChain = new Damage(amountChain, false, false, Damage.shock, getThis());
	        				target.modifyHP(damageChain, "Killed by lightning magic");
	
	                    }
	                }
				}
            }
        };
        return lightning;
	}

	public Effect flashFreeze(Creature player) {
		Effect freeze = new Effect(1, null, true, player) {
			public void start(Creature creature) {
				
				int savingThrow = creature.frostSave();
				int saveTarget = player.intelligenceSaveDC();
				if(savingThrow < saveTarget) {
					creature.addEffect(frozen());
				}else {
					creature.doAction("resist the spell!");
				}
			}
		};
		return freeze;
	}
	
	public Effect repelWand(Creature player) {
		Effect repel = new Effect(1, null, true, player){
			public void start(Creature creature) {
				int px = player.x();
				int py = player.y();
				int cx = creature.x();
				int cy = creature.y();
				if(creature == player) {
					//int amount = Math.max(0, 15-creature.defenseValue());
					
					double tempAmount = (ExtraMaths.diceRoll(1, 15));
					int amount = (int) Math.round(tempAmount);
					Damage damage = new Damage(amount, false, false, Damage.physical, getThis());
					creature.doAction("get crushed!");
					creature.modifyHP(damage, "Killed by kinetic energy");
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
						if(push > 2) {
							creature.doAction("get thrown back!");
						}
					}
				}
			}
        };
        return repel;
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
                        Creature bat = objectFactory.randomLesserMonster(0, player, 0);
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
	

}
