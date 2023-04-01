var comment = document.querySelector(".make-comment");

function addComment() {
    if (comment.classList.contains("active")) {
        comment.classList.remove("active");
        comment.style.display = "none";
    } else {
        comment.classList.add("active");
        comment.style.display = "flex";
    }
}

var likeButton = document.querySelector("#like");
var commentButton = document.querySelector("#add-comment");
var favoriteButton = document.querySelector("#add-favorites");

var likeIcon = document.querySelector("#like-b");
var commentIcon = document.querySelector("#comment-b");
var favoriteIcon = document.querySelector("#fav-b");


function activatePostButtons() {
    var list = [likeButton, commentButton, favoriteButton];

    list.forEach(item => {

        item.addEventListener("click", () => {
            if (item.classList.contains("active-button")) {
                item.classList.remove("active-button");
                item.style.color="#6e6e6e";
                if (item == likeButton) {
                    likeIcon.style.color = "#5b1266";
                } else if (item == commentButton) {
                    commentIcon.style.color = "#5b1266";
                } else {
                    favoriteIcon.style.color = "#5b1266";
                }

            } else {
                item.classList.add("active-button");
                item.style.color="white";
                if (item == likeButton) {
                    likeIcon.style.color = "white";
                } else if (item == commentButton) {
                    commentIcon.style.color = "white";
                } else {
                    favoriteIcon.style.color = "white";
                }

            }

        })
    })
}
activatePostButtons();