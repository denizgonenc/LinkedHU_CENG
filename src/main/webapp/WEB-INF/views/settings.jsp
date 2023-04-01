<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Preferences</title>
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
            <a class="side-bar-item active">
                <h3>Account preferences</h3>
            </a>
            <a class="side-bar-item" href="/edit-profile">
                <h3>Edit your profile</h3>
            </a>
            <a class="side-bar-item" href="/account-access">
                <h3>Sign In & Account access</h3>
            </a>
            <a class="side-bar-item" href="/blocked-accounts">
                <h3> Blocked accounts </h3>
            </a>
        </div>
        <!----Settings Menu Bar starts---->
    </div>
    <!----Left Side ends---->

    <!----Right Side starts---->
    <div class="right">
        <div class="settings-menu">
            <form method="post" class="settings-container" action="/settings">
                <div class="menu-header">
                    <h3> Profile information </h3>
                    <p>Your changes will appear in your profile</p>
                </div>
                <div class="change-info">
                    <label for="name">First name*</label>
                    <br>
                    <input name="firstName" type="text" id="name" required placeholder="${user.firstName}">
                </div>
<%--                <div class="change-info">--%>
<%--                    <label for="additional-name">Additional name:</label>--%>
<%--                    <br>--%>
<%--                    <input type="text" id="additional-name">--%>
<%--                </div>--%>
                <div class="change-info">
                    <label for="surname">Last name*</label>
                    <br>
                    <input name="lastName" type="text" id="surname" required placeholder="${user.lastName}">
                </div>
                <div class="menu-header">
                    <h3>Location</h3>
                </div>
                <div class="change-info"><label for="country-region">Country/Region*</label>
                    <br>
                    <input name="countryOrRegion" type="text" id="country-region" required placeholder="${user.countryOrRegion}">
                </div>
                <div class="change-info"><label for="city">City</label>
                    <br>
                    <input name="city" type="text" id="city" placeholder="${user.city}">
                </div>
                <div class="settings-footer">
                    <button id="save" type="submit"> Save</button>
                </div>
            </form>
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