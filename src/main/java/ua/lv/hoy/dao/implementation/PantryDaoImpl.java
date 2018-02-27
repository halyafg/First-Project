package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.PantryDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Pantry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class PantryDaoImpl implements PantryDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;


    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Pantry.class, id));
    }

    @Override
    public Pantry findById(int id) {
        return entityManager.find(Pantry.class, id);
    }

    @Override
    public List<Pantry> findAllPantries() {
        TypedQuery<Pantry> pantryTypedQuery = entityManager.createQuery("SELECT p FROM Pantry p order by number", Pantry.class);
        return pantryTypedQuery.getResultList();
    }

    @Override
    public List<Pantry> findFreePantriesInHouse(int houseId) {
        TypedQuery<Pantry> query = entityManager.createQuery("SELECT p FROM Pantry  p WHERE p.status='free' AND p.house.id=:id order by number", Pantry.class)
                .setParameter("id", houseId);
        return query.getResultList();
    }

    @Override
    public Pantry findByNumber(int number) {
        return (Pantry) entityManager.createQuery("SELECT p FROM Pantry p WHERE p.number=:number").setParameter("number", number).getSingleResult();
    }

    @Override
    public List<Pantry> findByCustomerId(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        TypedQuery<Pantry> query = entityManager.createQuery("SELECT p FROM Pantry p WHERE p.customer=:customer order by number", Pantry.class).setParameter("customer", customer);
        return query.getResultList();
    }
    @Override
    public List<Pantry> findAllPantriesInHouse(int houseId) {
        TypedQuery<Pantry> query = entityManager.createQuery("SELECT p FROM Pantry p where p.house.id=:id order by number", Pantry.class).setParameter("id", houseId);
        return query.getResultList();
    }
}
