package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.ScheduleDao;
import ua.lv.hoy.entity.Schedule;
import ua.lv.hoy.services.ScheduleService;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    CustomerDao customerDao;

    public void add(int customer_id, String date, double amount_$) {
        Schedule schedule = new Schedule();
        schedule.setCustomer(customerDao.findById(customer_id));
        schedule.setDate(date);
        schedule.setAmount_$(amount_$);
        scheduleDao.add(schedule);
    }

    public void edit(int id, String date, double amount_$) {
        Schedule schedule = scheduleDao.findById(id);

        if (date != null  && !date.equalsIgnoreCase("")){
            schedule.setDate(date);
        }
        if (amount_$ > 0){
            schedule.setAmount_$(amount_$);
        }

        scheduleDao.edit(schedule);
    }

    public void delete(int id) {
        scheduleDao.delete(id);
    }

    public Schedule findById(int id) {
        return scheduleDao.findById(id);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleDao.findAllSchedules();
    }

    public List<Schedule> findScheduleByCustId(int cust_id) {
        return scheduleDao.findByCustomer(cust_id);
    }

    public List<Schedule>findAllCustomerSchedules(String email){
        return scheduleDao.findAllCustomerSchedules(email);
    }

}
