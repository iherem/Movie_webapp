/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mosza16
 */
public class SearchFilter implements Filter {
    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       request.setCharacterEncoding("UTF-8");
              
        if(request!=null){
        HttpServletRequest req = (HttpServletRequest)request;
        String input = req.getParameter("id");
        ///JSP/movie.jsp
           
        if(input==null){
            System.out.println(req.getRequestURI());
          config.getServletContext().getRequestDispatcher("/JSP/listMovie.jsp").forward(request, response);
          return;
        }else{
             HttpServletRequest httpRequest = (HttpServletRequest) request;
            httpRequest.setCharacterEncoding("UTF-8");
             System.out.println(input);
            if(input.trim().length()==0){
                config.getServletContext().getRequestDispatcher("/JSP/listMovie.jsp").forward(request, response);
                return;
            }   
            Pattern p = Pattern.compile("[^a-z0-9&.ก-ฮ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(input.trim());
            boolean b = m.find();           
            if (b){
            System.out.println("There is a special character in my string");
            config.getServletContext().getRequestDispatcher("/JSP/listMovie.jsp").forward(request, response);
            return;
            }
            
        }
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
   
}
