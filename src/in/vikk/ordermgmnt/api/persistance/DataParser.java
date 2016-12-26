package in.vikk.ordermgmnt.api.persistance;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface DataParser<T> {
	public List<T> parseData(InputStream dataStream);
	
	public void savaData(List<T> dataList, OutputStream stream);
}
