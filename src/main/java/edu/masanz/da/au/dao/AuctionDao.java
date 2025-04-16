package edu.masanz.da.au.dao;

import edu.masanz.da.au.dto.*;
import edu.masanz.da.au.utils.Security;

import java.util.*;

import static edu.masanz.da.au.conf.Ctes.*;
import static edu.masanz.da.au.conf.Ini.*;

/**
 * Clase que simula la capa de acceso a datos. Cuando veamos las interfaces crearemos una interfaz para esta clase.
 * También crearemos una clase que implemente esa interfaz y que se conecte a una base de datos relacional.
 * Y una clase servicio que podrá utilizar cualquiera de las dos implementaciones, la simulada, la real u otra.
 * Por ahora, simplemente es una clase con métodos estáticos que simulan la interacción con una base de datos.
 */
public class   AuctionDao implements IAuctionDao {


    //region Colecciones que simulan la base de datos
    private static Map<String, Usuario> mapaUsuarios;// = new HashMap<>(
//        Map.of(
//            "Silvia", new Usuario("Silvia", "TQkBpYTCZAWcflzwAMQ2htRkiuXbEAj/", "775389b55f59e104a8f604a424abbadc9f3ea807", ROL_USR), // 1234
//            "Mikel", new Usuario("Mikel", "sCdre8FWSp2i15DUVdZzBHPL2WG3VsrG", "9ea1550b68b0bcd9faaa571b48a301c15eba2d41", ROL_USR), // 4567
//            "Javi", new Usuario("Javi", "XOiEiqgzJOpXrUn/dUxsdTfS14bKq7x9", "d8ab68dd5fa53faa563fc0c8a7d50cde99393389", ROL_USR), // 7890
//            "Alba", new Usuario("Alba", "8vrD3MzhsX7uBtBrqKfNI7IIdhhxe9NW", "f0ebcbf10ea3568a7a7129562b47ca167707e091", ROL_USR), // 0987
//            "Aitor", new Usuario("Aitor", "T1rYhR8K3aaOpKyY5uEIDomDEsqrpFLT", "7085165d5fc96588e4f320e2ce1973348d4165ec", ROL_USR), // 7654
//            "Ane", new Usuario("Ane", "/owpEKaM+QH9KEFuU4r1HhKCgY+OPBCu", "0f5babb90b0f0f93726b4ef05a66dbcd291dcf41", ROL_USR), // 4321
//            "Jon", new Usuario("Jon", "HtoTNwHQ6veslVRHC2a9hibPI0aEDtLc", "4f2ca0280bcfd4a9ae93d3ece5af6d944b7afd0e", ROL_USR), // 1357
//            "Amaia", new Usuario("Amaia", "2Fh/iaFA2UNo1YIGp+0HzeDPY4eeMd29", "82f16c31aed08ca8f478f3b3ba69ff9e298f3855", ROL_USR), // 2468
//            "Iker", new Usuario("Iker", "xTucHWKMq3CDdBIPZKXb8f1qFXIaXFHI", "60b1ecd6831f205d8f67b6f6aa1a5bcf575be675", ROL_USR), // 3690
//            "Admin", new Usuario("Admin", "5TjyGSewckePqzlVSrAbnOnhbQGAqfuG", "c11d9fe2c2617aef7929c49dab1b7e1be51b79d9", ROL_ADM) // 1590
//        )
//    );

    private static Map<Long, Item> mapaItems;// = new HashMap<Long, Item>(
//            Map.of(
//                    1L, new Item(1L, "Figura de \"R2-D2\"",
//                            "Figura mecánica activada por voz. Paquete original. Para mayores de 8 años. Incluye poster.",
//                            50, "https://i.postimg.cc/wBh2Hcrw/r2d2.png", "Mikel", STA_ACCEPTED, true),
//                    2L, new Item(2L, "Figura de \"Red Sonja\"",
//                            "Figura Funko Pop de Red Sonja sangrando #158. Para mayores de 14 años. Sin abrir.",
//                            30, "https://i.postimg.cc/2yj20b22/redsonja.png", "Aitor", STA_PENDING, false),
//                    3L, new Item(3L, "Figura de \"Skeletor\"",
//                            "Incluye accesorios dentro del envoltorio original. 14 cm altura. Mattel. Hecho en China, 2021.",
//                            35, "https://i.postimg.cc/YCQYm2x9/skeletor.png", "Alba", STA_ACCEPTED, false),
//                    4L, new Item(4L, "Figura de \"Chichi\"",
//                            "Figura anime, esposa de Goku. Caja de 15 cm x 10 cm x 20 cm. Peso 0.3 kg. Plástico PVC.",
//                            25, "https://i.postimg.cc/qvZVy0BP/chichi.png", "Javi", STA_ACCEPTED, false),
//                    5L, new Item(5L, "Figura de \"La Cosa\"",
//                            "Tamaño aprox: 18 cm altura x 16 cm anchura x 4 cm profundidad. Buen estado. Sin caja. Año 1994.",
//                            20, "https://i.postimg.cc/ZK7DDHk3/cosa.png", "Aitor", STA_ACCEPTED, false)
//            )
//    );

