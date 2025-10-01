package entities;

import javax.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue
    private Long id;
    private String DDD;
    private String numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "aluno_id", nullable = false)
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getDDD() {
        return DDD;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
