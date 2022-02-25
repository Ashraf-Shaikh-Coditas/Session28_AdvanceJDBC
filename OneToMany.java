package Week6.Session28_JDBC_Advance;

import java.sql.*;
import java.util.Formatter;

public class OneToMany {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try{
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
            fmt.format("%15s %15s %15s\n", "SID", "Name","TID");
            while (rs.next()) {
                fmt.format("%15s %15s %15s\n", rs.getInt(1), rs.getString(2),rs.getInt(3));
            }
            System.out.println(fmt);

            sql = "select TeacherO_M.tname,StudentO_M.sname from TeacherO_M,StudentO_M where TeacherO_M.tid=StudentO_M.tid;";
            rs=st.executeQuery(sql);

            System.out.println("-------------------------PRINING ONE TO MANY RELATIONSHIP -------------------------------");
            fmt.format("%15s %15s\n", "TName", "SName");
            while (rs.next()) {
                fmt.format("%15s %15s\n", rs.getString(1), rs.getString(2));
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
            SID            Name             TID
              1          Ashraf               1
              2          Swaraj               1
              3         Hussain               1
              4            Ajay               2
              5          Sohail               2
              6         Shaibaz               2
              7            Sara               3
              8             Sam               3
              9            Alex               3
             10            Azim               4
             11            Allu               4
             12          Sangha               4
             13           Varun               5
             14            Zayn               5
             15            Zack               5

-------------------------PRINING ONE TO MANY RELATIONSHIP -------------------------------
          TName           SName
   Prof. Vishal          Ashraf
   Prof. Vishal          Swaraj
   Prof. Vishal         Hussain
    Prof. Ajita            Ajay
    Prof. Ajita          Sohail
    Prof. Ajita         Shaibaz
  Prof. Aradhna            Sara
  Prof. Aradhna             Sam
  Prof. Aradhna            Alex
   Prof. Gaurav            Azim
   Prof. Gaurav            Allu
   Prof. Gaurav          Sangha
Prof. Chaitanya           Varun
Prof. Chaitanya            Zayn
Prof. Chaitanya            Zack



* */