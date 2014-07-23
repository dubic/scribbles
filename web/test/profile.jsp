<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Profile</title>
        <jsp:include page="head.html"/>
      
    </head>
    <body class="page-header-fixed">
        <jsp:include page="header.html"/>
        <!--<div id="testdiv" style="margin:40px auto;width: 100px">TODO write content</div>-->
        <div class="page-container profile" style="padding:20px">
            <div id="top-profile" class="row" style="margin-bottom: 10px">
                <div class="col-md-3">
                    <div><img src="img/femi.jpg" width="200" height="240" alt=""/></div>
                </div>
                <div class="col-md-9">
                    <div><h2>John May</h2></div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="dashboard-stat blue">
                                <div class="visual">
                                    <!--                                    <i class="icon-comments"></i>-->
                                </div>
                                <div class="details">
                                    <div class="number">1,379</div>
                                    <div class="desc">Jokes</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="dashboard-stat blue">
                                <div class="visual">
                                    <!--                                    <i class="icon-comments"></i>-->
                                </div>
                                <div class="details">
                                    <div class="number">457</div>
                                    <div class="desc">Proverbs</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="dashboard-stat blue">
                                <div class="visual">
                                    <!--                                    <i class="icon-comments"></i>-->
                                </div>
                                <div class="details">
                                    <div class="number">62</div>
                                    <div class="desc">Quotes</div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!--PROFILE BODY-->
            <div id="body-profile" class="row">
                <div id="about_div" class="col-md-4">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-user"></i>About</div>
                        </div>
                        <div class="portlet-body profile-bio">
                            <div class="row ">
                                <div class="col-md-5">johnny walker</div>
                            </div>
                            <div class="row dhover" id="fullname_div">
                                <div class="col-md-5">John May</div>
                                <i id="edit_btn" class="right-actions icon-edit dhover-show"></i>
                            </div>
                            <div class="row " id="name_edit_div" style="display:none">
                                <input type="text" class="form-control" placeholder="Firstname"/>
                                <input type="text" class="form-control" placeholder="Lastname"/>
                                <button class="btn btn-sm blue">save</button>
                                <button id="cancel_edit" class="btn btn-sm blue">cancel</button>
                            </div>
                            <div class="row ">
                                <div class="col-md-5">Joined</div>
                                <div class="right-actions">12 dec 2013</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="jokes_div" class="col-md-6">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-book"></i>Jokes</div>
                            <div class="tools">
                                <a href="" class="collapse"></a>
                                <a href="" class="reload"></a>
                            </div>
                        </div>
                        <div class="portlet-body ">
                            <div class="dscroll">
                                <ul id="jokelist" class="list-unstyled ">
                                    <!--                                    <li>
                                                                            <div class="post">
                                                                                <div class="row">
                                                                                    <span class="col-md-5">April 8, 2014 5:34 am</span>
                                                                                </div>
                                                                                <div class="row msg">
                                                                                    <p>There was a time akpos jokes was all popular</p>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <a href="#" class="nav-link pull-right">Read more <i class="m-icon-swapright"></i></a>
                                                                                </div>
                                    
                                                                            </div>
                                                                        </li>-->
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <jsp:include page="foot.html"/>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                App.init();
//                handleFixedSidebar();
//                            App.initBxSlider();
//                            Index.initRevolutionSlider();   
                //            console.log("pulsating");
                //            $("#testdiv").pulsate({
                //                    color: "#000000",
                //                    repeat: 10
                //                });
Deffects.hoverDisplay(".row .dhover",".dhover-show");
//                $(".dhover-show").hide();
//                $(".row .dhover").hover(function() {
//                    $(".dhover-show").show();
//                }, function() {
//                    $(".dhover-show").hide();
//                });
//    ================================SHOW NAME EDIT CONTROLS=============================================
                $("#edit_btn").click(function() {
                    $("#fullname_div").fadeOut(400, function() {
                        $("#name_edit_div").fadeIn(400);
                    });
                });
//   ================================HIDE NAME EDIT CONTROLS=============================================      
                $("#cancel_edit").click(function() {
                    $("#name_edit_div").fadeOut(400, function() {
                        $("#fullname_div").fadeIn(400);
                    });
                });
//   ================================LOAD JOKES PORTLET=============================================    
                loadJokes();
                function loadJokes() {
                    App.blockUI("#jokelist");
                    window.setTimeout(function() {
                        $.post("jokes.json", "userid", function(jokelist) {
//                        $("#jokelist").parent().unblock();
                            App.unblockUI("#jokelist");
                            for (var i = 0; i < jokelist.length; i++) {
                                var div = '<div class="post"><div class="row"><span class="col-md-5">' + jokelist[i].date + '</span></div>';
                                div = div + '<div class="row msg"><p>' + jokelist[i].msg + '</p></div>';
                                if (jokelist[i].more === true) {
                                    div = div + '<div class="row"><a href="#" class="nav-link pull-right">Read more <i class="m-icon-swapright"></i></a></div>';
                                }
                                div = div + '</div>';
                                var li = "<li>" + div + "</li>";
                                $("#jokelist").append(li);
                            }
                        });
                    }, 2000);
                }

                $(".dscroll").slimscroll({
                    height: '250px'
                }).bind('slimscroll', function(e, pos) {
                    if (pos === "bottom") {
                        loadJokes();
                    }
                });

            });
        </script>

        <!-- END JAVASCRIPTS -->
    </body>
</html>
