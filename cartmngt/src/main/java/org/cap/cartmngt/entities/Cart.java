package org.cap.cartmngt.entities;


public class Cart {

	private int id;
	private int productId;
	private int userId;

	public Cart(int id, int productId, int userId){
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
	}
	
	public Cart() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
