package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartDaoCollectionImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

@Service
public class CartService {
	
	@Autowired
	private CartDaoCollectionImpl cartDao;
	
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException{
		return cartDao.getAllCartItems(userId);
		
	}
	public void setCartDao( CartDao cartDao) {
		
	}
	public void addCartItem(long userId, long menuItemId) {
		cartDao.addCartItem(userId, menuItemId);
	}
	public void removeCartItem(long userId, long menuItemId) {
		
	}

}
