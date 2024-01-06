package org.example.common;

import org.example.annotation.AutoWiredField;
import org.example.common.responsibilitychain.Chain;
import org.example.common.responsibilitychain.ChainListImpl;
import org.example.controller.IController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * a bean factory.
 * get bean method will return the object you want, but only IController will be proxy.
 * （because jdk proxy need interface. I don't want to generate too many interfaces, so I proxy IController only for demonstration）
 */
public class BeanFactory {

    private static BeanFactory factory = new BeanFactory();
    private ChainListImpl chainList = new ChainListImpl();

    public ChainListImpl getChainList() {
        return chainList;
    }

    private BeanFactory() {
    }



    public static BeanFactory getInstance(){
        return factory;
    }



    private Map<Class<?>, Object> proxyContainer = new HashMap<>();
    private Map<Class<?>, Object> rowTypeContainer = new HashMap<>();
    private List<Object> sourceTypeRegister = new ArrayList<>();

    /**
     * get bean from the bean factory
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getBean(Class<T> clazz, Boolean isClient) {

        if(isClient){

        }

        if(clazz.isInterface()){
            throw new IllegalStateException("should not pass interface");
        }
        Object bean = factory.getProxyContainer().get(clazz);

        try{
            if(bean == null){

                if(factory.rowTypeContainer.containsKey(clazz)){
                    return (T)factory.rowTypeContainer.get(clazz);
                }

                synchronized (clazz) {

                    if(factory.getProxyContainer().containsKey(clazz)) {
                        return (T) factory.getProxyContainer().get(clazz);
                    }

                    if(factory.rowTypeContainer.containsKey(clazz)){
                        return (T)factory.rowTypeContainer.get(clazz);
                    }

                    bean = clazz.getDeclaredConstructor().newInstance();
                    factory.rowTypeContainer.put(clazz,bean);
                    populateBean(bean);
                    factory.rowTypeContainer.remove(clazz,bean);
                    if(bean instanceof IController){

                        Class<?>[] interfaces = clazz.getInterfaces();
                        for (Class<?> anInterface : interfaces) {
                            InvocationHandler handler = new ControllerExceptionHandler(bean);
                            Object proxy = Proxy.newProxyInstance(anInterface.getClassLoader(), bean.getClass().getInterfaces(), handler);
                            factory.getProxyContainer().put(anInterface, proxy);
                            factory.getProxyContainer().put(clazz, proxy);
                        }
                    } else if(bean instanceof Chain){
                        factory.getChainList().addChain((Chain) bean);
                    }else {
                        factory.getProxyContainer().put(clazz,bean);
                    }
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return (T) factory.getProxyContainer().get(clazz);
    }

    public static <T> T getClient(Class<T> clazz) {
        return getBean(clazz,true);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz,false);
    }


    private static void populateBean(Object bean) throws IllegalAccessException {
        Class<?> aClass = bean.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            AutoWiredField annotation = field.getAnnotation(AutoWiredField.class);
            if(annotation != null){
                field.set(bean, getBean(field.getType()));
            }
        }
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

//    private void register(Object o){
//        sourceTypeRegister.add(o);
//    }

//    public void registerToBeanFactory(Object ... array){
//        Arrays.stream(array).forEach(this::register);
//        init();
//    }



//        public void init() {

//        for (Object o : sourceTypeRegister) {
//            if(o instanceof IController){
//
//                Class<?>[] interfaces = o.getClass().getInterfaces();
//                for (Class<?> anInterface : interfaces) {
//                    InvocationHandler handler = new ControllerExceptionHandler(o);
//                    Object proxy = Proxy.newProxyInstance(anInterface.getClassLoader(), o.getClass().getInterfaces(), handler);
//                    proxyContainer.put(anInterface, proxy);
//                }
//            }
//        }


//    }




}
