package com.wzl.dao.impl;

import com.wzl.model.User;

public interface UserDaoImpl  {

User login(User user);

    User getUser(String username);

    void updateMoney(int us_id, float totalMoney);

    void registered(User user);
}
