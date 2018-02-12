package ua.lv.hoy.dao.implementation;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.hoy.dao.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AbstractDaoImpl extends AbstractDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;


    @Override
    public void add(Object o) {
        entityManager.persist((o));
    }

    @Override
    public void edit(Object o) {
        entityManager.merge(o);
    }

    @Override
    public void delete(Object o) {
        entityManager.remove(o);
    }

}
