package edu.masanz.da.au.controller;

import java.util.*;

import edu.masanz.da.au.dto.Item;
import edu.masanz.da.au.dto.PujaItem;
import edu.masanz.da.au.service.AuctionService;
import io.javalin.http.Context;

import static edu.masanz.da.au.conf.Ctes.EST_PENDIENTE;
import static edu.masanz.da.au.service.AuctionService.obtenerArticulosPujables;
import static edu.masanz.da.au.service.AuctionService.obtenerHistoricoGanadores;

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
        context.render("/templates/loginB.ftl", model);
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
            context.render("/templates/loginB.ftl", model);
        } else {
            context.sessionAttribute("username", username);
            context.sessionAttribute("isAdministrator", isAdministrator);
            context.req().changeSessionId();
            model.put("isAdministrator", isAdministrator);
            context.render("/templates/menuB.ftl", model);
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
        context.render("/templates/menuB.ftl", model);
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

    public static void verHistorialGanadores(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        List<PujaItem> ganadores = obtenerHistoricoGanadores();
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("obtenerHistoricoGanadores", obtenerHistoricoGanadores());
        context.render("/templates/mostrar-ganadores.ftl", model);
    }

    public static void verArticulosPujables(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        List<Item> ganadores = obtenerArticulosPujables();
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("obtenerArticulosPujables", obtenerArticulosPujables());
        context.render("/templates/pujas.ftl", model);
    }

    public static void pujarArticulo(Context context) {
        String username = context.formParam("username");
        String password = context.formParam("password");
        String pujaStr = context.formParam("puja");
        String itemIdStr = context.formParam("itemId");

        boolean authenticated = false;
        boolean isAdmin = false;
        int cantidadPuja = 0;
        long itemId = 0;

        try {
            authenticated = AuctionService.autenticar(username, password);
            isAdmin = AuctionService.esAdmin(username);
            cantidadPuja = Integer.parseInt(pujaStr);
            itemId = Long.parseLong(itemIdStr);
        } catch (Exception e) {
            context.redirect("/error");
            return;
        }

        if (!authenticated) {
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("error", true);
            context.render("/templates/loginB.ftl", model);
            return;
        }

        boolean exito = AuctionService.pujarArticulo(itemId, username, cantidadPuja);

        if (exito) {
            context.redirect("/menu");
        } else {
            context.result("La puja no pudo realizarse. Verifica el monto o el artículo.");
        }
    }
    public static void crearSubasta(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        String nombre = context.formParam("nombre");
        String descripcion = context.formParam("descripcion");
        int precio = Integer.parseInt(Objects.requireNonNull(context.formParam("precio")));
        String imagen = context.formParam("imagen");

        Item item = new Item(nombre, descripcion, precio, imagen, username);

        if (precio <= 0 || nombre == null || descripcion == null || imagen == null || imagen.isEmpty()) {
            context.redirect("/error");
            return;
        }

        if (AuctionService.ofrecerArticulo(item)) {
            context.redirect("/menu");
        } else {
            context.redirect("/error");
        }
    }

    public static void servirOfrecerProducto(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        context.render("/templates/crear-puja.ftl", model);
    }

    

    public static void resetearSubasta(Context context) {
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
        AuctionService.resetearSubasta();
        context.redirect("/menu");
    }


    public static void marcarTodosLosItemsComoHistoricos(Context context) {
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

        AuctionService.resetearSubasta();
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        context.render("/templates/resetear-subasta.ftl", model);
    }

}
