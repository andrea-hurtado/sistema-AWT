package ec.edu.ups.erp.model;

public abstract class Documento {
    private String numero;

    public Documento(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public abstract void mostrarDetalle();
}
