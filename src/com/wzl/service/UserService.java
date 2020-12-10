package com.wzl.service;

import com.wzl.dao.UserDao;
import com.wzl.dao.impl.UserDaoImpl;
import com.wzl.model.Shop;
import com.wzl.model.User;

import java.util.ArrayList;

public class UserService {

    UserDaoImpl userDao = new UserDao();


   public User login(User user){
       return userDao.login(user);
   }

   public void registered(User user){
       userDao.registered(user);
   }

    public void Like(int id, int us_id) {
        userDao.Like(id,us_id);
    }

    public ArrayList<Shop> getLike(int us_id) {
        return userDao.getLike(us_id);

    }
}
