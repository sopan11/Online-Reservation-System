import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Database_Connection {
    Scanner sc = new Scanner(System.in);
    private PreparedStatement pstate = null;
    private Connection con = null;
    private ResultSet resultSet = null;
    int i = 0;
    public Connection connection(){
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public int checkUser(){
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();

        try{
            con = connection();
            pstate = con.prepareStatement("Select * from userLogin where username=? and password=?");
            pstate.setString(1, username);
            pstate.setString(2, password);
            i = pstate.executeUpdate();

        }
        catch (Exception e){
            System.out.println();
        }
        return i;
    }

    public int insertRecord(List<User> listUser){
        User user = listUser.get(0);

        try{
            pstate = con.prepareStatement("insert into reservation values (?, ?, ?, ?, ?, ?, ?)");
            System.out.println("PNR no: "+user.getPnrNumber());
            pstate.setInt(1, user.getPnrNumber());
            pstate.setString(2, user.getPassengerName());
            pstate.setInt(3, user.getTrainNumber());
            pstate.setString(4, user.getClassType());
            pstate.setString(5, user.getJourneyDate());
            pstate.setString(6, user.getFrom());
            pstate.setString(7, user.getTo());

            i = pstate.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return i;
    }

    public int deleteRecord(int pnrNo){
        try{
            pstate = con.prepareStatement("delete from reservation where pnr_number = ?");
            pstate.setInt(1, pnrNo);
            i = pstate.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return i;
    }

    public void displayRecords(){
        try {
            pstate = con.prepareStatement("select * from reservation");
            resultSet = pstate.executeQuery();

            if (resultSet.next()) {
                do{
                    System.out.println("PNR Number: " + resultSet.getInt(1));
                    System.out.println("Passenger Name: " + resultSet.getString(2));
                    System.out.println("Train Number: " + resultSet.getInt(3));
                    System.out.println("Class Type: " + resultSet.getString(4));
                    System.out.println("Journey Date: " + resultSet.getString(5));
                    System.out.println("From station: " + resultSet.getString(6));
                    System.out.println("To station: " + resultSet.getString(7));
                } while(resultSet.next());
            }
            else{
                System.out.println("No record found");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}

