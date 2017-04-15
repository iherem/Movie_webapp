<%-- 
    Document   : index2
    Created on : 03-Nov-2015, 01:02:11
    Author     : mosza16
--%>

<%@page import="java.util.List"%>
<%@page import="model.Movie"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US"><head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" type="text/css" href="http://188.166.247.80/jsp_movie/assets/css/reset.css">
      <link rel="stylesheet" type="text/css" href="http://188.166.247.80/jsp_movie/assets/css/scrollbar.css">
      <link rel="stylesheet" type="text/css" href="http://188.166.247.80/jsp_movie/assets/css/styles.css">
      <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet" type="text/css">
      <link rel="stylesheet" type="text/css" href="http://188.166.247.80/jsp_movie/assets/mm.css">
      <link rel="stylesheet" type="text/css" href="http://188.166.247.80/jsp_movie/assets/css/responsive.min.css?ver=3.0.1">
      <title>JSP-Mov</title>
      <script type="text/javascript" async="" src="https://www.gstatic.com/recaptcha/api2/r20151013164303/recaptcha__th.js"></script><script async="" src="//www.google-analytics.com/analytics.js"></script><script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js?ver=2.1.3"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
         (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
         (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
         m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
         })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
         
         ga('create', 'UA-45182606-7', 'auto');
         ga('send', 'pageview');
         
      </script>
      <script type="text/javascript" src="http://188.166.247.80/jsp_movie/assets/js/jquery.idTabs.min.js"></script>
      <script src="http://188.166.247.80/jsp_movie/assets/js/paginador.js" type="text/javascript"></script>
      <script src="http://188.166.247.80/jsp_movie/assets/js/owl.carousel.js"></script>
      <script src="https://www.google.com/recaptcha/api.js" async="" defer=""></script>
      <script>
         var timer = 0;
         var perc = 0;
         function updateProgress(percentage) {
         	$('#pbar_innerdiv').css("width", percentage + "%");
         	$('#pbar_innertext').text( percentage + "%");
         }
         function animateUpdate() {
         	perc++;
         	updateProgress(perc);
         	if(perc < 100) {
         		timer = setTimeout(animateUpdate, 550);
         	}
         }
         $(document).ready(function() {
         	$('#pbar_outerdiv').click(function() {
         		clearTimeout(timer);
         		perc = 0;
         		animateUpdate();
         	});
         });	
         $(document).ready(function() {
            $("#arriba").click(function() {
                return $("html, body").animate({
                    scrollTop: 0
                }, 1250), !1
            })
         });
         
         
         
         
      </script>
      <style>.aregister{background-color:#8BBDE0;text-shadow:none;color:#fff}</style>
      <style>
         body{

         }
         #header .navegador, .rheader {background:#262b36;}
         #header .navegador .caja .menu li.current-menu-item a {color:#8BBDE0;}
         #header .navegador .caja .menu li a:hover {background:0f4578;}
         .buscaicon ul li a.buscaboton, .categorias li span, .iteslid ul li a.selected, .imdb_r .a span {background-color:#8BBDE0;}
         .news_home .noticias .new .fecha .dia, #header .navegador .caja .menu ul li ul.sub-menu li a:before,  .box_item h1 {color: #8BBDE0;}
      </style>
   <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-mousewheel/3.1.13/jquery.mousewheel.min.js"></script></head>
   <body id="bodyplus">
      <div class="rheader">
         <div class="box">
            <div class="left">
               <a class="rclic" id=""><b class="fa fa-bars fa-lg"></b></a>
            </div>
            <div class="rmenus" style="display: none;">
               <ul id="menu-menu-header" class="">
                  <li id="menu-item-540" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-540"><a href="#">หน้าแรก</a></li>
                  <li id="menu-item-539" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-539"><a href="#">หนังใหม่</a></li>
                  <li id="menu-item-539" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-539"><a href="#">หนังแนะนำ</a></li>
                  <li id="menu-item-539" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-539"><a href="#">คนดูเยอะสุด</a></li>
                  <li id="menu-item-3817" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-3817">
                     <a href="#">ติดต่อ</a>
                     <ul class="sub-menu">
                        <li id="menu-item-4618" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-4618"><a href="#">แจ้งปัญหา</a></li>
                        <li id="menu-item-6182" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-6182"><a href="#">ติดต่อโษณา</a></li>
                     </ul>
                  </li>
               </ul>
            </div>
            <div class="right">
               <a class="rclic2" id=""><b class="fa fa-search fa-lg"></b></a>
            </div>
            <div class="rbuscar" style="display: none;">
               <form method="get" id="searchform" action="#">
                  <div class="textar">
                     <input class="buscar" type="text" placeholder="Search.." name="s" id="s" value="">
                  </div>
               </form>
            </div>
            <div class="center">
               <a href="#"><img src="http://dbmovies.org/wp-content/themes/grifus/images/logo.png" alt=""></a>
            </div>
         </div>
      </div>
      <div id="header">
         <div id="cabeza" class="navegador">
            <div class="caja">
               <div class="logo">
                  <a href="#"><img src="http://dbmovies.org/wp-content/themes/grifus/images/logo.png" alt=""></a>
               </div>
               <div class="menu">
                  <ul id="menu-menu-header-1" class="">
                     <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-540"><a href="#">หน้าแรก</a></li>
                     <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-539"><a href="#">หนังใหม่</a></li>
                     <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-539"><a href="#">หนังแนะนำ</a></li>
                     <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-539"><a href="#">คนดูเยอะสุด</a></li>
                     <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-3817">
                        <a href="#">ติดต่อ</a>
                        <ul class="sub-menu">
                           <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-4618"><a href="#">แจ้งปัญหา</a></li>
                           <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-6182"><a href="#">ติดต่อโษณา</a></li>
                        </ul>
                     </li>
                  </ul>
               </div>
               <div class="buscaicon">
                  <ul>
                     <li>
                        <a class="share_social"><i class="fa fa-share-alt"></i></a>
                        <ul>
                           <li><a href="#" target="_blank" class="fbk">Facebook</a></li>
                           <li><a href="#" target="_blank" class="twtr">Twitter</a></li>
                           <li><a href="#" target="_blank" class="gge">Google +</a></li>
                        </ul>
                     </li>
                     <li><a class="buscaboton" id=""><i class="fa fa-search fa-2x"></i></a></li>
                  </ul>
               </div>
               <div class="usermenuadmin">
               </div>
            </div>
         </div>
      </div>
      <div id="contenedor">
         <div class="contenido">
            <div class="buscaformulario" style="display: none;">
               <form method="get" id="searchform" action="#">
                  <input type="text" placeholder="Search.." name="s" id="s" value="">
               </form>
            </div>
            <!-- Slider -->
            <div class="box">
               <div class="box_item">
                   
                  <div id="slid01" style="display: block;">
                     <div class="slider_box s_home">
                        <div class="head_slider">
                           <h3>New releases (2015) </h3>
                           <div class="controles">
                              <a class="prev btn"><b class="fa fa-angle-left"></b></a>
                              <a class="play btn"><b class="fa fa-play"></b></a>
                              <a class="next btn"><b class="fa fa-angle-right"></b></a>
                           </div>
                        </div>
                         <%
                         List<Movie> movies=Movie.getMovieForHome();
                         pageContext.setAttribute("movies", movies);
                         %>
                         
                        <div id="slider1" class="owl-carousel owl-theme" style="opacity: 1; display: block;">
                           <div class="owl-wrapper-outer">
                               
                               <div class="owl-wrapper" style="width: 2952px; left: 0px; display: block; transition: all 800ms ease; transform: translate3d(-123px, 0px, 0px);">
                                    
                                  <c:forEach items="${movies}" var="movie">
                                   <div class="owl-item" style="width: 123px;">
                                       <div class="item">
                              <div class="imagens">
                                 <a href="/MovieWebApp/movieId?id=${movie.movie_id}"><img src="${movie.image_url}" alt="${movie.movie_name}" width="100%" height="100%"></a>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b>${movie.imdb}</span>
                              </div>
                              <span class="ttps">${movie.movie_name}</span>
                              <span class="ytps">2015</span>
                           </div></div>
                                   </c:forEach>
                               
                               
                               </div>
                              
                               </div>
                           
                           
                           
                           
                           
                           
                           
                           
                           
                           
                           
                        </div>
                     </div>
                  </div>
                  <div id="slid02" style="display: none;">
                     <div class="slider_box s_home">
                        <div class="head_slider">
                           <h3>Best rated movies</h3>
                           <div class="controles">
                              <a class="prev btn"><b class="fa fa-angle-left"></b></a>
                              <a class="play btn"><b class="fa fa-play"></b></a>
                              <a class="next btn"><b class="fa fa-angle-right"></b></a>
                           </div>
                        </div>
                        <div id="slider2" class="owl-carousel owl-theme" style="opacity: 1; display: block;">
                           <div class="owl-wrapper-outer"><div class="owl-wrapper" style="width: 2706px; left: 0px; display: block; transition: all 800ms ease; transform: translate3d(-246px, 0px, 0px);"><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/band-of-brothers/"><img src="https://image.tmdb.org/t/p/w185/yRXTVpDRBA3983C3HjoY0SO4dV6.jpg" alt="Band of Brothers" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 9.6</span>
                              </div>
                              <span class="ttps">Band of Brothers</span>
                              <span class="ytps">2001</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/planet-earth/"><img src="https://image.tmdb.org/t/p/w185/zX2tjZXInTpKqFWI8Tf9LYeCg97.jpg" alt="Planet Earth" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 9.5</span>
                              </div>
                              <span class="ttps">Planet Earth</span>
                              <span class="ytps">2006</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/the-shawshank-redemption/"><img src="https://image.tmdb.org/t/p/w185/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg" alt="The Shawshank Redemption" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 9.3</span>
                              </div>
                              <span class="ttps">The Shawshank Redemption</span>
                              <span class="ytps">1994</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/the-martian/"><img src="https://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg" alt="The Martian" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 9.1</span>
                              </div>
                              <span class="ttps">The Martian</span>
                              <span class="ytps">2015</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/the-dark-knight/"><img src="https://image.tmdb.org/t/p/w185/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg" alt="The Dark Knight" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 9.0</span>
                              </div>
                              <span class="ttps">The Dark Knight</span>
                              <span class="ytps">2008</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/the-lord-of-the-rings-the-return-of-the-king/"><img src="https://image.tmdb.org/t/p/w185/50LoR9gJhbWZ5PpoHgi8MNTYgzd.jpg" alt="The Lord of the Rings: The Return of the King" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 8.9</span>
                              </div>
                              <span class="ttps">The Lord of the Rings: The Return of the King</span>
                              <span class="ytps">2003</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/baahubali-the-beginning/"><img src="https://image.tmdb.org/t/p/w185/hNMtfc5YUWTSrRURB3Pmh2IMMax.jpg" alt="Baahubali: The Beginning" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 8.9</span>
                              </div>
                              <span class="ttps">Baahubali: The Beginning</span>
                              <span class="ytps">2015</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/fight-club/"><img src="https://image.tmdb.org/t/p/w185/811DjJTon9gD6hZ8nCjSitaIXFQ.jpg" alt="Fight Club" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 8.9</span>
                              </div>
                              <span class="ttps">Fight Club</span>
                              <span class="ytps">1999</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/12-angry-men/"><img src="https://image.tmdb.org/t/p/w185/qcL1YfkCxfhsdO6sDDJ0PpzMF9n.jpg" alt="12 Angry Men" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 8.9</span>
                              </div>
                              <span class="ttps">12 Angry Men</span>
                              <span class="ytps">1957</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/the-lord-of-the-rings-the-fellowship-of-the-ring/"><img src="https://image.tmdb.org/t/p/w185/bxVxZb5O9OxCO0oRUNdCnpy9NST.jpg" alt="The Lord of the Rings: The Fellowship of the Ring" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 8.8</span>
                              </div>
                              <span class="ttps">The Lord of the Rings: The Fellowship of the Ring</span>
                              <span class="ytps">2001</span>
                           </div></div><div class="owl-item" style="width: 123px;"><div class="item">
                              <div class="imagens">
                                 <a href="http://dbmovies.org/forrest-gump/"><img src="https://image.tmdb.org/t/p/w185/y3EsNpMFwvpcucLmx4HiiRRhCXV.jpg" alt="Forrest Gump" width="100%" height="100%"></a>
                                 <span class="imdb"><b class="fa fa-star"></b> 8.8</span>
                              </div>
                              <span class="ttps">Forrest Gump</span>
                              <span class="ytps">1994</span>
                           </div></div></div></div>
                           
                           
                           
                           
                           
                           
                           
                           
                           
                           
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="iteslid">
               <ul class="idTabs">
                  <li><a href="#slid01" class="selected">New releases</a></li>
                  <li><a href="#slid02" style="float:right;" class="">TOP IMDb</a></li>
               </ul>
            </div>
            <!-- contenido -->
            <div class="box">
               <div class="header">
                  <li id="menu-item-7538" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-7538"><a href="#">หนังทั้งหมด</a></li>
                  
               </div>
               <div class="box_item">
                  <div class="peliculas">
                     <h1>หนังใหม่ล่าสุด</h1>
                     <!-- ************PELICULAS*************** -->
                     <div class="item_1 items">
                        <div id="mt-7192" class="item">
                           <a href="http://dbmovies.org/tvshows/breakthrough/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/9jumEd0rsBp95wYULdjEN71GvP8.jpg" alt="Breakthrough" width="100%" height="100%">
                                 <span class="player"></span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Breakthrough</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-7112" class="item">
                           <a href="http://dbmovies.org/tvshows/the-last-panthers/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/jBX4CxfKbXdLCFTUVyoKCS8FKNg.jpg" alt="The Last Panthers" width="100%" height="100%">
                                 <span class="player"></span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>The Last Panthers</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-7085" class="item">
                           <a href="http://dbmovies.org/tvshows/goldie-bear/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/voZRnV65eRYJiADrALyDNEcOE1B.jpg" alt="Goldie &amp; Bear" width="100%" height="100%">
                                 <span class="player"></span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Goldie &amp; Bear</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-4669" class="item">
                           <a href="http://dbmovies.org/our-brand-is-crisis/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/jLp0GFoptqvPHXqf8uPXAXTtxUp.jpg" alt="Our Brand Is Crisis" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 8.5</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Our Brand Is Crisis</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-4661" class="item">
                           <a href="http://dbmovies.org/the-pearl-button/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/vFQAFE1Xl6mED1FyFtrWcXy0Z7g.jpg" alt="The Pearl Button" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 7.3</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>The Pearl Button</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-6974" class="item">
                           <a href="http://dbmovies.org/tvshows/wicked-city/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/sAeZrFHyACdj8TDDLOvXtShwpFy.jpg" alt="Wicked City" width="100%" height="100%">
                                 <span class="player"></span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Wicked City</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-6967" class="item">
                           <a href="http://dbmovies.org/tvshows/bubblegum/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/bufiiF4a8nqyl1hZZBJ4JxDVqSx.jpg" alt="Bubblegum" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 9</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Bubblegum</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-6955" class="item">
                           <a href="http://dbmovies.org/tvshows/jekyll-and-hyde/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/jemsa3TTYZx49oyzpnLVBWsVW5p.jpg" alt="Jekyll and Hyde" width="100%" height="100%">
                                 <span class="player"></span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Jekyll and Hyde</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-4667" class="item">
                           <a href="http://dbmovies.org/suffragette/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/gG2Y8GNXzMrZCx65a8EhdNq2iuu.jpg" alt="Suffragette" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 5.7</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Suffragette</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-4665" class="item">
                           <a href="http://dbmovies.org/paranormal-activity-the-ghost-dimension/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/xvrboSpvjl58UxpK969gQ9W7EMc.jpg" alt="Paranormal Activity: The Ghost Dimension" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 7.5</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Paranormal Activity: The Ghost Dimension</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-4663" class="item">
                           <a href="http://dbmovies.org/the-last-witch-hunter/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/xw1GFKzxjgfm3dQoK1swFcHmfaC.jpg" alt="The Last Witch Hunter" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 7.7</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>The Last Witch Hunter</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-6870" class="item">
                           <a href="http://dbmovies.org/11-minutes/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/85FJboY2GPFsnLdg93SAndJ64jH.jpg" alt="11 Minutes" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 6.2</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>11 Minutes</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-6930" class="item">
                           <a href="http://dbmovies.org/tvshows/sea-of-plastic/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/wr8Zgp4RlZJePxpYv5fjJfbkZHt.jpg" alt="Sea of Plastic" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 10</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Sea of Plastic</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-4206" class="item">
                           <a href="http://dbmovies.org/my-king/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/cUV0Qj0XZHCEwzu52nAEbHJIeaL.jpg" alt="My King" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 5.9</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>My King</h2>
                              <span class="year">2015</span>
                           </div>
                        </div>
                        <div id="mt-6843" class="item">
                           <a href="http://dbmovies.org/chronic/">
                              <div class="image">
                                 <img src="https://image.tmdb.org/t/p/w185/yWmpbvM5CsfjcQqb8GWdfL3bF25.jpg" alt="Chronic" width="100%" height="100%">
                                 <span class="player"></span>
                                 <span class="imdb"><b><b class="fa fa-star"></b></b> 6.1</span>
                              </div>
                           </a>
                           <div class="fixyear">
                              <h2>Chronic</h2>
                              <span class="year">2015</span>
                           </div>
                        </div><div class="infinito" style="text-align: center;" id="ias_spinner_1446487094086"><img src="data:image/gif;base64,R0lGODlhEAAQAIABAAAAAP///yH/C05FVFNDQVBFMi4wAwEAAAAh+QQJCgABACwAAAAAEAAQAAACHYyPqcvtD6M0oAJo78vYzsOFXiBW5Fhe3GmmX1AAACH5BAkKAAEALAAAAAAQABAAAAIcjI+py+0PowIUwGofvlXKDXZBSI0iaW1miXpGAQAh+QQJCgABACwAAAAAEAAQAAACH4yPacCg2txDcdJms62aZ79h2ngxAXhU6IKtZyuSTwEAIfkECQoAAQAsAAAAABAAEAAAAiCMj2nAEO0UfE1RdmOa03rbfZm4VGRIpiV2miLqwepXAAAh+QQJCgABACwAAAAAEAAQAAACHoyPacAQ7eBqj8rKcKS6XwUeX9iRU2aW6cq27guDBQAh+QQJCgABACwAAAAAEAAQAAACHoyPacAQ7eBqj8rKcKS6XwWGySeSoQmi4sq27guDBQAh+QQJCgABACwAAAAAEAAQAAACHoyPqQEN7JZ7U8aqKl68m92BnGg13lk+J5lFq4seBQAh+QQJCgABACwAAAAAEAAQAAACHoyPqQoNm9yDR9Lqrl5W9/tR4cZlo2GdQZoxZksaBQA7"></div>
                        <!-- **************************** -->
                        <div class="nav-previous alignleft"><a href="http://dbmovies.org/page/2/"></a></div>
                        <div class="nav-next alignright"></div>
                        <!--เปลี่ยนหน้า-->
                     </div>
                  </div>
                  <div class="lateral">
                     <div id="fixer" class="">
                        <!-- MENU -->
                        <div class="movserie">
                           <ul class="idTabs">
                              <li><a href="#moviehome" class="selected">Movies</a></li>
                           </ul>
                        </div>
                        <!-- Genre -->
                        <div id="moviehome" style="display: block;">
                           <div class="categorias">
                              <h3>Genres <span class="fa fa-sort"></span></h3>
                              <ul class="scrolling cat mCustomScrollbar _mCS_1 mCS-autoHide" style="position: relative; overflow: visible;"><div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: 300px;" tabindex="0"><div id="mCSB_1_container" class="mCSB_container" style="position: relative; top: -213px; left: 0px;" dir="ltr">
                                 <li class="cat-item cat-item-1"><a href="#">Action</a> <span>658</span></li>
                                 <li class="cat-item cat-item-655"><a href="#">Adventure</a> <span>366</span></li>
                                 <li class="cat-item cat-item-737"><a href="#">Animation</a> <span>116</span></li>
                                 <li class="cat-item cat-item-721"><a href="#">Comedy</a> <span>630</span></li>
                                 <li class="cat-item cat-item-727"><a href="#">Crime</a> <span>313</span></li>
                                 <li class="cat-item cat-item-1416"><a href="#">Documentary</a> <span>22</span></li>
                                 <li class="cat-item cat-item-671"><a href="#">Drama</a> <span>967</span></li>
                                 <li class="cat-item cat-item-736"><a href="#">Family</a> <span>164</span></li>
                                 <li class="cat-item cat-item-656"><a href="#">Fantasy</a> <span>218</span></li>
                                 <li class="cat-item cat-item-2418"><a href="#">Foreign</a> <span>58</span></li>
                                 <li class="cat-item cat-item-848"><a href="#">History</a> <span>77</span></li>
                                 <li class="cat-item cat-item-663"><a href="#">Horror</a> <span>340</span></li>
                                 <li class="cat-item cat-item-795"><a href="#">Music</a> <span>52</span></li>
                                 <li class="cat-item cat-item-688"><a href="#">Mystery</a> <span>174</span></li>
                                 <li class="cat-item cat-item-672"><a href="#">Romance</a> <span>287</span></li>
                                 <li class="cat-item cat-item-654"><a href="#">Science Fiction</a> <span>280</span></li>
                                 <li class="cat-item cat-item-664"><a href="#">Thriller</a> <span>704</span></li>
                                 <li class="cat-item cat-item-3996"><a href="#">TV Movie</a> <span>4</span></li>
                                 <li class="cat-item cat-item-849"><a href="#">War</a> <span>50</span></li>
                                 <li class="cat-item cat-item-919"><a href="#">Western</a> <span>22</span></li>
                              </div></div><div id="mCSB_1_scrollbar_vertical" class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 103px; max-height: 266px; top: 73px;" oncontextmenu="return false;"><div class="mCSB_dragger_bar" style="line-height: 50px;"></div></div><div class="mCSB_draggerRail"></div></div></div></ul>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <!-- Footer -->
            <div id="footer">
               <span class="texto">
               <b>JSP-Movies</b> ดูหนังออนไลน์</span>
               <span class="copyright">
               Powered by  <a href="#"><b>Mos &amp; Friends</b></a>
               </span>
            </div>
         </div>
      </div>
      
      <script>
$(".se-q").click( function () {
  var container = $(this).parents(".se-c");
  var answer = container.find(".se-a");
  var trigger = container.find(".se-t");
  
  answer.slideToggle(200);
  
  if (trigger.hasClass("se-o")) {
    trigger.removeClass("se-o");
  }
  else {
    trigger.addClass("se-o");
  }
});
</script>
      <script src="http://188.166.247.80/jsp_movie/assets/js/functions.min.js?ver=3.0.1"></script>
      <script src="http://188.166.247.80/jsp_movie/assets/js/scrollbar.js?ver=3.0.1"></script>
      <script>
         (function($){
         	$(window).load(function(){
         		
         		$(".scrolling").mCustomScrollbar({
         			theme:"minimal-dark",
         			scrollButtons:{
         				enable:true
         			},
         			callbacks:{
         				onTotalScroll:function(){ addContent(this) },
         				onTotalScrollOffset:100,
         				alwaysTriggerOffsets:false
         			}
         		});
         			
         	});
         })(jQuery);
      </script>
      <a id="arriba" class="arribatodo" href="#"><b class="icon-chevron-up2"></b></a>
   

<!-- เหนื่อยชิบหาย --></body></html>