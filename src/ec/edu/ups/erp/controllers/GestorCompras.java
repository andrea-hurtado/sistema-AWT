package ec.edu.ups.erp.controllers;

import ec.edu.ups.erp.model.Producto;
import ec.edu.ups.erp.model.Proveedor;
import ec.edu.ups.erp.model.SolicitudCompra;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorCompras {
    private List<Proveedor> proveedores;
    private List<Producto> productos;
    private List<SolicitudCompra> solicitudes;
    private Scanner scanner;

    public GestorCompras() {
        proveedores = new ArrayList<>();
        productos = new ArrayList<>();
        solicitudes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void registrarProveedor() {
        System.out.print("ID del proveedor: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del proveedor: ");
        String nombre = scanner.nextLine();
        Proveedor p = new Proveedor(id, nombre);
        proveedores.add(p);
        System.out.println("Proveedor registrado.");
    }

    public void registrarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio unitario: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        Producto producto = new Producto(nombre, precio, cantidad);
        productos.add(producto);
        System.out.println("Producto registrado.");
    }

    public void asignarProductoAProveedor() {
        System.out.print("ID del proveedor: ");
        String id = scanner.nextLine();
        Proveedor proveedor = null;

        for (Proveedor p : proveedores) {
            if (p.getId().equals(id)) {
                proveedor = p;
                break;
            }
        }

        if (proveedor != null) {
            System.out.print("Nombre del producto a asignar: ");
            String nombreProducto = scanner.nextLine();
            Producto producto = null;

            for (Producto prod : productos) {
                if (prod.getNombre().equalsIgnoreCase(nombreProducto)) {
                    producto = prod;
                    break;
                }
            }

            if (producto != null) {
                proveedor.agregarProducto(producto);
                System.out.println("Producto asignado al proveedor.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public void registrarSolicitud() {
        System.out.print("Código de la solicitud: ");
        String codigo = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();

        SolicitudCompra sc = new SolicitudCompra(codigo, departamento);
        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {
            registrarProducto();
            Producto p = productos.get(productos.size() - 1);
            sc.agregarProducto(p);

            System.out.print("¿Agregar otro producto? (s/n): ");
            seguir = scanner.nextLine();
        }

        solicitudes.add(sc);
        System.out.println("Solicitud registrada.");
    }

    public void mostrarResumenSolicitud() {
        System.out.print("Código de la solicitud: ");
        String codigo = scanner.nextLine();
        boolean encontrada = false;

        for (SolicitudCompra s : solicitudes) {
            if (s.getCodigo().equalsIgnoreCase(codigo)) {
                s.mostrarResumen();
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Solicitud no encontrada.");
        }
    }

    public void mostrarProveedor() {
        System.out.print("ID del proveedor: ");
        String id = scanner.nextLine();
        boolean encontrado = false;
    }

    public void mostrarTotalSolicitud() {

    }
}