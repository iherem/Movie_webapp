/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Movie;
import static model.Movie.newMovies;

/**
 *
 * @author mosza16
 */
public class newMovies extends HttpServlet {

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
        HttpSession Session = (HttpSession) request.getSession(false);
        String i = request.getParameter("index");
        List<Movie> movies = null;
        List<String> paths = new ArrayList<String>();
        String active = request.getParameter("active");
        if (Session != null) {
            movies = (List) Session.getAttribute("movies");
        }

        int index;
        if (active != null || Session == null || movies == null) {

            movies = newMovies();
            Session = (HttpSession) request.getSession();
            Session.setAttribute("movies", movies);

        } else if (i != null && movies != null) {
            try {
                System.out.println("beloooooooooooooooooooooooooooo");
                index = Integer.parseInt(i);
                Movie m = movies.get(index);
                m.insertNewMovie();
                String path = getServletContext().getRealPath("image") + "/" + "i" + m.getMovie_id() + ".jpg";
                Movie.loadImage(m.getMovie_id(), m.getImage_url(), path);
                movies.remove(index);
                Session.setAttribute("movies", movies);
                List<Movie> allmovie = Movie.getMovieForHome();
                paths.add(path);
                request.setAttribute("movies", allmovie);
                Session.setAttribute("paths", paths);
                response.sendRedirect("/MovieWebApp/newMovies");
                return;
                //getServletContext().getRequestDispatcher("/JSP/updateMovie.jsp").forward(request, response);

            } catch (NumberFormatException ex) {
                if (i.equalsIgnoreCase("all")) {
                    try {
                        for (Movie m : movies) {
                            m.insertNewMovie();
                            String path = getServletContext().getRealPath("image") + "/" + "i" + m.getMovie_id() + ".jpg";
                            Movie.loadImage(m.getMovie_id(), m.getImage_url(), path);
                            paths.add(path);
                        }
                        movies.clear();
                        Session.setAttribute("paths", paths);
                        response.sendRedirect("/MovieWebApp/newMovies");
                        return;
                    } catch (ClassNotFoundException ee) {

                    } catch (SQLException ee) {
                        System.out.println(ee);
                    }

                }

            } catch (SQLException ex) {

            } catch (ClassNotFoundException ex) {

            } catch (IndexOutOfBoundsException es) {

            }

        }

        getServletContext().getRequestDispatcher("/JSP/updateMovie.jsp").forward(request, response);
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
