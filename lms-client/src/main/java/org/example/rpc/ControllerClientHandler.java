package org.example.rpc;

import org.example.common.BeanFactory;
import org.example.common.BusinessException;
import org.example.common.Result;
import org.example.common.responsibilitychain.ChainListImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerClientHandler implements InvocationHandler {

    private Object target;


    public ControllerClientHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> anInterface = target.getClass().getInterfaces()[0];
        String fullInterfaceName = anInterface.getName();
        String methodName = method.getName();

        Request request = new Request();
        request.setController(fullInterfaceName);
        request.setMethod(methodName);
        request.setParams(args);

        Session session = Session.getInstance();
        session.send(request);
        return session.receive();


    }
}
