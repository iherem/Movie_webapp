<%-- 
    Document   : index
    Created on : 27-Oct-2015, 21:20:37
    Author     : mosza16
--%>

<%@page import="model.Movie"%>
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
            span{
                 font-size: 30px;
                 color: orangered;
                 font-weight: bolder;
                
            }
        </style>
    </head>
    <body>
        <jsp:include page="header/searchForm.jsp"/>
        <jsp:include page="header/cateForm.jsp"/>
        <h1>${cat_name==null?"Home":cat_name}</h1>
          <c:choose>
           
                    <c:when test="${cat_name==null&&pageAmount>1}">
                <c:forEach begin="1" end="${pageAmount}" var="Mycount">
                    <a href="/MovieWebApp/home?page=${Mycount}"><span>${Mycount}</<span></a>
                    </c:forEach>
            </c:when>
            <c:when test="${pageAmount>1}">
                <c:forEach begin="1" end="${pageAmount}" var="Mycount">
                    <a href="/MovieWebApp/movieCategory?cat_id=${cat_name}&page=${Mycount}"><span>${Mycount}</<span></a>
                    </c:forEach>
            </c:when>
                    
        </c:choose>
        <c:choose>
            
            <c:when test="${movies!=null}">
                <table>
            <tr>
                <c:forEach items="${movies}" var="movie" varStatus="Mycount">     
                    <td>
                    
                        <a href="/MovieWebApp/movieId?id=${movie.movie_id}">
                         <img src="${movie.image}" width="320" height="240"/>
                          <h5>${movie.movie_name}</h5>
                          </a>
                          <a href="/MovieWebApp/deleteMovie?del_id=${movie.movie_id}">DELETE</a>
                       </td>
                      
                   ${Mycount.count%4==0?"</tr><tr>":""}
                   
                </c:forEach>
                   </table>
            </c:when>
        </c:choose>
      
        
    </body>
</html>
