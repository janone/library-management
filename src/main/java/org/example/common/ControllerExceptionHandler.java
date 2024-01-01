package org.example.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ControllerExceptionHandler implements InvocationHandler {
    private Object target;

    public ControllerExceptionHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try{

            System.out.println("Before method call " + method.getName());
            result = method.invoke(target, args);
            System.out.println("After method call " + method.getName());

        } catch (Exception e){
            e.printStackTrace();
            result = Result.fail(e.getMessage());
        }

        return result;
    }
}
