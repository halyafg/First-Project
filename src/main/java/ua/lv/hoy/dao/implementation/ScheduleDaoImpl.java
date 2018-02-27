package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.ScheduleDao;
import ua.lv.hoy.entity.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
@Repository
@Transactional
public class ScheduleDaoImpl implements ScheduleDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;


    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Schedule.class, id));
    }

    @Override
    public Schedule findById(int id) {
        return entityManager.find(Schedule.class, id);
    }

    @Override
    public List<Schedule> findAllSchedules() {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT s FROM Schedule s order by date", Schedule.class);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findAllSchedulesInHouse(int houseId) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT s FROM Schedule s WHERE s.house.id=:id", Schedule.class).setParameter("id", houseId);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findByCustomer(int custId) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT s FROM Schedule s WHERE s.customer.id=:id order by date", Schedule.class)
                .setParameter("id",custId);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findAllCustomerSchedules(String email) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT s FROM Schedule s WHERE s.customer.email =:email", Schedule.class).setParameter("email", email);
        return query.getResultList();
    }

}
