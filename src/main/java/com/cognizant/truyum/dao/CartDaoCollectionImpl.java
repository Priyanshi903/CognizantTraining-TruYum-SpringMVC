package com.cognizant.truyum.dao;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Repository
public class CartDaoCollectionImpl implements CartDao {

	// @Value("new HashMap<Long,cart>()")
	private HashMap<Long, Cart> userCarts = new HashMap<>();

	// public CartDaoCollectionImpl() {
	//// userCarts=new HashMap<Long,Cart>();
	// //doubt:->
	// if(userCarts == null) {
	// userCarts=new HashMap<Long,Cart>();
	// }
	// }

	// Imp:-
	public void addCartItem(long userId, long menuItemId) {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		// System.out.println(menuItemId);
		// System.out.println(menuItem);
		if (userCarts.containsKey(userId)) {
			List<MenuItem> userItemList = userCarts.get(userId).getMenuItemList();
			userItemList.add(menuItem);
		} else {
			List<MenuItem> newUserItemList = new ArrayList<MenuItem>();
			newUserItemList.add(menuItem);
			Cart newUser = new Cart(newUserItemList, 0);
			userCarts.put(userId, newUser);

		}
	}

	// imp
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		if (userCarts.containsKey(userId)) {
			List<MenuItem> cartItems = userCarts.get(userId).getMenuItemList();
			if (cartItems.isEmpty())
				throw new CartEmptyException("Cart is Empty!!!");
			else {
				double totalPrice = cartItems.stream().collect(Collectors.summingDouble(e -> e.getPrice()));
				userCarts.get(userId).setTotal(totalPrice);
				// double total_amount=userCarts.get(userId).getTotal();
				// System.out.println("total_amount:"+total_amount);
				return (cartItems);
			}
		}
		return null;
	}

	// Imp:-
	public void removeCartItem(long userId, long menuItemId) {

		if (userCarts.containsKey(userId)) {
			List<MenuItem> cartItems = userCarts.get(userId).getMenuItemList();
			cartItems = cartItems.stream().filter(e -> e.getId() != menuItemId).collect(Collectors.toList());
			userCarts.get(userId).setMenuItemList(cartItems);
		}
	
	}
}
