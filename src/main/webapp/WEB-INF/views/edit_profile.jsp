<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Your Profile</title>
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
            <a class="side-bar-item active">
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
            <form method="post" class="settings-container" enctype="multipart/form-data" action="/edit-profile">
                <div class="menu-header">
                    <h3> Edit your profile </h3>
                    <p>Edit how people see your profile.</p>
                </div>
                <!-----Change photos------>
                <div class="sub-section last-sub-child" id="edit-photos">
                    <!----Photos on the left starts---->
                    <div class="display">
                        <!----Cover starts---->
                        <div class="cover">
                            <div class="cover-photo">
                                <img src="displayCover/${user.id}">
                            </div>
                        </div>
                        <!----Cover ends---->
                        <!----Photo starts---->
                        <div class="profile-photo">
                            <img src="displayPp/${user.id}">
                        </div>
                        <!----Photo ends---->
                    </div>
                    <!----Photos on the left ends---->
                    <!----File uploads on the right starts---->
                    <!---- TODO MURAT --->
                    <div class="upload-photo">
                        <div class="sub-section">
                            <label for="cover">Upload Cover Photo</label>
                            <br>
                            <input class="image-upload" id="cover" name="cover" type="file" accept="image/*"/>
                        </div>
                        <div class="sub-section">
                            <label for="pp">Upload Profile Photo</label>
                            <br>
                            <input class="image-upload" id="pp" name="pp" type="file" accept="image/*"/>
                        </div>
                    </div>
                    <!----File uploads on the right ends---->

                </div>
                <!-----Other Information------>
                <div class="menu-header sub-header">
                    <h3> Other Information</h3>
                </div>
                <div class="sub-section">
                    <label for="about">About</label>
                    <br>

                    <textarea name="about" class="profile" type="text" id="about" rows="5" cols="70">${user.about}</textarea>
                </div>
                <div class="sub-section last-sub-child">
                    <label for="skills"> Skills & Endorsements (Please leave space between skills)</label>
                    <br>
                    <textarea name="skills" class="profile" type="text" id="skills" rows="5" cols="70">${skills}</textarea>
                </div>
                <div class="menu-header sub-header experience">
                    <h3>Experience</h3>
                    <a id="add-experience-button" onclick="addExpFunction()">
                        <i id="add-experience-icon" class="fa-solid fa-plus"></i>
                    </a>

                </div>

                <!-- submit -->
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