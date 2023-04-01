<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Log In</title>
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
    <button type="button" class="signin-button" onclick="window.location.href='signup';">
        Sign Up
        <i class="fa-solid fa-arrow-right-to-bracket"></i>
    </button>
</nav>

<!--Title-->
<header class="title">
    <h1>WELCOME BACK!</h1>
    <h2>LOGIN</h2>
</header>

<!--Main Visual-->
<section class="center">
    <div class="button-group">
        <button name="user_button" id="user_button" class="button">User</button>
        <button name="admin_button" id="admin_button" class="button">Admin</button>
    </div>

    <!--Form Box-->
    <div class="form-box form-login">
        <form class="form-signin" method="post" action="/login">
            <p class="text">
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="" autofocus="">
            </p>
            <p class="text">
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
            </p>

            <p class="remember-me">
                Remember Me?
                <label for="remember-me" class="sr-only">Remember Me?</label>
                <input type="checkbox" id="remember-me" name="remember-me" class="form-control">
            </p>

            <button class="submit" type="submit">Login</button>
        </form>
    </div>

</section>

<!--Footer-->
<footer class="page-footer">
    <div class="page-footer-content fixed-width clearfix">
        <small class="copyright">All Content Copyright 2022 - <strong>Maskeli Beşler</strong>. All Rights Reserved.
        </small>
        <small class="credits"> Site Designed by <strong>Maskeli Beşler</strong></small>
    </div>
</footer>
</body>

</html>
