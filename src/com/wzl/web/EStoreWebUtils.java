package com.wzl.web;



import com.wzl.model.ShoppingCart;
import com.wzl.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EStoreWebUtils {
	/**
	 * 获取购物车对象: 从 session 中获取, 若 session 中没有该对象.
	 * 则创建一个新的购物车对象, 放入到 session 中.
	 * 若有, 则直接返回. 
	 * @param request
	 * @return
	 */
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if(sc == null){
			sc = new ShoppingCart();
			session.setAttribute("ShoppingCart", sc);
		}
		
		return sc;
	}

	/**
	 * 获取用户对象: 从 session 中获取, 若 session 中没有该对象.
	 * 则无则返回空,
	 * 若有, 则直接返回.
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User) session.getAttribute("user");
	}
	/**
	 * session中清除user对象
	 * @param request
	 * @return
	 */
	public static void Exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		//销毁session对象
		session.invalidate();
	}
}
