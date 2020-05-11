package org.cap.cartmngt.dao;

import java.util.List;

import org.cap.cartmngt.entities.Cart;

public interface CartDao {

	Cart toSaveItems(Cart item);
	List<Cart> fetchCartItems(int userId);

}
