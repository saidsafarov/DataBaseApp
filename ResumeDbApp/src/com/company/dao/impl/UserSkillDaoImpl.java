package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {
    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int UserId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");
        return null;
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT " +
                    "u.*," +
                    "us.skill_id," +
                    "s.`name` as skill_name, " +
                    "us.power " +
                    "  FROM " +
                    " user_skill us " +
                    " LEFT JOIN user u ON us.user_id=u.id " +
                    " LEFT JOIN skill s ON us.skill_id=s.id " +
                    " where us.user_id=?");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultset = statement.getResultSet();
            while (resultset.next()) {
                UserSkill u = getUserSkill(resultset);
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
