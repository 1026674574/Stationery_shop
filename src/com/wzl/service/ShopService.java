package com.wzl.service;

import com.wzl.dao.DetailDao;
import com.wzl.dao.OrderDao;
import com.wzl.dao.ShopDao;
import com.wzl.dao.UserDao;
import com.wzl.dao.impl.DetailDaoImpl;
import com.wzl.dao.impl.OrderDaoImpl;
import com.wzl.dao.impl.ShopDaoIml;
import com.wzl.dao.impl.UserDaoImpl;
import com.wzl.model.*;
import com.wzl.web.Page;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ShopService {

    ShopDaoIml shopDaoIml = new ShopDao() ;
    UserDaoImpl userDao = new UserDao();
    OrderDaoImpl orderDao = new OrderDao();
    DetailDaoImpl detailDao = new DetailDao();
    public Page<Shop> getPage(int pageNo)
    {
        return shopDaoIml.getPage(pageNo);
    }

    public Shop getShop(int id) {
        return shopDaoIml.getShop(id);
    }

    public boolean addToCart(int id, ShoppingCart sc) {
        // TODO Auto-generated method stub
        Shop shop = shopDaoIml.getShop(id);

        if (shop != null) {
            sc.addShop(shop);
            return true;
        }
        return false;
    }

    public void cash(ShoppingCart shoppingCart, String username) {

        //1. 更新 shop 数据表相关记录的 number
        ArrayList<ShoppingCartItem> shoppingCartItems = new ArrayList<>(shoppingCart.getItems());
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            shopDaoIml.UpdateNumber(shoppingCartItem);
        }
        int us_id = userDao.getUser(username).getUs_id();
        //2. 更新 user 数据表的 money
        userDao.updateMoney(us_id, shoppingCart.getTotalMoney());

        //3. 向 order 数据表插入一条记录
        Order order = new Order();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        df = new SimpleDateFormat("yyyy-MM-dd");
        order.setOr_date(df.format(new Date()));
        order.setOr_time(date);
        order.setUs_id(us_id);
        orderDao.addOrder(order);

        //4. 向 detail 数据表插入 n 条记录
        for(ShoppingCartItem sci: shoppingCart.getItems()){
            Detail detail = new Detail();
            detail.setSh_id(sci.getShop().getSh_id());
            detail.setDe_number(sci.getQuantity());
            detail.setOr_id(order.getOr_id());
            System.out.println(detail);
            detailDao.addDetail(detail);
        }
        //5. 清空购物车
        shoppingCart.clear();
    }



    public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
        sc.updateItemQuantity(id, quantity);
    }
    public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
        sc.removeItem(id);
    }

    public void clearShoppingCart(ShoppingCart sc) {
        // TODO Auto-generated method stub
        sc.clear();
    }
}
