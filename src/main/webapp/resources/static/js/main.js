//SIDEBAR
const menuItems = document.querySelectorAll(".side-bar-item");

//===================SIDE BAR==============//

//remove active class from all menu items
const changeActiveItem = () => {
    menuItems.forEach(item => {
        item.classList.remove("active");
    })
}

menuItems.forEach(item => {
    item.addEventListener("click", () => {
        changeActiveItem();
        if (item.id != "groups-title") {
            item.classList.add("active");
        }
        if (item.id != "notifications" && item.className != "notification-popup") {
            document.querySelector(".notification-popup").style.display = "none";
        } else {
            document.querySelector(".notification-popup").style.display = "block";
            document.querySelector("#notifications #notification-count").style.display = "none";
        }
    })
})

var postPopUp = document.querySelector(".pop-up");
var userOptionsPopUp = document.querySelector(".user-options-pop-up");

function PopUpFunction(popUp) {
    if (popUp.classList.contains("pop-up-active")) {
        popUp.classList.remove("pop-up-active");
        popUp.style.display = "none";
    } else {
        popUp.classList.add("pop-up-active");
        popUp.style.display = "block";
    }
}

//Settings add experience
var addExperience = document.querySelector("#add-experience");

function addExpFunction() {
    if (addExperience.classList.contains("active")) {
        addExperience.classList.remove("active");
        addExperience.style.display = "none";
    } else {
        addExperience.classList.add("active");
        addExperience.style.display = "block";
    }
}

var hiddenPages= document.querySelector(".hidden-pages");
function reportsAndButton(){
    if (hiddenPages.classList.contains("active")) {
        hiddenPages.classList.remove("active");
        hiddenPages.style.display = "none";
    } else {
        hiddenPages.classList.add("active");
        hiddenPages.style.display = "block";
    }
}