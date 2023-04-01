<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="../../resources/static/css/home.css">
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
        <input id="search-bar" type="search" placeholder="      Search..">
        <button type="submit" id="search-button">
            <i class="fa-solid fa-magnifying-glass"></i>
        </button>
    </div>
    <!----Search form ends---->

    <!----Items starts---->
    <ul class="items">
        <li><a href="/logout">Log out <i class="fa-solid fa-arrow-right-from-bracket"></i></a></li>
    </ul>
    <!----Items ends---->


</nav>
<!----Nav Bar ends---->

<main class=>
    <div class="container">
        <!----Left Side starts---->
        <div class="left">

            <!----Profile Bar starts---->
            <div class="profile-bar">
                <a class="profile-photo" href="/profile/${user.username}"><img src="displayPp/${user.id}"></a>
                <div class="profile-info">
                    <a class="name" href="/profile/${user.username}"> ${user.firstName} ${user.lastName}</a>
                    <p class="member-type">${user.role}</p>
                </div>
            </div>
            <!----Profile Bar ends---->

            <!----Side Bar starts---->
            <div class="left-side-bar">
                <a class="side-bar-item active" href="#">
                    <span><i class="fa-solid fa-house-chimney"></i><h3>Home Page</h3></span>
                </a>
                <a class="side-bar-item" id="notifications" href="#">
                    <span><i class="fa-solid fa-bell"><small class="count" id="notification-count">6</small></i><h3>Notifications</h3></span>
                    <!-----Notification Popup------>
                    <div id="notificationAppender" class="notification-popup">
