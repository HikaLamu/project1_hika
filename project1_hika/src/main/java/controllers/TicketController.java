package controllers;

import com.google.gson.Gson;
import driver.Driver;
import entities.Ticket;
import entities.User;
import io.javalin.http.Handler;

import java.util.List;


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

    public Handler updateTicketHandler = (ctx) -> {
        String ticketJSON = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(ticketJSON, Ticket.class);
        Ticket updatedTicket = Driver.ticketService.updateTicket(ticket);
        String json = gson.toJson(updatedTicket);
        ctx.result(json);
    };

    public Handler getAllPendingTickets = (ctx) ->{
        List<Ticket> pendingTickets = Driver.ticketService.getAllPendingTickets();
        Gson gson = new Gson();
        String json = gson.toJson(pendingTickets);
        ctx.result(json);
    };

}
