package com.registration.service;

import com.registration.model.User;
import com.registration.dao.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import java.time.YearMonth;

@Path("user")
public class Service {

    private UserDao userDao = new UserDao();

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String createRequest(User user) {
        UserDao.getInfoUser(user);
        if (user.getBirth_year() < 2000) {
            return "Ошибка. Вы родились позже 2000 года";
        }
        else {
            int year = YearMonth.now().getYear();
            String msg = String.format(" %s вы родились в %s году, вам %s лет", user.getName(), user.getBirth_year(), (year - user.getBirth_year()));
            return user.getGender() == 1? "Уважаемый" + msg : "Уважаемая" + msg;
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") int userId){
        return userDao.getUserById(userId);
    }

    @GET
    @Path("byName/{Name}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsersByName(@PathParam("Name") String name) {
        return userDao.getUserByName(name);
    }

    @GET
    @Path("byGender/{Gender}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsersByGender(@PathParam("Gender") int gender) {
        return userDao.getUserByGender(gender);
    }

    @GET
    @Path("byBirthYear/{BirthYear}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsersByBirthYear(@PathParam("BirthYear") int birthYear) {
        return userDao.getUserByBirthYear(birthYear);
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("userId") int userId){
        return userDao.deleteUserById(userId);
    }
}