package ec.edu.ups.erp.model;

public class Empleado extends Persona {
    private String cargo;
    private String departamento;

    public Empleado(String id, String nombre, String cargo, String departamento) {
        super(id, nombre);
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cargo='" + cargo + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
