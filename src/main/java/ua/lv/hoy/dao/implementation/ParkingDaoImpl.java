package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.ParkingDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Parking;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class ParkingDaoImpl implements ParkingDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    public void add(Parking parking) {
        entityManager.persist((parking));
    }

    public void edit(Parking parking) {
        entityManager.merge(parking);
    }

    public void delete(int id) {
        entityManager.remove(entityManager.find(Parking.class, id));
    }

    public Parking findById(int id) {
        return entityManager.find(Parking.class, id);
    }

    public Parking findByNumber(int number) {
        return (Parking) entityManager.createQuery("SELECT p FROM Parking p WHERE p.number=:number").setParameter("number", number).getSingleResult();
    }

    public List<Parking> findAllParkings() {
        return entityManager.createQuery("SELECT p FROM Parking p order by  number").getResultList();
    }

    public List<Parking> findFreeParkingsInHouse(int houseId) {
        return entityManager.createQuery("SELECT p FROM Parking p WHERE p.status='free' AND p.house.id=:id order by  number")
                .setParameter("id", houseId).getResultList();
    }

    public List<Parking> findByCustomerId(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        return entityManager.createQuery("SELECT p FROM Parking  p WHERE p.customer=:customer order by  number").setParameter("customer", customer).getResultList();
    }

    @Override
    public List<Parking> findAllParkingInHouse(int houseId) {
        return entityManager.createQuery("SELECT p FROM Parking p WHERE p.house.id=:id").setParameter("id", houseId).getResultList();
    }
}
