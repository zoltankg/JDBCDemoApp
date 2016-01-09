import java.sql.*;
import java.util.Scanner;


/**
 * Created by condor on 26/02/15.
 * FastTrackIT, 2015
 * <p/>
 * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
 *
 * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
 * repeating the same common code multiple times
 *
 * BTW, exercise 1: how we reorg this/refactor in small pieces
 */
public class DemoCRUDOperations {


    public static void main(String[] args) {
        System.out.println("Hello database users! We are going to call DB from Java");
        try {
            //demo CRUD operations
            //demoCreate();
            //demoRead();
            //demoUpdate();
            //demoRead();
            //demoDelete();
            boolean gasit=false;
            gasit=login("ionel@gmail.com", "Ionel123");
            if(gasit)
                System.out.println("bravooo");
            else
                System.out.println("gresit!!!");

           // demoBlobInsert();
           // demoBlobRead();

            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("email");
                String email = sc.nextLine();
                System.out.println("password");
                String passw = sc.nextLine();
                if (login(email, passw)){
                    System.out.println("exista");
                    break;
                }
                else {
                    System.out.println("mai incearca!");
                }


            }
            while(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static boolean login(String email, String password)throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Zoltan";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        String q = "SELECT * FROM users WHERE email='"+ email + "'and password='" + password + "'";
        System.out.println(q);
        ResultSet rs = st.executeQuery(q);


        // 6. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("full_name").trim());
            System.out.print("---");
            System.out.print(rs.getString("email").trim());
            System.out.print("---");
            System.out.print(rs.getString("password").trim());
            System.out.println(" ");
            return true;
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return false;
    }

    private static void demoCreate() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Zoltan";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO USERS (FULL_NAME, EMAIL, PASSWORD) VALUES (?,?,?)");
        pSt.setString(1, "mihai");
        pSt.setString(2, "mihai@gmail.com");
        pSt.setString(3, "mihai123");

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    private static void demoRead() throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Zoltan";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT full_name, email, password FROM users");

        // 6. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("full_name").trim());
            System.out.print("---");
            System.out.print(rs.getString("email").trim());
            System.out.print("---");
            System.out.print(rs.getString("password").trim());
            System.out.println(" ");
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }

    private static void demoUpdate() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Zoltan";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE USERS SET FULL_NAME=?,PASSWORD=? WHERE EMAIL=?"); //so we have 3 params
        pSt.setString(1, "dani");
        pSt.setString(2, "pass1");
        pSt.setString(3, "password1");


        // 5. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }


    private static void demoDelete() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Agenda_Zoltan";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM USERS WHERE FULL_NAME=?");
        pSt.setString(1, "dani");

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 6. close the objects
        pSt.close();
        conn.close();
    }
}

