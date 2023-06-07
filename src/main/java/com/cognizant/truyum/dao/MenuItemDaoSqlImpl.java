package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.*;

@Component("menuItemDao")
public class MenuItemDaoSqlImpl implements MenuItemDao {

	public List<MenuItem> getMenuItemListAdmin() {
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		String query = "select * from menu_item";
		try {
			Connection con = ConnectionHandler.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rslt = stmt.executeQuery();
			while (rslt.next()) {
				long id = rslt.getLong("menu_id");
				String name = rslt.getString("menu_name");
				float price = rslt.getFloat("menu_price");
				String active = rslt.getString("menu_active");
				Date date = rslt.getDate("date_of_launch");
				String category = rslt.getString("menu_category");
				String free_delivery = rslt.getString("free_delivery");
				boolean fd = false;
				boolean act = false;
				if (active.equalsIgnoreCase("YES")) {
					act = true;
				}
				if (free_delivery.equalsIgnoreCase("YES")) {
					fd = true;
				}

				MenuItem m = new MenuItem(id, name, price, act, date, category, fd);
				menuItemList.add(m);
			}

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemList;
	}

	public List<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		// Date currDate = new Date();
		java.util.Date currDate = new java.util.Date();

		String query = "select * from menu_item where menu_active='yes'";
		// String query="select * from menu_item where menu_active='yes' and
		// date_of_launch<'2018-09-07'";
		Connection con;
		try {
			con = ConnectionHandler.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rslt = stmt.executeQuery();

			while (rslt.next()) {

				if (currDate.compareTo(rslt.getDate("date_of_launch")) >= 0) {
					long id = rslt.getLong("menu_id");
					String name = rslt.getString("menu_name");
					float price = rslt.getFloat("menu_price");
					String active = rslt.getString("menu_active");
					Date date = rslt.getDate("date_of_launch");
					String category = rslt.getString("menu_category");
					String free_delivery = rslt.getString("free_delivery");
					boolean fd = false;
					boolean act = false;
					if (active.equalsIgnoreCase("YES")) {
						act = true;
					}
					if (free_delivery.equalsIgnoreCase("YES")) {
						fd = true;
					}

					MenuItem m = new MenuItem(id, name, price, act, date, category, fd);
					menuItemList.add(m);
				}
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuItemList;
	}

	public MenuItem getMenuItem(long menuItemId) {
		Connection con;
		MenuItem m = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "select * from menu_item where menu_id=" + menuItemId;
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rslt = stmt.executeQuery();
			rslt.next();
			// while(rslt.next()) {
			long id = rslt.getLong("menu_id");
			String name = rslt.getString("menu_name");
			float price = rslt.getFloat("menu_price");
			String active = rslt.getString("menu_active");
			Date date = rslt.getDate("date_of_launch");
			String category = rslt.getString("menu_category");
			String free_delivery = rslt.getString("free_delivery");
			boolean fd = false;
			boolean act = false;
			if (active.equalsIgnoreCase("YES")) {
				act = true;
			}
			if (free_delivery.equalsIgnoreCase("YES")) {
				fd = true;
			}

			m = new MenuItem(id, name, price, act, date, category, fd);

			// }

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;
	}

	public void modifyMenuItem(MenuItem menuItem) {

		long id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		// NOTE
		java.sql.Date date = new Date(menuItem.getDateOfLaunch().getTime());
		String category = menuItem.getCategory();
		boolean free_delivery = menuItem.isFreeDelivery();
		String fd = null;
		String act = null;
		if (free_delivery == true) {
			fd = "yes";
		}
		if (active == true) {
			act = "yes";
		}

		String query = "update menu_item set menu_name='" + name + "' , menu_price=" + price + ", menu_active='" + act
				+ "' ,date_of_launch='" + date + "' , menu_category='" + category + "' ,free_delivery='" + fd
				+ "' where menu_id=" + id;
		System.out.println(query);
		try {
			Connection con = ConnectionHandler.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
