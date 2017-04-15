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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.jboss.weld.context.http.Http;

/**
 *
 * @author mosza16
 */
public class cat_fillter implements Filter {
FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        String cat=req.getParameter("cat_id");
        if(request!=null){
        if(cat!=null){
            if(cat.trim().length()>0){
             Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(cat.trim());
            boolean b = m.find();
            if (b){
            System.out.println("There is a special character in my string");
            config.getServletContext().getRequestDispatcher("/index.jsp");
            return;
            }else{
            try{
               Integer cid = Movie.getCateKey(cat);
               chain.doFilter(request, response);
               return;
            }catch(NullPointerException ex){
                 config.getServletContext().getRequestDispatcher("/index.jsp");
                 return;
            }
            }
            }else{
                config.getServletContext().getRequestDispatcher("/index.jsp");
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
