/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie_method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author mosza16
 */
public class Movie {

    private int movie_id;
    private String movie_name;
    static int viewer;
    private String image_url;
    private String trailer_url;
    private String access_url;
    private int cat_id;
    private List<link> links;
    private double imdb;
    private Date movie_time;

    static HashMap<Integer, String> cateCode = new HashMap();

    {
        cateCode.put(1, "Action");
        cateCode.put(2, "Adventure");
        cateCode.put(3, "Animation");
        cateCode.put(4, "Biography");
        cateCode.put(5, "Comedy");
        cateCode.put(6, "Crime");
        cateCode.put(7, "Documentary");
        cateCode.put(8, "Drama");
        cateCode.put(9, "Family");
        cateCode.put(10, "Fantasy");
        cateCode.put(11, "History");
        cateCode.put(12, "Horror");
        cateCode.put(13, "Musical");
        cateCode.put(14, "Mystery");
        cateCode.put(15, "Romance");
        cateCode.put(16, "Sci-Fi");
        cateCode.put(17, "Sport");
        cateCode.put(18, "Thriller");
        cateCode.put(19, "War");
        cateCode.put(20, "Western");
        cateCode.put(21, "zoom");
        cateCode.put(22, "ไม่มีหมวดหมู่");
    }

    public Date getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(Date movie_time) {
        this.movie_time = movie_time;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getViewer() {
        return viewer;
    }

    public void setViewer(int viewer) {
        this.viewer = viewer;
    }

    public String getImage_url() {
        return image_url;
    }
    
    public String getImage() {
        return "/MovieWebApp/image/i"+this.movie_id+".jpg";
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public String getAccess_url() {
        return access_url;
    }

    public void setAccess_url(String access_url) {
        this.access_url = access_url;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public List<link> getLinks() {
        return links;
    }

    public String getCateValue() {
        return cateCode.get(this.getCat_id());
    }

    public static String getCateValue(int cat_id) {
        return cateCode.get(cat_id);
    }

    public static int getCateKey(String value) {

        Integer strKey = null;
        for (Map.Entry e : cateCode.entrySet()) {

            if (value.equals(e.getValue())) {
                strKey = (Integer) e.getKey();
                break; //breaking because its one to one map
            }
        }
        return strKey;
    }

    public void setLinks() {
        this.links = null;
        link li = null;
        if (access_url != null) {
            List<String> links = Movie_method.getEachLinkVi(this.access_url);
            //System.out.println(links.size());
            for (String link : links) {
                try {
                    if (link != null || link.length() != 0) {
                        li = new link();
                        //  System.out.println("link"+link);
                        String spit[] = link.split(":::");
                        li.setType(spit[0]);
                        li.setResolution(spit[1]);
                        li.setLink_url(spit[2]);
                        if (this.links == null) {
                            this.links = new ArrayList<link>();
                        }
                        this.links.add(li);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("some Like Error skiping");
                }
            }

        }

    }

    private static void orm(ResultSet rs, Movie m) throws SQLException {
        m.setAccess_url(rs.getString("access_url"));
        m.setImage_url(rs.getString("image_url"));
        m.setMovie_id(rs.getInt("movie_id"));
        m.setMovie_name(rs.getString("movie_name"));
        m.setTrailer_url(rs.getString("trailer_url"));
        m.setViewer(rs.getInt("viewer"));
        m.setImdb(rs.getDouble("imdb"));
        m.setCat_id(rs.getInt("cat_id"));
        m.setMovie_time(rs.getDate("date"));

    }

    public static Movie getMovieById(int id) throws ClassNotFoundException {
        Connection con = null;
        Movie m = null;
        try {
            con = ConnetionBuilder.getConnectionBuilder();
            String sql = "select * from MOVIE where movie_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Movie();
                orm(rs, m);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return m;
    }

    public static List<Movie> getMovieByName(String name) {
        List<Movie> movies = null;
        Connection con = null;
        Movie m = null;
        try {
            con = ConnetionBuilder.getConnectionBuilder();
            String sql = "select * from MOVIE where movie_name like ?"
                    + "ORDER BY movie_id ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (movies == null) {
                    movies = new ArrayList<Movie>();
                }
                m = new Movie();
                orm(rs, m);
                if (movies.size() != 0) {
                    if (movies.get(movies.size() - 1).getMovie_name().equalsIgnoreCase(m.getMovie_name())) {
                        movies.remove(movies.size() - 1);
                    }
                }
                movies.add(m);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("ex");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return movies;
    }

    public static List<Movie> newMovies() {
        return newMovies("http://www.movie2free.com/post-sitemap2.xml");
    }

    public static List<Movie> newMovies(String checkXml) {
        boolean reconect = false;
        Document doc = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        do {
            try {
                reconect = false;
                doc = Jsoup.connect(checkXml)
                        .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
                        .get();
            } catch (IOException io) {
                System.out.println("Reconecting get URL video file");
                reconect = true;
            }
        } while (reconect);
        //    System.out.println(doc);
        Elements es = doc.select("url");
        ArrayList<String> movieNames = new ArrayList<String>();
        List<Movie> movies = null;
        //int count = 0;
        //  long time = System.currentTimeMillis();
        try {
            //  System.out.println(es.size());
            List<Movie> m = Movie.getMovieByName("%");
            for (Movie mm : m) {
                movieNames.add(mm.access_url);
            }
            for (int i = 0; i < es.size(); i++) {
                // System.out.println("i :  " + i);
                String accessUrl = es.get(i).select("loc").text();
                String movieName = es.get(i).select("image|image").select("image|caption").text();
                if (movieNames.contains(accessUrl)) {
                    //System.out.println("t1 Movie Name:" + movieName + " had already ");

                } else {

                    String imageUrl = es.get(i).select("image|image").select("image|loc").text();
                    movieName = movieName.replaceFirst("&amp;", "&");
                    // System.out.println(movieName);
                    //System.out.println("เทสสสสสสสสสสสสส");
                    String timeMovie = es.get(i).select("lastmod").text();
                    timeMovie = timeMovie.substring(0, timeMovie.indexOf("T"));
                    java.util.Date timeM = null;
                    try {
                        timeM = format.parse(timeMovie);
                        //   System.out.println("TIME : :"+timeM);
                    } catch (ParseException ex) {
                        System.out.println("Error format time movie Skip ");
                    }
                    try {
                        //System.out.println(movieName);
                        Movie movie = new Movie();
                        movie.setAccess_url(accessUrl);
                        movie.setMovie_name(movieName);
                        movie.setImage_url(imageUrl);
                        movie.setViewer(0);
                        movie.setMovie_time(timeM);
                        if (movies == null) {
                            movies = new ArrayList<Movie>();
                        }
                        movies.add(movie);
                        //   System.out.println(movies.size());
                    } catch (StringIndexOutOfBoundsException ex) {
                        System.out.println("SKIP");
                    }
                }

            }
            //  long time2 = System.currentTimeMillis();
            // System.out.println("duration Time : " + (time2 - time));

        } catch (IndexOutOfBoundsException ex) {

            System.out.println(ex);
        }
        return movies;

    }

    public void insertNewMovie() throws ClassNotFoundException, SQLException, MalformedURLException {
        Connection con = ConnetionBuilder.getConnectionBuilder();
        String Lastid = "select max(movie_id) from MOVIE; ";
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(Lastid);

        while (rs.next()) {
            this.setMovie_id(rs.getInt(1) + 1);
        }
        insertNewMovie(this.movie_id);
        

    }

    public void insertNewMovie(int movie_id) throws ClassNotFoundException {
        String ciy[] = Movie_method.getCate_imb_yt(this.getAccess_url()).split(":::");
        String category = ciy[0];
        double imdb = Double.parseDouble(ciy[1].replaceAll(",", "."));
        String youtube = ciy[2];
        this.setImdb(imdb);
        this.setCat_id(getCateKey(category));
        this.setTrailer_url(youtube);
        try {
            Connection con = ConnetionBuilder.getConnectionBuilder();
            String sql = "insert into MOVIE(movie_id,movie_name,image_url,trailer_url,cat_id,access_url,imdb,date)"
                    + "values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movie_id);
            ps.setString(2, this.movie_name);
            ps.setString(3, this.image_url);
            ps.setString(4, this.trailer_url);
            ps.setInt(5, this.cat_id);
            ps.setString(6, this.access_url);
            ps.setDouble(7, this.imdb);
            ps.setTimestamp(8, new java.sql.Timestamp(this.movie_time.getTime()));
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    public static List<Movie> findByCategory(int cid) throws ClassNotFoundException {

        List<Movie> movies = null;

        try {
            Connection con = ConnetionBuilder.getConnectionBuilder();
            String sql = "select * from MOVIE where CAT_ID = ?"
                    + "  ORDER BY movie_id desc";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (movies == null) {
                    movies = new ArrayList<Movie>();
                }
                Movie m = new Movie();
                orm(rs, m);
                movies.add(m);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return movies;
    }

    public static List<Movie> getMovieForHome() {
        List<Movie> allmovie = Movie.getMovieByName("%");
        List<Movie> movies = new ArrayList<Movie>();
        int amount=100; //จำนวนหนังที่จะเอาไปโชวในหน้า home
        for (int i = allmovie.size() - 1; i >= allmovie.size() - amount; i--) {
            try {
                movies.add(allmovie.get(i));
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex);
            }
        }
        return movies;
    }

    public static void deleteMovie(int movie_id) throws ClassNotFoundException {

        try {
            Connection con = ConnetionBuilder.getConnectionBuilder();
            String sql = "DELETE  FROM MOVIE WHERE movie_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movie_id);
            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
      //  String path = "web\\image\\i"+movie_id+".jpg";
       // DeleteFile.delFile(path);
        

    }

    public void deleteMovie() throws ClassNotFoundException {
        deleteMovie(this.movie_id);
        

    }

    public static int amountMovieInCat(int cat_id) throws ClassNotFoundException, SQLException {
        Connection con = ConnetionBuilder.getConnectionBuilder();
        String sql = "select count(cat_id) from MOVIE"
                + " where cat_id = " + cat_id + " ;";
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);
        int amount = 0;

        while (rs.next()) {
            amount = rs.getInt(1);
        }
        return amount;
    }

    public static int amountPage(List<Movie> movies, int AmountEachPage) {
        int amount = 0;
        int amountMovies = movies.size();
        if (AmountEachPage <= amountMovies) {
            if (amountMovies % AmountEachPage == 0) {
                amount = amountMovies / AmountEachPage;
            } else {
                amount = (amountMovies / AmountEachPage) + 1;
            }
        } else {
            amount = 1;
        }
        return amount;
    }

    public static int[] spiltMovie(List<Movie> movies, int AmountEachPage) {
        int amount = amountPage(movies, AmountEachPage);
        int begin[] = new int[amount];
        int end[] = new int[amount];
        int amountMovies = movies.size();
        int count = 0;
        for (int i = 0; i < amount; i++) {

            begin[i] = count;
            for (int ii = 0; ii < AmountEachPage; ii++) {
                if (count < amountMovies) {
                    ++count;
                } else {
                    break;
                }
            }
            end[i] = count;

        }
        return begin;

    }

    public static List<Movie> listMovieByIndexPage(int pageIndex, List<Movie> movies, int amountPerPageMovie) {
        int p[] = Movie.spiltMovie(movies, amountPerPageMovie);
       // int amountPage = p.length;
        //    System.out.println(p);
        List<Movie> pageMovies = new ArrayList<Movie>();
        for (int i = p[pageIndex - 1]; i < (p[pageIndex - 1] + amountPerPageMovie); i++) {
            try {
                pageMovies.add(movies.get(i));
                // System.out.println("I:"+i);
            } catch (IndexOutOfBoundsException ex) {
                break;
            }

        }

        return pageMovies;
    }
      
   public static  void loadImage(int movie_id,String image_url,String ServletPath) throws MalformedURLException{
                  String path=ServletPath;    
          try{
                        URL url = new URL(image_url);
                        System.out.println(image_url);
                        URLConnection connection = url.openConnection();
                        try {
                            InputStream stream = connection.getInputStream();
                             
                            FileOutputStream fos = new FileOutputStream(path);
                            BufferedOutputStream outs = new BufferedOutputStream(fos);
                            int len;
                            byte[] buf = new byte[1024];
                            while ((len = stream.read(buf)) > 0) {
                                outs.write(buf, 0, len);
                            }
                            outs.close();
                            
                            System.out.println("Add  Movie Id: "+ movie_id +" Pic.");
                        } catch (FileNotFoundException fnfe) {
                            System.out.println(path);
                            System.out.println("File Not Found. skip now");
                        }
                        }catch(IOException ex){
                            
                        }
    }
   
   
   
   public  void addViewer() throws ClassNotFoundException{
       Connection con = null;
       int view=this.getViewer();
       String sql;
       try{
          
           con = ConnetionBuilder.getConnectionBuilder();
           sql="UPDATE MOVIE set viewer=? where movie_id=?;";
           PreparedStatement ps= con.prepareStatement(sql);
           ps.setInt(1,(view+1));
           ps.setInt(2, this.movie_id);
           ps.executeUpdate();
           con.close();
           System.out.println("add view");
           this.setViewer(view+1);
       }catch(SQLException ex){
       }
       
   }
   
   
   
   
   
}

    //method for get url movie

