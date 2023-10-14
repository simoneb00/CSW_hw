package jdbc.model;

public class Cane {

    private String nome;
    private String razza;
    private Persona padrone;

    public Cane(String nome, String razza, Persona padrone) {
        this.nome = nome;
        this.razza = razza;
        this.padrone = padrone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public void setPadrone(Persona padrone) {
        this.padrone = padrone;
    }

    public Persona getPadrone() {
        return this.padrone;
    }
}
