package com.company.main;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoInter userDaoInter=Context.instanceUserDao();
       List<User> list= userDaoInter.getAll();
        System.out.println(list);
    }

}
