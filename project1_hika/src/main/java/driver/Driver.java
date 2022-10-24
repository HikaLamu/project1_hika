package driver;

import controllers.TicketController;
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

          CreateNewTicketHandler createNewTicketHandler = new CreateNewTicketHandler();
          GetTicketAmountHandler getTicketAmountHandler = new GetTicketAmountHandler();
          GetTicketByIdHandler getTicketByIdHandler = new GetTicketByIdHandler();
          GetTicketDescriptionHandler getTicketDescriptionHandler = new GetTicketDescriptionHandler();
        TicketController ticketController = new TicketController();


        app.get("/hello", helloHandler);

        app.get("/users/{id}", getUserByIdHandler);

        app.post("/users", createUserHandler);

        app.put("/users", updateUserNameHandler);

        app.delete("/users/{id}", deleteUserHandler);


        app.start();

    }

}
