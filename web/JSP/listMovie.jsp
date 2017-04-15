<%-- 
    Document   : listMovie
    Created on : 01-Nov-2015, 20:34:32
    Author     : mosza16
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                background-image: url('https://picjumbo.imgix.net/HNCK8487.jpg?q=40&w=1650&sharp=30');
                color:seashell;
            }
            .b1{
                background: rgba(0%,0%,0%,0.1);
                border-radius: 500px;
                transition: background 1s;
                border-style: dashed;
            }
            button:hover ,input:hover {
                background-color: red;

            }
            input{
                background: rgba(0%,0%,0%,0.1);
                border-radius: 500px;
                transition: background 1s;
                border-style: dashed;
                width: 300px;
                padding: 8px;
                height: 60px;
                font-size: 18px;
            }
            h1{
                color: darkslategray;
            }
            h2{
                color: crimson;
            }
            td{
                background: rgba(0%,0%,0%,0.5);
                border-radius: 20px;
                text-align: center;
                transition: background 1s;

            }
            a{
                color: aliceblue;
            }
            a:hover,td:hover{
                font-weight: bold;
                background: rgba(0%,0%,0%,0.9);
            }
        </style>
    </head>
    <body>
        <h2>SearchMovie</h2>
        <jsp:include page="../header/searchForm.jsp" />
    <c:choose>
        
    
         <c:when test="${requestScope.movies!=null}">
                    <table style="width: 500px;">

                        <c:forEach items="${requestScope.movies}" var="movie" >          
                            <tr><td><a href="/MovieWebApp/movieId?id=${movie.movie_id}&title=${movie.movie_name}">${movie.movie_name}</a><hr></td></tr>            
                                </c:forEach>

                    </table>
                </c:when>
         <c:otherwise>
                    <h1>${param.id} dose not exit</h1>    
                </c:otherwise>
        
        </c:choose>
    </body>
</html>
