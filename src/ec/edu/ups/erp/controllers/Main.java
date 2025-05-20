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
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Registrar producto");
        System.out.println("3. Asignar producto a proveedor");
        System.out.println("4. Registrar solicitud de compra");
        System.out.println("5. Mostrar resumen de solicitud");
        System.out.println("6. Mostrar proveedor");
        System.out.println("7. Mostrar total de una solicitud");
        System.out.println("0. Salir");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> gestor.registrarProveedor();
            case 2 -> gestor.registrarProducto();
            case 3 -> gestor.asignarProductoAProveedor();
            case 4 -> gestor.registrarSolicitud();
            case 5 -> gestor.mostrarResumenSolicitud();
            case 6 -> gestor.mostrarProveedor();
            case 7 -> gestor.mostrarTotalSolicitud();
            case 0 -> System.out.println("Saliendo del sistema. ¡Hasta pronto!");
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
