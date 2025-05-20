package ec.edu.ups.erp.model;

public class Proveedor extends Persona {

    public Proveedor(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String toString() {
        return "Proveedor -> " + super.toString();
    }
}
