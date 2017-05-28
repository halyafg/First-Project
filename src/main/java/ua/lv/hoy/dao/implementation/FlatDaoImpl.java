package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.FlatDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.entity.House;
import ua.lv.hoy.entity.Image;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class FlatDaoImpl implements FlatDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Flat flat) {
        entityManager.persist((flat));
    }

    @Transactional
    public void edit(Flat flat) {
        entityManager.merge(flat);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Flat.class, id));
    }

    @Transactional
    public Flat findById(int id) {
        return entityManager.find(Flat.class, id);
    }

    @Transactional
    public List<Flat> findAllFlats() {
        return entityManager.createQuery("SELECT f FROM Flat f order by flatnumber").getResultList();
    }

    public List<Flat> findFreeFlatsinHouse(int houseId) {
        return entityManager.createQuery("SELECT f from Flat f WHERE f.status='free' AND f.house.id=:id order by flatnumber")
                .setParameter("id", houseId).getResultList();
    }

    public Flat findByNumber(int number) {
        return (Flat) entityManager.createQuery("SELECT f FROM Flat f WHERE f.flatnumber=:number").setParameter("number", number).getSingleResult();
    }

    public List<Flat> findByCustomerId(int customer_id) {
        Customer customer = entityManager.find(Customer.class, customer_id);
        return entityManager.createQuery("SELECT f FROM Flat f WHERE f.customer=:customer order by flatnumber").setParameter("customer", customer).getResultList();
    }

    @Override
    public List<Flat> findAllFlatsInHouse(int houseId) {
        return entityManager.createQuery("SELECT f FROM Flat f where f.house.id=:id order by flatnumber").setParameter("id", houseId).getResultList();
    }
}
