<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Access</title>
    <link rel="stylesheet" href="../../resources/static/css/settings.css">
    <script src="https://kit.fontawesome.com/6e9897a906.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../resources/static/css/intlTelInput.css"/>
    <script src="../../resources/static/js/intlTelInput.min.js"></script>
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
            <a class="side-bar-item" href="/edit-profile">
                <h3>Edit your profile</h3>
            </a>
            <a class="side-bar-item active">
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
            <form method="post" class="settings-container" action="/account-access" onsubmit="process(event)">
                <div class="menu-header">
                    <h3> Account access </h3>
                    <p>Settings to help you keep your account secure.</p>
                </div>
                <div class="menu-header sub-header">
                    <label for="phone-number">Phone Number</label>
                    <br>
                    <input name="phoneNumber" type="tel" id="phone-number" placeholder="${user.phoneNumber}">
                </div>
                <div class="menu-header sub-header">
                    <h3> LinkedIn account </h3>
                    <p>Settings related to the Linkedin account your account is linked to..</p>
                </div>
                <div class="sub-section last-sub-child">
                    <label for="linkedin-link">Link of your Linkedin Account</label>
                    <br>
                    <input name="linkedinUrl" class="linkedin" type="url" id="linkedin-link" placeholder="${user.linkedinUrl}">
                </div>

                <div class="menu-header sub-header">
                    <h3> Change your password </h3>
                    <p>Choose a unique password to protect your account.</p>
                </div>

                <div class="sub-section">
                    <label for="current-password">Type your current password</label>
                    <br>
                    <input name="currentPassword" type="password" id="current-password">
                </div>
                <div class="sub-section">
                    <label for="new-password">Type your new password</label>
                    <br>
                    <input name="newPassword" type="password" id="new-password">
                </div>
                <div class="sub-section">
                    <label for="new-password2">Retype your new password</label>
                    <br>
                    <input name="retypedNewPassword" type="password" id="new-password2">
                </div>

                <!-- submit -->
                <div class="settings-footer">
                    <button id="save" type="submit" onclick="passwordCheck()"> Save</button>
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
<script src="../../resources/static/js/password-check.js"></script>
<script>
    const phoneInputField = document.querySelector("#phone-number");
    const phoneInput = window.intlTelInput(phoneInputField, {
        initialCountry: "tr",
        preferredCountries: ["tr"],
        separateDialCode: true,
        utilsScript:
            "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
    });

    // function process(event) {
    //     event.preventDefault();
    //     const phoneNumber = phoneInput.getNumber();
    // }
</script>

</body>
</html>