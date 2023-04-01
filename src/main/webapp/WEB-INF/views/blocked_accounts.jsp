<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Blocked Accounts</title>
    <link rel="stylesheet" href="../../resources/static/css/settings.css">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>

</head>
<body>
<!----Nav Bar starts---->
<nav>
    <!----Logo starts---->
    <div class="logo">
        <label onclick="location.href=href='/home'" style="cursor:pointer">
            <i id="icon" class="fa-solid fa-globe"></i> LinkedHU_CENG</label>
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
    <div class="items">
        <a class="profile-photo" href="/profile/${user.username}"><img src="displayPp/${user.id}"></a>
        <a href="/home">Home Page <i class="fa-solid fa-house-chimney"></i></a>
        <a href="/logout">Log out <i class="fa-solid fa-arrow-right-from-bracket"></i></a>
    </div>
    <!----Items ends---->


</nav>
<!----Nav Bar ends---->

<main>
    <!----Left Side starts---->
    <div class="left">

        <!----Settings Menu Bar starts---->
        <div class="left-side-bar">
            <a class="side-bar-item" href="/settings">
                <h3>Account preferences</h3>
            </a>
            <a class="side-bar-item" href="/edit-profile">
                <h3>Edit your profile</h3>
            </a>
            <a class="side-bar-item" href="/account-access">
                <h3>Sign In & Account access</h3>
            </a>
            <a class="side-bar-item active">
                <h3> Blocked accounts </h3>
            </a>
        </div>
        <!----Settings Menu Bar starts---->
    </div>
    <!----Left Side ends---->

    <!----Right Side starts---->
    <div class="right">
        <div class="settings-menu">
            <div class="settings-container">
                <div class="menu-header">
                    <h3> Blocked accounts </h3>
                    <p>Manage the accounts you have blocked.</p>
                </div>
                <!----Accounts starts---->
                <div class="accounts">
                    <c:forEach items="${user.blockedUsers}" var="blockedUser">
                        <div class="profile-content">
                            <a href="/profile/${blockedUser.username}" class="profile">
                                <img class="photo" src=../../resources/static/img/user-image.png>
                                <p>${blockedUser.firstName} ${blockedUser.lastName}</p>
                            </a>
                            <a href="/profile/${blockedUser.username}/unblock" class="edit-button"><i class="fa-solid fa-ban"></i> Unblock user</a>
                        </div>
                    </c:forEach>


<%--                    <div class="profile-content">--%>
<%--                        <a class="profile">--%>
<%--                            <img class="photo" src=${blockedUser.profileImage}>--%>
<%--                            <p>${blockedUser.firstName} ${blockedUser.lastName}</p>--%>
<%--                        </a>--%>
<%--                        <a class="edit-button">Unblock user <i class="fa-solid fa-ban"></i></a>--%>
<%--                    </div>--%>
                </div>

            </div>
        </div>
    </div>
    <!----Right Side ends---->

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