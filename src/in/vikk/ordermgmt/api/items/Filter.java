package in.vikk.ordermgmt.api.items;

public interface Filter<E> {
	public boolean contains (E pattern);
}
