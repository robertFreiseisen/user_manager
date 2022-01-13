import entities.Userelement;
import repositories.UserRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the easiest UserManager :)");

        UserRepository userRepo = new UserRepository("default");

        Scanner sc = new Scanner(System.in);
        boolean isLoginIn = true;
        boolean isRunning = true;

        // TODO: 13/01/2022 Make Register here 

        int trys = 0;

        while (isLoginIn){
            System.out.print("User: ");
            String userName = sc.nextLine();
            System.out.print("Password: ");
            String passwordIn = sc.nextLine();

            Userelement user = userRepo.getByUserName(userName);


            if (user.getHashedpassword().equals(Md5Hasher.getMd5(passwordIn))){
                isLoginIn = false;
            } else {
                System.out.println("Login false try again!");
                trys++;

                if (trys >= 3){
                    System.out.println("No more changes left! ...");
                    System.out.println("Program is finished!");

                    isRunning = false;
                }
            }
        }

        while (isRunning){
            System.out.println("Menü: ");
            System.out.println("+ ... add User");
            System.out.println("- ... delete User");
            System.out.println("f ... find User by Id");
            System.out.println("a ... print all");
            System.out.println("e ... exit");

            Scanner in = new Scanner(System.in);

            String input = in.nextLine();

            switch (input) {
                case "e":
                    isRunning = false;
                    break;

                case "+":
                    System.out.print("UserName: ");
                    String name = in.nextLine();
                    System.out.print("Password: ");
                    String password = in.nextLine();


                    Userelement user = new Userelement();
                    user.setId(null);
                    user.setName(name);
                    user.setHashedpassword(Md5Hasher.getMd5(password));
                    userRepo.createUser(user);
                    break;

                case "a":
                    for (Userelement item : userRepo.getAll()) {
                        System.out.println(item);
                    }
                    break;

                case "-":
                case "f":
                    System.out.print("UserId: ");
                    int id = in.nextInt();

                    if (input.equals("-")){
                        userRepo.deleteUser(id);
                        System.out.println("User deleted!");
                    } else {
                        Userelement foundUser = userRepo.findUserById(id);
                        if (foundUser != null) {
                            System.out.println(foundUser);
                        } else {
                            System.out.println("User not found!");
                        }
                    }

                    break;

                default:

                    System.out.println("Invalid command!");
                    System.out.println("Please try again!");
                    break;
            }
        }
    }
}
