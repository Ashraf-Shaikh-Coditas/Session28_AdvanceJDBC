package Week6.Session28_JDBC_Advance;

import java.sql.*;
import java.util.Formatter;

public class Teacher_QueSix {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "root");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql;

            sql = "SELECT * FROM Teacher";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING TEACHER TABLE -------------------------------");
            fmt.format("%15s %15s %20s\n", "ID", "Name", "Designation");
            while (rs.next()) {
                fmt.format("%15s %15s %20s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3));
            }
            System.out.println(fmt);

            // INSERTING ROW USING RESULTSET.
            rs.moveToInsertRow();
            rs.updateInt(1, 6);
            rs.updateString(2, "Vishal");
            rs.updateString(3, "Assistant Professor");
            rs.insertRow();

            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING TEACHER TABLE AFTER INSERTION -------------------------------");
            fmt.format("%15s %15s %20s\n", "ID", "Name", "Designation");
            while (rs.next()) {
                fmt.format("%15s %15s %20s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3));
            }
            System.out.println(fmt);

            // UPDATING RECORD AT INDEX 5

            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("id") == 5) {
                    rs.updateString(2, "Saniya");
                    rs.updateString(3, "Senior Professor");
                    rs.updateRow();
                }
            }

            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING TEACHER TABLE AFTER UPDATION -------------------------------");
            fmt.format("%15s %15s %20s\n", "ID", "Name", "Designation");
            while (rs.next()) {
                fmt.format("%15s %15s %20s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3));
            }
            System.out.println(fmt);

            // DELETING RECORD AT INDEX 6

            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("id") == 6) {
                    rs.deleteRow();
                }
            }

            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING TEACHER TABLE AFTER DELETION -------------------------------");
            fmt.format("%15s %15s %20s\n", "ID", "Name", "Designation");
            while (rs.next()) {
                fmt.format("%15s %15s %20s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3));
            }
            System.out.println(fmt);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
-------------------------PRINING TEACHER TABLE -------------------------------
             ID            Name          Designation
              1          Gaurav  Assistant Professor
              2         Pallavi  Assistant Professor
              3           Sunil     Senior Professor
              4            Ajit                  HOD
              5          Pankaj  Associate Professor


-------------------------PRINING TEACHER TABLE AFTER INSERTION -------------------------------
             ID            Name          Designation
              1          Gaurav  Assistant Professor
              2         Pallavi  Assistant Professor
              3           Sunil     Senior Professor
              4            Ajit                  HOD
              5          Pankaj  Associate Professor
              6          Vishal  Assistant Professor        // Inserted


-------------------------PRINING TEACHER TABLE AFTER UPDATION -------------------------------
             ID            Name          Designation
              1          Gaurav  Assistant Professor
              2         Pallavi  Assistant Professor
              3           Sunil     Senior Professor
              4            Ajit                  HOD
              5          Saniya     Senior Professor               // Updated
              6          Vishal  Assistant Professor


-------------------------PRINING TEACHER TABLE AFTER DELETION -------------------------------
             ID            Name          Designation
              1          Gaurav  Assistant Professor
              2         Pallavi  Assistant Professor
              3           Sunil     Senior Professor
              4            Ajit                  HOD
              5          Saniya     Senior Professor          // Deleted index 6 Record

* */