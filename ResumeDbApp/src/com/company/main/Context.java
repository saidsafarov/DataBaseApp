package com.company.main;

import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }
}
