<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/normalize.css">
</head>

<body>
<!--Navbar-->
<nav class="clearfix">
    <label class="logo"> <i id="icon" class="fa-solid fa-globe"></i>LinkedHU_CENG</label>
</nav>

<main>
    <!--Title-->
    <header class="title">
        <h1>SIGN UP</h1>
        <h3>Already have an account? <a href="login">Login</a></h3>
    </header>

    <!--Form Box-->
    <section class="form-box">
        <form method="post" action="signup">
            <div class="text">
                <input name="firstName" type="text" placeholder="Name" required>
            </div>
            <div class="text">
                <input name="lastName" type="text" placeholder="Surname" required>
            </div>
            <div class="text">
                <input name="email" type="email" placeholder="Email" required>
            </div>
            <div class="text">
                <input name="password" type="password" placeholder="Password" required>
            </div>
            <div class="terms-conditions">
                <input type="checkbox" name="terms" id="terms" required>

                <p>I Agree to the <a id="terms-link" href="#">Terms & Conditions</a></p>
            </div>

            <input class="submit" type="submit" value="Sign Up">
        </form>
    </section>
</main>
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
