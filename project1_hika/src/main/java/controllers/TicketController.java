package controllers;

import com.google.gson.Gson;
import driver.Driver;
import entities.Ticket;
import io.javalin.http.Handler;

public class TicketController {

    public Handler createNewTicketHandler = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);
        Ticket newTicket = Driver.ticketService.createNewTicket(ticket);
        String ticketJson = gson.toJson(newTicket);
        ctx.status(201);
        ctx.result(ticketJson);
    };

    public Handler getTicketByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Ticket ticket = Driver.ticketService.getTicketById(id);
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
    };

}
