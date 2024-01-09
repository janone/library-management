package org.example.view;

import org.example.common.ReturnException;
import org.example.entity.User;

public class MainView extends View{

    private static User loginUser = null;

    @Override
    public Object show(User user){
        UserLoginRegisterView userLoginRegisterView = new UserLoginRegisterView();
        loginUser = (User) userLoginRegisterView.show(null);// login success

        if(loginUser == null) {
            System.out.println("unexpected login failed. system exit");
            return null;
        }

        while(true){
            showMenu();
            System.out.println("please choose an operation");
            String operation = scanner.next();

            try{

                if(operation.equalsIgnoreCase("A")){
                    new AddBookView().showRepetitively(loginUser);
                }
                else if(operation.equalsIgnoreCase("D")){
                    new DeleteBookView().showRepetitively(loginUser);
                }
                else if(operation.equalsIgnoreCase("G")){
                    new UpgradeAccountView().showRepetitively(loginUser);
                }
                else if(operation.equalsIgnoreCase("L")){
                    new ListBooksView().show(loginUser);
                }
                else if(operation.equalsIgnoreCase("S")){
                    new SearchBooksView().showRepetitively(loginUser);
                }
                else if(operation.equalsIgnoreCase("B")){
                    new BorrowBooksView().showRepetitively(loginUser);
                }
                else if(operation.equalsIgnoreCase("R")){
                    new ReturnBooksView().showRepetitively(loginUser);
                }
                else if(operation.equalsIgnoreCase("O")){
                    loginUser = (User) new UserLoginRegisterView().show(null);
                }
                else if(operation.equalsIgnoreCase("E")){
                    System.exit(0);
                } else {
                    System.out.println("unexpected operation '"+ operation +"'. please try again");
                }


            } catch (ReturnException e){
                // do nothing. go to next loop
            }



        }



    }

    private static void showMenu() {
        System.out.println(" = = = = = = = = = = = = = = = = = = =");
        if(loginUser.getIsAdmin()){
            System.out.print("  (A): add books");
            System.out.print("  (D): delete books");
            System.out.println("  (G): upgrade an account to admin");
        }
        System.out.print("  (L): list all books");
        System.out.print("  (S): search books");
        System.out.println("  (B): borrow books");
        System.out.print("  (R): return books");
        System.out.print("  (O): logout");
        System.out.println("  (E): exit system");
        System.out.println("note: you can type '" + RETURN_COMMAND + "' at any time to return to this page.");
        System.out.println(" - - - - - - - - - - - - - - - - - - -");
    }

}
