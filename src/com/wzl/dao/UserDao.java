package com.wzl.dao;

import com.wzl.dao.impl.UserDaoImpl;
import com.wzl.db.DBConnection;
import com.wzl.model.Shop;
import com.wzl.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao implements UserDaoImpl {
    DBConnection db = new DBConnection();

    @Override
    public User login(User user) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int l = 3;
            preparedStatement = connection.prepareStatement("select * from user where us_name = ? and us_password = ?");
            preparedStatement.setString(1, user.getUs_name());
            preparedStatement.setString(2, user.getUs_password());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUs_id(resultSet.getInt(1));
                user.setUs_password(resultSet.getString("us_phone"));
                user.setUs_truename(resultSet.getString("us_truename"));
                user.setUs_address(resultSet.getString("us_address"));
                user.setUs_money(resultSet.getFloat("us_money"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public User getUser(String username) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from user where us_name = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUs_id(resultSet.getInt(1));
                user.setUs_password(resultSet.getString("us_phone"));
                user.setUs_truename(resultSet.getString("us_truename"));
                user.setUs_address(resultSet.getString("us_address"));
                user.setUs_money(resultSet.getFloat("us_money"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateMoney(int us_id, float totalMoney) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update user set us_money = us_money - ? where us_id = ?");
            preparedStatement.setFloat(1,totalMoney);
            preparedStatement.setInt(2,us_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registered(User user) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("insert into `文具店`.`user` (us_name, us_truename,us_password,us_address,us_phone,us_money) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getUs_name());
            preparedStatement.setString(2,user.getUs_truename());
            preparedStatement.setString(3,user.getUs_password());
            preparedStatement.setString(4,user.getUs_address());
            preparedStatement.setString(5,user.getUs_phone());
            preparedStatement.setFloat(6,user.getUs_money());
            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//            if(resultSet.next()){
//                user.setOr_id((int)resultSet.getLong(1));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Like(int id, int us_id) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("insert into `文具店`.`like` (sh_id,us_id) values (?, ?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,us_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Shop> getLike(int us_id) {
        ArrayList<Shop> shops = new ArrayList<>();
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from `like`,shop WHERE `文具店`.`like`.sh_id = shop.sh_id  AND us_id = ?");
            preparedStatement.setInt(1,us_id);
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
        }
        return shops;

    }
}


