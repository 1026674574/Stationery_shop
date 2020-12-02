package com.wzl.service;

import com.wzl.dao.ShopDao;
import com.wzl.dao.impl.ShopDaoIml;
import com.wzl.model.Shop;

import java.util.ArrayList;

public class ShopService {

    ShopDaoIml shopDaoIml = new ShopDao() ;


    public ArrayList<Shop> getAllShops()
    {
        return shopDaoIml.getAllShops();
    }
}
