<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>System Control</title>
    <link rel="stylesheet" href="../../resources/static/css/admin.css">
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
        <a> Hacettepe CS Sekreterlik <img class="profile-photo" src="../../resources/static/img/hacettepe-logo.jpg"></a>
    </div>

</nav>
<!----Nav Bar ends---->
<main class="container">
    <!----Left starts---->
    <div class="left">

        <div class="menu">
            <a class="menu-item active-menu-item" href="enrollments"><span><i
                    class="fa-solid fa-circle-check"></i><p> Enrollments </p></span></a>
            <a class="menu-item" href="manage-accounts"><span><i
                    class="fa-solid fa-user"></i><p>Manage Accounts</p></span></a>
            <a class="menu-item" id="reports-bans" onclick="reportsAndButton()"><span><i
                    class="fa-solid fa-circle-minus"></i><p>Reports and Bans</p></span></a>
            <div class="hidden-pages">
                <a class="menu-item sub-item" href="reported-accounts" ><span><i class="fa-solid fa-user-slash"></i><p>Accounts </p></span></a>
                <a class="menu-item sub-item" href="reported-groups" ><span><i class="fa-solid fa-users-between-lines"></i><p>Groups </p></span></a>
                <a class="menu-item sub-item" href="reported-posts" ><span><i class="fa-solid fa-file-circle-exclamation"></i><p>Posts </p></span></a>
            </div>
            <a class="menu-item" href="/mail"><span><i class="fa-solid fa-envelope"></i>Send Email</span></a>

        </div>
    </div>
    <!----Left ends---->


    <!----Right starts---->
    <div class="right">
        <h3 class="title">Enrollments</h3>
        <div class="enrollments">
            <div class="enrollments-header">
                <p>User Name</p>
                <p>Enrollment Date</p>
                <p>User Type</p>
            </div>
            <c:forEach items="${users}" var="user">
            <div class="profile-summary">

                    <div class="user-info">
                        <a><img class="profile-photo" src="../displayPp/${user.id}"></a>
                        <p>${user.firstName} ${user.lastName}</p>
                    </div>
                    <p class="enrollment-date"> 13/05/2022 </p>
                    <div class="user-status">
                        <div class="select-form">
                            <select id="status" name="status" onchange="location.href='enrollments/makeEnrollment/${user.id}/' + this.value">
                                <option value="default">Not Specified</option>
                                <option value="STUDENT">Student</option>
                                <option value="ACADEMICIAN">Academician</option>
                                <option value="GRADUATE">Graduate</option>
                            </select>
                        </div>
                    </div>

            </div>
            </c:forEach>
        </div>

    </div>
    <!----Right ends---->
</main>


<script src="../../resources/static/js/main.js">
</script>
</body>
</html>