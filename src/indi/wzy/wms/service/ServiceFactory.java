package indi.wzy.wms.service;

/**
 * 
 * @ClassName: ServiceFactory
 * @Description: Responsible for producing and saving service objects.
 * @author: 234
 * @date: 2020年1月24日 下午5:50:12
 * @param:
 */
public class ServiceFactory {	
	private static InventoryService inventoryService;
	
	public static synchronized InventoryService getInventoryService() {
		if(inventoryService==null)
			inventoryService = new InventoryServiceImplement();
		return inventoryService;
	}
}
