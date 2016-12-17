<!DOCTYPE html>
<html>
<% 
String author = (String) request.getAttribute("author"); 
String title = (String) request.getAttribute("title"); 
String jsfile = (String) request.getAttribute("jsfile");
%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><% out.print(author); %> - Tối ưu tổ hợp 1</title>
    <link rel="shortcut icon" type="image/png" href="/images/ico/favicon.ico" />
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif:400,700" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/css/animate.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/view.js"></script>
    <script type="text/javascript" src="/js/<% out.print(jsfile); %>.js"></script>
    <script src="https://apis.google.com/js/client.js?onload=init"></script>
</head>

<body>
    <div class="wrapper clearfix">

        <!--begin header-->
        <div class="header-wrapper">
            <header id="header" class="container-fluid">

                <!--begin logo site-->
                <div class="logo-field row" id="logo">
                    <div class="logo">
                        <h1 class="logo-big-title">Tối ưu <span class="orange-red-text">Tổ hợp 1</span></h1>
                        <h4 class="logo-small-title">Năm học: 2016-2017 - Design by trunggm</h4>
                    </div>
                    <div class="mobile-menu-icon">
                        <a href="#" class="mobile-menu-link">
                            <i class="fa fa-bars"></i>
                        </a>
                    </div>
                </div>

                <!--begin nav-bar site-->
                <div class="nav-field row">
                    <div class="container">
                        <div class="nav-area">
                            <nav class="nav-bar">
                                <!--list item menu nav-bar-->
                                <ul id="nav-menu" class="nav-list">
                                    <li class="nav-item selected-item">
                                        <a href="#">Home</a>
                                    </li>
                                    <li class="nav-item has-sub-menu">
                                        <a href="#">Header</a>
                                    </li>
                                    <li class="nav-item has-sub-menu">
                                        <a href="#">blog</a>
                                    </li>
                                    <li class="nav-item"><a href="#">shortcodes</a></li>
                                    <li class="nav-item"><a href="#">Features</a></li>
                                    <li class="nav-item"><a href="#">page</a></li>
                                    <li class="nav-item"><a href="#">contact</a></li>
                                </ul>

                                <div class="header-search-icon">
                                    <a href="#search" class="header-search-btn">
                                        <i class="fa fa-search"></i>
                                    </a>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </header>
        </div>

        <!--begin main view-->
        <div class="main-wrapper clearfix">
            <div id="main" class="container">
                <div class="row">

                    <!--list article view-->
                    <main class="col-md-8 content-area content-left">
                        <div id="contain">
                            <!--first article-->
                            <article class="post-area">
                                <div class="post-box">
                                    <div class="post-header">
                                        <div class="post-format-icon">
                                            <i class="fa fa-pencil"></i>
                                        </div>

                                        <!--begin infor article-->
                                        <div class="post-info-wrap">
                                            <h1 class="post-title">
                                                <a href="#"><% out.print(title); %></a>
                                            </h1>
                                            <div class="post-meta clearfix">
                                                <ul class="post-meta-list">
                                                    <li class="post-meta-item">
                                                        <i class="fa fa-user"></i>
                                                        <a href="#">
                                                        <% out.print(author); %>
                                                        </a>
                                                    </li>
                                                    <li class="post-meta-item">
                                                        <i class="fa fa-clock-o"></i>
                                                        <a href="#">
                                                            <time class="entry-date published" datetime="2016-11-01T11:33:35+07:00">November 1, 2016</time>
                                                        </a>
                                                    </li>
                                                    <li class="post-meta-item">
                                                        <i class="fa fa-tags"></i>
                                                        <span class="list-tag">
                                                            <a href="#" class="tag-item">Metaheuristics</a>
                                                        </span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!--
                                    <div class="post-media">
                                        <img style="width: 100%; height: auto;" src="images/cloud.jpg" alt="">
                                    </div>
                                    
                                    <div class="post-content form-site">
                                    </div>
                                    -->

                                    <div class="post-form">
                                        <div class="input-field">
                                            <h2>Input Field:</h2>
                                            <div class="input-box">
                                                <textarea id="input-text" placeholder="some input in here" style="width: 100%; height: 180px;"></textarea>
                                                <button id="submit" class="btn btn-primary">Not loading</button>
                                            </div>
                                            
                                        </div>
                                        <div class="output-field">
                                            <h2>Output Field:</h2>
                                            <div class="output-box">
                                                <h3>Best cost: <span id="best-cost"></span></h3>
                                                <h3>Time to run: <span id="best-time"></span></h3>
                                                <h3>Path:</h3>
                                                <p id="best-path"></p>

                                            </div>
                                        </div>
                                    </div>


                                    <div class="post-footer">
                                        <ul class="social-list social-squared">
                                            <li class="social-icon">
                                                <a class="social-icon-link social-facebook" href="#">
                                                    <i class="fa fa-facebook"></i>
                                                    <i class="fa fa-facebook"></i>
                                                </a>
                                            </li>

                                            <li class="social-icon">
                                                <a class="social-icon-link social-twitter" href="#">
                                                    <i class="fa fa-twitter"></i>
                                                    <i class="fa fa-twitter"></i>
                                                </a>
                                            </li>

                                            <li class="social-icon">
                                                <a class="social-icon-link social-google-plus" href="#">
                                                    <i class="fa fa-google-plus"></i>
                                                    <i class="fa fa-google-plus"></i>
                                                </a>
                                            </li>

                                            <li class="social-icon">
                                                <a class="social-icon-link social-linkedin" href="#">
                                                    <i class="fa fa-linkedin"></i>
                                                    <i class="fa fa-linkedin"></i>
                                                </a>
                                            </li>

                                            <li class="social-icon">
                                                <a class="social-icon-link social-digg" href="#">
                                                    <i class="fa fa-digg"></i>
                                                    <i class="fa fa-digg"></i>
                                                </a>
                                            </li>

                                            <li class="social-icon">
                                                <a class="social-icon-link social-pinterest" href="#">
                                                    <i class="fa fa-pinterest"></i>
                                                    <i class="fa fa-pinterest"></i>
                                                </a>
                                            </li>

                                            <li class="social-icon">
                                                <a class="social-icon-link social-email" href="#">
                                                    <i class="fa fa-envelope-o"></i>
                                                    <i class="fa fa-envelope-o"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </article>
                    </main>

                    <!--aside view blog widget-->
                    <aside class="col-md-4 sidebar sidebar-right">
                        <div class="side-bar-inner clearfix">

                            <!--categories sidebar widget-->
                            <div class="sidebar-widget clearfix">
                                <div class="widget-title-wrapper">
                                    <span class="widget-title-inner"></span>
                                    <h3 class="widget-title">
                                      <span>Danh sách bài tập</span>
                                    </h3>
                                </div>
                                <div class="ctg-wrapper">
                                    <ul class="ctg-list">
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/dunglx" target="_blank">Luyện Kim</a>
                                            <span class="author">Dunglx</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/duongkm" target="_blank">ILP</a>
                                            <span class="author">Duongkm</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/namnv" target="_blank">Nhánh cận</a>
                                            <span class="author">Namnv</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/phucnncb" target="_blank">Chess Board</a>
                                            <span class="author">Phucnn</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/phucnnlcs" target="_blank">Lcs</a>
                                            <span class="author">Phucnn</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/phucnned" target="_blank">Edit Distance</a>
                                            <span class="author">Phucnn</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/phucnnmm" target="_blank">Matrix Chain Multiplication</a>
                                            <span class="author">Phucnn</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/anhht" target="_blank">Cutting Stock</a>
                                            <span class="author">Anhht</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/trunggm" target="_blank">Graps</a>
                                            <span class="author">Trunggm</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/hieubq" target="_blank">Qhd</a>
                                            <span class="author">Hieubq</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/phuongq" target="_blank">Hungarian</a>
                                            <span class="author">Phuongq</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="http://localhost:81/20133348/" target="_blank">A*</a>
                                            <span class="author">Sonpt</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/thenn" target="_blank">Balo3</a>
                                            <span class="author">Thenn</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/huylq" target="_blank">Tabu Search</a>
                                            <span class="author">Huylq</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/kiennv" target="_blank">Bee for TSP</a>
                                            <span class="author">Kiennv</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/taip" target="_blank">ACO</a>
                                            <span class="author">Taip</span>
                                        </li>
                                        <li class="ctg-item clearfix">
                                            <a href="/ex/anhtv" target="_blank">GA</a>
                                            <span class="author">Anhtv</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!--end categories sidebar widget-->
                        </div>
                    </aside>
                    </div>
                </div>
            </div>

            <!--begin footer site-->
            <footer>
                <div class="footer-wrapper">
                    <h2 class="footer-title">© 2016 copyright <a href="https://github.com/trunggm">trunggm</a> | All rights reserved | Privacy Policy</h2>
                </div>
            </footer>

        </div>

        <!--back-to-top button-->
        <a id="back-to-top" href="#" class="back-to-top" role="button" title="Click to return on the top page" data-toggle="tooltip" data-placement="left">
            <span class="glyphicon glyphicon-chevron-up"></span>
        </a>
        <!--back-to-top button-->

        <!--search view-->
        <div id="search">
            <button type="button" class="close">×</button>
            <form>
                <input type="search" value="" placeholder="type keyword(s) here" />
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
        <!--end search view-->

        <!--begin basic scirpt -->
        <script>
            /* hover on nav-item.has-sub-menu
                  $('.has-sub-menu')
                  .hover(function() {
                    $(this).children('.sub-menu').css('display', 'block');
                  })
                  .mouseleave(function() {
                    $(this).children('.sub-menu').css('display', 'none');
                  });*/

            /* hover on sub-menu */
            $('.sub-menu')
                .hover(function() {
                    $(this).parent('.nav-item').addClass('nav-item-hover');
                })
                .mouseleave(function() {
                    $(this).parent('.nav-item').removeClass('nav-item-hover');
                });

            /* hover on figure  show link*/
            $('.post-thubmnail-wrapper').hover(function() {
                console.log('hover');
                $(this).children('i.fa').css('opacity', '1');
            });

            /* back-to-top script */
            $(document).ready(function() {
                $(window).scroll(function() {
                    if ($(this).scrollTop() > 50) {
                        $('#back-to-top').fadeIn();
                    } else {
                        $('#back-to-top').fadeOut();
                    }
                });
                // scroll body to 0px on click
                $('#back-to-top').click(function() {
                    $('#back-to-top').tooltip('hide');
                    $('body,html').animate({
                        scrollTop: 0
                    }, 800);
                    return false;
                });

                $('#back-to-top').tooltip('show');

            });

            /* navbar fixed */
            var affixElement = '.nav-field';
            $(affixElement).affix({
                offset: {
                    // Distance of between element and top page
                    top: function() {
                        console.log(this.top);
                        return (this.top = $(affixElement).offset().top)
                    }
                }
            });

            /* show search when click */
            $(function() {
                $('a[href="#search"]').on('click', function(event) {
                    event.preventDefault();
                    $('#search').addClass('open');
                    $('#search > form > input[type="search"]').focus();
                });

                $('#search, #search button.close').on('click keyup', function(event) {
                    if (event.target == this || event.target.className == 'close' || event.keyCode == 27) {
                        $(this).removeClass('open');
                    }
                });

                //Do not include! This prevents the form from submitting for DEMO purposes only!
                $('form').submit(function(event) {
                    event.preventDefault();
                    return false;
                })
            });
        </script>
</body>

</html>