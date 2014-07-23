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
function jsfevent(evt) {
    console.log("event - "+evt.status);
    if (evt.status === "begin") {
        console.log("event == begin");
        $('#'+evt.source.id).addClass("spinner");
    }
    if (evt.status === "complete") {
        console.log("event == complete");
        $('#'+evt.source.id).removeClass("spinner");
    }

}
function registerEvt(evt) {
    if (evt.status === "begin") {
        Deffects.transitionFade($("#regBtn"), $("#regLdr"), 0);
    }
    if (evt.status === "complete") {
        Deffects.transitionFade($("#regLdr"), $("#regBtn"), 200);
    }
}
function loginEvt(evt) {
    if (evt.status === "begin") {
        Deffects.transitionFade($("#loginBtn"), $("#loginLdr"), 0);
    }
    if (evt.status === "complete") {
        Deffects.transitionFade($("#loginLdr"), $("#loginBtn"), 200);
    }
}
function fpemevt(evt) {
    if (evt.status === "begin") {
        Deffects.transitionFade($("#fpemBtn"), $("#fpLdr"), 0);
    }
    if (evt.status === "complete") {
        Deffects.transitionFade($("#fpLdr"), $("#fpemBtn"), 200);
    }
}
function fptkevt(evt) {
    if (evt.status === "begin") {
        Deffects.transitionFade($("#fptkBtn"), $("#fptkLdr"), 0);
    }
    if (evt.status === "complete") {
        Deffects.transitionFade($("#fptkLdr"), $("#fptkBtn"), 200);
    }
}

function verifyPassword(){
    var pword = $('#password');
    var vpword = $('#vpassword');
    
    if(vpword !== pword){
        
    }
}

function reload(test){
    if(test === true){
        location.href="/scribbles/home.jsf";
    }
}