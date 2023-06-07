package com.cognizant.truyum.dao;




import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cognizant.truyum.model.MenuItem;

import com.cognizant.truyum.util.DateUtil;

//@Repository
public class MenuItemDaoCollectionImpl implements MenuItemDao {
	
	private List<MenuItem> menuItemList;
	
	public MenuItemDaoCollectionImpl() {
		menuItemList=new ArrayList<>();
		if(menuItemList.isEmpty()) {
			menuItemList =new ArrayList<>();
			menuItemList.add(new MenuItem(1, "Sandwich", (float)99.00, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true));			
			menuItemList.add(new MenuItem(2, "Burger", (float)129.00, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			menuItemList.add(new MenuItem(3, "Pizza", (float)149.00, true, DateUtil.convertToDate("21/8/2018"), "Main Course", false));
			menuItemList.add(new MenuItem(4, "French Fries", (float)57.00, false, DateUtil.convertToDate("02/07/2017"), "Starter", true));
			menuItemList.add(new MenuItem(5, "Chocolate Brownie", (float)32.00, true, DateUtil.convertToDate("02/11/2022"), "Dessert", true));
		}
	}
	
	public List<MenuItem> getMenuItemListAdmin(){
		return menuItemList;
	}
	//imp
	public List<MenuItem> getMenuItemListCustomer(){
		Date currentDate = new Date();
		List<MenuItem> filteredMenuItems=(List<MenuItem>) menuItemList.stream().
				filter(e->((e.getDateOfLaunch().before(currentDate) || e.getDateOfLaunch().equals(currentDate)) && 
				e.isActive() == true)).collect(Collectors.toList());;
		return filteredMenuItems;
	}
	public void modifyMenuItem( MenuItem menuItem) {
//		System.out.println("inside modifyMenuItem");
		for(MenuItem mi : menuItemList) {
			if(mi.equals(menuItem)) {
//				System.out.println("inside if");
				mi.setId(menuItem.getId());
				mi.setName(menuItem.getName());
				mi.setPrice(menuItem.getPrice());
				mi.setActive(menuItem.isActive());
				mi.setDateOfLaunch(menuItem.getDateOfLaunch());
				mi.setCategory(menuItem.getCategory());
				mi.setFreeDelivery(menuItem.isFreeDelivery());
//				System.out.println("Modification done from modifymenuItem");
			}			
		}
	}
	public MenuItem getMenuItem(long menuItemId) {
		for(MenuItem mi : menuItemList) {
			if(mi.getId() == menuItemId)
				return(mi);
		}
		return null;
	}

}
