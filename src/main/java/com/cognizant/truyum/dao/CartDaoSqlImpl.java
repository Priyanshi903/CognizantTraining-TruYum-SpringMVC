package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	public List<MenuItem> getAllCartItems(long userId) {
		Connection con;
		try {
			con = ConnectionHandler.getConnection();
			String query = "";
			PreparedStatement stmt = con.prepareStatement(query);
			// ResultSet rslt = stmt.executeQuery();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void addCartItem(long userId, long menuItemId) {
		Connection con;
		try {
			con = ConnectionHandler.getConnection();
			String query = "insert into users values(" + userId + "," + menuItemId + ")";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.execute();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void removeCartItem(long userId, long menuItemId) {
	}

}
