package in.vikk.ordermgmnt.persistance.factory;

import in.vikk.ordermgmnt.api.persistance.DataSource;

public class DataSourceFactory {
	public static DataSource getDataSource(String dataSourceName, Object...objects) {
		DataSource dataSource = null;
		
		try {
			dataSource = (DataSource) Class.forName(dataSourceName).newInstance();
			dataSource.init(objects);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
		
		return dataSource;
	}
}
