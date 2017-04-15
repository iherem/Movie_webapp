/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Movie;
/**
 *
 * @author mosza16
 */
public class deleteMovie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String movie_id = request.getParameter("del_id");
            String target= "/home";
            System.out.println(target);
            if(movie_id!=null){
                try{
                    int id= Integer.parseInt(movie_id);
                    Movie.deleteMovie(id);
                    List<Movie> allmovie = Movie.getMovieForHome();
                    request.setAttribute("movies", allmovie);
                    String path = getServletContext().getRealPath("image");
                    path+="/"+"i"+movie_id + ".jpg";
                    File deleteFile = new File(path);
                    boolean sus= deleteFile.delete();
                    if(sus){
                         System.out.println("Deleted");
                    }else{
                        System.out.println("Delete Error");
                    }
                }catch(NumberFormatException ex){
                    
                }catch(ClassNotFoundException ex){
                    
                }
                
            }
            response.sendRedirect("/MovieWebApp/home");
           return;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
