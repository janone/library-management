package org.example.common;

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

        if(method.getReturnType() != Result.class){
            return method.invoke(target,args);
        }

        Object result = null;
        try {

//            System.out.println("Before method call " + method.getName());
            ChainListImpl chainList = BeanFactory.getInstance().getChainList();
            // every ChainList has a pointer. every invocation should own one for isolation
            ChainListImpl replica = chainList.replica();
            replica.addChain((chain,args1)-> method.invoke(target,args));
            result = replica.doFilter(null,args);


//            result = method.invoke(target,args);

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
