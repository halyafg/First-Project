package ua.lv.hoy.services;

import ua.lv.hoy.entity.Schedule;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface ScheduleService {
    void add(int id, String date, double amount_$);

    void edit(int id, String data, double amount_$);

    void delete(int id);

    Schedule findById(int id);

    List<Schedule> findAllSchedules();

    List<Schedule> findScheduleByCustId(int cust_id);

    List<Schedule> findAllCustomerSchedules(String email);
}
