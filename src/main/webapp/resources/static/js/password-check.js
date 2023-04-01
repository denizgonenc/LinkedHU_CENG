function passwordCheck(){
    var newPassword = document.getElementById("new-password");
    document.getElementById("current-password").required = false;
    if (newPassword.innerHTML !== "") {
        document.getElementById("current-password").required = true;
    } else {
        document.getElementById("current-password").required = false;
    }

    var retypedNewPassword = document.getElementById("new-password2");
    if (retypedNewPassword.innerHTML !== "") {
        document.getElementById("current-password").required = true;
        document.getElementById("new-password").required = true;
    } else {
        document.getElementById("current-password").required = false;
        document.getElementById("new-password").required = false;
    }

    if (newPassword.innerHTML !== retypedNewPassword.innerHTML) {
        document.getElementById("new-password2").setCustomValidity("Passwords must be matching");
    }
    else {
        document.getElementById("new-password2").setCustomValidity("");
    }
}