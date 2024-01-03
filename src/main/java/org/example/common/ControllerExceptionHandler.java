package org.example.common;

import org.example.common.responsibilitychain.Chain;
import org.example.common.responsibilitychain.ChainListImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerExceptionHandler implements InvocationHandler {
    private Object target;


    public ControllerExceptionHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {

//            System.out.println("Before method call " + method.getName());
            ChainListImpl chainList = BeanFactory.getInstance().getChainList();
            ChainListImpl replica = chainList.replica();
            replica.doFilter(null,args);

            result = method.invoke(target,args);

//            System.out.println("After method call " + method.getName());
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof BusinessException)) {
                e.printStackTrace();
            }
            result = Result.fail(e.getTargetException().getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
