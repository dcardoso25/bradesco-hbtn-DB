package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Set;

public class GestaoCursosMain {

    public static void main(String[] args) {
        var alunoDao = new AlunoModel();
        var cursoDao = new CursoModel();
        Telefone telefone = getTelefone();
        Endereco endereco = getEndereco();
        Aluno aluno = getAluno();
        Professor professor = getProfessor();
        MaterialCurso materialCurso = getMaterialCurso();
        Curso curso = getCurso();
        curso.setProfessor(professor);
        curso.setMaterialCurso(materialCurso);
        aluno.setTelefone(Set.of(telefone));
        telefone.setAluno(aluno);
        aluno.setEndereco(Set.of(endereco));
        endereco.setAluno(aluno);

        aluno.setCurso(Set.of(curso));
        curso.setAluno(Set.of(aluno));
        alunoDao.create(aluno);
    }

    private static Curso getCurso() {
        var curso = new Curso();
        curso.setNome("nome");
        curso.setSigla("sigla");
        return curso;
    }

    private static MaterialCurso getMaterialCurso() {
        var materialCurso = new MaterialCurso();
        materialCurso.setUrl("url");
        return materialCurso;
    }

    private static Professor getProfessor() {
        var professor = new Professor();
        professor.setNomeCompleto("nome");
        professor.setMatricula("matricula");
        professor.setEmail("email");
        return professor;
    }

    private static Telefone getTelefone() {
        var telefone = new Telefone();
        telefone.setDDD("ddd");
        telefone.setNumero("11942464444");
        return telefone;
    }

    private static Aluno getAluno() {
        var aluno = new Aluno();
        aluno.setEmail("email");
        var nascimento = Date.from(LocalDate.of(1986, 12, 2)
                .atStartOfDay().toInstant(ZoneOffset.UTC));
        aluno.setNascimento(nascimento);
        aluno.setMatricula("1234");
        return aluno;
    }

    private static Endereco getEndereco() {
        var endereco = new Endereco();
        endereco.setCep(13321130);
        endereco.setEndereco("endereco");
        endereco.setBairro("bairro");
        endereco.setCidade("cidae");
        endereco.setEstado("estado");
        endereco.setLogradouro("logradouro");
        return endereco;
    }
}