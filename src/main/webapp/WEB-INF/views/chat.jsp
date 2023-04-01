<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message Box</title>
    <link rel="stylesheet" href="/css/message.css">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>
    <!--    imports in below are added by mustafa -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>


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

        <!----Message Container starts---->
        <div id = "chatRoomAppender" class="message-container">

            <div class = "message-box">

                <!-- TODO better if you can -->
                <div class ="search" style="visibility: hidden">
                    <input id="userName" type = "text" placeholder="search" value= '${user.username}'/>
                </div>

                <a class="item" id="messages-title">
                    <span><h3>Messages</h3></span>
                    <i class="fa-solid fa-pen"></i>
                </a>

                <!-- user list starts -->
                <ul id="usersList" class="list">

                </ul>
                <!-- user list ends -->
            </div>


            <!-------Default Message Tab starts---->
            <div id="default-message-tab" class="default-message-tab">
                <div class="message-tab-content">
                    <h2 class="align">Your Messages</h2>
                    <p class="align">Send messages to your connections</p>
                    <a class="align"> Send Messages </a>
                </div>
            </div>
            <!-------Default Message Tab end---->

        </div>
        <!----Message Container ends---->


        <!----Right Side starts---->
        <div class="right">
            <!----Side Bar starts---->
            <div class="side-bar">
                <a class="side-bar-item" href="/profile/${user.username}">
                    <span><i class="profile-photo"><img src="../../resources/static/img/user-image.png"></i><h3>Profile</h3></span>
                </a>
                <a class="side-bar-item" href="/home">
                    <span><i class="fa-solid fa-house-chimney"></i><h3>Home Page</h3></span>
                </a>
                <a class="side-bar-item" href="#">
                    <span><i class="fa-solid fa-bell"></i><h3>Notifications</h3></span>
                </a>

                <a class="side-bar-item active" href="#">
                    <span><i class="fa-solid fa-message"></i><h3>Messages</h3></span>
                </a>
                <a class="side-bar-item" href="#">
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

<!--Footer-->
<footer class="page-footer">
    <div class="page-footer-content">
        <small class="copyright">All Content Copyright 2022 - <strong>Maskeli Beşler</strong>. All Rights Reserved.
        </small>
        <small class="credits"> Site Designed by <strong>Maskeli Beşler</strong></small>
    </div>
</footer>


<!-- TODO days are shown with "Today". -->
<script id="message-template" type="text/x-handlebars-template">
    <li class="sent-inbox">
        <div class="sent-inbox">
            <div class="sent-message">
                <p>{{content}}</p>
                <span class="time">{{time}}</span>
            </div>
            <i class="profile-photo" href="/profile/${user.username}"><img
                    src="../../resources/static/img/user-image.png"></i>
        </div>
    </li>
</script>

<script id="message-response-template" type="text/x-handlebars-template">
    <li>
        <div class="received-inbox">
            <i class="profile-photo" href="{{senderProfile}}"><img src="../../resources/static/img/user-image.png"></i> <!--TODO go to sender's profile-->
            <div class="received-message">
                <p>{{content}}</p>
                <span class="time">{{time}}</span>
            </div>
        </div>
    </li>
</script>

<script id="chat-room" type="text/x-handlebars-template">
    <div  class="chat-room" >
        <!-------Chat Header starts---->
        <div class="chat-header">
            <div class="user-info">
                <i class="profile-photo" href="#"><img src="../../resources/static/img/user-image.png"></i>
                <a id="selectedUserId" href="#"><h3></h3></a>
            </div>
            <a class="chat-options"> <i class="fa-solid fa-ellipsis-vertical"></i> </a>

        </div>
        <!-------Chat Header ends---->


        <!-------chat history starts---->
        <div id = "chatList" class="chat">
            <ul>

            </ul>

        </div> <!-- end chat-history -->

        <!-------Chat ends ends---->

        <!-------Chat Bottom starts---->
        <div class="chat-bottom">
            <!-------Chat Form starts---->
            <div class="chat-form">
                <textarea id="message-to-send" name="message-to-send" placeholder="Write your message here..." ></textarea>
                <button id="sendBtn">
                    <i class="fa-solid fa-paper-plane"></i>
                </button>
            </div>
            <!-------Chat Form ends ends---->
        </div>
        <!-------Chat Bottom end---->

    </div>
    <!-------Chat Room ends---->
</script>


<script src="/js/chat.js"></script>
<script src="/js/custom.js"></script>
<!-- imports by mustafa ends -->

</body>
</html>