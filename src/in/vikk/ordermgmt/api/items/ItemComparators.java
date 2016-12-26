package in.vikk.ordermgmt.api.items;

import java.util.Comparator;

public interface ItemComparators {

	public Comparator<Item> ItemNameComparator = new Comparator<Item>() {
		@Override
		public int compare(Item o1, Item o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	public Comparator<Item> ItemPriceComparator = new Comparator<Item>() {
		@Override
		public int compare(Item o1, Item o2) {
			return o1.getPrice() > o2.getPrice() ? 1 : o1.getPrice() == o2.getPrice() ? 0 : -1;
		}
	};

}
