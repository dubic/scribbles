<%-- 
    Document   : home
    Created on : May 4, 2014, 1:24:36 PM
    Author     : dubem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <jsp:include page="head.html"/>
        <style>

        </style>
    </head>
    <body class="page-header-fixed">
        <jsp:include page="header.html"/>
        <div class="page-container">
            <div class="middle-page">
                <div class="row">
                    <ul class="horizontal post-links" style="margin: auto;display: table">
                        <li><a href="#" class="active">Jokes</a></li>
                        <li><a href="#">Quotes</a></li>
                        <li><a href="#">Proverbs</a></li>
                    </ul>
                </div>

                <section class="container row">
                    <div style="width: 50%;margin: auto">   
                        <input placeholder="Search..." class="form-control" style="width: 70%;display: inline" type="text">
                        <span><button type="button" class="btn green">  Search &nbsp;   <i class="m-icon-swapright m-icon-white"></i></button></span>    
                    </div>
                </section>

                <section class="container row">
                    <div style="width: 50%;margin: auto">   
                        <div class="row"><textarea id="comment-txt" style="width: 100%" placeholder="post a joke"></textarea></div>
                        <div class="pull-right"><button class="btn blue">post</button></div>
                    </div>
                </section>

                <section class="container">
                    <jsp:include page="post.html"/>
                </section>
            </div>
        </div>


    <jsp:include page="foot.html"/> 
    <script type="text/javascript">
        jQuery(document).ready(function() {
            App.init();
        });
        $("#loginModal").on("show.bs.modal", function(e) {
            $(".forgot-password-cont").hide();
            $(".register-cont").hide();
            $(".login-cont").show();
        });
        $("#forget-password").click(function() {
            Deffects.transitionFade($(".login-cont"), $(".forgot-password-cont"), 300);
        });
        $("#register-btn").click(function() {
            Deffects.transitionFade($(".login-cont"), $(".register-cont"), 300);
        });
        $("#back-btn").click(function() {
            Deffects.transitionFade($(".forgot-password-cont"), $(".login-cont"), 300);
        });
        $("#register-back-btn").click(function() {
            Deffects.transitionFade($(".register-cont"), $(".login-cont"), 300);
        });
        //////////////////POST ACTION/////////////////////////
        $(".post-links a").click(function() {
//            alert($(this).closest("ul").find("a").length);
            $(this).closest("ul").find("a").removeClass("active");
            $(this).addClass("active");
        });

        $("#post-list .action-bar a.comment-action").click(function(evt) {
            $(this).closest(".post-container").find(".my-comment").slideToggle();
            evt.preventDefault();
        });
        $("#post-list .hide-action").click(function(evt) {
            var hideLink = $(this);
            $(this).closest(".post-container").find(".comments-body").slideToggle();
            if (hideLink.hasClass("active")) {
                hideLink.text("show comments");
                hideLink.removeClass("active");
            } else {
                hideLink.text("hide comments");
                hideLink.addClass("active");
            }
            evt.preventDefault();
        });

        $("#post-list .like-action").click(function(evt) {
            var likes = $(this).closest(".post-container").find(".likes");
            likes.text(parseInt(likes.text()) + 1);
            evt.preventDefault();
        });
        $("#post-list .report-action").click(function(evt) {
            $(this).siblings(".dpopover").fadeIn(300);
            evt.preventDefault();
        });
        $("#post-list .share-action").click(function(evt) {
            $(this).siblings(".dpopover").fadeIn(300);
            evt.preventDefault();
        });
        $(".dpopover .icon-remove").click(function(evt) {
            $(this).closest(".dpopover").fadeOut(300);
        });
//        $("button").click(function(){
//           var btn = $("<button></button>").addClass("btn green").text("Alerts").click(function(){
//             alert("final event reached");  
//           });
//           $(this).parent().append(btn);
//        });
        
        $(".report-submit").click(function(){
           var boxes = $(this).parent().find(".checker .checked");
           boxes.each(function(){
//               console.log($(this).closest('li').text());
              alert($(this).find('input[type="checkbox"]').val()); 
           });
        });


    </script>
</body>
</html>
