package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.FlatDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Flat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class FlatDaoImpl implements FlatDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Flat.class, id));
    }

    @Override
    public Flat findById(int id) {
        return entityManager.find(Flat.class, id);
    }

    @Override
    public List<Flat> findAllFlats() {
        TypedQuery<Flat> flatTypedQuery = entityManager.createQuery("SELECT f FROM Flat f order by f.flatNumber", Flat.class);
        return flatTypedQuery.getResultList();
    }

    @Override
    public List<Flat> findFreeFlatsInHouse(int houseId) {
        TypedQuery<Flat> flatTypedQuery = entityManager.createQuery("SELECT f from Flat f WHERE f.status='free' AND f.house.id=:id order by f.flatNumber", Flat.class)
                .setParameter("id", houseId);
        return flatTypedQuery.getResultList();
    }

    @Override
    public Flat findByNumber(int number) {
        return (Flat) entityManager.createQuery("SELECT f FROM Flat f WHERE f.flatNumber=:number").setParameter("number", number).getSingleResult();
    }

    @Override
    public List<Flat> findByCustomerId(int customer_id) {
        Customer customer = entityManager.find(Customer.class, customer_id);
        TypedQuery<Flat> flatTypedQuery = entityManager.createQuery("SELECT f FROM Flat f WHERE f.customer=:customer order by f.flatNumber", Flat.class).setParameter("customer", customer);
        return flatTypedQuery .getResultList();
    }

    @Override
    public List<Flat> findAllFlatsInHouse(int houseId) {
        TypedQuery<Flat> flatTypedQuery = entityManager.createQuery("SELECT f FROM Flat f where f.house.id=:id order by f.flatNumber", Flat.class).setParameter("id", houseId);
        return flatTypedQuery.getResultList();
    }
}
