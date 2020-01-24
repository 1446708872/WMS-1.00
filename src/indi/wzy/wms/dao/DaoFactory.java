package indi.wzy.wms.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import indi.wzy.wms.util.InventoryProxy;

/**
 * 
 * @ClassName: DaoFactory
 * @Description: Persistence layer object factory.
 * @author: 234
 * @date: 2020年1月24日 下午6:25:18
 * @param:
 */
public class DaoFactory {
	
	public static InventoryDao inventoryDao = null;

	public synchronized static InventoryDao getInventoryDao() {
		if (inventoryDao == null) {
			final InventoryDaoImplememt inventory = new InventoryDaoImplememt();
			final InventoryProxy inventoryProxy = new InventoryProxy();
			InventoryDao proxyService = (InventoryDao) Proxy.newProxyInstance(DaoFactory.class.getClassLoader(),
					inventory.getClass().getInterfaces(), new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							// TODO Auto-generated method stub
							Connection connection = inventoryProxy.before();
							args[0]=connection;
							Object obj = method.invoke(inventory, args);
							inventoryProxy.after(connection);
							return obj;
						}
					});
			inventoryDao = proxyService;
		}
		return inventoryDao;
	}
}
