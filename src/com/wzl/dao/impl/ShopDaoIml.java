package com.wzl.dao.impl;

import com.wzl.model.Shop;
import com.wzl.model.ShoppingCartItem;
import com.wzl.web.Page;

import java.util.ArrayList;
import java.util.Collection;

public interface ShopDaoIml {
    /**获取所有商品**/
    ArrayList<Shop> getAllShops(Page<Shop> page);

    /**获得页面所需要的参数**/
    Page<Shop> getPage(int pageNo);
    /**获得总的商品数量**/
    long getTotalComputerNumber();
    /**获得单个商品**/
    Shop getShop(int id);
    /**修改商品数量**/
    void UpdateNumber(ShoppingCartItem shoppingCartItem);
}
