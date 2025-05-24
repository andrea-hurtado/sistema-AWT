package ec.edu.ups.erp.model;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private static GestorUsuarios instancia;
    private List<Usuario> usuarios;

    private GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public static GestorUsuarios getInstance() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (usuarios.stream().anyMatch(u -> u.getUsuario().equals(usuario.getUsuario()))) {
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean validarCredenciales(String usuario, String password) {
        return usuarios.stream()
                .anyMatch(u -> u.getUsuario().equals(usuario) &&
                        u.getContraseña().equals(password));
    }
}
