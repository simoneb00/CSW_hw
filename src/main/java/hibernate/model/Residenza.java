package hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "residenza")
public class Residenza implements Serializable {

    @Id
    private String indirizzo;

    @Id
    private String città;

    @Column(nullable = false)
    private int cap;

    protected Residenza() {}

    public Residenza(String indirizzo, String città, int cap) {
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }
}
