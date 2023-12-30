package org.example.common;

import org.example.controller.Controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerFactory {

    public Map<Class<?>, Object> proxyContainer = new HashMap<>();
    public List<Object> sourceTypeRegister = new ArrayList<>();

    public <T> T getBean(Class<T> clazz){
        Object bean = proxyContainer.get(clazz);
        return (T)bean;
    }


    public void register(Object o){
        sourceTypeRegister.add(o);
    }

    public void init() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        for (Object o : sourceTypeRegister) {
            if(o instanceof Controller){

                Class<?>[] interfaces = o.getClass().getInterfaces();
                for (int i = 0; i < interfaces.length; i++) {
                    Class<?> anInterface = interfaces[i];
                    InvocationHandler handler = new ControllerExceptionHandler(o);
                    Object proxy = Proxy.newProxyInstance(anInterface.getClassLoader(), o.getClass().getInterfaces(), handler);
                    proxyContainer.put(anInterface,proxy);

                }
            }
        }


    }




}
