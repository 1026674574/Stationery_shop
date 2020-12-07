package com.wzl.dao;

import com.wzl.dao.impl.UserDaoImpl;
import com.wzl.db.DBConnection;
import com.wzl.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            preparedStatement = connection.prepareStatement("select * from user where us_truename = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUs_id(resultSet.getInt(1));
                user.setUs_truename(resultSet.getString(7));
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
}


