package ec.edu.ups.erp.model;

import java.util.ArrayList;
import java.util.List;


public class GestorCompras {
    private static GestorCompras instance;
    private List<Proveedor> proveedores;
    private List<Producto> productos;
    private List<SolicitudCompra> solicitudes;

    private GestorCompras() {
        proveedores = new ArrayList<>();
        productos = new ArrayList<>();
        solicitudes = new ArrayList<>();
    }

    public static GestorCompras getInstance() {
        if (instance == null) {
            instance = new GestorCompras();
        }
        return instance;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public boolean eliminarProducto(String id) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            productos.remove(producto);
            return true;
        }
        return false;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Producto buscarProducto(String id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public boolean eliminarProveedor(String id) {
        Proveedor proveedor = buscarProveedor(id);
        if (proveedor != null) {
            proveedores.remove(proveedor);
            return true;
        }
        return false;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Proveedor buscarProveedor(String id) {
        for (Proveedor p : proveedores) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void agregarSolicitud(SolicitudCompra solicitud) {
        solicitudes.add(solicitud);
    }

    public boolean eliminarSolicitud(String numero) {
        SolicitudCompra solicitud = buscarSolicitud(numero);
        if (solicitud != null) {
            solicitudes.remove(solicitud);
            return true;
        }
        return false;
    }

    public List<SolicitudCompra> getSolicitudes() {
        return solicitudes;
    }

    public SolicitudCompra buscarSolicitud(String numero) {
        for (SolicitudCompra s : solicitudes) {
            if (s.getNumero().equals(numero)) {
                return s;
            }
        }
        return null;
    }

    public boolean aprobarSolicitud(String numero) {
        SolicitudCompra solicitud = buscarSolicitud(numero);
        if (solicitud != null) {
            solicitud.setEstado(EstadoSolicitud.APROBADA);
            return true;
        }
        return false;
    }

    public boolean rechazarSolicitud(String numero) {
        SolicitudCompra solicitud = buscarSolicitud(numero);
        if (solicitud != null) {
            solicitud.setEstado(EstadoSolicitud.RECHAZADA);
            return true;
        }
        return false;
    }

    public String obtenerListaProductos() {
        if (productos.isEmpty()) {
            return "No hay productos registrados.";
        }
        StringBuilder sb = new StringBuilder("Lista de productos:\n");
        for (Producto p : productos) {
            int cantidadTotal = 0;
            for (SolicitudCompra solicitud : solicitudes) {
                Integer cantidad = solicitud.getProductos().get(p);
                if (cantidad != null) {
                    cantidadTotal += cantidad;
                }
            }
            sb.append(p.toString())
                    .append(" | Cantidad: ")
                    .append(cantidadTotal)
                    .append("\n");
        }
        return sb.toString();
    }

    public String obtenerListaProveedores() {
        if (proveedores.isEmpty()) {
            return "No hay proveedores registrados.";
        }
        StringBuilder sb = new StringBuilder("Lista de proveedores:\n");
        for (Proveedor p : proveedores) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public String obtenerListaSolicitudes() {
        if (solicitudes.isEmpty()) {
            return "No hay solicitudes registradas.";
        }
        StringBuilder sb = new StringBuilder("Lista de solicitudes:\n");
        for (SolicitudCompra s : solicitudes) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    public double calcularTotalSolicitud(String numero) {
        SolicitudCompra solicitud = buscarSolicitud(numero);
        if (solicitud != null) {
            return solicitud.calcularCostoTotal();
        }
        return 0.0;
    }
}