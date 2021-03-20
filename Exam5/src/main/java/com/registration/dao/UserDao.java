package com.registration.dao;

import com.registration.model.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDao {
    public static User getInfoUser(User user){
        String SQL = "insert into users(name, request_date_time, birth_year, gender) values(?, now(), ?, ?)";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getBirth_year());
            stmt.setInt(3, user.getGender());
            stmt.executeUpdate();
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserByName(String name) {
        String SQL = "select * from users where name = ?";
        List<User> List = new ArrayList<>();

        try (Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setRequest_date_time(rs.getTimestamp("request_date_time"));
                    user.setName(rs.getString("name"));
                    user.setBirth_year(rs.getInt("birth_year"));
                    user.setGender(rs.getInt("gender"));
                    List.add(user);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public List<User> getUserByBirthYear(int birthYear) {
        String SQL = "select * from users where birth_year = ?";
        List<User> List = new ArrayList<>();

        try (Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, birthYear);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setRequest_date_time(rs.getTimestamp("request_date_time"));
                    user.setName(rs.getString("name"));
                    user.setBirth_year(rs.getInt("birth_year"));
                    user.setGender(rs.getInt("gender"));
                    List.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public List<User> getUserByGender(int gender) {
        String SQL = "select * from users where gender = ?";
        List<User> List = new ArrayList<>();

        try (Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, gender);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setRequest_date_time(rs.getTimestamp("request_date_time"));
                    user.setName(rs.getString("name"));
                    user.setBirth_year(rs.getInt("birth_year"));
                    user.setGender(rs.getInt("gender"));
                    List.add(user);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        String SQL = "select * from users";
        try(Connection conn = DbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()){
                User user = new User(
                        rs.getString("name"),
                        rs.getInt("birth_year"),
                        rs.getInt("gender")
                );
                userList.add(user);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return userList;
    }

    public static User getUserById(int id){
        String SQL = "select * from users where id = ?";
        User user = null;
        try(Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    user = new User(
                            rs.getString("name"),
                            rs.getInt("birth_year"),
                            rs.getInt("gender")
                    );
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public String deleteUserById(int id){
        String SQL = "delete from users where id = ?";
        try(Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Successfully deleted";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Something Went Wrong";
    }
}

