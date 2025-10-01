package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Curso {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String sigla;

    @OneToOne(cascade = CascadeType.ALL)
    private MaterialCurso materialCurso;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Set<Aluno> aluno;


    public Set<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(Set<Aluno> aluno) {
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialCurso getMaterialCurso() {
        return materialCurso;
    }

    public void setMaterialCurso(MaterialCurso materialCurso) {
        this.materialCurso = materialCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}










