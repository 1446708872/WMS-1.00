package indi.wzy.wms.vo;

import java.util.List;

import javax.swing.JOptionPane;

/**
 * 
 * @ClassName: PageFactory
 * @Description: A production workshop with an interface.
 * @author: 234
 * @date: 2020年1月24日 下午8:21:47
 * @param:
 */
public class PageFactory {
	private static HomePage homePage = null;
	private static AddDataPage addDataPage = null;
	private static ModifyDataPage modifyDataPage = null;

	public static HomePage getHomePage() {
		if (homePage == null)
			homePage = new HomePage();
		return homePage;
	}

	public static AddDataPage getAddDataPage() {
		if (addDataPage == null)
			addDataPage = new AddDataPage();
		return addDataPage;
	}
	
	public static void closeAddDataPage() {
		addDataPage.dispose();
		addDataPage = null;
	}
	
	public static ModifyDataPage getModifyDataPage(List <Integer> productIDs) {
		if (modifyDataPage == null)
			modifyDataPage = new ModifyDataPage(productIDs);
		return modifyDataPage;
	}
	
	public static ModifyDataPage getModifyDataPage() {
		if (modifyDataPage == null)
			return null;
		return modifyDataPage;
	}
	
	public static void closeModifyDataPage() {
		modifyDataPage.dispose();
		modifyDataPage = null;
	}

	/**
	 * 
	 * @Title: isOperation @Description: Determine if there are other
	 * operations. @param: @return @return: boolean @throws
	 */
	public static boolean isOperation() {
		if (addDataPage != null) {
			JOptionPane.showMessageDialog(null,
					"This operation cannot be performed because you are already performing other operations.", "Waring",
					JOptionPane.NO_OPTION);
			return false;
		}
		if (modifyDataPage != null) {
			JOptionPane.showMessageDialog(null,
					"This operation cannot be performed because you are already performing other operations.", "Waring",
					JOptionPane.NO_OPTION);
			return false;
		}
		return true;
	}
}
