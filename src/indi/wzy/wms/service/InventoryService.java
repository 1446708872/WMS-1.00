package indi.wzy.wms.service;

import java.util.List;

import indi.wzy.wms.model.Inventory;
/**
 * 
 * @ClassName: InventoryService
 * @Description: Interface to inventory.
 * @author: 234
 * @date: 2020年1月24日 下午5:34:16
 * @param:
 */
public interface InventoryService {
	public String[][] getInventorys(List <Integer> productIDs);
	
	public String[][] getInventorys();
	
	public boolean save(Inventory inventory);
	
	public boolean updata(List<Inventory> inventorys);
	
	public boolean updata(Inventory inventory);
	
	public boolean remove(List<Integer> inventorys);
}
