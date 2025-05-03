package edu.masanz.da.au.dao;

import edu.masanz.da.au.conf.Ctes;
import edu.masanz.da.au.dto.Item;
import edu.masanz.da.au.dto.ItemPujas;
import edu.masanz.da.au.dto.Puja;
import edu.masanz.da.au.dto.PujaItem;
import edu.masanz.da.au.dto.Usuario;
import edu.masanz.da.au.utils.Security;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.masanz.da.au.conf.Ctes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class AuctionDaoTest {

    private AuctionDao auctionDao;

    @BeforeEach
    void resetear() {
        auctionDao = new AuctionDao();
    }

    // Pruebas para autenticar(String, String)
    @Test
    void autenticar_UsuarioExistenteContrasenaCorrecta_DevuelveTrue() {
        String nombreUsuario = "Admin";
        String password = "1590";

        assertTrue(auctionDao.autenticar(nombreUsuario, password));
    }

    @Test
    void autenticar_UsuarioExistenteContrasenaIncorrecta_DevuelveFalse() {
        String nombreUsuario = "Admin";
        String password = "contraseñaIncorrecta";

        assertFalse(auctionDao.autenticar(nombreUsuario, password));
    }

    @Test
    void autenticar_UsuarioNoExistente_DevuelveFalse() {
        String nombreUsuario = "UsuarioInexistente";
        String password = "cualquierContrasena";

        assertFalse(auctionDao.autenticar(nombreUsuario, password));
    }

    @Test
    void autenticar_UsuarioNulo_DevuelveFalse() {
        assertFalse(auctionDao.autenticar(null, "contraseña"));
    }

    // Pruebas para esAdmin(String)
    @Test
    void esAdmin_UsuarioEsAdmin_DevuelveTrue() {
        String nombreUsuario = "Admin";
        assertTrue(auctionDao.esAdmin(nombreUsuario));
    }

    @Test
    void esAdmin_UsuarioNoEsAdmin_DevuelveFalse() {
        // Asumiendo que "Silvia" no es admin
        String nombreUsuario = "Silvia";
        assertFalse(auctionDao.esAdmin(nombreUsuario));
    }

    @Test
    void esAdmin_UsuarioNoExistente_DevuelveFalse() {
        String nombreUsuario = "UsuarioInexistente";
        assertFalse(auctionDao.esAdmin(nombreUsuario));
    }

    @Test
    void esAdmin_UsuarioNulo_DevuelveFalse() {
        assertFalse(auctionDao.esAdmin(null));
    }

    // Pruebas para obtenerUsuarios()
    @Test
    void obtenerUsuarios_DevuelveListaNoVacia() {
        List<Usuario> usuarios = auctionDao.obtenerUsuarios();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void obtenerUsuarios_ContieneUsuariosConocidos() {
        List<Usuario> usuarios = auctionDao.obtenerUsuarios();
        boolean contieneAdmin = usuarios.stream()
                .anyMatch(u -> "Admin".equals(u.getNombre()));
        assertTrue(contieneAdmin);
    }

    // Pruebas para obtenerUsuario(String)
    @Test
    void obtenerUsuario_UsuarioExistente_DevuelveUsuario() {
        String nombreUsuario = "Admin";
        Usuario usuario = auctionDao.obtenerUsuario(nombreUsuario);
        assertNotNull(usuario);
        assertEquals(nombreUsuario, usuario.getNombre());
    }

    @Test
    void obtenerUsuario_UsuarioNoExistente_DevuelveNull() {
        String nombreUsuario = "UsuarioInexistente";
        Usuario usuario = auctionDao.obtenerUsuario(nombreUsuario);
        assertNull(usuario);
    }

    @Test
    void obtenerUsuario_NombreNulo_DevuelveNull() {
        Usuario usuario = auctionDao.obtenerUsuario(null);
        assertNull(usuario);
    }

    // Pruebas para crearUsuario(String, String, boolean)
    @Test
    void crearUsuario_NuevoUsuario_DevuelveTrue() {
        String nombre = "NuevoUsuario";
        String password = "nuevaContrasena";
        boolean esAdmin = false;

        assertTrue(auctionDao.crearUsuario(nombre, password, esAdmin));

        // Verificar que el usuario fue creado
        Usuario usuario = auctionDao.obtenerUsuario(nombre);
        assertNotNull(usuario);
        assertEquals(esAdmin ? ROL_ADMIN : ROL_USER, usuario.getRol());
    }

    @Test
    void crearUsuario_UsuarioExistente_DevuelveFalse() {
        String nombre = "Admin"; // Usuario que ya existe
        String password = "nuevaContrasena";
        boolean esAdmin = false;

        assertFalse(auctionDao.crearUsuario(nombre, password, esAdmin));
    }

    @Test
    void crearUsuario_NombreNulo_DevuelveFalse() {
        assertFalse(auctionDao.crearUsuario(null, "password", false));
    }

    // Pruebas para modificarPasswordUsuario(String, String)
    @Test
    void modificarPasswordUsuario_UsuarioExistente_DevuelveTrueYCambiaPassword() {
        String nombre = "Admin";
        String nuevaPassword = "nuevaContrasena";

        assertTrue(auctionDao.modificarPasswordUsuario(nombre, nuevaPassword));

        //Ver si ha sido cambiada correctamente
        assertTrue(auctionDao.autenticar(nombre, nuevaPassword));
    }

    @Test
    void modificarPasswordUsuario_UsuarioNoExistente_DevuelveFalse() {
        String nombre = "UsuarioInexistente";
        String nuevaPassword = "nuevaContrasena";

        assertFalse(auctionDao.modificarPasswordUsuario(nombre, nuevaPassword));
    }

    @Test
    void modificarPasswordUsuario_PasswordNula_DevuelveFalse() {
        String nombre = "Admin";
        assertFalse(auctionDao.modificarPasswordUsuario(nombre, null));
    }

    // Pruebas para modificarRolUsuario(String, String)
    @Test
    void modificarRolUsuario_UsuarioExistente_DevuelveTrueYCambiaRol() {
        String nombre = "Silvia";
        String nuevoRol = ROL_ADMIN;

        assertTrue(auctionDao.modificarRolUsuario(nombre, nuevoRol));

        Usuario usuario = auctionDao.obtenerUsuario(nombre);
        assertEquals(nuevoRol, usuario.getRol());
    }

    @Test
    void modificarRolUsuario_UsuarioNoExistente_DevuelveFalse() {
        String nombre = "UsuarioInexistente";
        String nuevoRol = ROL_ADMIN;

        assertFalse(auctionDao.modificarRolUsuario(nombre, nuevoRol));
    }

    @Test
    void modificarRolUsuario_RolInvalido_DevuelveFalse() {
        String nombre = "Silvia";
        String rolInvalido = "RolInvalido";

        assertFalse(auctionDao.modificarRolUsuario(nombre, rolInvalido));
    }

    // Pruebas para eliminarUsuario(String)
    @Test
    void eliminarUsuario_UsuarioExistente_DevuelveTrueYUsuarioEliminado() {
        // Primero creamos un usuario para eliminarlo
        String nombre = "UsuarioParaEliminar";
        auctionDao.crearUsuario(nombre, "password", false);

        assertTrue(auctionDao.eliminarUsuario(nombre));
        assertNull(auctionDao.obtenerUsuario(nombre));
    }

    @Test
    void eliminarUsuario_UsuarioNoExistente_DevuelveFalse() {
        String nombre = "UsuarioInexistente";
        assertFalse(auctionDao.eliminarUsuario(nombre));
    }

    @Test
    void eliminarUsuario_NombreNulo_DevuelveFalse() {
        assertFalse(auctionDao.eliminarUsuario(null));
    }

    // Pruebas para obtenerArticulosPendientes()
    @Test
    void obtenerArticulosPendientes_DevuelveSoloArticulosPendientes() {
        List<Item> articulosPendientes = auctionDao.obtenerArticulosPendientes();

        assertNotNull(articulosPendientes);
        for (Item item : articulosPendientes) {
            assertEquals(EST_PENDIENTE, item.getEstado());
            assertFalse(item.isHistorico());
        }
    }

    @Test
    void obtenerArticulosPendientes_NoIncluyeArticulosAceptados() {
        List<Item> articulosPendientes = auctionDao.obtenerArticulosPendientes();

        for (Item item : articulosPendientes) {
            assertNotEquals(EST_ACEPTADO, item.getEstado());
        }
    }

    @Test
    void validarArticulo_ArticuloPendienteAceptado_CambiaEstado() {
        // Primero encontramos un artículo pendiente
        List<Item> pendientes = auctionDao.obtenerArticulosPendientes();
        if (!pendientes.isEmpty()) {
            long id = pendientes.get(0).getId();

            assertTrue(auctionDao.validarArticulo(id, true));

            Item item = auctionDao.mapaItems.get(id);
            assertEquals(EST_ACEPTADO, item.getEstado());
        }
    }

    @Test
    void validarArticulo_ArticuloPendienteRechazado_CambiaEstado() {
        List<Item> pendientes = auctionDao.obtenerArticulosPendientes();
        if (!pendientes.isEmpty()) {
            long id = pendientes.get(0).getId();

            assertTrue(auctionDao.validarArticulo(id, false));

            Item item = auctionDao.mapaItems.get(id);
            assertEquals(EST_RECHAZADO, item.getEstado());
        }
    }

    @Test
    void validarArticulo_ArticuloNoPendiente_DevuelveFalse() {
        // Buscamos un artículo que no sea pendiente
        for (Item item : auctionDao.mapaItems.values()) {
            if (item.getEstado() != EST_PENDIENTE) {
                assertFalse(auctionDao.validarArticulo(item.getId(), true));
                return;
            }
        }
    }

    @Test
    void validarArticulo_ArticuloHistorico_DevuelveFalse() {
        // Marcamos un artículo como histórico
        for (Item item : auctionDao.mapaItems.values()) {
            item.setHistorico(true);
            assertFalse(auctionDao.validarArticulo(item.getId(), true));
            item.setHistorico(false); // Restauramos
            break;
        }
    }

    @Test
    void validarArticulo_IdNoExistente_DevuelveFalse() {
        long idInexistente = -1L;
        assertFalse(auctionDao.validarArticulo(idInexistente, true));
    }

// TODO: Test unitarios de miembro 2 del grupo (Eneko)

    @Test
    void validarTodos_cambiaPendientesANoHistoricos() {
        AuctionDao gestor = new AuctionDao();

        Item pendiente = new Item();
        pendiente.setEstado(Ctes.EST_PENDIENTE);
        pendiente.setHistorico(false);

        Item aceptado = new Item();
        aceptado.setEstado(Ctes.EST_ACEPTADO);
        aceptado.setHistorico(false);

        gestor.mapaItems.put(1L, pendiente);
        gestor.mapaItems.put(2L, aceptado);

        boolean result = gestor.validarTodos();

        assertTrue(result);
        assertEquals(Ctes.EST_ACEPTADO, gestor.mapaItems.get(1L).getEstado());
    }

    @Test
    void obtenerArticulosConPujas_devuelveSoloAceptadosYNoHistoricosConPujas() {
        AuctionDao gestor = new AuctionDao();

        Item item = new Item();
        item.setId(1L);
        item.setEstado(Ctes.EST_ACEPTADO);
        item.setHistorico(false);

        Puja puja = new Puja(1L, "Usuario1", 100);

        gestor.mapaItems.put(1L, item);
        gestor.mapaPujas.put(1L, new ArrayList<>(List.of(puja)));

        List<ItemPujas> resultado = gestor.obtenerArticulosConPujas();

        assertEquals(1, resultado.size());
        assertEquals(item, resultado.get(0).getItem());
        assertEquals(1, resultado.get(0).getPujas().size());
    }

    @Test
    void resetearSubasta_marcaTodosLosItemsComoHistoricos() {
        AuctionDao gestor = new AuctionDao();

        Item item1 = new Item();
        item1.setHistorico(false);
        Item item2 = new Item();
        item2.setHistorico(false);

        gestor.mapaItems.put(1L, item1);
        gestor.mapaItems.put(2L, item2);

        boolean resultado = gestor.resetearSubasta();

        assertTrue(resultado);
        assertTrue(item1.isHistorico());
        assertTrue(item2.isHistorico());
    }

    @Test
    void obtenerHistoricoGanadores_devuelveMayorPujaDeCadaItemHistorico() {
        AuctionDao gestor = new AuctionDao();

        Item item = new Item();
        item.setId(1L);
        item.setHistorico(true);

        Puja puja1 = new Puja(1L, "User1", 100);
        Puja puja2 = new Puja(1L, "User2", 150);

        gestor.mapaItems.put(1L, item);
        gestor.mapaPujas.put(1L, List.of(puja1, puja2));

        List<PujaItem> resultado = gestor.obtenerHistoricoGanadores();

        assertEquals(1, resultado.size());
        assertEquals("User2", resultado.get(0).getPuja().getNombreUsuario());
    }

    @Test
    void obtenerArticuloPujable_devuelveItemValido() {
        AuctionDao gestor = new AuctionDao();

        Item item = new Item();
        item.setId(1L);
        item.setEstado(Ctes.EST_ACEPTADO);
        item.setHistorico(false);

        gestor.mapaItems.put(1L, item);

        Item result = gestor.obtenerArticuloPujable(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void obtenerArticulosPujables_devuelveListaDeAceptadosYNoHistoricos() {
        AuctionDao gestor = new AuctionDao();

        Item item1 = new Item();
        item1.setEstado(Ctes.EST_ACEPTADO);
        item1.setHistorico(false);

        Item item2 = new Item();
        item2.setEstado(Ctes.EST_PENDIENTE);
        item2.setHistorico(false);

        gestor.mapaItems.put(1L, item1);
        gestor.mapaItems.put(2L, item2);

        List<Item> result = gestor.obtenerArticulosPujables();

        assertEquals(1, result.size());
        assertEquals(item1, result.get(0));
    }

    @Test
    void pujarArticulo_creaNuevaPujaEnArticuloValido() {
        AuctionDao gestor = new AuctionDao();

        Item item = new Item();
        item.setId(1L);
        item.setEstado(Ctes.EST_ACEPTADO);
        item.setHistorico(false);

        gestor.mapaItems.put(1L, item);

        boolean resultado = gestor.pujarArticulo(1L, "Postor", 300);

        assertTrue(resultado);
        assertEquals(1, gestor.mapaPujas.get(1L).size());
        assertEquals("Postor", gestor.mapaPujas.get(1L).get(0).getNombreUsuario());
    }

    @Test
    void obtenerPujasVigentesUsuario_devuelveSoloPujasDeUsuarioYNoHistoricos() {
        AuctionDao gestor = new AuctionDao();

        Item item = new Item();
        item.setId(1L);
        item.setHistorico(false);

        Puja puja = new Puja(1L, "Usuario1", 200);

        gestor.mapaItems.put(1L, item);
        gestor.mapaPujas.put(1L, List.of(puja));

        List<PujaItem> resultado = gestor.obtenerPujasVigentesUsuario("Usuario1");

        assertEquals(1, resultado.size());
        assertEquals("Usuario1", resultado.get(0).getPuja().getNombreUsuario());
    }

    @Test
    void ofrecerArticulo_asignaIdIncrementalYGuardaItem() {
        AuctionDao gestor = new AuctionDao();

        Item item1 = new Item();
        item1.setId(1L);
        gestor.mapaItems.put(1L, item1);

        Item nuevo = new Item();
        boolean resultado = gestor.ofrecerArticulo(nuevo);

        assertTrue(resultado);
        assertNotNull(nuevo.getId());
        assertTrue(gestor.mapaItems.containsKey(nuevo.getId()));
    }
}