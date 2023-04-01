<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.firstName} ${user.lastName}'s Profile</title>
    <link rel="stylesheet" href="../../resources/static/css/profile.css">
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

<main class="left">
    <div class="container">

        <!----Profile Section starts---->
        <div class="profile-container">
            <!----Header starts---->
            <div class="profile-header">
                <!----Cover starts---->
                <div class="cover">
                    <img src="../displayCover/${user.id}">
                </div>
                <!----Cover ends---->
                <!----Details starts---->
                <div class="details">
                    <div class="left-details">
                    <!----Photo starts---->
                    <div class="profile-photo">
                        <img src="../displayPp/${user.id}">
                    </div>
                    <!----Photo ends---->
                    <!----Info and Buttons starts---->
                    <div class="info-buttons">
                        <div class="info">
                            <h3>${user.firstName} ${user.lastName}</h3>
                            <p>${user.role}</p>
                            <a>Followers ${followerCount}&emsp;Followed ${followedCount}</a>
                        </div>

                        <div class="buttons">
                            <div id="follow-unfollow">
                                <a id="follow-unfollow-link"><i class="fa-solid fa-globe"></i><div id="follow-unfollow-text"></div> </a>
                            </div>
                            <div id="send-message-button">
                                <a href="/chat"><i class="fa-solid fa-paper-plane"></i>SEND MESSAGE</a>
                            </div>
                            <a target="_blank" href=${user.linkedinUrl}><i class="fa-brands fa-linkedin"></i> LINKEDIN </a>
                        </div>

                    </div>
                    <!----Info and Buttons ends---->
                    <!----User Options ends---->
                </div>
                <a id="ellipses"
                   onclick="PopUpFunction(userOptionsPopUp)"><i class="fa-solid fa-ellipsis"></i>
                </a>
                <!----Details ends---->

            </div>
            <!----Header ends---->

            <!----Profile Feed starts---->
            <div class="profile-feed">
                <!----About starts---->
                <div class="about feed-box">
                    <h3>About</h3>
                    <p>
                        ${user.about}
                    </p>

                </div>
                <!----About ends---->
                <!----Skills starts---->
                <div class="skills feed-box">
                    <h3>Skills & Endorsements</h3>
                    <div class="skills-content">
                        <c:forEach items="${user.skills}" var="skill">
                            <span><a>${skill}</a></span>
                        </c:forEach>
                    </div>
                </div>
                <!----Skills ends---->

                <!----Education starts---->
                <div class="education feed-box">
                    <h3>Education</h3>
                    <a class="content">
                        <img class="photo" src="../../resources/static/img/hacettepe-logo.jpg">
                        <div class="content-info">
                            <h4>Hacettepe University</h4>
                            <p>Bachelor's student in the field of Computer Engineering</p>
                            <p>2019-2023</p>
                        </div>
                    </a>
                </div>
                <!----Education ends---->

            </div>
            <!----Profile Feed starts---->


        </div>
        <!----Profile Section ends---->

        <!-----Options Pop Up------>
        <div class="user-options-pop-up">
            <div class="pop-up-item">
                <a class="pop-up-item-link" href="/profile/${user.username}/report"><i class="fa-solid fa-flag"></i>
                    <div>Report User</div></a>
            </div>
            <div class="pop-up-item">
                <a class="pop-up-item-link" id="block-unblock-link"><i class="fa-solid fa-ban"></i>
                    <div id="block-unblock-text"></div></a>
            </div>
        </div>

        <!----Right Side starts---->
        <div class="right">

            <!----Side Bar starts---->
            <div class="side-bar">
                <a class="side-bar-item" href="#">
                    <span><i class="profile-photo" href="#"><img src="../displayPp/${user.id}"></i><h3>Profile</h3></span>
                </a>
                <a class="side-bar-item" href="/home">
                    <span><i class="fa-solid fa-house-chimney"></i><h3>Home Page</h3></span>
                </a>

                <a onclick="closePopUp()" class="side-bar-item" id="notifications" href="#">
                    <span class="notifications-text"><i class="fa-solid fa-bell"><small class="count"  id="notification-count">6</small></i><h3>Notifications</h3></span>
                    <!-----Notification Popup------>
                    <div class="notification-popup">
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>
                        <div class="notification-body">
                            <div class="profile-photo">
                                <img src="../../resources/static/img/user-image.png">
                            </div>
                            <div>
                                <b>Name Surname</b> accepted your friend request</b>
                                <small>2 DAYS AGO</small>
                            </div>
                        </div>

                    </div>
                </a>

                <a class="side-bar-item" href="/chat">
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
        <!----Right Side ends---->

    </div>

</main>

<script src="../../resources/static/js/main.js"></script>
<script>
    var username = '${user.username}';

    if (${isSameUser}){
        document.getElementById("follow-unfollow").style.display='none';
        document.getElementById("send-message-button").style.display='none';
        document.getElementById("ellipses").hidden = true;
    }
    else {
        if (!${isFollowing}) {
            document.getElementById("follow-unfollow-text").innerHTML = "FOLLOW";
            document.getElementById("follow-unfollow-link").href = "/profile/" + username + "/follow";
        } else {
            document.getElementById("follow-unfollow-text").innerHTML = "UNFOLLOW";
            document.getElementById("follow-unfollow-link").href = "/profile/" + username + "/unfollow";
        }

        if (${isBlocked}){
            document.getElementById("block-unblock-text").innerHTML = "Unblock";
            document.getElementById("block-unblock-link").href = "/profile/" + username + "/unblock";
        }
        else {
            document.getElementById("block-unblock-text").innerHTML = "Block";
            document.getElementById("block-unblock-link").href = "/profile/" + username + "/block";
        }
    }
</script>

<!--Footer-->
<footer class="page-footer">
    <div class="page-footer-content">
        <small class="copyright">All Content Copyright 2022 - <strong>Maskeli Beşler</strong>. All Rights Reserved.
        </small>
        <small class="credits"> Site Designed by <strong>Maskeli Beşler</strong></small>
    </div>
</footer>

</body>
</html>