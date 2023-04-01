<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Verify Your Mail</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/normalize.css">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>

</head>

<body>
<!--Navbar-->
<nav class="clearfix">
    <label class="logo"> <i id="icon" class="fa-solid fa-globe"></i> LinkedHU_CENG</label>
    <button type="button" class="signin-button" onclick="window.location.href='sign-up.html';">
        Sign Up
        <i class="fa-solid fa-arrow-right-to-bracket"></i>
    </button>
</nav>

<!--Main Visual-->
<main>
    <div class="main-box">
        <h3>VERIFY YOUR EMAIL</h3>
        <img src="/img/mail.png">
        <p>To complete your sign up, please check your email & click the verification link to activate your account.</p>
        <button class="button" type="submit">Resend the Link</button>
    </div>
</main>

<!--Footer-->
<footer class="page-footer">
    <div class="page-footer-content fixed-width clearfix">
        <small class="copyright">All Content Copyright 2022 - <strong>Maskeli Besler</strong>. All Rights Resevered.
        </small>
        <small class="credits"> Site Designed by <strong>Maskeli Besler</strong></small>
    </div>
</footer>
</body>

</html>
