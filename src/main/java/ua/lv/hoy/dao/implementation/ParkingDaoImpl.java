package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.ParkingDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Parking;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class ParkingDaoImpl implements ParkingDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Parking.class, id));
    }

    @Override
    public Parking findById(int id) {
        return entityManager.find(Parking.class, id);
    }

    @Override
    public Parking findByNumber(int number) {
        return (Parking) entityManager.createQuery("SELECT p FROM Parking p WHERE p.number=:number").setParameter("number", number).getSingleResult();
    }

    @Override
    public List<Parking> findAllParkings() {
        TypedQuery<Parking> query = entityManager.createQuery("SELECT p FROM Parking p order by  number", Parking.class);
        return query.getResultList();
    }

    @Override
    public List<Parking> findFreeParkingsInHouse(int houseId) {
        TypedQuery<Parking> query = entityManager.createQuery("SELECT p FROM Parking p WHERE p.status='free' AND p.house.id=:id order by  number", Parking.class)
                .setParameter("id", houseId);
        return query.getResultList();
    }

    @Override
    public List<Parking> findByCustomerId(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        TypedQuery<Parking> query = entityManager.createQuery("SELECT p FROM Parking  p WHERE p.customer=:customer order by  number", Parking.class).setParameter("customer", customer);
        return query.getResultList();
    }

    @Override
    public List<Parking> findAllParkingInHouse(int houseId) {
        TypedQuery<Parking> query = entityManager.createQuery("SELECT p FROM Parking p WHERE p.house.id=:id", Parking.class).setParameter("id", houseId);
        return query.getResultList();
    }
}
