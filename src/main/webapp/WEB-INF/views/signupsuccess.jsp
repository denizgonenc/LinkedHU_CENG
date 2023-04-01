<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>Success!</title>
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
  <button type="button" class="signin-button" onclick="window.location.href='login';">
    Login
    <i class="fa-solid fa-arrow-right-to-bracket"></i>
  </button>
</nav>

<!--Main Visual-->
<main>
  <div class="main-box verified">
    <h3 id="verified">VERIFIED</h3>
    <img src="/img/verified.png">
    <p id="verified-message">You have successfully verified your account. <br>
      Your username is: <strong>${user.username}</strong>
    </p>

  </div>
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
