package ua.lv.hoy.dao;


import ua.lv.hoy.entity.Schedule;

import java.util.List;

/**
 * Created by Administrator on 26-Feb-17.
 */
public interface ScheduleDao {

    void delete(int id);

    Schedule findById (int id);

    List<Schedule> findAllSchedules();

    List<Schedule> findAllSchedulesInHouse(int houseId);

    List<Schedule> findByCustomer(int custId);

    List<Schedule> findAllCustomerSchedules(String email);
}
