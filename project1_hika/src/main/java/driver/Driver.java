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

        Javalin app = Javalin.create().start();

        UserController userController = new UserController();

        TicketController ticketController = new TicketController();

        app.post("/createuser", userController.createUser);

        //app.get("/users", userController.getAllUsers);

        app.post("/login/{userName}/{password}",userController.loginUser);

        //app.get("/users/{id}", userController.getUserByIdHandler);

        app.post("/createticket", ticketController.createNewTicketHandler);


        //app.put("/updateticket", ticketController.updateTicketHandler);

        //app.get("/getalltickets/{userName}/{password}",ticketController.getAllTicketsBasedOnRole);

        app.get("/allpendingtickets/{userName}/{password}",ticketController.getAllPendingTicketsBasedOnRole);

        app.get("/ticket/{id}", ticketController.getTicketByIdHandler);

        app.put("/updatestatus",ticketController.getStatusUpdate);

    }

}
