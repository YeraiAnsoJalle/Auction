package edu.masanz.da.au.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.masanz.da.au.dto.PujaItem;
import edu.masanz.da.au.service.AuctionService;
import io.javalin.http.Context;

public class MainController {

    // app.get("/", MainController::iniciar);
    // app.get("/login", MainController::iniciar);
    // app.get("/error", MainController::iniciar);
    // app.get("/exit", MainController::iniciar);
    public static void iniciar(Context context) {
        context.req().getSession().invalidate();
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("error", false);
        context.render("/templates/login.ftl", model);
    }

    // app.post("/autenticar", MainController::autenticar);
    public static void autenticar(Context context) {
        String username = null;
        String password = null;
        boolean authenticated = false;
        boolean isAdministrator = false;
        try {
            username = context.formParam("username").trim();
            password = context.formParam("password");
            authenticated = AuctionService.autenticar(username, password);
            if (authenticated) {
                isAdministrator = AuctionService.esAdmin(username);
            }
        } catch (Exception e) {
        }
        Map<String, Object> model = new HashMap<>();
        if (!authenticated) {
            model.put("username", username);
            model.put("error", true);
            context.render("/templates/login.ftl", model);
        } else {
            context.sessionAttribute("username", username);
            context.sessionAttribute("isAdministrator", isAdministrator);
            context.req().changeSessionId();
            context.redirect("/menu");
        }
    }

    //app.get("/menu", MainController::mostrarMenu);
    public static void mostrarMenu(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        Map<String, Object> model = new HashMap<>();
        model.put("isAdministrator", isAdministrator);
        context.render("/templates/menu.ftl", model);
    }

    //app.post("/admin/user-management", MainController::gestionarUsuarios);
    public static void gestionarUsuarios(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        if (!isAdministrator) {
            context.redirect("/error");
            return;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        context.render("/templates/todo.ftl", model);
    }

    public static void verHistorialPujas(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        List<PujaItem> pujasVigentesUsuario = AuctionService.obtenerPujasVigentesUsuario(username);
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("pujasVigentesUsuario", pujasVigentesUsuario);
        context.render("/templates/historialpujas-user.ftl", model);
    }
}
