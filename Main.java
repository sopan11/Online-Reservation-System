import java.sql.Connection;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        UserInfo info = new UserInfo();

        i = info.validateUser();
        if (i > 0) {
            do{
                System.out.println("1.Insert Record\n2.Delete Record\n3.Display all Records\n0.Exit");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        info.insertInfo();
                        break;

                    case 2:
                        info.deleteInfo();
                        break;

                    case 3:
                        info.displayAll();
                        break;
                    default:
                        System.out.println("Enter valid choice");
                }
                System.out.println("Do you want to update record:(y/n): ");
            }while (sc.next().equals("y"));
        }
        else{
            System.out.println("Please enter valid username and password");
        }
    }
}