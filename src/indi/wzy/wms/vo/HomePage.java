package indi.wzy.wms.vo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import indi.wzy.wms.model.Inventory;
import indi.wzy.wms.service.ServiceFactory;
import indi.wzy.wms.util.Enumeration;
import indi.wzy.wms.util.ExcelFileFilter;

/**
 * 
 * @Description:This is main interface and some controls in the system.
 * @author:234
 * @date: 2020年1月23日 下午4:11:19
 * @version V1.00
 */
public class HomePage extends JFrame implements PageImplement {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	// Title content on the main screen.
	private String INTERFACE_TITLE = "WMS";

	// This is a wrapper class for a table class for display data on the main page.
	private HomePageTable table;

	// The remaining common controls.
	private JFileChooser importData, exportData;
	private JPopupMenu popupMenu;
	DefaultTableModel tableModel;
	private ExcelFileFilter excelFilter;
	private JMenuBar menuBar;
	private JLabel time;

	{
		this.initializationWindow(this, 600, 200, 1000, 600, INTERFACE_TITLE, new BorderLayout(), true,
				JFrame.EXIT_ON_CLOSE);
		
		this.initializationTable();
		this.initializationMenuBar();
		this.initializationPuPoPMenu();
		this.initializationTime();
	}

	/**
	 * 
	 * @Title: initializationTable
	 * @Description: crate table. 
	 * @param: 
	 * @see indi.wzy.wms.vo.PageImplement#initializationTable()
	 */
	public void initializationTable() {
		tableModel = new DefaultTableModel(ServiceFactory.getInventoryService().getInventorys(),
				Enumeration.HOME_PAGE_TABLE_TITLE.getData());
		table= new HomePageTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
	}
	
	/**
	 * 
	 * @Title: initializationMenuBar
	 * @Description: menu bar.
	 * @param: 
	 * @see indi.wzy.wms.vo.ShowDataPage#initializationMenuBar()
	 */
	@Override
	public void initializationMenuBar() {
		menuBar = new JMenuBar();
		JMenu menuItem;
		JMenuItem menuItems;
		for (String item : Enumeration.HOME_PAGE_MENU_BAR.getData()) {
			menuItem = new JMenu(item);
			switch (item) {
			case "Menu":
				for (String menu : Enumeration.HOME_PAGE_MENU_BAR_MENU.getData()) {
					menuItems = new JMenuItem(menu);
					menuItem.add(menuItems);
					menuItems.addActionListener(new HomePageMenuBarListener());
				}
				break;
			case "Record":
				for (String record : Enumeration.HOME_PAGE_MENU_BAR_RECORD.getData()) {
					menuItems = new JMenuItem(record);
					menuItem.add(menuItems);
					menuItems.addActionListener(new HomePageMenuBarListener());
				}
				break;
			case "Action":
				for (String action : Enumeration.HOME_PAGE_MENU_BAR_ACTION.getData()) {
					menuItems = new JMenuItem(action);
					menuItem.add(menuItems);
					menuItems.addActionListener(new HomePageMenuBarListener());
				}
				break;
			case "Notice":
				for (String notice : Enumeration.HOME_PAGE_MENU_BAR_NOTICE.getData()) {
					menuItems = new JMenuItem(notice);
					menuItem.add(menuItems);
					menuItems.addActionListener(new HomePageMenuBarListener());
				}
				break;
			}
			menuBar.add(menuItem);
		}
		this.setJMenuBar(menuBar);
	}

	/**
	 * 
	 * @Title: RefreshTable
	 * @Description: table refresh data.
	 * @param: 
	 * @see indi.wzy.wms.vo.ShowDataPage#RefreshTable()
	 */
	@Override
	public void RefreshTable() {
		DefaultTableModel tableModel = new DefaultTableModel(ServiceFactory.getInventoryService().getInventorys(),
				Enumeration.HOME_PAGE_TABLE_TITLE.getData());
		table.setModel(tableModel);
	}

