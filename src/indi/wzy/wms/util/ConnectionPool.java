package indi.wzy.wms.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * @ClassName: ConnectionPool
 * @Description: Thread pool implementation class.
 * @author: 234
 * @date: 2020年1月24日 下午2:13:41
 * @param:
 */
public class ConnectionPool implements InterfaceConnectionPool {
	
	private static ConnectionPool pool = null;

	//Read the database file properties.
	private static Properties configObj = new Properties();

	static {
		InputStream connectionPool = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jdbc.properties");
		InputStreamReader inputStreamReader = new InputStreamReader(connectionPool);
		try {
			configObj.load(inputStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool = new ConnectionPool();
	}

	private static String getValue(String key) {
		return configObj.getProperty(key);
	}

	public static ConnectionPool getConnectionPoolFactory() {
		if(pool==null) {
			pool = new ConnectionPool();
		}
		return pool;
	}
	
	// Free thread set and active thread set.
	private List<Connection> freeConnection = new CopyOnWriteArrayList<Connection>();
	private List<Connection> activeConnecion = new CopyOnWriteArrayList<Connection>();

	// The atomic class marks the number of connections stored in the free pool
	private AtomicInteger atomicInteger;

	public ConnectionPool() {
		this.atomicInteger = new AtomicInteger(0);
		this.Initialization();
	}

	private void Initialization() {
		for (int i = 0; i < Integer.valueOf(getValue("initConnections")); i++) {
			Connection connection = CreateConnection();
			if (null != connection) {
				freeConnection.add(connection);
			}
		}
	}

	/**
	 * 
	 * @Title: CreateConnection @Description: Create database
	 * links. @param: @return @return: Connection @throws
	 */
	private Connection CreateConnection() {
		try {
			Class.forName(getValue("driverName"));
			Connection connection = DriverManager.getConnection(getValue("url"),getValue("userName"),
					getValue("password"));
			atomicInteger.addAndGet(1);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 
	 * @Title: getConnection
	 * @Description:Get database link.
	 * @param: @return
	 * @see indi.wzy.wms.util.InterfaceConnectionPool#getConnection()
	 */
	@Override
	public synchronized Connection getConnection() {
		// TODO Auto-generated method stub
		Connection connection = null;
		if (this.atomicInteger.get() < Integer.valueOf(getValue("maxActiveConnetions"))) {
			if (this.freeConnection.size() > 0) {
				connection = freeConnection.remove(0);
			} else {
				connection = CreateConnection();
			}
			if (this.isAvailable(connection)) {
				this.activeConnecion.add(connection);
			} else {
				this.atomicInteger.decrementAndGet();
				connection = getConnection();
			}
		} else {
			try {
				wait(Integer.valueOf(getValue("connTimeOut")));
			} catch (InterruptedException e) {

			}
		}
		return connection;
	}

	/**
	 * 
	 * @Title: releaseConnection
	 * @Description: Freeing database links.
	 * @param: @param connection
	 * @see indi.wzy.wms.util.InterfaceConnectionPool#releaseConnection(java.sql.Connection)
	 */
	@Override
	public synchronized void releaseConnection(Connection connection) {
		// TODO Auto-generated method stub
		if (this.isAvailable(connection)) {
			if (this.freeConnection.size() < Integer.valueOf(getValue("maxConnections"))) {
				this.freeConnection.add(connection);
			} else {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.activeConnecion.remove(connection);
			this.atomicInteger.decrementAndGet();
		} else {
			throw new RuntimeException("Link recovery exception");
		}
	}

	/**
	 * 
	 * @Title: isAvailable @Description: Determine if the link is
	 *         available. @param: @param connection @param: @return @return:
	 *         boolean @throws Is the link available.
	 */
	public boolean isAvailable(Connection connection) {
		try {
			if (null == connection || connection.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