    private static Map<Long, List<Puja>> mapaPujas;// = new HashMap<>(
//            Map.of(
//                    5L, new ArrayList<>(Arrays.asList(
//                            new Puja(5L, "Silvia", 25, "2024-12-31 23:52:01.265"),
//                            new Puja(5L, "Alba", 21, "2025-01-01 08:37:14.323"),
//                            new Puja(5L, "Javi", 22, "2025-01-01 11:32:08.786"),
//                            new Puja(5L, "Mikel", 25, "2025-01-01 20:22:14.324"))),
//                    4L, new ArrayList<>(Arrays.asList(
//                            new Puja(4L, "Alba", 25, "2025-01-01 08:37:14.323"),
//                            new Puja(4L, "Aitor", 27, "2025-01-02 11:32:08.786"),
//                            new Puja(4L, "Mikel", 35, "2025-01-01 20:22:14.324")))
//            )
//    );
    //endregion

    //region Inicialización de la base de datos simulada
    {
        mapaUsuarios = new TreeMap<>();
        for (String lineaUsuario : USUARIOS) {
            String[] campos = lineaUsuario.split(",");
            String nombre = campos[0];
            String sal = campos[1];
            String hashPwSal = campos[2];
            String rol = campos[3];
            Usuario usuario = new Usuario(nombre, sal, hashPwSal, rol);
            mapaUsuarios.put(nombre, usuario);
        }

    }

    {
        mapaItems = new TreeMap<>();
        for (String lineaItem : ITEMS) {
            String[] campos = lineaItem.split(",");
            long id = Long.parseLong(campos[0]);
            String nombre = campos[1];
            String descripcion = campos[2];
            int precioInicio = Integer.parseInt(campos[3]);
            String urlImagen = campos[4];
            String nombreUsuario = campos[5];
            int estado = Integer.parseInt(campos[6]);
            boolean historico = Boolean.parseBoolean(campos[7]);
            Item item = new Item(id, nombre, descripcion, precioInicio, urlImagen, nombreUsuario, estado, historico);
            mapaItems.put(id, item);
        }
    }

    {
        mapaPujas = new HashMap<>();
        for (String lineaPuja : PUJAS) {
            String[] campos = lineaPuja.split(",");
            long idItem = Long.parseLong(campos[0]);
            String nombreUsuario = campos[1];
            int precioPujado = Integer.parseInt(campos[2]);
            String instanteTiempo = campos[3];
            Puja puja = new Puja(idItem, nombreUsuario, precioPujado, instanteTiempo);
            List<Puja> pujas = mapaPujas.get(idItem);
            if (pujas == null) {
                pujas = new ArrayList<>();
                mapaPujas.put(idItem, pujas);
            }
            pujas.add(puja);
        }

    }
    //endregion

    //region Usuarios
    public boolean autenticar(String nombreUsuario, String password) {
//        return password.equals("1234");
        Usuario usuario = mapaUsuarios.get(nombreUsuario);
        if (usuario != null && Security.validateHashedPasswordSalt(password, usuario.getSal(), usuario.getHashPwSal())) {
            return true;
        }
        return false;
    }

    public boolean esAdmin(String nombreUsuario) {
//        return nombreUsuario.equalsIgnoreCase("Admin");
        Usuario usuario = mapaUsuarios.get(nombreUsuario);
        if (usuario != null && usuario.getRol().equals(ROL_ADMIN)) {
            return true;
        }
        return false;
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(mapaUsuarios.values());
    }

    public Usuario obtenerUsuario(String nombre) {
        return mapaUsuarios.get(nombre);
    }

    public boolean crearUsuario(String nombre, String password, boolean esAdmin) {
        if (mapaUsuarios.containsKey(nombre)) {
            return false;
        }
        String salt = Security.generateSalt();
        String hpwsalt = Security.hash(password + salt);
        String rol = esAdmin ? ROL_ADMIN : ROL_USER;
        Usuario usuario = new Usuario(nombre, salt, hpwsalt, rol);
        mapaUsuarios.put(nombre, usuario);
        return true;
    }

    public boolean modificarPasswordUsuario(String nombre, String password) {
        Usuario usuario = mapaUsuarios.get(nombre);
        if (usuario != null) {
            String salt = Security.generateSalt();
            String hpwsalt = Security.hash(password + salt);
            usuario.setSal(salt);
            usuario.setHashPwSal(hpwsalt);
            return true;
        }
        return false;
    }

