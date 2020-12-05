package com.wzl.dao;

import com.wzl.dao.impl.OrderDaoImpl;
import com.wzl.db.DBConnection;
import com.wzl.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao implements OrderDaoImpl {
    DBConnection db = new DBConnection();
    @Override
    public void addOrder(Order order) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(" insert into `文具店`.`order` (or_time, or_date,us_id, or_state) values (?, ?,?, 0 )");
            preparedStatement.setString(1,order.getOr_time());
            preparedStatement.setString(2,order.getOr_date());
            preparedStatement.setInt(3,order.getUs_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                order.setOr_id((int)resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
