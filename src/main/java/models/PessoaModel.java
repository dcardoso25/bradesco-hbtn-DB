package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao atualizar a pessoa: " + e.getMessage());
        } finally {
            emf.close();
        }

    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pessoa pessoaToDelete = em.find(Pessoa.class, p.getId());
            if (pessoaToDelete != null) {
                em.remove(pessoaToDelete);
                em.getTransaction().commit();
                System.out.println("Pessoa deletada com sucesso !!!");
            } else {
                System.out.println("Pessoa não encontrada para exclusão.");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao deletar a pessoa: " + e.getMessage());
        } finally {
            emf.close();
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;
        try {
            pessoa = em.find(Pessoa.class, p.getId());
        } catch (Exception e) {
            System.out.println("Erro ao buscar a pessoa: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return pessoa;
    }

    public List<Pessoa> findAll() {

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            pessoas = em.createQuery("FROM Pessoa", Pessoa.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar as pessoas: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return pessoas;
    }
}
