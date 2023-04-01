<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Group Page</title>
    <link rel="stylesheet" href="../../resources/static/css/group.css">
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
<main class>
    <div class="container">
    <!----Right Side starts---->
    <div class="left">

        <!----Side Bar starts---->
        <div class="side-bar">
            <a class="side-bar-item" href="/profile/${user.username}">
                <span><i class="profile-photo" href="/profile/${user.username}"><img src="/displayPp/${user.id}"></i><h3>Profile</h3></span>
            </a>
            <a class="side-bar-item" href="/home">
                <span><i class="fa-solid fa-house-chimney"></i><h3>Home Page</h3></span>
            </a>
            <a class="side-bar-item" href="#">
                <span><i class="fa-solid fa-bell"></i><h3>Notifications</h3></span>
            </a>

            <a class="side-bar-item" href="/chat">
                <span><i class="fa-solid fa-message"></i><h3>Messages</h3></span>
            </a>
        </div>
        <!----Side Bar ends---->

        <!----Groups Bar starts---->
        <div class="groups-bar">
            <a class="side-bar-item" id="groups-title">
                <span><h3>Groups</h3></span>
            </a>

            <c:forEach items="${groups}" var="membership">
                <a class="side-bar-item" href="/group/${membership.group.id}">
                    <span><h3>${membership.group.name}</h3></span>
                </a>
            </c:forEach>
        </div>
        <!----Groups Bar ends---->
    </div>
    <!----Right Side ends---->

    <!----Profile Section starts---->
    <div class="profile-container">
        <!----Header starts---->
        <div class="profile-header">
            <!----Cover starts---->
            <div class="cover">
                <img src="/displayGroupCover/${group.id}">
            </div>
            <!----Cover ends---->
            <!----Details starts---->
            <div class="details">
                <!----Photo starts---->
                <div class="profile-photo">
                    <img src="/displayGp/${group.id}">
                </div>
                <!----Photo ends---->
                <!----Info and Buttons starts---->
                <div class="info-buttons">
                    <div class="info">
                        <h3>${group.name}</h3>
                        <a>${memberCount} members</a>
                    </div>

                    <div class="buttons">
                        <a id="join-leave-link"><i class="fa-solid fa-user-group"></i><div id="join-leave-text"></div></a>
                    </div>

                    <div class="buttons">
                        <a href="/group/${group.id}/report"><i class="fa-solid fa-user-group"></i>REPORT GROUP</a>
                    </div>
                </div>
                <!----Info and Buttons starts---->

            </div>
            <!----Details ends---->

        </div>
        <!----Header ends---->

        <!----Profile Feed starts---->
        <div class="info-feed">

            <div class="group-info">
                <!----About starts---->
                <div class="about feed-box">
                    <h3>About</h3>
                    <p>
                        ${group.description}
                    </p>

                </div>
                <!----About ends---->
                <!----Members starts---->
                <div class="members feed-box">
                    <h3>Members</h3>
                    <div class="member-profiles">
                        <c:forEach items="${members}" var="membership">
                            <a href="/profile/${membership.member.username}" class="profile-photo">
                                <img src="/displayPp/${membership.member.id}">
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <!----Members ends---->
            </div>

            <!----Middle Section starts---->
            <div class="middle">

                <!----Create Post starts---->
                <form method="post" action="/group/${group.id}/add-post" class="post-bar" enctype="multipart/form-data">
                    <div id="profile-photo">
                        <img src="/displayPp/${user.id}">
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
                                        <img src="/displayPp/${post.owner.id}">
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

                            <!-- TODO check if image -->
                            <div class="post-content">
                                <c:set var="fileType" value="${post.file.fileType}"/>
                                <c:set var="fileTypeSplit" value="${fn:split(fileType, '/')}"/>
                                <c:if test = "${fileTypeSplit[0] == 'image'}">
                                    <img src="/group/display/${post.id}">
                                </c:if>
                                <c:if test="${fileTypeSplit[0] == 'application'}">
                                    <a href="/group/downloadFile/${post.file.id}">${post.file.name}</a>
                                </c:if>
                                <c:if test = "${fileTypeSplit[0] == 'video'}">
                                    <video controls>
                                        <source src="/group/displayVideo/${post.id}" type="video/mp4">
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
        <!----Profile Feed starts---->
    </div>
    <!----Profile Section ends---->
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
<script>
    if (${isFounder}){
        document.getElementById("join-leave-text").innerHTML = "DELETE GROUP";
        document.getElementById("join-leave-link").href = "/group/${id}/delete";
    }
    else{
        if (${isMember}){
            document.getElementById("join-leave-text").innerHTML = "LEAVE GROUP";
            document.getElementById("join-leave-link").href = "/group/${id}/remove-member";
        }
        else{
            document.getElementById("join-leave-text").innerHTML = "JOIN GROUP";
            document.getElementById("join-leave-link").href = "/group/${id}/add-member";
        }
    }

</script>

</body>
</html>