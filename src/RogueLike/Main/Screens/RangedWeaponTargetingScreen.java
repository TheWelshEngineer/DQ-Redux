package RogueLike.Main.Screens;

import RogueLike.Main.AoE.Line;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Creatures.Creature;

public class RangedWeaponTargetingScreen extends TargetBasedScreen{

	public RangedWeaponTargetingScreen(Creature player, int sx, int sy) {
		super(player, "Fire "+player.nameOf(player.weapon())+" at?", sx, sy);
	}
	
	public boolean isAcceptable(int x, int y) {
		if(!player.canSee(x, y, player.z)) {
			return false;
		}
		for(Point p : new Line(player.x, player.y, x, y)) {
			if(player.realTile(p.x, p.y, player.z).blocksRangedAttacks()) {
				return false;
			}
		}
		return true;
	}
	
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY) {
		Creature other = player.creature(x, y, player.z);
		
		if(other == null || other.isContainer() == true || other.isDisguised() == true) {
			player.notify("There's nothing there to attack.");
		}else {
//			if(player.weapon().isExtraAttack()) {
//				player.rangedWeaponAttack(other);
//			}
			//player.rangedWeaponAttack(other);
			player.ai().playerAIRangedAttack(other);
		}
	}

}
