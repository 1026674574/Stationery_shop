package com.wzl.dao.impl;

import com.wzl.model.Shop;
import com.wzl.model.User;

import java.util.ArrayList;

public interface UserDaoImpl  {

User login(User user);

    User getUser(String username);

    void updateMoney(int us_id, float totalMoney);

    void registered(User user);

    void Like(int id, int us_id);

    ArrayList<Shop> getLike(int us_id);
}