	/**
	 * 
	 * @Title: initializationPuPoPMenu
	 * @Description: create right click menu. 
	 * @param: 
	 * @see indi.wzy.wms.vo.ShowDataPage#initializationPuPoPMenu()
	 */
	@Override
	public void initializationPuPoPMenu() {
		popupMenu = new JPopupMenu();
		for (String item : Enumeration.HOME_PAGE_POPUP.getData()) {
			JMenuItem menuItem = new JMenuItem(item);
			menuItem.addActionListener(new HomePagePupopMenuListener(table));
			popupMenu.add(menuItem);
		}
		table.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3)
					popupMenu.show(HomePage.this, e.getX() + 10, e.getY() + 75);
			}
		});
	}

	/**
	 * 
	 * @Title: initializationImportData @Description: Import excel
	 *         file @param: @return: void @throws
	 */
	public void initializationImportData() {
		importData = new JFileChooser();
		importData.setFileSelectionMode(JFileChooser.FILES_ONLY);
		excelFilter = new ExcelFileFilter();
		importData.setFileFilter(excelFilter);
		importData.showOpenDialog(null);
	}

	/**
	 * 
	 * @Title: InitializationExportData @Description: Export excel
	 *         file @param: @return: void @throws
	 */
	public void initializationExportData() {
		exportData = new JFileChooser();
		exportData.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		exportData.showOpenDialog(null);
		String file = exportData.getSelectedFile().getPath();
		System.out.println(file);
	}

	/**
	 * 
	 * @Title: initializationTime
	 * @Description: show time.
	 * @param:  
	 * @return: void 
	 * @throws
	 */
	private void initializationTime() {
		time = new JLabel(new Date().toString());
		this.add(time, BorderLayout.SOUTH);
		new Thread(() -> {
			boolean life = true;
			while (life) {
				try {
					Thread.sleep(1000);
					time.setText(new Date().toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
					life = false;
				}
			}
		}).start();
	}
}

/**
 * 
 * @Description:This class is wrapper class of JTable class.The HomePageTable
 *                   class configures the table to be selected but not modified.
 * @author:234
 * @date: 2020年1月23日 下午4:27:31
 * @version V1.00
 */
class HomePageTable extends JTable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @Title: HomePage.java
	 * @Description: The constructor calls the constructor of the parent class.
	 * @param tableModel
	 */
	public HomePageTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	/**
	 * 
	 * @Title: isCellEditable
	 * @Description: Destroys the edit cell method.
	 * @param row
	 * @param column
	 * @param: @return
	 * @see javax.swing.JTable#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}

/**
 * 
 * @ClassName: MenuBarListener
 * @Description: Click events for all menu bars.
 * @author: 234
 * @date: 2020年1月24日 下午1:16:36
 */
class HomePageMenuBarListener implements ActionListener {
	/**
	 * 
	 * @Title: actionPerformed
	 * @Description: Implement menu bar events.
	 * @param: @param e Menu bar behavior.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add":
			// VisualInterfaceFactory.newVisualInterfaceDataActionJFrame("Add Data");
			if (PageFactory.isOperation())
				PageFactory.getAddDataPage();
			break;
		case "Exit":
			System.exit(0);
			break;
		case "Open":
			// VisualInterfaceFactory.getVis ualInterfaceRecordJFrame().setVisible(true);
			break;
		case "Appear":

			JOptionPane.showMessageDialog(null,
					"If there are any defects in the system ,please contact 1446708872qq@.com  .",
					"Matters needing attention", JOptionPane.NO_OPTION);

			break;
		case "Import":
			PageFactory.getHomePage().initializationImportData();
			break;
		case "Export":
			PageFactory.getHomePage().initializationExportData();
			break;
		}
	}
}

/**
 * 
 * @ClassName: PupopMenuListener
 * @Description: Right-click the menu click event press.
 * @author: 234
 * @date: 2020年1月24日 下午1:38:48
 */
class HomePagePupopMenuListener implements ActionListener {
	private HomePageTable table;

