package indi.wzy.wms.util;

import java.sql.Connection;

/**
 * 
 * @ClassName: InterfaceConnectionPool
 * @Description: Database connection pool interface.
 * @author: 234
 * @date: 2020年1月24日 下午2:07:58
 */
public interface InterfaceConnectionPool {
	
	/**
	 * 
	 * @Title: getConnection
	 * @Description: Get database link
	 * @param: @return 
	 * @return: Connection 
	 * @throws: none
	 */
    public Connection getConnection();

    /**
     * 
     * @Title: releaseConnection
     * @Description: Freeing database links
     * @param: @param connection 
     * @return: void 
     * @throws: none
     */
    public void releaseConnection(Connection connection);
}
