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
				inventorys.add(
						new Inventory(rs.getInt("ProductID"), rs.getString("Name"), rs.getString("SynthesisTechnology"),
								rs.getString("Capacity"), rs.getString("Version"), rs.getInt("Amount")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return ConvertToArray(inventorys, 6);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: Save the data.
	 * @param: @param  connection
	 * @param: @param  inventory
	 * @param: @return
	 * @see indi.wzy.wms.dao.InventoryDao#save(java.sql.Connection,
	 *      indi.wzy.wms.model.Inventory)
	 */
	@Override
	public boolean save(Connection connection, Inventory inventory) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO inventory (Name,SynthesisTechnology,Capacity,Version,Amount)VALUES(?,?,?,?,?);";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, inventory.getName());
			ps.setString(2, inventory.getSynthesisTechnology());
			ps.setString(3, inventory.getCapacity());
			ps.setString(4, inventory.getVersion());
			ps.setInt(5, inventory.getAmount());
			int result = ps.executeUpdate();
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: remove
	 * @Description: Delete inventory data.
	 * @param: @param  connection
	 * @param: @param  inventorys
	 * @param: @return
	 * @see indi.wzy.wms.dao.InventoryDao#remove(java.sql.Connection,
	 *      java.util.List)
	 */
	@Override
	public boolean remove(Connection connection, List<Integer> inventorys) {
		// TODO Auto-generated method stub
		String sql = "delete from inventory where ProductID=?";

		for (Integer temporary : inventorys) {
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, temporary);
				int result = ps.executeUpdate();
				if (result == 0) {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: ConvertToArray @Description: Converts the collection to an
	 *         array. @param: @param list Array to convert. @param: @param i Number
	 *         of object elements. @param: @return @return: String[][] Converted
	 *         array. @throws
	 */
	public String[][] ConvertToArray(List<Inventory> list, int i) {
		String[][] ays = new String[list.size()][i];
		for (int j = 0; j < list.size(); j++) {
			for (int k = 0; k < i; k++) {
				ays[j] = list.get(j).getAys();
			}
		}
		return ays;
	}

	/**
	 * 
	 * @Title: getInventorys
	 * @Description: Return inventory information by product id.
	 * @param: @param  connection
	 * @param: @param  productIDs
	 * @param: @return
	 * @see indi.wzy.wms.dao.InventoryDao#getInventorys(java.sql.Connection,
	 *      java.util.List)
	 */
	@Override
	public String[][] getInventorys(Connection connection, List<Integer> productIDs) {
		// TODO Auto-generated method stub
		String sql = "select * from inventory where ProductID=?";
		List<Inventory> inventorys = new ArrayList<Inventory>();
		for (Integer temporary : productIDs) {
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, temporary);
				ResultSet rs = null;
				rs = ps.executeQuery();
				while (rs.next()) {
					inventorys.add(new Inventory(rs.getInt("ProductID"), rs.getString("Name"),
							rs.getString("SynthesisTechnology"), rs.getString("Capacity"), rs.getString("Version"),
							rs.getInt("Amount")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return ConvertToArray(inventorys, 6);
	}

	/**
	 * 
	 * @Title: update
	 * @Description:
	 * @param: @param  connection
	 * @param: @param  inventorys
	 * @param: @return
	 * @see indi.wzy.wms.dao.InventoryDao#save(java.sql.Connection, java.util.List)
	 */
	@Override
	public boolean updata(Connection connection, List<Inventory> inventorys) {
		// TODO Auto-generated method stub
		String sql = "update inventory set Name=?,SynthesisTechnology=?,Capacity=?,Version=?,Amount=? where ProductID=?";
		for (Inventory temporparty : inventorys) {
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, temporparty.getName());
				ps.setString(2, temporparty.getSynthesisTechnology());
				ps.setString(3, temporparty.getCapacity());
				ps.setString(4, temporparty.getVersion());
				ps.setInt(5, temporparty.getAmount());
				ps.setInt(6, temporparty.getProductID());
				int i = ps.executeUpdate();
				if (i == 0)
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: update
	 * @Description:
	 * @param: @param  inventory
	 * @param: @return
	 * @see indi.wzy.wms.dao.InventoryDao#updata(indi.wzy.wms.model.Inventory)
	 */
	@Override
	public boolean updata(Connection connection, Inventory inventory) {
		// TODO Auto-generated method stub
		String sql = "update inventory set Amount=? where ProductID=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, inventory.getAmount());
			ps.setInt(2, inventory.getProductID());
			int i = ps.executeUpdate();
			if (i == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
}