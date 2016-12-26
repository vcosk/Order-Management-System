package in.vikk.ordermgmnt.persistance.factory;

import in.vikk.ordermgmnt.api.persistance.DataParser;

public class DataParserFactory {
	@SuppressWarnings("unchecked")
	public static<T> DataParser<T> getDataParser(String parserClass) {
		DataParser<T> dataParser = null;
		try {
			dataParser = (DataParser<T>) Class.forName(parserClass).newInstance();
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
		
		return dataParser;
	}
}
