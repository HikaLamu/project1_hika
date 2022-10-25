package driver;

import controllers.TicketController;
import controllers.UserController;
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


          UserController userController = new UserController();

          TicketController ticketController = new TicketController();


        app.get("/users/{id}", userController.getUserByIdHandler);

        app.post("/users", userController.createUser);

        app.put("/users", userController.updateUserHandler);

        app.delete("/users/{id}", userController.deleteUserHandler);

        app.get("/ticket/{id}", ticketController.getTicketByIdHandler);

        app.post("/ticket", ticketController.createNewTicketHandler);


        app.start(8070);

    }

}
