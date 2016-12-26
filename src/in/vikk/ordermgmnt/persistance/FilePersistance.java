package in.vikk.ordermgmnt.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import in.vikk.ordermgmnt.api.persistance.DataParser;
import in.vikk.ordermgmnt.api.persistance.DataSource;
import in.vikk.ordermgmnt.api.persistance.Persistance;

public class FilePersistance<E> implements Persistance<E> {

	private DataSource dataSource;
	
	private DataParser<E> dataParser;
	
	public DataSource getDataStore() {
		return dataSource;
	}

	public void setDataStore(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataParser<E> getDataParser() {
		return dataParser;
	}

	public void setDataParser(DataParser<E> dataParser) {
		this.dataParser = dataParser;
	}

	@Override
	public void closePersistance(List<E> dataList) {
		closePersistance(dataList, dataSource, dataParser);
	}
	
	@Override
	public List<E> openPersistance() {
		return openPersistance(dataSource, dataParser);
	}
	
	public List<E> openPersistance(DataSource dataSource, DataParser<E> dataParser) {
		
		String dataFile = (String)dataSource.getDataSource();
		
		validateFile(dataFile);		

		if (dataParser == null) {
			throw new IllegalArgumentException("Invalid file parser");
		}

		List<E> itemList = null;
		try {
			FileInputStream fileStream = new FileInputStream(dataFile);
			itemList = dataParser.parseData(fileStream);
			fileStream.close();
		} 
		catch (FileNotFoundException e) {
			throw new IllegalStateException("Data file " + dataFile + " does not exists", e);
		} 
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
		return itemList;
	}

	public void closePersistance(List<E> dataList, DataSource dataSource, DataParser<E> dataParser) {
		String dataFile = (String)dataSource.getDataSource();
		validateFile (dataFile);
		if (dataParser == null) {
			throw new IllegalArgumentException("Invalid file parser");
		}
		
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(dataFile);
			dataParser.savaData(dataList, outputStream);
		} 
		catch (Exception e) {
			throw new IllegalStateException("Data file " + dataFile + " does not exists", e);
		}
		finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} 
				catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}		
	}

	private void validateFile (String dataFile) {
		if (dataFile == null || dataFile.length() == 0) {
			throw new IllegalArgumentException("Invalid data file name.");
		}

		File dataFileObj = new File(dataFile);

		if (!dataFileObj.isFile()) {
			throw new IllegalArgumentException(dataFile + " is not a file");					
		}
		if (!dataFileObj.canRead() || !dataFileObj.canWrite()) {
			throw new IllegalArgumentException("Need read and write access on file " + dataFile);
		}
	}
}
