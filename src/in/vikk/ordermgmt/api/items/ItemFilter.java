package in.vikk.ordermgmt.api.items;

public interface ItemFilter {
	public boolean filterName(String pattern);
	
	public boolean filterDescription(String pattern);
	
	public boolean filterPrice(String pattern);
}
