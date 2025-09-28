package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao atualizar o produto: " + e.getMessage());
        } finally {
            emf.close();
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Produto produtoToDelete = em.find(Produto.class, p.getId());
            if (produtoToDelete != null) {
                em.remove(produtoToDelete);
                em.getTransaction().commit();
                System.out.println("Produto deletado com sucesso !!!");
            } else {
                System.out.println("Produto com id " + p.getId() + " não encontrado.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao deletar o produto: " + e.getMessage());
        } finally {
            emf.close();
        }
    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto = null;
        try {
            produto = em.find(Produto.class, p.getId());
        } catch (Exception e) {
            System.out.println("Erro ao buscar o produto: " + e.getMessage());
        } finally {
            emf.close();
        }
        return produto;
    }

    public List<Produto> findAll() {

        List<Produto> produtos = new ArrayList<Produto>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            produtos = em.createQuery("from Produto", Produto.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar os produtos: " + e.getMessage());
        } finally {
            emf.close();
        }
        return produtos;
    }
}
