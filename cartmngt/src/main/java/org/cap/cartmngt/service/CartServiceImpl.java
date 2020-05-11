package org.cap.cartmngt.service;

import org.cap.cartmngt.dao.CartDao;
import org.cap.cartmngt.entities.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {	
    private CartDao cartItemDao;
    
	public CartDao getCartItemDao() {
		return cartItemDao;
	}
	@Autowired
	public void setCartItemDao(CartDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}

	@Override
	public Cart toSaveItems(Cart item) {
		Cart cartItem= cartItemDao.toSaveItems(item);
		return cartItem;
	}

	@Override
	public List<Cart> fetchCartItems(int userId) {
		List<Cart> items = cartItemDao.fetchCartItems(userId);
		return items;
	}

  
}
