package driver;

import controllers.UserController;
import handlers.*;
import io.javalin.Javalin;
import repositories.TicketDAOPostgres;
import repositories.UserDAOPostgres;
import services.TicketService;
import services.TicketServiceImp;
import services.UserService;
import services.UserServiceImp;

public class Driver {

    public static UserService userService = new UserServiceImp(new UserDAOPostgres());

    public static TicketService ticketService = new TicketServiceImp(new TicketDAOPostgres());

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        HelloHandler helloHandler = new HelloHandler();
          GetUserByIdHandler getUserByIdHandler = new GetUserByIdHandler();
          CreateUserHandler createUserHandler = new CreateUserHandler();
          CreateUserNameHandler createUserNameHandler = new CreateUserNameHandler();
          CreatePasswordHandler createPasswordHandler = new CreatePasswordHandler();
          UpdateUserNameHandler updateUserNameHandler = new UpdateUserNameHandler();
          DeleteUserHandler deleteUserHandler = new DeleteUserHandler();
          UserController userController = new UserController();


        app.get("/hello", helloHandler);

        app.get("/users/{id}", userController.getUserByIdHandler);

        app.post("/users", userController.createUserHandler);

        app.put("/users", userController.updateUserNameHandler);

        app.delete("/users/{id}", userController.deleteUserHandler);


        app.start();

    }

}
