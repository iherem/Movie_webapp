/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.stream.FileImageOutputStream;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author mosza16
 */
public class Movie_method {

    static String web = "http://www.movie2free.com/";

    /**
     * @param args the command line arguments
     *///FileOutputStream fos = new FileOutputStream("comedyLink.txt");
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
    insertAll();
       
    }
    
    public static String getCate_imb_yt(String accessUrl){
         //accessUrl="https://www.movie2free.com/kunlun-fight-muaythai-buakaw-vs-gu-hui-%E0%B8%A8%E0%B8%B6%E0%B8%81%E0%B8%A1%E0%B8%A7%E0%B8%A2%E0%B9%84%E0%B8%97%E0%B8%A2%E0%B8%84%E0%B8%B8%E0%B8%99%E0%B8%AB%E0%B8%A5%E0%B8%B8%E0%B8%99%E0%B9%84/";
         boolean reconect = false;    
            Document doc = null;
            do {
                try {
                    reconect = false;
                    doc = Jsoup.connect(accessUrl)
                            .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
                            .get();
                } catch (IOException io) {
                    System.out.println("Reconecting get URL video file");
                    reconect = true;
                }
            } while (reconect);
            Elements es = doc.select("div.Breadcrumb");
            String category=es.select("span").get(1).select("a").text();
            try{
            category=category.substring(0,category.indexOf(" "));
            }catch(StringIndexOutOfBoundsException ex){
            category="ไม่มีหมวดหมู่";
            }
          //  System.out.println(category);
            String imbd="-1";
            try{
             imbd = doc.select("div.imbdrat").select("span").get(0).text();
            }catch(IndexOutOfBoundsException ex){
                imbd="-1";
            }
           // System.out.println("IMDB:"+imbd);
            String youtube = doc.select("iframe").get(0).attr("src");
            //System.out.println("Youtube : "+youtube);
            String information =category+":::"+imbd+":::"+youtube;
            return information;
    }

    public static void insertAll() throws SQLException, ClassNotFoundException{
          List<Movie> movies= AllMovies("https://www.movie2free.com/post-sitemap2.xml");
          for(int i=0;i<movies.size();i++){
              
              movies.get(i).insertNewMovie(i+1000);
              System.out.println(""+(i+1)+" / "+movies.size());
          }
          

        
    }

    
    

    private static List<String> getCategory() {
        List<String> categoryList = null;
        Document doc=null;
        try {
            boolean reconect=false;
            do{
                try{
                reconect=false;
                 doc = Jsoup.connect(web)
                    .userAgent("Chrome")
                    .get();
                }catch(IOException ex){
                    reconect=true;
                }
            }while(reconect);
            categoryList = new ArrayList<String>();
            Elements es = doc.select("a[href^=http://www.movie2free.com/category]");
            for (int i = 12; i < es.size(); i++) {
                Element elementCategory = es.get(i);
                //System.out.println(elementCategory);
                String category = elementCategory.text().replaceAll(" .*", "");
                if (category.equalsIgnoreCase("หนังซูม")) {
                    category = "zoom";
                }
                if (category != null) {
                    categoryList.add(category);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            categoryList=null;
        }
        return categoryList;
    }

    /* private static List<String> ListforQuery(String cat, int pageIndex) {
     List<String> names = null;
     //int amount = getNumberOfPage(cat);
     try {
     //access page by index
     Document doc = Jsoup.connect("http://www.movie2free.com/category/" + cat + "/page/" + pageIndex)
     .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
     .get();
     names = new ArrayList<String>();
     Elements es1 = doc.select("div.moviefilm");
     Elements es2 = es1.select("img[src]");
     if (es1.size() == es2.size()) {
     for (int i = 0; i < es2.size(); i++) {
     Element eName = es2.get(i);
     String Link = es1.get(i).select("a").first().attr("href");

     if (eName != null) {

     String name = eName.attr("alt") + ":::" + eName.attr("src") + ":::" + Link;
     names.add(name);
     }
     }
     }

     } catch (IOException ex) {
     System.out.println("url Dose not exit");
     //ListforQuery(cat, i);
     }
     return names;
     }*/
    

    private static List<String> getMovieNamePerPage(String cat, int pageIndex) {
        List<String> names = null;
        //int amount = getNumberOfPage(cat);
        try {
            //access page by index
            Document doc = Jsoup.connect("http://www.movie2free.com/category/" + cat + "/page/" + pageIndex)
                    .userAgent("Chrome")
                    .get();
            names = new ArrayList<String>();
            Elements es1 = doc.select("div.moviefilm");
            Elements es2 = es1.select("img[src]");
            if (es1.size() == es2.size()) {
                for (int i = 1; i < es2.size() - 1; i++) {
                    Element eName = es2.get(i);
                    //    String Link=es1.select("a").get(i).attr("href");
                    if (eName != null) {

                        String name = eName.attr("alt") + ":::" + eName.attr("src");
                        names.add(name);
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("url Dose not exit");
        }
        return names;
    }

    private static List<String> getAllMovNameByCate(String cat) {
        List<String> names = null;
        int amount = getNumberOfPage(cat);
        //access each page per page
        System.out.println(amount);
        for (int count = 1; count <= amount; count++) {
            if (names == null) {
                names = new ArrayList<String>();
            }
            names.addAll(getMovieNamePerPage(cat, count));
        }
        return names;
    }

    private static int getNumberOfPage(String cat) {
        int amount = 0;
        try {

            //String category="sci-fi";
            Document doc = Jsoup.connect("http://www.movie2free.com/category/" + cat)
                    .userAgent("Chrome")
                    .get();
            Elements e = doc.select("a[href^=http://www.movie2free.com/category/" + cat + "/page]");
            if (e.size() > 0) {
                // System.out.println(e.get(e.size()-2).text());
                amount = Integer.parseInt(e.get(e.size() - 2).text());
            } else {
                amount = 1;
                //System.out.println("Had 1 page");
            }

        } catch (IOException ex) {
            System.out.println("url Dose not exit");
        } catch (NumberFormatException ex) {
            System.out.println("access not number");
        }
        return amount;

    }

    public static List<String> getLinkMovie(String cat, int pageIndex) {
        //ไม่ใช่ link ไฟล์ วิดีโอ
        List<String> links = null;
        try {
            Document doc = Jsoup.connect("http://www.movie2free.com/category/" + cat + "/page/" + pageIndex)
                    .userAgent("Chrome")
                    .get();

            Elements inDiv = doc.select("div.moviefilm");
            Elements inA = inDiv.select("a");
            //get  each name
            int count = 0;
            for (int i = 0; i < (inA.size()) / 2; i++) {
                if (links == null) {
                    links = new ArrayList<String>();
                }
                String url = inA.get(i * 2).attr("href");
                links.add(url);
                //System.out.println(++count+":"+url);
            }
        } catch (IOException ex) {
            System.out.println("Link Error");
        }
        return links;
    }

    private List<String> getLinkVi(String cat) {
        //link video
        //  int sum = 0;
        // int c = 1;
        List<String> videos = new ArrayList<String>();
        for (int i = 1; i <= getNumberOfPage(cat); i++) {
            List<String> links = getLinkMovie(cat, i);
            //sum += links.size();

            for (String s : links) {

                try {
                    Document doc = Jsoup.connect(s)
                            .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
                            .get();
                    Element e = doc.select("center").get(1);
                    String[] type = e.text().split(" ");
                    Elements es = e.select("a[href]");
                    for (Element ee : es) {
                        String t = "";
                        String sub = "";
                        for (String a : type) {
                            if (a.equalsIgnoreCase("พากย์ไทย")) {
                                t += a;
                                t += " " + ee.text();
                                t += " " + ee.attr("href");
                            } else if (a.equalsIgnoreCase("Soundtrack")) {
                                sub += a;
                                sub += " " + ee.text();
                                sub += " " + ee.attr("href");
                            }

                        }
                        videos.add(t);
                        if (sub.equals("")) {
                            sub = null;
                        } else {
                            videos.add(sub);
                        }

                    }

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
        /*for(String sss:videos){
         System.out.println(c+++":::"+sss);
         }*/
        // System.out.println(sum);
        return videos;
    }

    public static void insertCat() throws SQLException, ClassNotFoundException {
        java.sql.Connection con = ConnetionBuilder.getConnectionBuilder();
        List<String> categories = getCategory();
        int id = 0;
        for (String s : categories) {
           
                String sql = "insert into CATEGORY(cat_name,cat_id) values(?,?)";
            
           // String sql = "update category set cat_name=? where cat_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, ++id);
            ps.executeUpdate();
        }
        System.out.println(categories.size());
    }

    private static String getLinkYoutube(String linkMovie) {
        String youtube = "";

        try {
            Document doc = Jsoup.connect(linkMovie)
                    .userAgent("Chrome")
                    .get();

            Elements inDiv = doc.select("div.filmaltiaciklama");
            String e = inDiv.select("iframe").first().attr("src");
            youtube = e;
            //get  each name

        } catch (IOException ex) {
            System.out.println("Link Error");
        }

        return youtube;
    }

    public static List<String> getEachLinkVi(String link) {
        List<String> videos = null;

        try {
            String type = "";
            boolean reconect = false;
            //List<String> links = movie.Movie.getLinkMovie("action", 1);

            //เรื่อง
            Document doc = null;
            do {
                try {
                    reconect = false;
                    doc = Jsoup.connect(link)
                            .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
                            .get();
                } catch (IOException io) {
                    System.out.println("Reconecting get URL video file");
                    reconect = true;
                }
            } while (reconect);
            if (doc != null) {

                videos = new ArrayList<String>();
                Element e = doc.select("center").get(1);
                Elements ess = e.select("br");
                String o = "";
                if (ess.size() == 2) {
                    //page has only thai
                    o = e.toString().replaceFirst("<br>", "<p>");
                    o = o.replaceFirst("<br>", "</p>");
                    doc = Jsoup.parse(o);
                    ess = doc.select("p");

                } else if (ess.size() == 3) {
                    o = e.toString().replaceFirst("<br>", "<p>");
                    o = o.replaceFirst("<br>", "</p><p>");
                    o = o.replaceFirst("<br>", "</p>");
                    doc = Jsoup.parse(o);
                    ess = doc.select("p");
                    //if page had sub
                } else {
                    System.out.println("errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
                }

                for (Element ee : ess) {
                    //loop ที่ใส่ tag p เเล้ว
                    type = ee.text().replaceAll(" .*", "");
                    Elements es = ee.select("a").prepend(type + ":::");
                    for (Element ele : es) {
                        //ข้างในเรื่อง
                        videos.add(ele.text() + ":::" + ele.attr("href"));
                        //type(พากไทย หรือ sub) :::resolu:::url_video
                    }

                }
            } else {

            }

        } catch (Exception ex) {
            System.out.println(" Error");
            videos = null;
        }

        return videos;
    }
    
    public static List<Movie> AllMovies(String Xml){
         boolean reconect = false;
        Document doc = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        do {
            try {
                reconect = false;
                doc = Jsoup.connect(Xml)
                        .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5")
                        .get();
            } catch (IOException io) {
                System.out.println("Reconecting get URL video file");
                reconect = true;
            }
        } while (reconect);
        //    System.out.println(doc);
        Elements es = doc.select("url");     
        List<Movie> movies=null;
        try {
            for (int i = 0; i < es.size(); i++) {
                String movieName = es.get(i).select("image|image").select("image|caption").text();               
                   String accessUrl = es.get(i).select("loc").text();
                String imageUrl = es.get(i).select("image|image").select("image|loc").text();
                movieName = movieName.replaceAll("&amp;", "&");
                    String timeMovie = es.get(i).select("lastmod").text();
                    timeMovie = timeMovie.substring(0, timeMovie.indexOf("T"));
                    java.util.Date timeM=null;
                    try {
                        timeM = format.parse(timeMovie);
                    } catch (ParseException ex) {
                        System.out.println("Error format time movie Skip ");
                    }
                    try {                 
                         Movie movie = new Movie();               
                        movie.setAccess_url(accessUrl);  
                        movie.setMovie_name(movieName);
                        movie.setImage_url(imageUrl);
                        movie.setViewer(0);
                        movie.setMovie_time(timeM);
                       
                          if(movies==null){
                             movies= new ArrayList<Movie>();
                          }
                          movies.add(movie);
                    } catch (StringIndexOutOfBoundsException ex) {
                        System.out.println("SKIP");
                    }
            }

        } catch (IndexOutOfBoundsException ex) {

            System.out.println(ex);
        }
        
        return movies;
    }
    

}
