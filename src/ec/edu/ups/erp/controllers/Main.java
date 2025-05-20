package ec.edu.ups.erp.controllers;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorCompras gestor = new GestorCompras();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 15);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE COMPRAS ERP =====");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Registrar producto");
        System.out.println("3. Registrar solicitud de compra");
        System.out.println("4. Listar proveedores");
        System.out.println("5. Listar productos");
        System.out.println("6. Listar solicitudes de compra");
        System.out.println("7. Buscar proveedor por ID");
        System.out.println("8. Buscar producto por nombre");
        System.out.println("9. Buscar solicitud por número");
        System.out.println("13. Aprobar / Rechazar solicitud de compra");
        System.out.println("14. Calcular total de una solicitud");
        System.out.println("15. Salir");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> gestor.registrarProveedor();
            case 2 -> gestor.registrarProducto();
            case 3 -> gestor.registrarSolicitud();
            case 4 -> gestor.listarProveedores();
            case 5 -> gestor.listarProductos();
            case 6 -> gestor.listarSolicitudes();
            case 7 -> gestor.buscarProveedorPorID();
            case 8 -> gestor.buscarProductoPorNombre();
            case 9 -> gestor.buscarSolicitudPorNumero();
            case 13 -> gestor.aprobarRechazarSolicitud();
            case 14 -> gestor.calcularTotalSolicitud();
            case 15 -> System.out.println("Saliendo del sistema. ¡Hasta pronto!");
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor ingrese un número.");
            return -1;
        }
    }
}
