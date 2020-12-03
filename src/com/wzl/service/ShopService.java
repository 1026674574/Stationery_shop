package com.wzl.service;

import com.wzl.dao.ShopDao;
import com.wzl.dao.impl.ShopDaoIml;
import com.wzl.model.Shop;
import com.wzl.web.Page;

import java.util.ArrayList;

public class ShopService {

    ShopDaoIml shopDaoIml = new ShopDao() ;


    public Page<Shop> getPage(int pageNo)
    {
        return shopDaoIml.getPage(pageNo);
    }
}
