package in.vikk.ordermgmt;

import java.util.Date;
import java.util.List;

import in.vikk.ordermgmnt.api.persistance.DataParser;
import in.vikk.ordermgmnt.api.persistance.DataSource;
import in.vikk.ordermgmnt.api.persistance.Persistance;
import in.vikk.ordermgmnt.persistance.CVSFileSource;
import in.vikk.ordermgmnt.persistance.CVSItemParser;
import in.vikk.ordermgmnt.persistance.FilePersistance;
import in.vikk.ordermgmnt.persistance.factory.DataParserFactory;
import in.vikk.ordermgmnt.persistance.factory.DataSourceFactory;
import in.vikk.ordermgmnt.persistance.factory.PersistanceFactory;
import in.vikk.ordermgmt.api.items.Item;

public class Main {

	public static void main(String[] args) throws Exception {
		
		DataParser<Item> parser = DataParserFactory.getDataParser(CVSItemParser.class.getName());
		DataSource dataSource = DataSourceFactory.getDataSource(CVSFileSource.class.getName(), "itemdata.cvs");
		
		Persistance<Item> persistance = PersistanceFactory.getParsistance(FilePersistance.class.getName(), parser, dataSource);
		
		List<Item> items = persistance.openPersistance();
		System.out.println(items);
		for (Item item : items) {
			item.setDescription(item.getDescription() + " " + new Date());
		}
		
		persistance.closePersistance(items);
	}

}
