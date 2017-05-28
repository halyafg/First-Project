package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Flat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    @Transactional
    public void edit(Customer customer) {
        entityManager.merge(customer);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Customer.class, id));

    }

    @Transactional
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    public List<Customer> findAllCustomers() {
        return entityManager.createQuery("select c FROM Customer c order by surname").getResultList();
    }


    @Transactional
    public Customer findByLogin(String login) {
        return (Customer) entityManager.createQuery("SELECT c from Customer c where c.email=:email or c.phone=:phone").setParameter("email", login).setParameter("phone", login).getSingleResult();
    }
}
