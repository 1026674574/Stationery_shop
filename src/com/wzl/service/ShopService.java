package com.wzl.service;

import com.wzl.dao.ShopDao;
import com.wzl.dao.impl.ShopDaoIml;
import com.wzl.model.Shop;
import com.wzl.model.ShoppingCart;
import com.wzl.web.Page;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class ShopService {

    ShopDaoIml shopDaoIml = new ShopDao() ;


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

    //业务方法.
//    public void cash(ShoppingCart shoppingCart, String username,
//                     String accountId) {
//
//        //1. 更新 mycomputers 数据表相关记录的 salesamount 和 storenumber
//        shopDaoIml.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
//
//        //2. 更新 account 数据表的 balance
//        accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
//
//        //3. 向 trade 数据表插入一条记录
//        Trade trade = new Trade();
//        trade.setTradeTime(new Date(new java.util.Date().getTime()));
//        trade.setUserId(userDAO.getUser(username).getUserId());
//        tradeDAO.insert(trade);
//
//        //4. 向 tradeitem 数据表插入 n 条记录
//        Collection<TradeItem> items = new ArrayList<>();
//        for(ShoppingCartItem sci: shoppingCart.getItems()){
//            TradeItem tradeItem = new TradeItem();
//
//            tradeItem.setComputerId(sci.getComputer().getId());
//            tradeItem.setQuantity(sci.getQuantity());
//            tradeItem.setTradeId(trade.getTradeId());
//
//            items.add(tradeItem);
//        }
//        tradeItemDAO.batchSave(items);
//
//        //5. 清空购物车
//        shoppingCart.clear();
//    }



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
