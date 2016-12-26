package in.vikk.ordermgmnt.persistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import in.vikk.ordermgmnt.api.persistance.DataParser;
import in.vikk.ordermgmt.api.items.Item;
import in.vikk.ordermgmt.items.ItemImpl;

public class CVSItemParser implements DataParser<Item> {

	@Override
	public List<Item> parseData(InputStream dataStream) {
		
		List<Item> itemList = new ArrayList<>();
		try {
			if (dataStream == null) {
				throw new IllegalArgumentException("Invalid data stream");
			}		
			BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
			
			String dataString = reader.readLine();
			while (dataString != null && dataString.trim().length() != 0) {
				Item dataItem = parseCSVToItem(dataString);
				itemList.add(dataItem);
				dataString = reader.readLine();
			}
		} 
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
			
		return itemList;
	}
	
	public void savaData(List<Item> itemList, OutputStream stream) {
		if (itemList == null) {
			throw new IllegalArgumentException("Invalid item map");
		}
		
		if (stream == null) {
			throw new IllegalArgumentException("Invalid output stream");
		}

		for (Item item : itemList) {
			String cvsIItemString = parseItemToCSV(item);
			if (cvsIItemString == null || cvsIItemString.length() == 0) {
				throw new IllegalStateException("Invalid item CVS string");
			}
			System.out.println(cvsIItemString);
			try {
				stream.write(cvsIItemString.getBytes());
			} 
			catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}
	
	private String parseItemToCSV (Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item is null");
		}
		StringBuilder cvs = new StringBuilder();
		cvs.append(item.getId());
		cvs.append(',');
		cvs.append(item.getName());
		cvs.append(',');
		cvs.append(item.getDescription());
		cvs.append(',');
		cvs.append(item.getPrice());
		cvs.append('\n');
		
		return cvs.toString();
	}
	private Item parseCSVToItem(String itemString) {
		if (itemString == null || itemString.length() == 0) {
			throw new IllegalArgumentException("Invalid item string");
		}
		
		Item item = new ItemImpl();
		
		String[] itemDataArray = itemString.split(",");
		if (itemDataArray.length != 4) {
			throw new IllegalStateException("Incorrect item string");
		}
		item.setId(itemDataArray[0]);
		item.setName(itemDataArray[1]);
		item.setDescription(itemDataArray[2]);
		item.setPrice(Double.parseDouble(itemDataArray[3]));
		
		return item;
	}

}
