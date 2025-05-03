package edu.masanz.da.au.dao;

import edu.masanz.da.au.dto.Item;
import edu.masanz.da.au.dto.Usuario;
import edu.masanz.da.au.utils.Security;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.masanz.da.au.conf.Ctes.*;
import static org.junit.jupiter.api.Assertions.*;

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
}