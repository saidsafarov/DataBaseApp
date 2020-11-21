package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    private User getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        int nationality_id = resultSet.getInt("nationality_id");
        int birthplace_id = resultSet.getInt("birthplace_id");
        String nationality = resultSet.getString("nationality");
        String birthplace = resultSet.getString("birthplace");
        Date birthDate = resultSet.getDate("birthdate");
        Country nationality1 = new Country(nationality_id, null, nationality);
        Country birthplace1 = new Country(birthplace_id, birthplace, nationality);
        List<UserSkill> list = new ArrayList<>();
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT" +
                    "\tu.*," +
                    "\tn.nationality," +
                    "\tn.name AS birthplace " +
                    "FROM" +
                    "\tUSER u" +
                    "\tLEFT JOIN country n ON u.nationality_id = n.id" +
                    "\tLEFT JOIN country  ON u.birthplace_id = n.id");
            ResultSet resultset = statement.getResultSet();
            while (resultset.next()) {
                User u = getUser(resultset);
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int UserId) {
        EntityManagerFactory entityManagerFactory=
                Persistence.createEntityManagerFactory("");
        return null;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into user (name,surname,phone,email)  values(?,?,?,?)");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getPhone());
            statement.setString(4, u.getEmail());
            return statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateUser(User u) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "update user set name=?,surname=?,phone=?,email=? where id=?");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getPhone());
            statement.setString(4, u.getEmail());
            statement.setInt(5, u.getId());
            return statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            return statement.execute("delete  user where =" + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int UserId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");
        return null;
    }

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
