package handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateUserNameHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        ctx.result("checking user name handler creation");
    }
}
