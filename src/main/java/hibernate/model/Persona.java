package hibernate.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Persona", uniqueConstraints = {@UniqueConstraint(columnNames = "codice_fiscale")})
public class Persona {
    @Id
    @Column(name="codice_fiscale", nullable = false, unique = true)
    private String codiceFiscale;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Residenza residenza;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Domicilio> domicili;

    protected Persona() {}

    public Persona(String codiceFiscale, String nome, String cognome, Residenza residenza, List<Domicilio> domicili) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.residenza = residenza;
        this.domicili = domicili;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Residenza getResidenza() {
        return residenza;
    }

    public void setResidenza(Residenza residenza) {
        this.residenza = residenza;
    }

    public List<Domicilio> getDomicili() {
        return domicili;
    }

    public void setDomicilio(List<Domicilio> domicili) {
        this.domicili = domicili;
    }
}
