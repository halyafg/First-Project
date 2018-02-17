package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.PaymentDao;
import ua.lv.hoy.entity.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Payment.class, id));
    }

    @Transactional
    public Payment findById(int id) {
        return entityManager.find(Payment.class, id);
    }

    @Transactional
    public List<Payment> findAllPayments() {
        return entityManager.createQuery("SELECT p FROM Payment p order by data").getResultList();
    }

    @Transactional
    @Override
    public List<Payment> findAllPaymentsInHouse(int houseId) {
        return entityManager.createQuery("SELECT p FROM Payment  p WHERE p.house.id=:id").setParameter("id", houseId).getResultList();
    }

    @Transactional
    public List<Payment> findAllCustomerPayments(String email) {
        return entityManager.createQuery("SELECT p FROM Payment p WHERE p.customer.email =:email order by data").setParameter("email", email).getResultList();
    }
}
