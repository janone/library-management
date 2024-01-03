package org.example.service;


import org.example.dao.BaseDao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * base service that provide common methods
 * @param <D>
 * @param <E>
 */
public abstract class BaseService<D extends BaseDao,E> {

    protected D d;

    public BaseService(){
        Type type = this.getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            // 获取第一个泛型参数的类型
            Class<?> genericArg1 = (Class<?>) parameterizedType.getActualTypeArguments()[0];

//            System.out.println("泛型参数类型为：" + genericArg1);

            try {
                d = (D)genericArg1.getDeclaredConstructor().newInstance();

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("该类没有定义泛型参数");
        }
    }

    public E insert(E e){
        return (E) d.insert(e);
    }

    public boolean delete(Serializable id){
        return d.delete(id);
    }

    public boolean update(E e){
        return d.update(e);
    }

    public E getById(Serializable id){
        return (E) d.getById(id);
    }

    /**
     * this method provide 'and' logic for all field criteria.
     * if a field is a string. program will lowercase the field to compare with database.
     * and other type will use equals method to compare.
     * @param e
     * @return List<E>
     */
    public List<E> list(E e){
        return d.list(e);
    }

    public List<E> list(){
        return d.list();
    }

}
