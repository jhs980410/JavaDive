package JavaDive.dao.odclass;

import java.sql.Connection;
import java.util.List;

public class ODClassDao {

private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	public List<ODClassDto> selectList() throws Exception{
		
	}
}
