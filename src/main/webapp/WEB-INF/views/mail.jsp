<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mail Başlığı</title>
    <link rel="stylesheet" href="../../../resources/static/css/mail.css">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>
</head>
<body>

<!----Nav Bar starts---->
<nav>
    <!----Logo starts---->
    <div class="logo">
        <label> <i id="icon" class="fa-solid fa-globe"></i> LinkedHU_CENG</label>

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

    <div class="profile">
        <a href="/profile"> User Name <img class="profile-photo" src="../../../resources/static/img/user-image.png"></a>
    </div>
    <!---New Mail starts--->
    <!-- The Modal -->
    <div id="modal" class="modal">

        <!-- Modal content -->
        <div class="new-mail-box">
            <!----Mail Box Header starts--->
            <div class="header">
                <p><i class="fa-solid fa-envelope-open"></i> New Mail</p>
                <span class="close">&times;</span>

            </div>
            <!----Mail Box Header ends--->
            <!----Mail Box Content starts--->
            <div class="mail-box-content">
                <form method="post" class="settings-container" action="/mail">
                    <div class="info-input">
                        <label for="to"> To: </label>
                        <input  name="to" type="email" id="to" required placeholder="${email.to}">
                    </div>
                    <div class="info-input">
                        <label for="subject"> Subject: </label>
                        <input name="subject" id="subject" type="text" required placeholder="${email.subject}">
                    </div>
                    <div class="mail-area">
                        <textarea name="content" id="mail-text" rows="22" cols="80"  class="form-control" required placeholder="${email.content}"></textarea>
                    </div>

                    <div class="footer">
                        <button type="submit">Send</button>
                        <div class="media-type">

                            <!---File Upload Starts--->
                            <label for="file-upload" class="custom-file-upload">
                                <i class="fa-solid fa-folder-open"></i> </label>
                            <input id="file-upload" type="file" accept="file/*"/>
                            <!---File Upload Ends--->

                            <!---Photo Upload Starts--->
                            <label for="image-upload" class="custom-file-upload">
                                <i class="fa-solid fa-image"></i> </label>
                            <input id="image-upload" type="file" accept="image/*"/>
                            <!---Photo Upload Ends--->

                            <!---Video Upload Starts--->
                            <label for="video-upload" class="custom-file-upload">
                                <i class="fa-solid fa-film"></i> </label>
                            <input id="video-upload" type="file" accept="video/*"/>
                            <!---Video Upload Ends--->
                        </div>
                    </div>

                </form>

            </div>
            <!----Mail Box Content ends--->
            <!----Mail Box Footer starts--->


            <!----Mail Box Footer ends--->

        </div>


    </div>
</nav>
<!----Nav Bar ends---->
<main class="container">
    <!----Left starts---->
    <div class="left">
        <button class="new-message">
            <i class="fa-solid fa-pen-to-square"></i>
            <p>Create</p>
        </button>
        <div class="menu">
            <a class="menu-item" href="/home"><span><i class="fa-solid fa-home"></i><p> Home</p></span></a>
            <a class="menu-item" href="/mail"><span><i class="fa-solid fa-paper-plane"></i><p>Sent</p></span></a>
            <!--<a class="menu-item"><span><i class="fa-solid fa-star"></i> <p>Starred</p></span></a>
            <a class="menu-item"><span><i class="fa-solid fa-trash"></i>Trash</span></a>
            <a class="menu-item"><span><i class="fa-solid fa-circle-exclamation"></i>Spam</span></a>-->
        </div>

    </div>
    <!----Left ends---->

    <!----Right starts---->
    <div class="right">
        <div class="mail-header">
            <div class="mail-options">
                <a id="arrow" ><i class="fa-solid fa-arrow-left"></i></a>
                <a><i class="fa-solid fa-circle-exclamation"></i></a>
                <a><i class="fa-solid fa-trash"></i></a>
            </div>
            <div class="pagination">
                <p> 1 out of 25</p>
                <a><i class="fa-solid fa-angle-left"></i></a>
                <a><i class="fa-solid fa-angle-right"></i></a>
            </div>
        </div>
        <c:forEach items="${emails}" var="EmailMessage">
            <div class="mail-page">
                <div class="mail-page-header">
                    <p>Sends</p>
                </div>
                <div class="mail-page-content">
                    <div class="mail-page-info">
                        <div class="right-side">
                            <p id="mail-time">10:18</p>
                            <div class="actions">
                                <input class="star" type="checkbox" title="bookmark page"><br/><br/>
                                <!---Answer the mail--->
                                <a><i class="fa-solid fa-share"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="inside">
                        <c:forEach items="${emails}" var="EmailMessage">
                            <br>${EmailMessage.mail_Message}<br>
                        </c:forEach>

                    </div>

                </div>
                <!--<div class="footer">
                    <button id="answer"><i class="fa-solid fa-share"></i> Answer</button>
                </div>-->


            </div>
        </c:forEach>



    </div>


    <!----Right ends---->
</main>


<script src="../../../resources/static/js/mail.js">
</script>
</body>
</html>