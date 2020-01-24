package indi.wzy.wms.vo;

/**
 * 
 * @ClassName: PageFactory
 * @Description: A production workshop with an interface.
 * @author: 234
 * @date: 2020年1月24日 下午8:21:47
 * @param:
 */
public class PageFactory {
	private static HomePage homePage =null;
	
	public static HomePage getHomePage() {
		if(homePage==null)
			homePage=new HomePage();
		return homePage;
	}
}
