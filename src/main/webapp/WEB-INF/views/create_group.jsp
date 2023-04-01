<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Group</title>
    <link rel="stylesheet" href="../../resources/static/css/create-group.css">
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
    <div class="items">
        <a class="profile-photo" href="/profile/${user.username}"><img src="../../resources/static/img/user-image.png"></a>
        <a href="/home">Home Page <i class="fa-solid fa-house-chimney"></i></a>
        <a href="/logout">Log out <i class="fa-solid fa-arrow-right-from-bracket"></i></a>
    </div>
    <!----Items ends---->


</nav>
<!----Nav Bar ends---->

<main>
    <!----Left Side starts---->
    <div class="left">

        <!----Side Bar starts---->
        <div class="groups-bar">
            <a class="side-bar-item">
                <span><h3>Groups</h3></span>
            </a>

            <c:forEach items="${groups}" var="membership">
                <a class="side-bar-item" href="/group/${membership.group.id}">
                    <span><h3>${membership.group.name}</h3></span>
                </a>
            </c:forEach>

        </div>
        <!----Side Bar ends---->
    </div>
    <!----Left Side ends---->

    <!----Right Side starts---->
    <div class="right">
        <div class="settings-menu">
            <form enctype="multipart/form-data" method="post" action="/create-group/create" class="settings-container">
                <div class="menu-header">
                    <h3> Create New Group </h3>
                    <p>Create a new group by filling the form below. </p>
                </div>
                <!-----Group Name------->
                <div class="sub-section">
                    <label for="group-name">Group Name:</label>
                    <br>
                    <input name="name" type="text" id="group-name" required>
                </div>
                <!-----Change photos------>
                <div class="sub-section " id="edit-photos">
                    <!----Photos on the left starts---->
                    <div class="display">
                        <!----Cover starts---->
                        <div class="cover">
                            <div class="cover-photo">
                                <img src="../../resources/static/img/default-cover-page.png">
                            </div>
                        </div>
                        <!----Cover ends---->
                        <!----Photo starts---->
                        <div class="profile-photo">
                            <img src="../../resources/static/img/user-image.png">
                        </div>
                        <!----Photo ends---->
                    </div>
                    <!----Photos on the left ends---->
                    <!----File uploads on the right starts---->
                    <div class="upload-photo">
                        <div class="sub-section last-sub-child">
                            <label for="cover">Upload Cover Photo:</label>
                            <br>
                            <input name="cover" class="image-upload" id="cover" type="file" accept="image/*"/>
                        </div>
                        <div class="sub-section last-sub-child">
                            <label for="pp">Upload Profile Photo:</label>
                            <br>
                            <input name="gp" class="image-upload" id="pp" type="file" accept="image/*"/>
                        </div>
                    </div>
                    <!----File uploads on the right ends---->

                </div>
                <!-----Biography------>
                <div class="sub-section last-sub-child">
                    <label for="description">Group Description:</label>
                    <br>
                    <textarea name="description" class="profile" type="text" id="description" rows="5" cols="70" required></textarea>
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