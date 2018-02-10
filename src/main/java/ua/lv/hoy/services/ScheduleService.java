package ua.lv.hoy.services;

import ua.lv.hoy.entity.Schedule;

import java.util.List;

/**
 * Created by Administrator on 05-Mar-17.
 */
public interface ScheduleService {
    void add(int id, String date, double amountUSA);

    void edit(int id, String data, double amountUSA);

    void delete(int id);

    Schedule findById(int id);

    List<Schedule> findAllSchedules();

    List<Schedule> findScheduleByCustId(int custId);

    List<Schedule> findAllCustomerSchedules(String email);
}
