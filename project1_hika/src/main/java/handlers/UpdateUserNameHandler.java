package handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateUserNameHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        ctx.result("Updating user name ...");
    }
}
