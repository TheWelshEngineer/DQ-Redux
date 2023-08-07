package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Item;

public class ReadScreen extends InventoryBasedScreen{
	
	private int sx;
	private int sy;

	public ReadScreen(Creature player, int sx, int sy) {
		super(player);
		this.sx = sx;
		this.sy = sy;
	}

	@Override
	protected String getVerb() {
		return "read";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return !item.writtenSpells().isEmpty();
	}

	@Override
	protected Screen use(Item item) {
		if(item.isSpellbook() > 0){
			player.learnSpell(item.writtenSpells().get(0), item);
			return null;
		}else {
			if(item.writtenSpells().size() == 1 && !item.writtenSpells().get(0).isSelfCast()) {
				return new CastSpellScreen(player, "Cast spell at?", sx, sy, item.writtenSpells().get(0), item);
			}else if(item.writtenSpells().size() == 1 && item.writtenSpells().get(0).isSelfCast()){
				player.castSpell(item.writtenSpells().get(0), player.x(), player.y(), item);
				return null;
			}else {
				return new ReadSpellScreen(player, sx, sy, item);
			}
		}
		
		
		
	}
	

}
