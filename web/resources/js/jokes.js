/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    App.blockUI(".middle-page");
    $.getJSON("/scribbles/testapi/jokes", function(posts) {
        for (var i = 0; i < posts.length; i++) {
            var post = createJoke(posts[i]);
            $('#post-list').append(post);
        }
        App.unblockUI(".middle-page");
        ///////////////POST CREATION EVENTS////////////////////////////////////////
        $(".dpopover .icon-remove").click(function(evt) {
            $(this).closest(".dpopover").fadeOut(300);
        });
        $(".report-submit").click(function() {
            var boxes = $(this).parent().find(".checker .checked");
            boxes.each(function() {
                console.log($(this).closest('li').text());
//              alert($(this).find('input[type="checkbox"]').text()); 
            });
        });
        $("select, input:checkbox, input:radio, input:file").uniform();
        $("#post-list .report-action").click(function(evt) {
            $(this).siblings(".dpopover").fadeToggle(300);
            evt.preventDefault();
        });
    });
});

function createJoke(post) {
    var postContainer = $('<section class="post-container"></section>')
            .append($('<div class="row"></div>')
                    .append($('<div class="col-md-1"></div>')
                            .append($('<img class="poster-img" width="60" height="60"/>').attr('src', 'assets/img/avatar1.jpg')))
                    .append($('<div  class="col-md-10"></div>')
                            .append($('<div class="row"></div>').append($('<a class="person"></a>').attr('href', 'profile.jsp').text(post.poster)))
                            .append($('<div class="row"></div>').append($('<span class="date-grey"></span>').text('3 hrs ago')))
                            .append($('<div class="row stats"></div>')
                                    .append($('<i class="icon-eye-open"></i>')).append($('<span></span>').text('23,675'))
                                    .append($('<i class="icon-thumbs-up"></i>')).append($('<span class="likes"></span>').text(post.likes))
                                    .append($('<i class="icon-thumbs-down"></i>')).append($('<span class="dislikes"></span>').text(post.dislikes))
                                    .append($('<i class="icon-comments"></i>')).append($('<span></span>').text(post.comments.length))
                                    )));
    postContainer.append($('<div class="row post"></div>').append($('<p></p>').text(post.post)));
    //////////////ACTIONS BAR AND POP UPS//////////////////////
    var sharePop = $('<div class="dpopover"><div class="pop-head"><span>Share Options</span><i class="icon-remove" title="close"></i></div><div class="pop-body"><input class="form-text" type="text"/><button class="btn green small">show</button></div></div>');
    var shareLink = $('<a href="#">Share</a>').click(function(evt) {
        openShareOptions(evt, $(this));
    });
    var reportLink = $('<a href="#" class="report-action">Report</a>');
    var likeLink = $('<a href="#">Like</a>').click(function(evt) {
        openReportOptions(evt, $(this));
    });
    var dislikeLink = $('<a href="#">Dislike</a>').click(function(evt) {
        openReportOptions(evt, $(this));
    });
    var commentLink = $('<a href="#">Comment</a>').click(function(evt) {
        showMyComment(evt, $(this));
    });
    var reportPop = $('<div class="dpopover"><div class="pop-head"><span>Report Post</span><i class="icon-remove" title="close"></i></div><div class="pop-body"><ul class="list-unstyled"><li><input type="checkbox" value="rep_off">Offensive</li><li><input type="checkbox" value="rep_vul">Vulgar</li><li><input type="checkbox" value="rep_sal">Salacious</li></ul><button class="btn btn-small report-submit">submit</button></div></div>');
    postContainer.append($('<div class="row action-bar"></div>')
            .append($('<ul class="list-inline"></ul>')
                    .append($('<li></li>').append(likeLink))
                    .append($('<li></li>').append(dislikeLink))
                    .append($('<li></li>').append(commentLink))
                    .append($('<li></li>').append(shareLink).append(sharePop))
                    .append($('<li></li>').append(reportLink).append(reportPop))));
    /////////////////HIDE COMMENTS LINK//////////////////////////
    var hideCommLink = $('<span class="pull-right" style="position: relative"></span>').append($('<a href="#">show comments</a>').click(function(evt) {
        hideComments(evt, $(this));
    }));
    ////////////////////////////////////////
    var commentsBody = $('<div class="comments-body"></div>');
    var commentBlock = $('<div class="row comment"></div>')
            .append($('<div class="col-md-1"></div>')
                    .append($('<img class="poster-img" width="40" height="40"/>').attr('src', '/scribbles/test/img/femi.jpg')))
            .append($('<div class="col-md-10"></div>')
                    .append($('<div class="row"></div>')
                            .append($('<a class="person"></a>').attr('href', 'profile.jsp').text('Chinwe Collette'))
                            .append($('<span class="date-grey" style="margin:0px 10px"></span>').text('2 hrs 3 mins ago')))
                    .append($('<div class="row"></div>').append($('<p></p>').text('code optimization is still going on. Most of the pages'))));

    postContainer.append($('<div class="row comments"></div>').append(hideCommLink).append(commentsBody.append(commentBlock)));
    ///////////////MY COMMENT//////////////////////////////

    var postCommentBtn = $('<button class="btn blue" onclick="postComment($(this),' + post.id + ')">comment</button>');
    var myComment = $('<div class="row my-comment" style="display:none"></div>')
            .append($('<div class="row"><textarea style="width: 100%" placeholder="comment on this joke"></textarea></div>'))
            .append($('<div class="pull-right"></div>')
                    .append(postCommentBtn)
                    .append($('<span class="ajax-loader"><img title="c_loader.gif" src="assets/img/loading.gif"/></span>')));

    postContainer.append(myComment);

    return postContainer;
}

function openShareOptions(evt, source) {
    source.parent().find(".dpopover").fadeToggle(300);
    evt.preventDefault();
}
//////////////SHOW COMMENT TEXTAREA//////////////////////
function showMyComment(evt, source) {
    source.closest(".post-container").find(".my-comment").slideDown(800).focus(true);
    evt.preventDefault();
}
function hideComments(evt, source) {
    source.closest(".post-container").find(".comments-body").slideToggle();
    if (source.hasClass("active")) {
        source.text("show comments");
        source.removeClass("active");
    } else {
        source.text("hide comments");
        source.addClass("active");
    }
    evt.preventDefault();
}
//////////////POST A COMMENT ACTION//////////////////////
function postComment(source, postId) {
    var loader = source.closest('.my-comment').find('.ajax-loader');
    Deffects.transitionFade(source, loader, 0);
    var comment = source.closest('.my-comment').find('textarea').val();
    alert("post id = " + postId + ", comment = " + comment);
    Deffects.transitionFade(loader, source, 500);
    source.closest(".post-container").find(".comments-body").append(getCommentBlock(comment));

}
///////////////////TEST FUNCTION//////////////////
function getCommentBlock(text) {
    var cblock = $('<div class="row comment"></div>')
            .append($('<div class="col-md-1"></div>')
                    .append($('<img class="poster-img" width="40" height="40"/>').attr('src', '/scribbles/test/img/femi.jpg')))
            .append($('<div class="col-md-10"></div>')
                    .append($('<div class="row"></div>')
                            .append($('<a class="person"></a>').attr('href', 'profile.jsp').text('Chinwe Collette'))
                            .append($('<span class="date-grey" style="margin:0px 10px"></span>').text('2 hrs 3 mins ago')))
                    .append($('<div class="row"></div>').append($('<p></p>').text(text))));
    return cblock;
}
//////////////////CALLBACK FOR POSTED JOKE///////////////////////////////
function updatePosted(post) {
    $('#post-list').prepend(createJoke(post));
}

