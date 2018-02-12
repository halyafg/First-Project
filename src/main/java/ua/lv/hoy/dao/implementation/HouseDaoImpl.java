package ua.lv.hoy.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.entity.House;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 07-Apr-17.
 */
@Repository
@Transactional
public class HouseDaoImpl implements HouseDao {


    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(House.class, id));
    }

    @Override
    public House findById(int id) {
        return (House) entityManager.createQuery("SELECT h FROM House h WHERE h.id=:id").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<House> findAll() {
        return entityManager.createQuery("SELECT h FROM  House  h").getResultList();
    }
}
