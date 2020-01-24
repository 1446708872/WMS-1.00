package indi.wzy.wms.vo;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * 
 * @Description:This is main interface and some controls in the system.
 * @author:234
 * @date: 2020年1月23日 下午4:11:19
 * @version V1.00
 */
public class HomePage extends JFrame {

	// Virtual data in the table
	private String[][] item = { { "1", "Mr.Wang", "Total synthesis", "1", "OW-40", "2" },
			{ "2", "Mr.Wang", "Total synthesis", "1", "OW-40", "2" },
			{ "3", "Mr.Wang", "Total synthesis", "1", "OW-40", "2" },
			{ "4", "Mr.Wang", "Total synthesis", "1", "OW-40", "2" },
			{ "5", "Mr.Wang", "Total synthesis", "1", "OW-40", "2" } };

	// Title content on the main screen.
	private String INTERFACE_TITLE = "Warehouse Management System";

	// This is a wrapper class for a table class for display data on the main page.
	HomePageTable table;

	// The remaining common controls.
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JPopupMenu popupMenu;
	private JMenuBar menuBar;
	private JLabel time;

	/**
	 * 
	 * @Title: HomePage.java
	 * @Description: Constructor with no arguments, and methods have methods inside
	 *               to initialize other controls
	 * @param: none.
	 */
	public HomePage() {
		this.InitializationWindow();
		this.InitializationTable();
		this.InitializationMenuBar();
		this.InitializationPuPoPMenu();
		this.InitializationTime();
	}

	private void InitializationWindow() {
		this.setBounds(600, 200, 1000, 600);
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(INTERFACE_TITLE);
		this.setVisible(true);
	}

	private void InitializationTable() {
		tableModel = new DefaultTableModel(item,Enumeration.HOME_PAGE_TABLE_TITLE.getData());
		table = new HomePageTable(tableModel);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void InitializationMenuBar() {
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
					// menuItems.addActionListener(new MainMenuBarListener());
				}
				break;
			case "Record":
				for (String record : Enumeration.HOME_PAGE_MENU_BAR_RECORD.getData()) {
					menuItems = new JMenuItem(record);
					menuItem.add(menuItems);
					// menuItems.addActionListener(new MainMenuBarListener());
				}
				break;
			case "Notice":
				for (String notice : Enumeration.HOME_PAGE_MENU_BAR_NOTICE.getData()) {
					menuItems = new JMenuItem(notice);
					menuItem.add(menuItems);
					// menuItems.addActionListener(new MainMenuBarListener());
				}
				break;
			}
			menuBar.add(menuItem);
		}
		this.setJMenuBar(menuBar);
	}

	private void InitializationPuPoPMenu() {
		popupMenu = new JPopupMenu();
		for (String item : Enumeration.HOME_PAGE_POPUP.getData()) {
			JMenuItem menuItem = new JMenuItem(item);
//			 menuItem.addActionListener(new MainPupopMenuListener(this.tableModel,
//			 table));
			popupMenu.add(menuItem);
		}
		table.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3)
					popupMenu.show(HomePage.this, e.getX()+10, e.getY()+75);
			}
		});
	}

	private void InitializationTime() {
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