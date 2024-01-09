# library-management
ncs assignment

how to run?

```
1: install jdk  
2: install maven  
3: install git  
4: open a terminal, run : git clone  https://github.com/janone/library-management.git  
5: cd to the project directory(cd library-management), then run command : mvn package  
6: cd to server 'target' directory(cd lms-server/target), then run : java -jar lms-server-jar-with-dependencies.jar  
7: open a new terminal, cd to client 'target' directory(cd lms-client/target), then run : java -jar lms-client-jar-with-dependencies.jar
8: there are two initial accounts: root/root11 and user1/user11.
```

- It is divided into two sides, server side and client side, so that it can be used by multiple people in the same time.

- and it is a command line interface. when you run client, it will ask you to login.

- in previous version, it is also a command line interface. but it was not divided into two sides, and I tended to do that and explain why I used controller and MVC architecture in face to face presentation.

- it is a hurry version, it is not that perfect but enough for demonstration.

- thanks for your review.
