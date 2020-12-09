package com.wzl.dao;

import com.wzl.dao.impl.UserDaoImpl;
import com.wzl.db.DBConnection;
import com.wzl.model.User;

import java.sql.*;

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
}


