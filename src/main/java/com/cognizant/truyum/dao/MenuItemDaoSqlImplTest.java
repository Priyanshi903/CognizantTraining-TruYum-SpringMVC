package dao;

import java.util.List;

import model.MenuItem;
import util.DateUtil;

public class MenuItemDaoSqlImplTest {
	public static void testGetMenuItemListAdmin() {
		MenuItemDao menuItemDao=new MenuItemDaoSqlImpl();
		List<MenuItem> menuItemAdmin= menuItemDao.getMenuItemListAdmin();
		System.out.println("Menu item list admin sql:->");
		for(MenuItem m: menuItemAdmin) {
			System.out.println(m);
		}
	}
	public static void testGetMenuItemListCustomer() {
		MenuItemDao menuItemDao=new MenuItemDaoSqlImpl();
		List<MenuItem> menuItemCustomer= menuItemDao.getMenuItemListCustomer();
		System.out.println("Menu item list customer sql:->");
		for(MenuItem m: menuItemCustomer) {
			System.out.println(m);
		}
	}
	public static void testModifyMenuItem() {
		MenuItemDao menuItemDao=new MenuItemDaoSqlImpl();
		MenuItem mobj = new MenuItem(1,"Momos", (float)120.00, true, DateUtil.convertToDate("15/03/2018"), "Starter", true);
		menuItemDao.modifyMenuItem(mobj);
		if(menuItemDao.getMenuItem(mobj.getId()).equals(mobj))
			System.out.println("Modification Done!!!");
	}
	public void testGetMenuItem() {}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
//		testModifyMenuItem();

	}

}
