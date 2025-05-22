package ec.edu.ups.erp.controllers;

import ec.edu.ups.erp.model.EstadoSolicitud;
import ec.edu.ups.erp.model.Producto;
import ec.edu.ups.erp.model.Proveedor;
import ec.edu.ups.erp.model.SolicitudCompra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorCompras {

    private final Scanner scanner = new Scanner(System.in);

    private final List<Proveedor> proveedores = new ArrayList<>();
    private final List<Producto> productos = new ArrayList<>();
    private final List<SolicitudCompra> solicitudes = new ArrayList<>();

    // 1. Registrar proveedor
    public void registrarProveedor() {
        System.out.print("Ingrese ID del proveedor: ");
        String id = scanner.nextLine().trim();
        System.out.print("Ingrese nombre del proveedor: ");
        String nombre = scanner.nextLine().trim();
        Proveedor proveedor = new Proveedor(id, nombre);
        proveedores.add(proveedor);
        System.out.println("Proveedor registrado exitosamente.");
    }

    // 2. Registrar producto
    public void registrarProducto() {
        System.out.print("Ingrese ID del producto: ");
        String id = scanner.nextLine().trim();
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ingrese precio unitario: ");
        double precio = leerDouble();
        Producto producto = new Producto(id, nombre, precio);
        productos.add(producto);
        System.out.println("Producto registrado exitosamente.");
    }

    // 3. Registrar solicitud de compra
    public void registrarSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        String numero = scanner.nextLine().trim();

        if (buscarSolicitudPorNumeroInterno(numero) != null) {
            System.out.println("Ya existe una solicitud con ese número.");
            return;
        }

        SolicitudCompra solicitud = new SolicitudCompra(numero);
        boolean agregarMas = true;

        while (agregarMas) {
            System.out.print("Ingrese ID del producto para agregar a la solicitud: ");
            String idProducto = scanner.nextLine().trim();
            Producto producto = buscarProductoPorId(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Ingrese cantidad: ");
            int cantidad = leerEntero();

            solicitud.agregarProducto(producto, cantidad);

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            String resp = scanner.nextLine().trim().toLowerCase();
            agregarMas = resp.equals("s") || resp.equals("si");
        }

        solicitud.setEstado(EstadoSolicitud.SOLICITADA);
        solicitudes.add(solicitud);
        System.out.println("Solicitud registrada exitosamente.");
    }

    // 4. Listar proveedores
    public void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }
        System.out.println("Lista de proveedores:");
        for (Proveedor p : proveedores) {
            System.out.println(p);
        }
    }

    // 5. Listar productos
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("Lista de productos:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    // 6. Listar solicitudes
    public void listarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
            return;
        }
        System.out.println("Lista de solicitudes:");
        for (SolicitudCompra s : solicitudes) {
            System.out.println(s);
        }
    }

    // 7. Buscar proveedor por ID
    public void buscarProveedorPorID() {
        System.out.print("Ingrese ID del proveedor: ");
        String id = scanner.nextLine().trim();
        for (Proveedor p : proveedores) {
            if (p.getId().equalsIgnoreCase(id)) {
                System.out.println("Proveedor encontrado: " + p);
                return;
            }
        }
        System.out.println("Proveedor no encontrado.");
    }

    // 8. Buscar producto por nombre
    public void buscarProductoPorNombre() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Producto encontrado: " + p);
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    // 9. Buscar solicitud por número
    public void buscarSolicitudPorNumero() {
        System.out.print("Ingrese número de solicitud: ");
        String numero = scanner.nextLine().trim();
        SolicitudCompra solicitud = buscarSolicitudPorNumeroInterno(numero);
        if (solicitud != null) {
            System.out.println("Solicitud encontrada: " + solicitud);
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private SolicitudCompra buscarSolicitudPorNumeroInterno(String numero) {
        for (SolicitudCompra s : solicitudes) {
            if (s.getNumero().equalsIgnoreCase(numero)) {
                return s;
            }
        }
        return null;
    }

    // 13. Aprobar / Rechazar solicitud
    public void aprobarRechazarSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        String numero = scanner.nextLine().trim();
        SolicitudCompra solicitud = buscarSolicitudPorNumeroInterno(numero);
        if (solicitud == null) {
            System.out.println("Solicitud no encontrada.");
            return;
        }
        System.out.println("Estado actual: " + solicitud.getEstado());
        System.out.print("Ingrese nuevo estado (APROBADA/RECHAZADA): ");
        String estado = scanner.nextLine().trim().toUpperCase();

        if (estado.equals("APROBADA")) {
            solicitud.setEstado(EstadoSolicitud.APROBADA);
            System.out.println("Solicitud aprobada.");
        } else if (estado.equals("RECHAZADA")) {
            solicitud.setEstado(EstadoSolicitud.RECHAZADA);
            System.out.println("Solicitud rechazada.");
        } else {
            System.out.println("Estado no válido.");
        }
    }

    // 14. Calcular total de solicitud
    public void calcularTotalSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        String numero = scanner.nextLine().trim();
        SolicitudCompra solicitud = buscarSolicitudPorNumeroInterno(numero);
        if (solicitud == null) {
            System.out.println("Solicitud no encontrada.");
            return;
        }
        double total = solicitud.calcularCostoTotal();
        System.out.println("El total de la solicitud es: $" + total);
    }

    // Métodos auxiliares para leer datos numéricos

    private int leerEntero() {
        while (true) {
            try {
                String linea = scanner.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, ingrese un número entero: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                String linea = scanner.nextLine();
                return Double.parseDouble(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, ingrese un número decimal: ");
            }
        }
    }

    // Buscar producto por id (auxiliar)
    private Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

public String obtenerListaProductos() {
    if (productos.isEmpty()) {
        return "No hay productos registrados.";
    }
    StringBuilder sb = new StringBuilder("Lista de productos:\n\n");
    for (Producto p : productos) {
        sb.append(p.toString()).append("\n");
    }
    return sb.toString();
}

public String obtenerListaProveedores() {
    if (proveedores.isEmpty()) {
        return "No hay proveedores registrados.";
    }
    StringBuilder sb = new StringBuilder("Lista de proveedores:\n\n");
    for (Proveedor p : proveedores) {
        sb.append(p.toString()).append("\n");
    }
    return sb.toString();
}

public void agregarProducto(Producto producto) {
    productos.add(producto);
}

public void agregarProveedor(Proveedor proveedor) {
    proveedores.add(proveedor);
}
}