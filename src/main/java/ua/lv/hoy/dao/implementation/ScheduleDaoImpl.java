package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.ScheduleDao;
import ua.lv.hoy.entity.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Schedule.class, id));
    }

    @Transactional
    public Schedule findById(int id) {
        return entityManager.find(Schedule.class, id);
    }

    @Transactional
    public List<Schedule> findAllSchedules() {
        return entityManager.createQuery("SELECT s FROM Schedule s order by date").getResultList();
    }
    @Transactional
    public List<Schedule> findByCustomer(int custId) {
        return entityManager.createQuery("SELECT s FROM Schedule s WHERE s.customer.id=:id order by date")
                                        .setParameter("id",custId).getResultList();
    }

    @Transactional
    public List<Schedule> findAllCustomerSchedules(String email) {
        return entityManager.createQuery("SELECT s FROM Schedule s WHERE s.customer.email =:email").setParameter("email", email).getResultList();
    }
}
