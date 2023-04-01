<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Post</title>
    <link rel="stylesheet" href="../../resources/static/css/post.css">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>
</head>
<body>
<!----Nav Bar starts---->
<nav>
    <!----Logo starts---->
    <div class="logo">
        <label onclick="location.href=href='/home'" style="cursor:pointer"> <i id="icon" class="fa-solid fa-globe"></i> LinkedHU_CENG</label>
    </div>
    <!----Logo ends---->

    <!----Search form starts---->
    <div class="search-form">
        <input id="search-bar" type="search" placeholder="      Search.."
               onkeypress="this.style.width = ((this.value.length + 1) * 8) + 'px';">
        <button type="submit" id="search-button">
            <i class="fa-solid fa-magnifying-glass"></i>
        </button>
    </div>
    <!----Search form ends---->

    <!----Items starts---->
    <ul class="items">
        <li><a href="/home">Home Page <i class="fa-solid fa-house-chimney"></i></a></li>
        <li><a href="/logout">Log out <i class="fa-solid fa-arrow-right-from-bracket"></i></a></li>
        <li> <a class="profile-photo" href="/profile/${currentUser.username}"><img src="../displayPp/${currentUser.id}"></a></li>
    </ul>
    <!----Items ends---->


</nav>
<!----Nav Bar ends---->

<main class=>
    <div class="container">

        <!-------Feed Starts------>
        <div class="feeds">
            <!-------Post Starts------>
            <div class="post">
                <div class="post-header">
                    <div class="user">
                        <img class="profile-photo" src="../displayPp/${post.owner.id}">
                        <div class="info">
                            <h3 onclick="location.href=href='/profile/${post.owner.username}'" style="cursor:pointer">
                                ${post.owner.firstName} ${post.owner.lastName}<small class="status">${post.owner.role}</small></h3>
                            <small class="time">${post.formattedDate}</small>
                        </div>
                    </div>
                    <a class="post-options" id="ellipses" onclick="PopUpFunction(postPopUp)"><i class="fa-solid fa-ellipsis"></i>
                    </a>
                </div>

                <div class="post-content">
                    <c:set var="fileType" value="${post.file.fileType}"/>
                    <c:set var="fileTypeSplit" value="${fn:split(fileType, '/')}"/>
                    <c:if test = "${fileTypeSplit[0] == 'image'}">
                        <img src="${post.id}/display">
                    </c:if>
                    <c:if test="${fileTypeSplit[0] == 'application'}">
                        <a href="${post.id}/downloadFile">${post.file.name}</a>
                    </c:if>
                    <c:if test = "${fileTypeSplit[0] == 'video'}">
                        <video controls>
                            <source src="${post.id}/displayVideo" type="video/mp4">
                        </video>
                    </c:if>
                </div>

                <div class="caption" id="post-content">
                    <p>${post.content}</p>
                </div>
                <div class="liked-by">
                    <span><img src="../../resources/static/img/like.png"></span>
                    <p>Liked by ${likeCount}</p>
                </div>
                <div class="post-footer">
                    <div class="post-buttons">
                        <a id="like-unlike-link"><i id="like-b"class="fa-solid fa-thumbs-up" ></i><div id="like-unlike-text"></div></a>
                        <a id="add-comment" onclick="addComment()"><i id="comment-b" class="fa-solid fa-comment-dots"></i> ADD COMMENT</a>
                        <a id="fav-unfav-link"><i class="fa-solid fa-bookmark" id="fav-b"></i><div id="fav-unfav-text"></div></a>
                    </div>
                    <!-----Comments starts---->
                    <div class="comment-box">
                        <div class="make-comment">
                            <div class="comment-form">
                                <form class="comment-form" method="post" action="/post/${post.id}">
                                    <textarea name="comment_content" id="text-input-box" type="text" placeholder="Write a comment here..." required></textarea>
                                    <button type="submit" id="append">
                                        <i class="fa-solid fa-paper-plane"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${comments}" var="comment">
                        <div class="comment">
                            <img class="profile-photo" src="../displayPp/${comment.user.id}">
                            <span id = "deleteButtonAppender_${comment.id}" class="comment-content">
                            <p class="commenter-name">${comment.user.firstName} ${comment.user.lastName}</p>
                            <p class="comment-text">${comment.content}</p>
                            <p class="comment-time">${comment.formattedDate}</p>
                            </span>
