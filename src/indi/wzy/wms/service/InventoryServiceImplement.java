package indi.wzy.wms.service;

import java.util.List;

import indi.wzy.wms.dao.DaoFactory;
import indi.wzy.wms.model.Inventory;

/**
 * 
 * @ClassName: InventoryServiceImplement
 * @Description: Inventory implementation class.
 * @author: 234
 * @date: 2020年1月24日 下午5:35:00
 * @param:
 */
public class InventoryServiceImplement  implements InventoryService{

	@Override
	public String[][] getInventorys() {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().getInventorys(null);
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Inventory invebtory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(List<Inventory> inventorys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(List<Inventory> inventorys) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
