package indi.wzy.wms.util;

/**
 * 
 * @Description: Stores the main page string data
 * @author:234
 * @date: 2020Äê1ÔÂ23ÈÕ ÏÂÎç5:45:18
 * @version V1.00
 */
public enum Enumeration {
	HOME_PAGE_TABLE_TITLE("Product ID","Name","Synthesis technology","Capacity(liter)","Version","Amount"),
	ADD_DATA_PAGE_TABLE_TITLE("Name","Synthesis technology","Capacity(liter)","Version","Amount"),
	
	HOME_PAGE_MENU_BAR("Menu","Record","Action","Notice"),
	HOME_PAGE_MENU_BAR_MENU("Add","Exit"),
	HOME_PAGE_MENU_BAR_RECORD("Open"),
	HOME_PAGE_MENU_BAR_ACTION("Import","Export"),
	HOME_PAGE_MENU_BAR_NOTICE("Appear"),
	
	DATA_ACTION_MENU_BAR("Menu"),
	DATA_ACTION_MENU("Save","Cancel"),
	
	HOME_PAGE_POPUP("Taken out","Deposited","Modify","Add","Delete","Refresh");
	
	private String[] datas;
	
	Enumeration (String...datas) {
		this.datas = datas;
	}
	
	public String[] getData(){
        if(datas == null)
            throw new RuntimeException("In the CommonStringEnum class between the \"data[]\" data does not exist.Null pointer exception");
        return this.datas;
    }
}