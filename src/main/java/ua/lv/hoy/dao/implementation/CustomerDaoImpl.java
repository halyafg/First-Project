package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;


    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Customer.class, id));
    }

    @Override
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        TypedQuery<Customer> query = entityManager.createQuery("select c FROM Customer c order by surname", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findByLogin(String login) {
        return (Customer) entityManager.createQuery("SELECT c from Customer c where c.email=:email or c.phone=:phone").setParameter("email", login).setParameter("phone", login).getSingleResult();
    }
}
