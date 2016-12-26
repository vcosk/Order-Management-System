package in.vikk.ordermgmt.api.items;

public interface Item extends ItemFilter {
	public String getId();

	public void setId(String itemId);

	public String getName();

	public void setName(String itemName);

	public String getDescription();

	public void setDescription(String itemDesc);
	
	public double getPrice();
	
	public void setPrice(double price);
}
