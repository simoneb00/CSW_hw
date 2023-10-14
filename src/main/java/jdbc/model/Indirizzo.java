package jdbc.model;

public class Indirizzo {
    private String nome;
    private String città;

    public Indirizzo(String nome, String città) {
        this.nome = nome;
        this.città = città;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getCittà() {
        return this.città;
    }
}
