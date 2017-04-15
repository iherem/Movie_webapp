package model;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class getImage {

    
    public static void main(String[] args) throws ClassNotFoundException {
        int rs2 = 0;
        int count = 0;
        String pathpic = "C:\\Users\\mosza16\\Documents\\NetBeansProjects\\MovieWebApp\\web\\image\\";
        try {
            
            Connection con = ConnetionBuilder.getConnectionBuilder();
            String sql = "select IMAGE_URL,MOVIE_ID from MOVIE";
            Statement sm = con.createStatement();
            ResultSet rs = sm.executeQuery(sql);
            while(rs.next()){
                URL url = new URL(rs.getString(1));
                        URLConnection connection = url.openConnection();
                        try {
                            InputStream stream = connection.getInputStream();
                            FileOutputStream fos = new FileOutputStream(pathpic +"i"+rs.getInt(2) + ".jpg");
                            BufferedOutputStream outs = new BufferedOutputStream(fos);
                            int len;
                            byte[] buf = new byte[1024];
                            while ((len = stream.read(buf)) > 0) {
                                outs.write(buf, 0, len);
                            }
                            outs.close();
                            count++;
                            System.out.println("Add "+ count +"Pic.");
                        } catch (FileNotFoundException fnfe) {
                            System.out.println("File Not Found. skip now");
                        }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
  
}
