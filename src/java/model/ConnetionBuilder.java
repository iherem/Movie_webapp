/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

/**
 *
 * @author mosza16
 */
public class ConnetionBuilder {

    public static Connection getConnectionBuilder() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        boolean reconectDB = false;
        do {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://188.166.247.80:3306/jsp?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8", "jsp", "jsp");
                reconectDB = false;
            } catch (com.mysql.jdbc.CommunicationsException cm) {
                reconectDB = true;
                
            } catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException ex){
                reconectDB = true;
            }
        } while (reconectDB);
        return conn;
    }

    public static void main(String[] args) {
        try {

            Connection conn = ConnetionBuilder.getConnectionBuilder();
            System.out.println("can connect to database");
            //getColsName(conn, "APP", "CUSTOMER");
            System.out.println("----------------");
            DatabaseMetaData metadate = conn.getMetaData();
            ResultSet rs = metadate.getColumns(null, "APP", "CUSTOMER", null);
            createColumn(rs);
            conn.close();
        } catch (Exception ex) {
            System.out.println("cant connect to database");
        }
    }

    public static void getColsName(Connection conn, String USER, String Table_name) throws SQLException {
        DatabaseMetaData metadate = conn.getMetaData();
        ResultSet rs = metadate.getColumns(null, USER, Table_name, null);
        while (rs.next()) {

            System.out.println(rs.getString("TYPE_NAME") + " / " + rs.getString("COLUMN_NAME"));

        }

    }

    public static void createColumn(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String x = "";
            if (rs.getString("TYPE_NAME").equalsIgnoreCase("char")
                    || rs.getString("TYPE_NAME").equalsIgnoreCase("varchar")) {
                x = "String";
            } else if (rs.getString("TYPE_NAME").equalsIgnoreCase("integer")) {
                x = "int";
            }
            System.out.println(x + " " + rs.getString("COLUMN_NAME").toLowerCase() + ";");
        }
    }
}
