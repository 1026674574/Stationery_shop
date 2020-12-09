package com.wzl.dao;

import com.wzl.dao.impl.TypeDaoIml;
import com.wzl.db.DBConnection;
import com.wzl.model.Shop;
import com.wzl.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDao implements TypeDaoIml {
    DBConnection db = new DBConnection();
    @Override
    public ArrayList<Type> getTypes() {
        ArrayList<Type> types = new ArrayList<>();
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from type");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Type type = new Type();
                type.setTy_id(resultSet.getInt(1));
                type.setTy_name(resultSet.getString(2));
                types.add(type);
            }
            return types;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
