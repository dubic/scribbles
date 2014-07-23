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