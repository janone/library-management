package org.example.rpc;


import org.example.common.Result;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ControllerClientHandler implements InvocationHandler {

    private Object target;


    public ControllerClientHandler(Object target) {
        this.target = target;
    }

    private static List<String> ignoreMethodList = Arrays.asList("toString","hashCode","equals","getClass");

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getReturnType()!= Result.class) {
            return method.invoke(target,args);
        }

        Class<?> anInterface = target.getClass().getInterfaces()[0];
        String fullInterfaceName = anInterface.getName();
        String methodName = method.getName();

        Request request = new Request();
        request.setController(fullInterfaceName);
        request.setMethod(methodName);
        request.setParams(args);

        Session session = Session.getInstance();
        request.setToken(session.getSessionId());

        session.send(request);
        Result receive = session.receive();
        return receive;


    }
}
