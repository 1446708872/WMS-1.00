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

	/**
	 * 
	 * @Title: getInventorys
	 * @Description: Return inventory information.
	 * @param: @return
	 * @see indi.wzy.wms.service.InventoryService#getInventorys()
	 */
	@Override
	public String[][] getInventorys() {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().getInventorys(null);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: Save inventory information.
	 * @param: @param inventory
	 * @param: @return
	 * @see indi.wzy.wms.service.InventoryService#save(indi.wzy.wms.model.Inventory)
	 */
	@Override
	public boolean save(Inventory inventory) {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().save(null, inventory);
	}

	/**
	 * 
	 * @Title: remove
	 * @Description: Remove inventory information.
	 * @param: @param inventorys
	 * @param: @return
	 * @see indi.wzy.wms.service.InventoryService#remove(java.util.List)
	 */
	@Override
	public boolean remove(List<Integer> inventorys) {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().remove(null, inventorys);
	}

	/**
	 * 
	 * @Title: getInventorys
	 * @Description: return inventory information by product id.
	 * @param: @param productIDs
	 * @param: @return
	 * @see indi.wzy.wms.service.InventoryService#getInventorys(java.util.List)
	 */
	public String[][] getInventorys(List<Integer> productIDs) {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().getInventorys(null, productIDs);
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 
	 * @param: @param inventorys
	 * @param: @return
	 * @see indi.wzy.wms.service.InventoryService#updata(java.util.List)
	 */
	public boolean updata(List<Inventory> inventorys) {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().updata(null, inventorys);
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 
	 * @param: @param inventorys
	 * @param: @return
	 * @see indi.wzy.wms.service.InventoryService#updata(indi.wzy.wms.model.Inventory)
	 */
	public boolean updata(Inventory inventory) {
		// TODO Auto-generated method stub
		return DaoFactory.getInventoryDao().updata(null, inventory);
	}
}
