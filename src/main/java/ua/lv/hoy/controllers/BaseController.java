package ua.lv.hoy.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * Created by Administrator on 02-Mar-17.
 */
@Controller
public abstract class BaseController {

    abstract String findAll(int houseId, Model model);

    abstract String openAddPage(int houseId, Model model);

    /**
     *
     * @param id - id of object, which will be edited
     * @param model - add object to edit in .jsp
     * @return reference to editPage.jsp
     */
    abstract String openEditPage(int id, Model model);

    abstract String delete(int houseId, int id);

}
