package indi.wzy.wms.dao;

import java.sql.Connection;
import java.util.List;

import indi.wzy.wms.model.Inventory;

/**
 * 
 * @ClassName: InventoryDao
 * @Description: Inventory persistence layer interface
 * @author: 234
 * @date: 2020年1月24日 下午6:02:20
 * @param:
 */
public interface InventoryDao {
	public String[][] getInventorys(Connection connection);

	public Inventory getInventory(Connection connection);

	public boolean save(Connection connection,Inventory invebtory);

	public boolean remove(Connection connection,Inventory inventory);

	public boolean remove(Connection connection,List<Inventory> inventorys);

	public boolean modify(Connection connection,Inventory inventory);

	public boolean modify(Connection connection,List<Inventory> inventorys);
}
