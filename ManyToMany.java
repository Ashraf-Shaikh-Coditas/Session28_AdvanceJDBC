package Week6.Session28_JDBC_Advance;

import java.sql.*;
import java.util.Formatter;

public class ManyToMany {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodbtwo", "root", "root");
            st = con.createStatement();
            String sql;

            sql = "SELECT * FROM TeacherO_M";
            rs=st.executeQuery(sql);

            System.out.println("-------------------------PRINING TEACHER TABLE -------------------------------");
            fmt.format("%15s %15s\n", "ID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            sql = "SELECT * FROM StudentO_M";
            rs=st.executeQuery(sql);

            System.out.println("-------------------------PRINING STUDENT TABLE -------------------------------");
            fmt.format("%15s %15s\n", "SID", "Name");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println(fmt);

            sql = "SELECT * FROM Bridge";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING Many to Many Relationship TABLE -------------------------------");
            fmt.format("%15s %15s\n", "TID", "SID");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getInt(1), rs.getInt(2));
            }
            System.out.println(fmt);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*

-------------------------PRINING TEACHER TABLE -------------------------------
             ID            Name
              1    Prof. Vishal
              2     Prof. Ajita
              3   Prof. Aradhna
              4    Prof. Gaurav
              5 Prof. Chaitanya


-------------------------PRINING STUDENT TABLE -------------------------------
            SID            Name
              1          Ashraf
              2          Swaraj
              3         Hussain
              4            Ajay
              5          Sohail
              6         Shaibaz
              7            Sara
              8             Sam
              9            Alex
             10            Azim
             11            Allu
             12          Sangha
             13           Varun
             14            Zayn
             15            Zack


-------------------------PRINING Many to Many Relationship TABLE -------------------------------
            TID             SID
              1               1
              1               2
              1               3
              2               1
              3               1
              2               2
              2               3
              2               4
              3               2
              3               4





* */