<%--                        <div>--%>
<%--                            <div class="profile-photo">--%>
<%--                                <img src="../../resources/static/img/user-image.png">--%>
<%--                            </div>--%>
<%--                            <div class="notification-body">--%>
<%--                                <b>Name Surname</b> accepted your friend request</b>--%>
<%--                                <small>2 DAYS AGO</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                </a>

                <a class="side-bar-item" id=""  href="/chat" >
                    <span><i class="fa-solid fa-message"></i><h3>Messages</h3></span>
                </a>

                <a class="side-bar-item" href="/mail">
                    <span><i class="fa-solid fa-envelope"></i><h3>Mail Box</h3></span>
                </a>
                <a class="side-bar-item" href="/favorited-posts">
                    <span><i class="fa-solid fa-bookmark"></i><h3>Favorites</h3></span>
                </a>
                <a class="side-bar-item" href="/settings">
                    <span><i class="fa-solid fa-gear"></i><h3>Settings</h3></span>
                </a>
            </div>
            <!----Side Bar ends---->


        </div>
        <!----Left Side ends---->

        <!----Middle Section starts---->
        <div class="middle">

            <!----Create Post starts---->
            <form method="post" action="/home" class="post-bar" enctype="multipart/form-data">
                <div id="profile-photo">
                    <img src="displayPp/${user.id}">
                </div>
                <div class="create-post">
                        <div class="text">
                            <input name="content" id="post-input" type="text" placeholder="What are you thinking?">
                            <button type="submit" value="post" id="post-button">
                                <i class="fa-solid fa-paper-plane"></i>
                            </button>
                        </div>
                    <div class="media-type">

                        <!---File Upload Starts--->
                        <label for="file-upload" class="custom-file-upload">
                            <i class="fa-solid fa-folder-open"></i> File
                        </label>
                        <input id="file-upload" name="file" type="file" accept="application/*"/>
                        <!---File Upload Ends--->

                        <!---Photo Upload Starts--->
                        <label for="image-upload" class="custom-file-upload">
                            <i class="fa-solid fa-image"></i> Image
                        </label>
                        <input id="image-upload" name="image" type="file" accept="image/*"/>
                        <!---Photo Upload Ends--->

                        <!---Video Upload Starts--->
                        <label for="video-upload" class="custom-file-upload">
                            <i class="fa-solid fa-film"></i> Video
                        </label>
                        <input id="video-upload" name="video" type="file" accept="video/*"/>
                        <!---Video Upload Ends--->
                    </div>
                </div>
            </form>
            <!----Create Post ends---->

            <!-------Feed Starts------>
            <div class="feeds">
                <!-------Post Starts------>
                <c:forEach items="${posts}" var="post">
                    <div class="post">

                        <div class="post-header">
                            <div class="user">
                                <div class="profile-photo">
                                    <img src="displayPp/${post.owner.id}">
                                </div>
                                <div class="info">
                                    <h3 onclick="location.href=href='/profile/${post.owner.username}'" style="cursor:pointer">
                                            ${post.owner.firstName} ${post.owner.lastName}
                                                <small class="status">${post.owner.role}</small></h3>
                                    <small class="time"> ${post.formattedDate}</small>
                                </div>
                            </div>
                        </div>

                        <!-- TODO check if image -->
                        <div class="post-content">
                            <c:set var="fileType" value="${post.file.fileType}"/>
                            <c:set var="fileTypeSplit" value="${fn:split(fileType, '/')}"/>
                            <c:if test = "${fileTypeSplit[0] == 'image'}">
                                <img src="home/display/${post.id}">
                            </c:if>
                            <c:if test="${fileTypeSplit[0] == 'application'}">
                                <a href="home/downloadFile/${post.file.id}">${post.file.name}</a>
                            </c:if>
                            <c:if test = "${fileTypeSplit[0] == 'video'}">
                                <video controls>
                                    <source src="home/displayVideo/${post.id}" type="video/mp4">
                                </video>
                            </c:if>
                        </div>

                        <div class="caption" onclick="location.href=href='/post/${post.id}'" style="cursor:pointer">
                            <p >${post.content}</p>
                        </div>

                        <div class="liked-by">
                            <span><img src="../../resources/static/img/like.png"></span>
                        </div>
                        <div class="outline"></div>
                        <div class="post-buttons">
                            <a href="/post/${post.id}"><i class="fa-solid fa-thumbs-up"></i> LIKE </a>
                            <a href="/post/${post.id}"><i class="fa-solid fa-comment-dots"></i> ADD COMMENT </a>
                            <a href="/post/${post.id}"><i class="fa-solid fa-bookmark"></i> ADD TO FAVORITES </a>
                        </div>
                <!-------Post Ends------>
                    </div>
                </c:forEach>
            <!-------Feed Ends------>
        </div>
        <!----Middle Section ends---->
        </div>

        <!----Right Side starts---->
        <div class="right">
            <!----Side Bar starts---->
            <div class="groups-bar">
                <a class="side-bar-item">
                    <span><h3>Groups</h3></span>
                </a>
                <c:forEach items="${memberships}" var="membership">
                    <a class="side-bar-item" href="/group/${membership.group.id}">
                        <span><h3>${membership.group.name}</h3></span>
                    </a>
                </c:forEach>

                <a class="side-bar-item" href="/create-group" id="create-group">
                    <span><i class="fa-solid fa-plus"></i><h3>Create New Group</h3></span>
                </a>
            </div>
            <!----Side Bar ends---->
        </div>
        <!----Right Side ends---->
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script>
    console.log("in home.jsp script");

    $.get("/fetchNotifications", function (response) {
        let notifications = response;
        console.log(notifications)
        for (let i = 0; i < notifications.length; i++) {
            $('#notificationAppender').append('<div>'+
                '<div class="profile-photo">'+
                '<img src="../../resources/static/img/user-image.png">'+
                '</div>'+
            '<div class="notification-body">'+
                '<b>'+ notifications[i][0]+'</b>'+  <!-- content -->
            '<small>'+ notifications[i][1] +'</small>'+ <!-- date -->
        '</div>'+
        '</div>');
        }
    });

</script>

</body>
</html>