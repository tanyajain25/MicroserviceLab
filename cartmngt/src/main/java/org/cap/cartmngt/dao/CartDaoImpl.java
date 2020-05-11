package org.cap.cartmngt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cap.cartmngt.entities.Cart;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements CartDao {

	Map<Integer, Cart> map = new HashMap<>();

	public Cart toSaveItems(Cart item) {
		int id= generateId();
		item.setId(id);
		map.put(item.getId(), item);
		return item;
	}

	public List<Cart> fetchCartItems(int userId) {
		Collection<Cart> item=map.values();
		List<Cart> items = new ArrayList<>();
		for(Cart citem:item)
		{
			if(citem.getUserId()==userId) {
				items.add(citem);
			}
		}
		return items;
		
	}
	
	private int generatedId;
	public int generateId() {
		 return generatedId++;		
	}
}
