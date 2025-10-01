package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AlunoModel {


    private static final String pu = "gestao-cursos-jpa";

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(pu);

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @FunctionalInterface
    private interface QueryConsumer {
        void consume(EntityManager em);
    }

    private void executeAction(QueryConsumer consumer, String action) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            consumer.consume(em);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            System.out.println("Exception in " + action + ": " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void create(Aluno aluno) {
        executeAction(em -> em.persist(aluno), "create");
    }

    public Optional<Aluno> findById(Long id) {
        Optional<Aluno> output = Optional.empty();
        EntityManager em = getEntityManager();
        try {
            output = Optional.of(em.find(Aluno.class, id));
        } catch (Exception e) {
            System.out.println("Exception in findById: " + e.getMessage());
        } finally {
            em.close();
        }
        return output;
    }

    public Set<Aluno> findAll() {
        EntityManager em = getEntityManager();
        Set<Aluno> output = Set.of();
        try {
            output = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultStream()
                    .collect(Collectors.toSet());
        }catch (Exception e) {
            System.out.println("Exception in findAll: " + e.getMessage());
        } finally {
            em.close();
        }
        return new HashSet<>(output);
    }

    public void update(Aluno aluno){
        executeAction(em -> em.merge(aluno), "update");
    }

    public void delete(Aluno aluno) {
        EntityManager em = getEntityManager();
        em.remove(em.contains(aluno)? aluno: em.merge(aluno));
    }
}