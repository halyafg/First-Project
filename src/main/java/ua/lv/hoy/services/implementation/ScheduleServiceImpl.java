package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.AbstractDao;
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
    @Autowired
    AbstractDao abstractDao;

    public void add(int customerId, String date, double amountUSA) {
        Schedule schedule = new Schedule();
        schedule.setCustomer(customerDao.findById(customerId));
        schedule.setDate(date);
        schedule.setAmountUSA(amountUSA);
        abstractDao.add(schedule);
    }

    public void edit(int id, String date, double amountUSA) {
        Schedule schedule = scheduleDao.findById(id);

        if (date != null  && !date.equalsIgnoreCase("")){
            schedule.setDate(date);
        }
        if (amountUSA > 0){
            schedule.setAmountUSA(amountUSA);
        }

        abstractDao.edit(schedule);
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

    @Override
    public List<Schedule> findAllSchedulesInHouse(int houseId) {
        return scheduleDao.findAllSchedulesInHouse(houseId);
    }

    public List<Schedule> findScheduleByCustId(int custId) {
        return scheduleDao.findByCustomer(custId);
    }

    public List<Schedule>findAllCustomerSchedules(String email){
        return scheduleDao.findAllCustomerSchedules(email);
    }

}
