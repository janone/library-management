package org.example.dao;

import org.example.annotation.IDField;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;

public class BaseDao<T> {

    private String getIdFieldName;

    public BaseDao() {
        Type type = this.getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            // 获取第一个泛型参数的类型
            Class<?> genericArg1 = (Class<?>) parameterizedType.getActualTypeArguments()[0];

//            System.out.println("泛型参数类型为：" + genericArg1);

            Field[] fields = genericArg1.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String fieldName = field.getName();
                String capitalizedFieldName = capitalizedFieldName(fieldName);
                String getMethodName = "get" + capitalizedFieldName;
                IDField annotation = field.getAnnotation(IDField.class);
                if (annotation != null) {
                    this.getIdFieldName = getMethodName;
                }
            }
        } else {
            System.out.println("该类没有定义泛型参数");
        }
    }

    Map<Serializable, T> storage = new HashMap<>();

    private String capitalizedFieldName(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private Serializable getIdByEntity(T t) {
        try {
            Method method = t.getClass().getMethod(this.getIdFieldName);
            return (Serializable) method.invoke(t);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    public T insert(T t) {
        storage.put(getIdByEntity(t), t);
        return t;
    }

    public boolean delete(Serializable id) {
        T t = storage.remove(id);
        if (t != null) {
            return true;
        }
        return false;
    }

    public boolean update(T t) {
        Serializable id = getIdByEntity(t);
        storage.put(id, t);
        return true;
    }

    public T getById(Serializable id) {
        return storage.get(id);
    }

    public List<T> list(T t) {
        try {

            Collection<T> values = storage.values();
            List<T> result = new ArrayList<>(values);

            if(t == null) return result;

            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Class<?> type = field.getType();
                IDField annotation = field.getAnnotation(IDField.class);
                if(annotation != null){
                    // id field then the field value should be absolutely the same
                    result = Arrays.asList(getById((Serializable) field.get(t)));
                } else {
                    if (type == String.class) {
                        String param = (String) field.get(t);
                        if(param == null) continue;

                        for (T dbObj : values) {
                            String dbFiledValue = (String) field.get(dbObj);
                            if (!dbFiledValue.toLowerCase().contains(param)) {
                                result.remove(dbObj);
                            }
                        }
                    } else {
                        Object param = field.get(t);
                        if(param == null) continue;

                        for (T dbObj : values) {
                            Object dbFiledValue = field.get(dbObj);
                            if (!dbFiledValue.equals(param)) {
                                result.remove(dbObj);
                            }
                        }
                    }
                }

            }
            return result;

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    public List<T> list() {
        return list(null);
    }

}
