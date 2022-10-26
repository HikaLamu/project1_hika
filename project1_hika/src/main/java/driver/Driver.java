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

        app.post("/register", userController.createUser);

        app.put("/login/{userName}/{password}",userController.loginUser);

        app.get("/users/{id}", userController.getUserByIdHandler);

        app.put("/update", userController.updateUserHandler);

        app.delete("/delete/{id}", userController.deleteUserHandler);

        app.get("/users", userController.getAllUsers);

        app.post("/ticket", ticketController.createNewTicketHandler);

        app.get("/ticket/{id}", ticketController.getTicketByIdHandler);

        app.put("/updateticket", ticketController.updateTicketHandler);



        app.start(8070);

    }

}