<%--                            <a id="delete-comment"> <i class="fa-solid fa-trash"></i></a>--%>
                        </div>
                    </c:forEach>
                    </div>
                    <!-----Comments ends---->
                </div>
            <!-------Post Ends------>
        </div>
        <!-------Feed Ends------>
        <!-----Options Pop Up------>
        <div class="pop-up">
            <!-- FOLLOW or EDIT -->
            <div class="pop-up-item">
                <a class="pop-up-item-link" id="follow-or-edit-link"><i class="fa-solid fa-flag"></i>
                    <div id="follow-or-edit-text"></div></a>
            </div

            <!-- BLOCK or DELETE -->
            <div class="pop-up-item">
                <a class="pop-up-item-link" id="block-or-delete-link"><i class="fa-solid fa-flag"></i>
                    <div id="block-or-delete-text"></div></a>
            </div>

            <!-- REPORT -->
            <div class="pop-up-item" id="report-user">
                <a class="pop-up-item-link" id="report-user-link"><i class="fa-solid fa-flag"></i>
                    <div id="report-user-text"></div></a>
            </div>

            <div class="pop-up-item" id="report-post">
                <a class="pop-up-item-link" id="report-post-link"><i class="fa-solid fa-flag"></i>
                    <div id="report-post-text"></div></a>
            </div>
        </div>

</main>
<!--Footer-->
<footer class="page-footer">
    <div class="page-footer-content">
        <small class="copyright">All Content Copyright 2022 - <strong>Maskeli Beşler</strong>. All Rights Reserved.
        </small>
        <small class="credits"> Site Designed by <strong>Maskeli Beşler</strong></small>
    </div>
</footer>
<script src="../../resources/static/js/main.js"></script>
<script src="../../resources/static/js/post-comment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
    console.log("hi");

    const editPostHtml = '<form method="post" action="/post/' + '${post.id}' +
        '/edit"><div class="text"><input name="updatedContent" type="text" value=' + '${post.content}' +
        '></div></form>';

    function activateEditPost(){
        document.getElementById("post-content").innerHTML = editPostHtml;
    }

    if(${isPostLiked}){
        document.getElementById("like-unlike-text").innerHTML = "UNLIKE";
        document.getElementById("like-unlike-link").href = "/post/" + '${post.id}' + "/unlike";
    }
    else{
        document.getElementById("like-unlike-text").innerHTML = "LIKE";
        document.getElementById("like-unlike-link").href = "/post/" + '${post.id}' + "/like";
    }

    if(${isPostFaved}){
        document.getElementById("fav-unfav-text").innerHTML = "UNFAVORITE";
        document.getElementById("fav-unfav-link").href = "/post/" + '${post.id}' + "/unfavorite";
    }
    else{
        document.getElementById("fav-unfav-text").innerHTML = "FAVORITE";
        document.getElementById("fav-unfav-link").href = "/post/" + '${post.id}' + "/favorite";
    }

    if (${isSameUser}){
        document.getElementById("follow-or-edit-text").innerHTML = "Edit Post";
        document.getElementById("follow-or-edit-text").setAttribute('onclick', 'activateEditPost()');

        document.getElementById("block-or-delete-text").innerHTML = "Delete Post";
        document.getElementById("block-or-delete-link").href = "/post/" + '${post.id}' + "/delete";

        document.getElementById("report-user").style.display='none';
        document.getElementById("report-post").style.display='none';
    }
    else {
        if (!${isFollowing}) {
            document.getElementById("follow-or-edit-text").innerHTML = "Follow User";
            document.getElementById("follow-or-edit-link").href = "/profile/" + '${post.owner.username}' + "/follow";
        } else {
            document.getElementById("follow-or-edit-text").innerHTML = "Unfollow User";
            document.getElementById("follow-or-edit-link").href = "/profile/" + '${post.owner.username}' + "/unfollow";
        }

        if (${isBlocked}) {
            document.getElementById("block-or-delete-text").innerHTML = "Unblock User";
            document.getElementById("block-or-delete-link").href = "/profile/" + '${post.owner.username}' + "/unblock";
        } else {
            document.getElementById("block-or-delete-text").innerHTML = "Block User";
            document.getElementById("block-or-delete-link").href = "/profile/" + '${post.owner.username}' + "/block";
        }

        document.getElementById("report-user-text").innerHTML = "Report User";
        document.getElementById("report-user-link").href = "/profile/" + '${post.owner.username}' + "/report";

        document.getElementById("report-post-text").innerHTML = "Report Post";
        document.getElementById("report-post-link").href = "/post/" + '${post.id}' + "/report";
    }

    console.log("hello"); // TODO delete

    $.get("/fetchOwnedComments/" + '${post.id}', function (response) {
        let comments = response;
        console.log(comments)
        for (let i = 0; i < comments.length; i++) {
            $('#deleteButtonAppender_' + comments[i]).append('<a id="delete-comment" href="/post/' + '${post.id}'+ '/deleteComment/' + comments[i] + '"> <i class="fa-solid fa-trash"></i></a>');
        }
    });

</script>

</body>
</html>