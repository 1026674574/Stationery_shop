package com.wzl.dao.impl;

import com.wzl.model.Shop;
import com.wzl.web.Page;

import java.util.ArrayList;

public interface ShopDaoIml {
    //获取所有商品
    ArrayList<Shop> getAllShops(Page<Shop> page);
    Page<Shop> getPage(int pageNo);
    long getTotalComputerNumber();
}
