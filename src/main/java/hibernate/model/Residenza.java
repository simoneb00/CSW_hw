package hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "residenza", uniqueConstraints = @UniqueConstraint(columnNames={"indirizzo", "città"}))
public class Residenza implements Serializable {

    @Id
    private String indirizzo;

    @Id
    private String città;

    @Column(nullable = false)
    private String cap;

    protected Residenza() {}

    public Residenza(String indirizzo, String città, String cap) {
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

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}
