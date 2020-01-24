package indi.wzy.wms.util;

import java.sql.Connection;

/**
 * 
 * @ClassName: InventoryProxy
 * @Description: 
 * @author: 234
 * @date: 2020年1月24日 下午6:11:41
 * @param:
 */
public class InventoryProxy {
	/**
	 * 
	 * @Title: before
	 * @Description: Pull out the database link object.
	 * @param: @return The database link object to fetch.
	 * @return: Connection 
	 * @throws
	 */
	public Connection before() {
		return ConnectionPool.getConnectionPoolFactory().getConnection();
	}
	
	/**
	 * 
	 * @Title: after
	 * @Description: Return the database link object.
	 * @param: @param connection Object to be returned.
	 * @return: void 
	 * @throws
	 */
	public void after(Connection connection) {
		ConnectionPool.getConnectionPoolFactory().releaseConnection(connection);
	}
}
