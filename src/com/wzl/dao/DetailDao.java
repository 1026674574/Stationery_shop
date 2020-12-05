package com.wzl.dao;

import com.wzl.dao.impl.DetailDaoImpl;
import com.wzl.db.DBConnection;
import com.wzl.model.Detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class DetailDao implements DetailDaoImpl {
    DBConnection db = new DBConnection();
    @Override
    public void addDetail(Detail detail) {
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(" insert into detail (de_number, or_id, sh_id) values (?,?,?)");
            preparedStatement.setInt(1,detail.getDe_number());
            preparedStatement.setInt(2,detail.getOr_id());
            preparedStatement.setInt(3,detail.getSh_id());
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
