package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.TextUtils;
import RogueLike.Main.Creatures.Player;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.CharacterSheet.ArmorClassElement;
import RogueLike.Main.Screens.CharacterSheet.CharacterSheetElement;
import RogueLike.Main.Screens.CharacterSheet.DamageModifierElement;
import RogueLike.Main.Screens.CharacterSheet.DexterityElement;
import RogueLike.Main.Screens.CharacterSheet.EffectElement;
import RogueLike.Main.Screens.CharacterSheet.GoldElement;
import RogueLike.Main.Screens.CharacterSheet.HealthElement;
import RogueLike.Main.Screens.CharacterSheet.HungerElement;
import RogueLike.Main.Screens.CharacterSheet.IntelligenceElement;
import RogueLike.Main.Screens.CharacterSheet.LevelElement;
import RogueLike.Main.Screens.CharacterSheet.ManaElement;
import RogueLike.Main.Screens.CharacterSheet.NameElement;
import RogueLike.Main.Screens.CharacterSheet.ProficiencyBonusElement;
import RogueLike.Main.Screens.CharacterSheet.SpacerElement;
import RogueLike.Main.Screens.CharacterSheet.StrengthElement;
import RogueLike.Main.Screens.CharacterSheet.VisionRadiusElement;
import RogueLike.Main.Screens.CharacterSheet.XPElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.AlchemancySkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.ArmorTrainingSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.CryomancySkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.ElectromancySkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.EvocationSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.FerromancySkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.FinesseWeaponsSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.FortitudeSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.MartialWeaponsSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.PerceptionSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.PyromancySkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.RangedWeaponsSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.SimpleWeaponsSkillElement;
import RogueLike.Main.Screens.CharacterSheet.Skills.StealthSkillElement;

public class CharacterSheetScreen implements Screen, Serializable{

	protected Player player;

	public CharacterSheetScreen(Player player) {
		this.player = player;

		elements = new ArrayList<>();
		elements.add( // column 1: stats
			List.of(
				new NameElement(player),
				new LevelElement(player),
				new XPElement(player),
				new GoldElement(player),
				//
				new SpacerElement(),
				//
				new HealthElement(player),
				new ManaElement(player),
				new HungerElement(player),
				//
				new SpacerElement(),
				//
				new StrengthElement(player),
				new DexterityElement(player),
				new IntelligenceElement(player),
				//
				new SpacerElement(),
				//
				new ArmorClassElement(player),
				new ProficiencyBonusElement(player),
				new VisionRadiusElement(player)
			)
		);
		elements.add( // column 2: skills
			List.of(
				new SimpleWeaponsSkillElement(player),
				new MartialWeaponsSkillElement(player),
				new FinesseWeaponsSkillElement(player),
				new RangedWeaponsSkillElement(player),
				//
				new SpacerElement(),
				//
				new ArmorTrainingSkillElement(player),
				new FortitudeSkillElement(player),
				new StealthSkillElement(player),
				new PerceptionSkillElement(player),
				//
				new SpacerElement(),
				//
				new EvocationSkillElement(player),
				new PyromancySkillElement(player),
				new CryomancySkillElement(player),
				new ElectromancySkillElement(player),
				new AlchemancySkillElement(player),
				new FerromancySkillElement(player)
			)
		);
		elements.add( // column 3: current effects
			player.effects().stream()
				.filter(effect -> effect.name() != null && effect.showInMenu())
				.map(EffectElement::new)
				.collect(Collectors.toList())
		);
		elements.add( // column 4: damage modifiers
			Arrays.stream(DamageType.RESISTABLE_TYPES).map(
				type -> new DamageModifierElement(player, type)
			).collect(Collectors.toList())
		);
	}

	private final List<List<CharacterSheetElement>> elements;

	int cX = 0;
	int cY = 0;

	private CharacterSheetElement selectedElement() {
		return elements.get(cX).get(cY);
	}

	@Override
	public void displayOutput() {
		ExtendedAsciiPanel.clear();

		Screen.generateBorders();

		ExtendedAsciiPanel.writeCenter("== Character Sheet ==", 1);
		int[] column_start_y = {3, 3, 3, 3};
		int[] column_x = {5, 32, 59, 89};
		String[] column_headers = {"Stats", "Skills", "Status Effects", "Damage Resistances"};

		CharacterSheetElement selectedElement = selectedElement();

		for (int column_no = 0; column_no < 4; column_no++) {
			int x = column_x[column_no];
			int y = column_start_y[column_no];
			ExtendedAsciiPanel.write(String.format("== %s ==", column_headers[column_no]), x, y++);

			for (CharacterSheetElement element: elements.get(column_no)) {
				String selector = element == selectedElement ? ">> " : "";
				ExtendedAsciiPanel.write(selector, x, y);
				ExtendedAsciiPanel.write(element.formattedHeader(),x + selector.length(), y++);
			}
		}

		int rightPadding = 4;
		int maxWidth = ExtendedAsciiPanel.getWidthInCharacters() - (column_x[0] + rightPadding);
		ExtendedAsciiPanel.writeMultiline(TextUtils.wordWrap(selectedElement.details(), maxWidth, 4), column_x[0], 31);

        ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 42);
	}

	private void moveSelectionVertical(int direction) {
		var currentColumn = elements.get(cX);
		if (currentColumn.isEmpty()) {
			throw new IllegalStateException(String.format("Empty column at cX=%d shouldn't be selected.", cX));
		}
		// Keep moving down until we hit a selectable element.
		// Can't loop infinitely:
		//  - We start in column 0 which has selectable elements.
		//  - moveSelectionHorizontal checks that the column it's moving into has a selectable element.
		do {
			cY = trueModulo(cY+direction, currentColumn.size());
		} while (!selectedElement().isSelectable());
	}

	private void moveSelectionHorizontal(int direction) {
		int numColumns = elements.size(); // i.e. size on the x-axis
		// Keep moving until we hit a column with selectable elements.
		// Can't loop infinitely as column 0 always has selectable elements.
		do {
			cX = trueModulo(cX+direction, numColumns);
		} while (elements.get(cX).stream().noneMatch(CharacterSheetElement::isSelectable));

		// now ensure that our y-position is within bounds
		var column = elements.get(cX);
		if (cY >= column.size()) {
			cY = column.size();
			// note that moveSelectionVertical doesn't require cY to be in-bounds
			moveSelectionVertical(-1);
		}
		if (!selectedElement().isSelectable()) {
			moveSelectionVertical(-1);
		}
	}

	private int trueModulo(int dividend, int divisor) {
		int javaModulo = dividend % divisor;
		if (javaModulo < 0) {
			return javaModulo + divisor;
		}
		else {
			return javaModulo;
		}
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuLeft:
			moveSelectionHorizontal(-1);
			return this;
		case KeybindManager.navigateMenuRight:
			moveSelectionHorizontal(1);
			return this;
		case KeybindManager.navigateMenuUp:
			moveSelectionVertical(-1);
			return this;
		case KeybindManager.navigateMenuDown:
			moveSelectionVertical(1);
			return this;
		case KeybindManager.navigateMenuBack:
			return null;
		default: return this;
		}
	}

}
