package in.vikk.ordermgmnt.persistance;

import in.vikk.ordermgmnt.api.persistance.DataSource;

public class CVSFileSource implements DataSource {
	
	private String source;
	
	@Override
	public Object getDataSource() {
		return source;
	}

	@Override
	public void init(Object... objects) {
		if (objects == null || objects.length == 0) {
			throw new IllegalArgumentException("Invalid init");
		}
		
		source = (String) objects[0];
	}

}
