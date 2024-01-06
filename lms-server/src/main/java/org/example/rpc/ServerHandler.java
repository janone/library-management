package org.example.rpc;

import org.example.common.BeanFactory;
import org.example.common.Result;

import java.lang.reflect.Method;
import java.util.Map;

public class ServerHandler implements Runnable {

    private Session session;
    public ServerHandler(Session session) {
        this.session = session;
    }

    @Override
    public void run() {

        while(true){


            try {
                // 1 get received object
                Request receive = session.receive();
                String controller = receive.getController();
                String method = receive.getMethod();
                Object[] params = receive.getParams();
                if (controller == null || method == null) {
                    session.send(Result.fail("please specify the controller and method"));
                    break;
                }

                Object bean = BeanFactory.getBean(controller);

                Method declaredMethod = bean.getClass().getDeclaredMethod(method);

                Object invoke = declaredMethod.invoke(bean, params);

                session.send((Result) invoke);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }


        // 2 process the object
    }
}
