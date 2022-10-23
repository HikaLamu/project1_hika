package handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateUserHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        ctx.result("Checking user handler creation.");
    }
}
