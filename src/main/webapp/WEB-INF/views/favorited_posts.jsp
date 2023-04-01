<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <a class="profile-photo" href="#"><img src="../../resources/static/img/user-image.png"></a>
                <div class="profile-info">
                    <a class="name" href="/profile/${user.username}"> ${user.firstName} ${user.lastName}</a>
                    <p class="member-type">${user.role}</p>
                </div>
            </div>
            <!----Profile Bar ends---->

            <!----Side Bar starts---->
            <div class="left-side-bar">
                <a class="side-bar-item" href="/home">
                    <span><i class="fa-solid fa-house-chimney"></i><h3>Home Page</h3></span>
                </a>
                <a class="side-bar-item" id="notifications" href="#">
                    <span><i class="fa-solid fa-bell"><small class="count" id="notification-count">6</small></i><h3>Notifications</h3></span>
                    <!-----Notification Popup------>
                    <div class="notification-popup">
                        <div>
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div class="notification-body">
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div>
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div class="notification-body">
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div>
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div class="notification-body">
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                    </div>
                </a>

                <a class="side-bar-item" id="messages" href="/chat">
                    <span><i class="fa-solid fa-message"><small class="count" id="message-notifications">6</small></i><h3>Messages</h3></span>
                </a>
                <a class="side-bar-item" href="#">
                    <span><i class="fa-solid fa-envelope"></i><h3>Mail Box</h3></span>
                </a>
                <a class="side-bar-item active">
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
        <div class="middle" style="alignment: center">
                <div class="post-bar">
                    <p style="font-family: sans-serif; color: #757575;">Your Favorite Posts</p>
                </div>

            <!-------Feed Starts------>
            <div class="feeds">
                <!-------Post Starts------>
                <c:forEach items="${posts}" var="post">
                    <div class="post">
                        <div class="post-header">
                            <div class="user">
                                <div class="profile-photo">
                                    <img src="../../resources/static/img/user-image.png">
                                </div>
                                <div class="info">
                                    <h3 onclick="location.href=href='/profile/${post.owner.username}'" style="cursor:pointer">
                                            ${post.owner.firstName} ${post.owner.lastName}
                                        <small class="status">${post.owner.role}</small></h3>
                                    <small class="time"> ${post.formattedDate}</small>
                                </div>
                            </div>
                            <span class="post-options"><i class="fa-solid fa-ellipsis"></i></span>
                        </div>

                            <%--                        <div class="post-content">--%>
                            <%--                            <!-- TODO get post photo w/e -->--%>
                            <%--                        </div>--%>
                        <div class="caption" onclick="location.href=href='/post/${post.id}'" style="cursor:pointer">
                            <p >${post.content}</p>
                        </div>
                        <!-- no way to get like count in for each afaik :/ -->
                            <%--                        <div class="liked-by">--%>
                            <%--                            <span><img src="../../resources/static/img/user-image.png"></span>--%>
                            <%--                        </div>--%>
                        <div class="outline"></div>
                        <div class="post-buttons">
                            <a href="/post/${post.id}"><i class="fa-solid fa-thumbs-up"></i> LIKE </a>
                            <a href="/post/${post.id}"><i class="fa-solid fa-comment-dots"></i> ADD COMMENT </a>
                            <a href="/post/${post.id}"><i class="fa-solid fa-bookmark"></i> ADD TO FAVORITES </a>
                        </div>

                    </div>
                </c:forEach>
                <!-------Post Ends------>
            </div>
            <!-------Feed Ends------>

        </div>
        <!----Middle Section ends---->

        <!----Right Side starts---->
        <div class="right">
            <!----Side Bar starts---->
            <div class="groups-bar">
                <a class="side-bar-item">
                    <span><h3>Groups</h3></span>
                </a>
                <a class="side-bar-item" href="#">
                    <span><h3>BBM 382/384 Course</h3></span>
                </a>
                <a class="side-bar-item" href="#">
                    <span><h3>BBM341 Course</h3></span>
                </a>
                <a class="side-bar-item" href="#">
                    <span><h3>BBM405 Course</h3></span>
                </a>
                <a class="side-bar-item" href="#">
                    <span><h3>Maskeli Beşler Project Group</h3></span>
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

</body>
</html>