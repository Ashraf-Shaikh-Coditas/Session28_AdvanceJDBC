package Week6.Session28_JDBC_Advance;

import java.sql.*;
import java.util.Formatter;

public class TransactionDemo {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "root");
            con.setAutoCommit(false);
            st = con.createStatement();
            String sql;

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            con.commit();

            System.out.println("-------------------------PRINING PEOPLE TABLE -------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            sql = "UPDATE People SET name = 'Dummy'";
            st.executeUpdate(sql);

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("------------------------- PEOPLE TABLE BEFORE ROLLBACK-------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            con.rollback();   // performing rollback;

            sql = "SELECT * FROM People";
            rs = st.executeQuery(sql);

            System.out.println("------------------------- PEOPLE TABLE AFTER ROLLBACK-------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
-------------------------PRINING PEOPLE TABLE -------------------------------
             ID            Name
              1          Ashraf
              2            Aman
              3         Shaibaz
              4          Shoaib
              5            Akil

-------------------------PEOPLE TABLE BEFORE ROLLBACK -------------------------------
             ID            Name
              1           Dummy
              2           Dummy
              3           Dummy
              4           Dummy
              5           Dummy

------------------------- PEOPLE TABLE AFTER ROLLBACK-------------------------------
             ID            Name
              1          Ashraf
              2            Aman
              3         Shaibaz
              4          Shoaib
              5            Akil




* */