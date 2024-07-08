package RogueLike.Main;

import java.util.ArrayList;
import java.util.Collections;

import RogueLike.Main.Items.Item;

public class Inventory {
	
	private ArrayList<Item> items;
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void shuffle() {
		Collections.shuffle(items);
	}
	
	public Item get(int i) {
		return items.get(i);
	}
	
	public Inventory(int max) {
		items = new ArrayList<Item>(max);
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void remove(Item item) {
		items.remove(item);
	}
	
	public boolean isFull() {
		int size = 0;
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i) != null) {
				size++;
			}
		}
		return size == items.size()-1;
	}

}





































/*package RogueLike.Main;

//import java.util.ArrayList;
//import java.util.Arrays;

public class Inventory {
	
	private Item[] items;
	public Item[] getItems() {
		return items;
	}
	public Item get(int i) {
		return items[i];
	}
	
	
	
	public Inventory(int max) {
		items = new Item[max];
		
	}
	
	public void add(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				items[i] = item;
				break;
			}
			
		}
	}
	
	public void remove(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == item) {
				items[i] = null;
				return;
			}
		}
	}
	
	public boolean isFull() {
		int size = 0;
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				size++;
			}
		}
		return size == items.length;
	}
	
}*/


