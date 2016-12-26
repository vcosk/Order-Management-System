package in.vikk.ordermgmnt.api.persistance;

public interface DataSource {
	public Object getDataSource();
	
	public void init(Object...objects);
}
