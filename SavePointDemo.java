package Week6.Session28_JDBC_Advance;

import java.sql.*;
import java.util.Formatter;

public class SavePointDemo {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodbtwo", "root", "root");
            con.setAutoCommit(false);
            st = con.createStatement();
            String sql;

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING PEOPLE TABLE -------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);
//
            Savepoint sp1 = con.setSavepoint("SP1");  // save point 1

            sql = "DELETE FROM People where id =3";
            st.executeUpdate(sql);

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING PEOPLE AFTER DELETING ID 3 -------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            Savepoint sp2 = con.setSavepoint("SP2"); // save point 2

            sql = "DELETE FROM People where id =5";
            st.executeUpdate(sql);

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING PEOPLE AFTER DELETING ID 5 -------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            con.rollback(sp2);        // rollback

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING PEOPLE AFTER ROLLBACKING  TO SP2-------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);


            con.rollback(sp1);            // rollback

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING PEOPLE AFTER ROLLBACKING  TO SP1-------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            con.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
-------------------------PRINING PEOPLE TABLE -------------------------------
             ID            Name
              1          Ashraf
              2          Swaraj
              3           Tejas
              4            John
              5              DK
              6            Loyd

-------------------------PRINING PEOPLE AFTER DELETING ID 3 -------------------------------
             ID            Name
              1          Ashraf
              2          Swaraj              // id 3 deleted
              4            John
              5              DK
              6            Loyd

-------------------------PRINING PEOPLE AFTER DELETING ID 5 -------------------------------
             ID            Name
              1          Ashraf
              2          Swaraj
              4            John        // id 5 deleted
              6            Loyd


-------------------------PRINING PEOPLE AFTER ROLLBACKING  TO SP2-------------------------------
             ID            Name
              1          Ashraf
              2          Swaraj
              4            John
              5              DK             // id 5 restored
              6            Loyd

-------------------------PRINING PEOPLE AFTER ROLLBACKING  TO SP1-------------------------------
             ID            Name
              1          Ashraf
              2          Swaraj
              3           Tejas              // id 3 restored
              4            John
              5              DK
              6            Loyd



* */
