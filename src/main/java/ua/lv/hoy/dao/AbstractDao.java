package ua.lv.hoy.dao;


import java.util.List;

public abstract class AbstractDao<T> {
    public abstract void add(T t);
    public abstract void edit(T t);
    public abstract void delete(T t);
}
