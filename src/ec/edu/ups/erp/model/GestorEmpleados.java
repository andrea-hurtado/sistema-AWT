
package ec.edu.ups.erp.model;

import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {
    private static GestorEmpleados instance;
    private List<Empleado> empleados;

    private GestorEmpleados() {
        empleados = new ArrayList<>();
    }

    public static GestorEmpleados getInstance() {
        if (instance == null) {
            instance = new GestorEmpleados();
        }
        return instance;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public boolean eliminarEmpleado(String id) {
        Empleado empleado = buscarEmpleado(id);
        if (empleado != null) {
            empleados.remove(empleado);
            return true;
        }
        return false;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public Empleado buscarEmpleado(String id) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public String obtenerListaEmpleados() {
        if (empleados.isEmpty()) {
            return "No hay empleados registrados.";
        }
        StringBuilder sb = new StringBuilder("Lista de empleados:\n");
        for (Empleado e : empleados) {
            sb.append("ID: ").append(e.getId())
                    .append(" | Nombre: ").append(e.getNombre())
                    .append(" | Cargo: ").append(e.getCargo())
                    .append(" | Departamento: ").append(e.getDepartamento())
                    .append("\n");
        }
        return sb.toString();
    }
}