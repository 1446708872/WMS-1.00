package indi.wzy.wms.vo;



import indi.wzy.wms.util.ConnectionPool;

/**
 * 
 * @Title: main
 * @Description: Initiator class.
 * @param: @param ays 
 */
public class Main {

	static {
		ConnectionPool.getConnectionPoolFactory();
	}
	
	public static void main(String[] ays) {
		PageFactory.getHomePage();
	}
}
