package in.vikk.ordermgmnt.persistance.factory;

import in.vikk.ordermgmnt.api.persistance.DataParser;
import in.vikk.ordermgmnt.api.persistance.DataSource;
import in.vikk.ordermgmnt.api.persistance.Persistance;

public class PersistanceFactory {
	@SuppressWarnings("unchecked")
	public static<E> Persistance<E> getParsistance(String persistanceClass, DataParser<E> dataParser, DataSource dataSource) {
		Persistance<E> persistance = null;
		
		try {
			persistance = (Persistance<E>)Class.forName(persistanceClass).newInstance();
			persistance.setDataParser(dataParser);
			persistance.setDataStore(dataSource);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
		
		return persistance;
	}
}
