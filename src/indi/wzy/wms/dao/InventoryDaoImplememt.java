package indi.wzy.wms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import indi.wzy.wms.model.Inventory;

/**
 * 
 * @ClassName: InventoryDaoImplememt
 * @Description: Inventory persistence layer implementation class.
 * @author: 234
 * @date: 2020年1月24日 下午6:04:31
 * @param:
 */
public class InventoryDaoImplememt implements InventoryDao {

	/**
	 * 
	 * @Title: getInventorys
	 * @Description: Get all inventory data.
	 * @param: @param  connection Database link object.
	 * @param: @return The data set.
	 * @see indi.wzy.wms.dao.InventoryDao#getInventorys(java.sql.Connection)
	 */
	@Override
	public String[][] getInventorys(Connection connection) {
		// TODO Auto-generated method stub
		String sql = "select * from inventory";
		List<Inventory> inventorys = new ArrayList<Inventory>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = null;
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				int i =0;
				inventorys.add(
						new Inventory(rs.getInt("ProductID"), rs.getString("User"), rs.getString("SynthesisTechnology"),
								rs.getString("Capacity"), rs.getString("Version"), rs.getInt("Amount")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		}
		System.out.println("Read all inventory data.");
		return ConvertToArray(inventorys, 6);
	}

	@Override
	public Inventory getInventory(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Connection connection, Inventory invebtory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Connection connection, Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Connection connection, List<Inventory> inventorys) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Connection connection, Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Connection connection, List<Inventory> inventorys) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @Title: ConvertToArray
	 * @Description: Converts the collection to an array.
	 * @param: @param list Array to convert.
	 * @param: @param i Number of object elements.
	 * @param: @return 
	 * @return: String[][] Converted array.
	 * @throws
	 */
	public String[][] ConvertToArray(List<Inventory> list,int i){
		String[][] ays = new String [list.size()][i];
		for(int j=0;j<list.size();j++) {
			for(int k=0;k<i;k++) {
				ays[j]=list.get(j).getAys();
			}
		}
		return ays;
	}
}
