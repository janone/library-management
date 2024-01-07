package org.example.rpc;


import org.example.common.GenerateUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ClientFactory {

    private ClientFactory() {

    }

    private static ClientFactory instance = new ClientFactory();

    public static ClientFactory getInstance() {
        return instance;
    }

    private Map<Object, Object> controllerContainer = new HashMap<>();

    public static <T> T getController(Class<T> controller){
        Map<Object, Object> container = instance.controllerContainer;
        if(container.containsKey(controller)){
            return (T) container.get(controller);
        }

        synchronized (controller){
            if(container.containsKey(controller)){
                return (T) container.get(controller);
            }

            T bean = GenerateUtil.generate(controller);

            InvocationHandler handler = new ControllerClientHandler(bean);
            Object proxy = Proxy.newProxyInstance(controller.getClassLoader(), bean.getClass().getInterfaces(), handler);
            container.put(controller, proxy);
            container.put(bean, proxy);
            container.put(controller.getName(), proxy);
            return (T) proxy;
        }

    }




}
