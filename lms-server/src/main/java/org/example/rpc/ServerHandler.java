package org.example.rpc;

import org.example.common.BeanFactory;
import org.example.common.Result;
import org.example.controller.IUserController;
import org.example.service.UserService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
                String token = receive.getToken();

                String controller = receive.getController();
                String method = receive.getMethod();

                boolean b = checkLogin(controller, method, token);
                if (!b) {
                    session.send(Result.fail("please login"));
                    break;
                }

                Object[] params = receive.getParams();
                if (controller == null || method == null) {
                    session.send(Result.fail("please specify the controller and method"));
                    break;
                }

                Object bean = BeanFactory.getBean(controller);

                Method declaredMethod;
                //获取params的type数组
                if (params != null && params.length != 0) {
                    Class<?>[] paramTypes = new Class[params.length];
                    for (int i = 0; i < params.length; i++) {
                        paramTypes[i] = params[i].getClass();
                    }
                    declaredMethod = bean.getClass().getDeclaredMethod(method,paramTypes);
                } else {
                    declaredMethod = bean.getClass().getDeclaredMethod(method);
                }


                Object invokeResult = declaredMethod.invoke(bean, params);

                session.send((Result) invokeResult);

            } catch (Exception e) {
                e.printStackTrace();
                session.send(Result.fail(e.getMessage()));

            }


        }
    }

    private List<String> noneedLogin = Arrays.asList("login","register","getUserByAccount");


    public boolean checkLogin(String controller, String method, String token){

        if(controller.equals(IUserController.class.getName()) && noneedLogin.contains(method)){
            return true;
        }

        if(token == null){
            return false;
        }

        UserService userService = BeanFactory.getBean(UserService.class);
        return userService.isLogin(token);
    }
}
