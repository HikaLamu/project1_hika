package controllers;

import com.google.gson.Gson;
import driver.Driver;
import entities.User;
import io.javalin.http.Handler;

public class UserController {

    public Handler createUserHandler = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        User registeredUser = Driver.userService.createUser(user);
        String userJson = gson.toJson(registeredUser);
        ctx.status(201);
        ctx.result(userJson);
    };


    public Handler getUserByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = Driver.userService.getUserById(id);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        ctx.result(json);
    };

    public Handler updateUserHandler = (ctx) -> {
        String userJSON = ctx.body();
        Gson gson = new Gson();
        User user = gson.fromJson(userJSON, User.class);
        User updatedUser = Driver.userService.updateUser(user);
        String json = gson.toJson(updatedUser);
        ctx.result(json);
    };

    public Handler deleteUserHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = Driver.userService.deleteUserById(id);
        if(result){
            ctx.status(204);
        }
        else{
            ctx.status(400);
            ctx.result("Could not process your delete request");
        }
    };
}