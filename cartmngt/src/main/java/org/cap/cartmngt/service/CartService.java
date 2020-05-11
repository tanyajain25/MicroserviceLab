package org.cap.cartmngt.service;

import java.util.List;

import org.cap.cartmngt.entities.Cart;

public interface CartService {

	Cart toSaveItems(Cart item);
	List<Cart> fetchCartItems(int userId);

}
