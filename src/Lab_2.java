import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.sql.*;

public class Lab_2 {
    public static void main(String[] args) throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("DESKTOP-QD7TAVI");
        ds.setPortNumber(1433);
        ds.setDatabaseName("Group_11");


        try (Connection conn = ds.getConnection()) {
            System.out.println("Connect Success");
            System.out.println(conn.getCatalog());

            var sql = "select * from dbo.Members";
            var st = conn.prepareStatement(sql);
            var rs = st.executeQuery();


            showResult(rs);

        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    private static void showResult(ResultSet rs) throws SQLException {
        System.out.printf("%-10s%-25s%-35s%-15s\n", "ID", "Name", "Email", "Address");
        while (rs.next()) {
            var id = rs.getInt("ID");
            var Name = rs.getString("Name");
            var email = rs.getString("email");
            var address = rs.getString("address");

            System.out.printf("%-10s%-25s%-35s%-15s\n", id, Name, email, address);
        }
    }
}