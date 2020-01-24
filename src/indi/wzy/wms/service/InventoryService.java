package indi.wzy.wms.service;

import java.util.List;

import indi.wzy.wms.model.Inventory;
/**
 * 
 * @ClassName: InventoryService
 * @Description: Interface to inventory.
 * @author: 234
 * @date: 2020��1��24�� ����5:34:16
 * @param:
 */
public interface InventoryService {
	public String[][] getInventorys();
	
	public Inventory getInventory();
	
	public boolean save(Inventory invebtory);
	
	public boolean remove(Inventory inventory);
	
	public boolean remove(List<Inventory> inventorys);
	
	public boolean modify(Inventory inventory);
	
	public boolean modify(List<Inventory> inventorys);
}
