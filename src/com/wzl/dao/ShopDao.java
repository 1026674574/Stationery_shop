package com.wzl.dao;

import com.wzl.dao.impl.ShopDaoIml;
import com.wzl.db.DBConnection;
import com.wzl.model.Shop;
import com.wzl.model.ShoppingCartItem;
import com.wzl.web.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ShopDao implements ShopDaoIml {
    DBConnection db = new DBConnection();
    @Override
    public ArrayList<Shop> getAllShops(Page<Shop> page) {
        ArrayList<Shop> shops = new ArrayList<>();
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = connection.prepareStatement(" select * from shop LIMIT ?,? ");
            preparedStatement.setInt(1,(page.getPageNo()-1)*page.getPageSize());

            preparedStatement.setInt(2,page.getPageSize());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Shop shop = new Shop();
                shop.setSh_name(resultSet.getString("sh_name"));
                shop.setSh_picpth(resultSet.getString("sh_picpth"));
                shop.setSh_id(resultSet.getInt("sh_id"));
                shop.setSh_price(resultSet.getFloat("sh_price"));
                shop.setSh_text(resultSet.getString("sh_text"));
                shop.setTy_id(resultSet.getInt("ty_id"));
                shops.add(shop);
            }
            return shops;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }



    public Page<Shop> getPage(int pageNo)
    {
        Page<Shop> page = new Page<>(pageNo);
        page.setTotalItemNumber(getTotalComputerNumber());
        ArrayList<Shop> allShops = getAllShops(page);
        page.setList(allShops);
        return page;
    }



    @Override
    public long getTotalComputerNumber() {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select count(sh_id) from shop ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getLong("count(sh_id)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    return 0;
    }

    @Override
    public Shop getShop(int id) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Shop shop = new Shop();
        try {
            preparedStatement=connection.prepareStatement("select * from shop where sh_id = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                shop.setSh_name(resultSet.getString("sh_name"));
                shop.setSh_picpth(resultSet.getString("sh_picpth"));
                shop.setSh_id(resultSet.getInt("sh_id"));
                shop.setSh_price(resultSet.getFloat("sh_price"));
                shop.setSh_text(resultSet.getString("sh_text"));
                shop.setTy_id(resultSet.getInt("ty_id"));
                shop.setSh_number(resultSet.getInt("sh_number"));
                return shop;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shop;
    }

    @Override
    public void UpdateNumber(ShoppingCartItem shoppingCartItem) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement=connection.prepareStatement("update  shop set sh_number= sh_number + ? where sh_id = ?");
            preparedStatement.setInt(1,shoppingCartItem.getQuantity());
            preparedStatement.setInt(2,shoppingCartItem.getShop().getSh_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
