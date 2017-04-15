<%@page import="model.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table>
            <tr>
                <td><a href="/MovieWebApp/movieCategory?cat_id=1"> Action(<%=Movie.amountMovieInCat(1)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=2">Adventure(<%=Movie.amountMovieInCat(2)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=3">Animation(<%=Movie.amountMovieInCat(3)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=4">Biography(<%=Movie.amountMovieInCat(4)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=5">Comedy(<%=Movie.amountMovieInCat(5)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=6">Crime(<%=Movie.amountMovieInCat(6)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=7">Documentary(<%=Movie.amountMovieInCat(7)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=8">Drama(<%=Movie.amountMovieInCat(8)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=9">Family(<%=Movie.amountMovieInCat(9)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=10">Fantasy(<%=Movie.amountMovieInCat(10)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=11">History(<%=Movie.amountMovieInCat(11)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=12">Horror(<%=Movie.amountMovieInCat(12)%>)</a></td>
            </tr> <tr>
        <td><a href="/MovieWebApp/movieCategory?cat_id=13">Musical(<%=Movie.amountMovieInCat(13)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=14">Mystery(<%=Movie.amountMovieInCat(14)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=15">Romance(<%=Movie.amountMovieInCat(15)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=16">Sci-Fi(<%=Movie.amountMovieInCat(16)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=17">Sport(<%=Movie.amountMovieInCat(17)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=18">Thriller(<%=Movie.amountMovieInCat(18)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=19">War(<%=Movie.amountMovieInCat(19)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=20">Western(<%=Movie.amountMovieInCat(20)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=21">zoom(<%=Movie.amountMovieInCat(21)%>)</a></td>
        <td><a href="/MovieWebApp/movieCategory?cat_id=22">"ไม่มีหมวดหมู่"(<%=Movie.amountMovieInCat(22)%>)</a></td>
            </tr>
        </tr>
        </table>