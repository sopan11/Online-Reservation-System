import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserInfo {
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Database_Connection db_Connection = new Database_Connection();
    private Connection con = null;
    private List listUser = new ArrayList();
    private static int i=0;

    public int validateUser(){
        i = db_Connection.checkUser();
        return i;
    }

    public void insertInfo() {
        con = db_Connection.connection();
        if (con != null) {
            int pnr_number = random.nextInt(1000, 10000);
            System.out.println("Enter Passenger Name: ");
            String passengerName = sc.next();
            System.out.println("Enter Train Number: ");
            int train_Number = sc.nextInt();
            System.out.println("Enter Class Type: ");
            String classType = sc.next();
            System.out.println("Enter Journey Date: ");
            String joureyDate = sc.next();
            System.out.println("Enter From station: ");
            String fromStation = sc.next();
            System.out.println("Enter To station: ");
            String toStation = sc.next();

            User user = new User(pnr_number, passengerName, train_Number, classType, joureyDate, fromStation, toStation);
            listUser.add(user);

            i = db_Connection.insertRecord(listUser);
            if(i>0){
                System.out.println("New Record inserted successfully");
            }
        }
    }

    public void deleteInfo(){
        System.out.println("Enter PNR Number to delete record: ");
        int pnrNo = sc.nextInt();

        i = db_Connection.deleteRecord(pnrNo);
        if(i>0){
            System.out.println("Record deleted successfully");
        }
        else {
            System.out.println("Some error occurred to delete record");
        }
    }

    public void displayAll(){
        db_Connection.displayRecords();
    }
}