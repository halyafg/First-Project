package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.PaymentDao;
import ua.lv.hoy.entity.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Payment.class, id));
    }

    @Override
    public Payment findById(int id) {
        return entityManager.find(Payment.class, id);
    }

    @Override
    public List<Payment> findAllPayments() {
        TypedQuery<Payment> query = entityManager.createQuery("SELECT p FROM Payment p order by data", Payment.class);
        return query.getResultList();
    }

    @Override
    public List<Payment> findAllPaymentsInHouse(int houseId) {
        TypedQuery<Payment> query = entityManager.createQuery("SELECT p FROM Payment  p WHERE p.house.id=:id", Payment.class).setParameter("id", houseId);
        return query.getResultList();
    }

    @Override
    public List<Payment> findAllCustomerPayments(String email) {
        TypedQuery<Payment> query = entityManager.createQuery("SELECT p FROM Payment p WHERE p.customer.email =:email order by data", Payment.class).setParameter("email", email);
        return query.getResultList();
    }
}
