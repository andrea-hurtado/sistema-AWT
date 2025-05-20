package ec.edu.ups.erp.model;

public abstract class Documento {
    protected String codigo;

    public Documento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public abstract void mostrarResumen();
}
