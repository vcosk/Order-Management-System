package in.vikk.ordermgmnt.api.persistance;

import java.util.List;


public interface Persistance<E> {
	
	public DataSource getDataStore();

	public void setDataStore(DataSource dataSource);

	public DataParser<E> getDataParser();

	public void setDataParser(DataParser<E> dataParser);
	
	public List<E> openPersistance();
	
	public void closePersistance(List<E> dataList);
	
	public List<E> openPersistance(DataSource dataStore, DataParser<E> dataParser);
	
	public void closePersistance(List<E> dataList, DataSource dataSource, DataParser<E> dataParser);
}