	public HomePagePupopMenuListener(HomePageTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum;
		switch (e.getActionCommand()) {
		case "Taken out":
			if (isSelectedRows()) {
				String temporaryStr = null;
				boolean temporaryBool = true;
				while (temporaryBool) {
					temporaryStr = JOptionPane.showInputDialog("Please enter the quantity to be taken out:");
					if (temporaryStr == null || temporaryStr.equals(""))
						break;
					isNum = pattern.matcher(temporaryStr);
					if (!isNum.matches()) {
						JOptionPane.showMessageDialog(null, "The number of remaining stocks can only be a number.",
								"Waring", JOptionPane.NO_OPTION);
					} else {
						temporaryBool = false;
						int amount = Integer.valueOf((String) table.getValueAt(table.getSelectedRows()[0],
								Enumeration.HOME_PAGE_TABLE_TITLE.getData().length - 1));
						if (amount - Integer.parseInt(temporaryStr) < 0) {
							JOptionPane.showMessageDialog(null, "There is not enough stock for you to take out.",
									"Waring", JOptionPane.NO_OPTION);
							break;
						}
						Inventory inventory = new Inventory();
						inventory.setAmount(amount - Integer.parseInt(temporaryStr));
						inventory.setProductID(
								Integer.valueOf((String) table.getValueAt(table.getSelectedRows()[0], 0)));
						if (ServiceFactory.getInventoryService().updata(inventory)) {
							JOptionPane.showMessageDialog(null, "The update is successful.", "Hint",
									JOptionPane.NO_OPTION);
							PageFactory.getHomePage().RefreshTable();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to update data.", "Hint",
									JOptionPane.NO_OPTION);
						}
					}
				}
			}
			break;
		case "Deposited":
			if (isSelectedRows()) {
				String temporaryStr = null;
				boolean temporaryBool = true;
				while (temporaryBool) {
					temporaryStr = JOptionPane.showInputDialog("Please enter the quantity to be deposited:");
					if (temporaryStr == null || temporaryStr.equals(""))
						break;
					isNum = pattern.matcher(temporaryStr);
					if (!isNum.matches()) {
						JOptionPane.showMessageDialog(null, "The number of remaining stocks can only be a number.",
								"Waring", JOptionPane.NO_OPTION);
					} else {
						temporaryBool = false;
						int amount = Integer.valueOf((String) table.getValueAt(table.getSelectedRows()[0],
								Enumeration.HOME_PAGE_TABLE_TITLE.getData().length - 1));
						Inventory inventory = new Inventory();
						inventory.setAmount(amount + Integer.parseInt(temporaryStr));
						inventory.setProductID(
								Integer.valueOf((String) table.getValueAt(table.getSelectedRows()[0], 0)));
						if (ServiceFactory.getInventoryService().updata(inventory)) {
							JOptionPane.showMessageDialog(null, "The update is successful.", "Hint",
									JOptionPane.NO_OPTION);
							PageFactory.getHomePage().RefreshTable();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to update data.", "Hint",
									JOptionPane.NO_OPTION);
						}
					}
				}
			}
			break;
		case "Modify":
			if (table.getSelectedRows().length >= 1) {
				if (PageFactory.isOperation()) {
					List<Integer> productIDs = new ArrayList<Integer>();
					for (int i = 0; i < table.getSelectedRows().length; i++) {
						productIDs.add(Integer.valueOf((String) table.getValueAt(table.getSelectedRows()[i], 0)));
					}
					PageFactory.getModifyDataPage(productIDs);
				}
			} else if (table.getSelectedRows().length == 0) {
				JOptionPane.showMessageDialog(null, "Please select what you want to modify.", "Waring",
						JOptionPane.NO_OPTION);
			}
			break;
		case "Add":
			if (PageFactory.isOperation())
				PageFactory.getAddDataPage();
			break;
		case "Delete":
			if (table.getSelectedRows().length > 0 && PageFactory.isOperation()) {
				int i = JOptionPane.showConfirmDialog(null, "Are you sure you wang to delete it ?", "Waring!",
						JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					List<Integer> selectedData = new ArrayList<Integer>();
					for (int j = 0; j < table.getSelectedRows().length; j++) {
						selectedData.add(Integer.valueOf((String) table.getValueAt(table.getSelectedRows()[j], 0)));
					}
					if (!ServiceFactory.getInventoryService().remove(selectedData)) {
						JOptionPane.showMessageDialog(null, "Error!	Server exception.", "Waring",
								JOptionPane.NO_OPTION);
					}
					PageFactory.getHomePage().RefreshTable();
					break;
				}
			} else if (table.getSelectedRows().length == 0) {
				JOptionPane.showMessageDialog(null, "Please select what you want to delete.", "Waring",
						JOptionPane.NO_OPTION);
			}
			break;
		case "Refresh":
			PageFactory.getHomePage().RefreshTable();
			break;
		}
	}

	private boolean isSelectedRows() {
		if (table.getSelectedRows().length == 0) {
			JOptionPane.showMessageDialog(null, "Select the data you want to work with.", "Waring",
					JOptionPane.NO_OPTION);
			return false;
		} else if (table.getSelectedRows().length > 1) {
			JOptionPane.showMessageDialog(null, "Only one piece of data can be selected for manipulation.", "Waring",
					JOptionPane.NO_OPTION);
			return false;
		} else if (!PageFactory.isOperation()) {
			return false;
		}
		return true;
	}
}