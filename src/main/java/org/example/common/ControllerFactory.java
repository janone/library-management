package org.example.common;

import org.example.controller.IController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

public class ControllerFactory {

    private static ControllerFactory factory = new ControllerFactory();

    private ControllerFactory() {

    }

    public static ControllerFactory getInstance(){
        return factory;
    }



    private Map<Class<?>, Object> proxyContainer = new HashMap<>();
    private List<Object> sourceTypeRegister = new ArrayList<>();

    public static <T> T getBean(Class<T> clazz){
        Object bean = factory.getProxyContainer().get(clazz);
        return (T)bean;
    }

    public Map<Class<?>, Object> getProxyContainer() {
        return proxyContainer;
    }

    public void setProxyContainer(Map<Class<?>, Object> proxyContainer) {
        this.proxyContainer = proxyContainer;
    }

    public List<Object> getSourceTypeRegister() {
        return sourceTypeRegister;
    }

    public void setSourceTypeRegister(List<Object> sourceTypeRegister) {
        this.sourceTypeRegister = sourceTypeRegister;
    }

    private void register(IController o){
        sourceTypeRegister.add(o);
    }

    public void registerToBeanFactory(IController... array){
        Arrays.stream(array).forEach(o->{
            register(o);
        });
        init();
    }



        public void init() {

        for (Object o : sourceTypeRegister) {
            if(o instanceof IController){

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
