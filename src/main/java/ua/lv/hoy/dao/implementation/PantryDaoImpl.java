package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.PantryDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Pantry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class PantryDaoImpl implements PantryDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Pantry pantry) {
        entityManager.persist((pantry));
    }

    @Transactional
    public void edit(Pantry pantry) {
        entityManager.merge(pantry);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Pantry.class, id));
    }

    @Transactional
    public Pantry findById(int id) {
        return entityManager.find(Pantry.class, id);
    }

    @Transactional
    public List<Pantry> findAllPantries() {
        return entityManager.createQuery("SELECT p FROM Pantry p order by number").getResultList();
    }

    public List<Pantry> findFreePantriesInHouse(int houseId) {
        return entityManager.createQuery("SELECT p FROM Pantry  p WHERE p.status='free' AND p.house.id=:id order by number")
                .setParameter("id", houseId).getResultList();
    }

    public Pantry findByNumber(int number) {
        return (Pantry) entityManager.createQuery("SELECT p FROM Pantry p WHERE p.number=:number").setParameter("number", number).getSingleResult();
    }

    public List<Pantry> findByCustomerId(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        return entityManager.createQuery("SELECT p FROM Pantry p WHERE p.customer=:customer order by number").setParameter("customer", customer).getResultList();
    }
    @Override
    public List<Pantry> findAllPantriesInHouse(int houseId) {
        return entityManager.createQuery("SELECT p FROM Pantry p where p.house.id=:id order by number").setParameter("id", houseId).getResultList();
    }
}
