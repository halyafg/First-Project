package ua.lv.hoy.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.hoy.dao.AbstractDao;
import ua.lv.hoy.dao.CustomerDao;
import ua.lv.hoy.dao.HouseDao;
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
    ScheduleDao scheduleDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    HouseDao houseDao;
    @Autowired
    AbstractDao abstractDao;

    @Override
    public void add(int houseId, int customerId, Schedule schedule) {
        schedule.setCustomer(customerDao.findById(customerId));
        schedule.setHouse(houseDao.findById(houseId));
        abstractDao.add(schedule);
    }


    @Override
    public void edit(int id, Schedule editedSchedule) {
        Schedule schedule = scheduleDao.findById(id);
        if (editedSchedule.getDate() != null  && !editedSchedule.getDate().equalsIgnoreCase("")){
            schedule.setDate(editedSchedule.getDate());
        }
        if (editedSchedule.getAmountUSA() > 0){
            schedule.setAmountUSA(editedSchedule.getAmountUSA());
        }

        abstractDao.edit(schedule);
    }

    @Override
    public void delete(int id) {
        scheduleDao.delete(id);
    }

    @Override
    public Schedule findById(int id) {
        return scheduleDao.findById(id);
    }

    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleDao.findAllSchedules();
    }

    @Override
    public List<Schedule> findAllSchedulesInHouse(int houseId) {
        return scheduleDao.findAllSchedulesInHouse(houseId);
    }

    @Override
    public List<Schedule> findScheduleByCustId(int custId) {
        return scheduleDao.findByCustomer(custId);
    }

    @Override
    public List<Schedule>findAllCustomerSchedules(String email){
        return scheduleDao.findAllCustomerSchedules(email);
    }

}
