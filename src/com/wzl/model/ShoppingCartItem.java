package com.wzl.model;



/**
 * 封装了购物车中的商品, 包含对商品的引用以及购物车中该商品的数量
 *
 */
public class ShoppingCartItem {

	private Shop shop;
	private int quantity;
	
	public ShoppingCartItem(Shop shop) {
		this.shop = shop;
		this.quantity = 1;
	}
	
	public Shop getShop() {
		return shop;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 返回该商品在购物车中的钱数
	 * @return
	 */
	public float getItemMoney(){
		return shop.getSh_price() * quantity;
	}
	
	/**
	 * 使商品数量 + 1
	 */
	public void increment(){
		quantity++;
	}
	
	
}
