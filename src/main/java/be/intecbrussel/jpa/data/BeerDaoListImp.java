package be.intecbrussel.jpa.data;

import be.intecbrussel.jpa.model.Beer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.util.stream.Stream;

public class BeerDaoListImp implements BeerDao {

    @Override
    public void createBeer(Beer beer) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("mypersistenceunit");
            em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            System.out.println("id before persist " + beer.getId());
            transaction.begin();
            //if (!em.contains(beer)) {
                em.persist(beer);
                transaction.commit();
                System.out.println("Beer saved");
                System.out.println("id after persist " + beer.getId());
            //} else {
            //    System.out.println("Beer already exist");
            //}

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }


    @Override
    public Beer readBeer(String beerName) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Beer beerToRead = new Beer();
        try {
            emf = Persistence.createEntityManagerFactory("mypersistenceunit");
            em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Query query = em.createQuery("SELECT b FROM Beer AS b WHERE b.beerName LIKE ?1 ",Beer.class);
            query.setParameter(1,beerName);
            Stream<Beer> results = query.getResultStream();
            beerToRead = results.findFirst().get();

            transaction.commit();
            System.out.println(beerToRead + " Beer readed by name");
            //} else {
            //    System.out.println("Beer already exist");
            //}

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
        return beerToRead;
    }

    @Override
    public Beer readBeer(int beerId) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Beer beer = new Beer();
        try {
            emf = Persistence.createEntityManagerFactory("mypersistenceunit");
            em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            //if (!em.contains(beer)) {
            beer = em.find(Beer.class, beerId);
            transaction.commit();
            System.out.println("Beer readed");
            //} else {
            //    System.out.println("Beer already exist");
            //}

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
        return beer;
    }

    @Override
    public void updateBeer(Beer beer) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("mypersistenceunit");
            em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            Beer beerToUpdate = em.find(Beer.class,beer.getId());
            transaction.begin();

            beerToUpdate.setAlcoholPercentage(beer.getAlcoholPercentage());
            beerToUpdate.setBeerName(beer.getBeerName());
            beerToUpdate.setPrice(beer.getPrice());
            beerToUpdate.setStock(beer.getStock());

            transaction.commit();
            System.out.println(beer + " updated");

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    @Override
    public void deleteBeer(Beer beer) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("mypersistenceunit");
            em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Beer beerToDelete = em.find(Beer.class,beer.getId());
            em.remove(beerToDelete);

            transaction.commit();
            System.out.println(beer + " deleted");

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
