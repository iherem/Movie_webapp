/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Movie;

/**
 *
 * @author mosza16
 */
public class movieCategory extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        String cat=request.getParameter("cat_id");
        //List<Movie> allmovie = (List)getServletContext().getAttribute("movies");
        
        System.out.println("URI"+request.getRequestURI());
        String p = request.getParameter("page");
        if(p==null){
            p="1";
        }
       int page =Integer.parseInt(p);
        String target="/page?page="+page;
        
        if(cat!=null){
           
            try{
            int cid = Integer.parseInt(cat);
            List<Movie> movies = Movie.findByCategory(cid);
            request.setAttribute("cat_name", Movie.getCateValue(cid));
            request.setAttribute("movies", movies);
            }catch(NumberFormatException ex){
                try{
                Integer cid = Movie.getCateKey(cat);
                System.out.println("CID:"+cid);
                if(cid!=null){
                    List<Movie> movies = Movie.findByCategory(cid);
                    request.setAttribute("cat_name", Movie.getCateValue(cid));
                    request.setAttribute("movies", movies);
                }
                }catch(NullPointerException e){
                    target="/index.jsp";
                }
            }
            
        }else{
            target="/index.jsp";
        }
        getServletContext().getRequestDispatcher(target).forward(request, response);
        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(movieCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(movieCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
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