    public boolean modificarRolUsuario(String nombre, String rol) {
        Usuario usuario = mapaUsuarios.get(nombre);
        if (usuario != null) {
            usuario.setRol(rol);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(String nombre) {
        if (!mapaUsuarios.containsKey(nombre)) {
            return false;
        }
        mapaUsuarios.remove(nombre);
        return true;
    }

    //endregion

    //region Validación de artículos
    public List<Item> obtenerArticulosPendientes() {
        List<Item> items = new ArrayList<>();
        for (Item item : mapaItems.values()) {
            if (item.getEstado() == EST_PENDIENTE) {
                items.add(item);
            }
        }
        return items;
    }

    public boolean validarArticulo(long id, boolean valido) {
        Item item = mapaItems.get(id);
        if (item != null && item.getEstado() == EST_PENDIENTE && !item.isHistorico()) {
            if (valido){
                item.setEstado(EST_ACEPTADO);
            } else {
                item.setEstado(EST_RECHAZADO);
            }
            return true;
        }
        return false;
    }

    public boolean validarTodos() {
        for (Item item : mapaItems.values()) {
            if (item.getEstado() == EST_PENDIENTE && !item.isHistorico()) {
                item.setEstado(EST_ACEPTADO);
            }
        }
        return true;
    }
    //endregion

    //region Gestión de artículos y pujas de administrador
    public List<ItemPujas> obtenerArticulosConPujas() {
        List<ItemPujas> articulosConPujas = new ArrayList<>();
        for (Item item : mapaItems.values()) {
            if (item.getEstado() == EST_ACEPTADO && !item.isHistorico()) {
                ItemPujas itemPujas = new ItemPujas(item);
                long id = item.getId();
                List<Puja> pujas = mapaPujas.get(id);
                itemPujas.setPujas(pujas);
                articulosConPujas.add(itemPujas);
            }
        }
        return articulosConPujas;
    }

    public boolean resetearSubasta() {
        for (Item item : mapaItems.values()) {
            item.setHistorico(true);
        }
        return true;
    }

    public List<PujaItem> obtenerHistoricoGanadores() {
        List<PujaItem> historicoGanadores = new ArrayList<>();
        for (Map.Entry<Long, List<Puja>> entry : mapaPujas.entrySet()) {
            long id = entry.getKey();
            Item item = mapaItems.get(id);
            if (!item.isHistorico()) {
                continue;
            }
            List<Puja> pujas = entry.getValue();
            Puja maxPuja = new Puja();
            for (Puja puja : pujas) {
                if (puja.getPrecioPujado() > maxPuja.getPrecioPujado()) {
                    maxPuja = puja;
                }
            }
            PujaItem pujaItem = new PujaItem(maxPuja);
            pujaItem.setItem(item);
            historicoGanadores.add(pujaItem);
        }
        return historicoGanadores;
    }
    //endregion

    //region Acciones por parte de usuario normal (no admin)

    public Item obtenerArticuloPujable(long idArt) {
        Item item = mapaItems.get(idArt);
        if (item != null && item.getEstado() == EST_ACEPTADO && !item.isHistorico()) {
            return item;
        }
        return null;
    }

    public List<Item> obtenerArticulosPujables() {
        List<Item> items = new ArrayList<>();
        for (Item item : mapaItems.values()) {
            if (item.getEstado() == EST_ACEPTADO && !item.isHistorico()) {
                items.add(item);
            }
        }
        return items;
    }

    public boolean pujarArticulo(long idArt, String nombre, int precio) {
        Item item = mapaItems.get(idArt);
        if (item != null && item.getEstado() == EST_ACEPTADO && !item.isHistorico()) {
            List<Puja> pujas = mapaPujas.get(idArt);
            if (pujas == null) {
                pujas = new ArrayList<>();
                mapaPujas.put(idArt, pujas);
            }
            Puja puja = new Puja(idArt, nombre, precio);
            pujas.add(puja);
            return true;
        }
        return false;
    }

    public List<PujaItem> obtenerPujasVigentesUsuario(String nombreUsuario) {
        List<PujaItem> pujasUsuario = new ArrayList<>();
        for (Map.Entry<Long, List<Puja>> entry : mapaPujas.entrySet()) {
            long id = entry.getKey();
            List<Puja> pujas = entry.getValue();
            for (Puja puja : pujas) {
                if (puja.getNombreUsuario().equals(nombreUsuario)) {
                    Item item = mapaItems.get(id);
                    if (item.isHistorico()) {
                        break;
                    }
                    PujaItem pujaItem = new PujaItem(puja);
                    pujaItem.setItem(item);
                    pujasUsuario.add(pujaItem);
                }
            }
        }
        return pujasUsuario;
    }

    public boolean ofrecerArticulo(Item item) {
        // Obtener el id más alto del mapa de items y sumarle 1
        long id = 0L;
        for (Long key : mapaItems.keySet()) {
            if (key > id) {
                id = key;
            }
        }
        id += 1;
//        long id = itemsMap.keySet().stream().max(Long::compare).orElse(0L) + 1; // Más bonito ;)
        item.setId(id);
        // Añadir el item al mapa de items
        mapaItems.put(id, item);
        return true;
    }

    //endregion
}
