const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let newMessages = new Map();
let registered;
let messageTemplateHTML = "";

//
function connectToChat(userName) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);   // means we would use stomp client over websockets
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, async function (response) {
            let data = JSON.parse(response.body);
            console.log(data);

            // TODO burada dbde sakla
            if (selectedUser === data.sender) {
                render(data.message, data.sender);
            } else {
                console.log("baska bi userdan mesaj vra)");
                if(newMessages.has(data.sender)){
                    console.log("in if");
                    document.getElementById("newMessage_" + data.sender).outerHTML = "";
                    newMessages.set(data.sender, newMessages.get(data.sender) + 1);
                }
                else{
                    console.log("in else");
                    newMessages.set(data.sender, 1);
                }
                fetchAllUsers();
                await sleep(1000);
                $('#userNameAppender_' + data.sender).append('<small class="count" id="newMessage_' + data.sender + '">'+newMessages.get(data.sender)+'</small>');
            }
        });
    });
}

function sendMsg(from, text) {
    console.log("selectedUser from sendMsg: "+ selectedUser);

    console.log("stompClient is not undefined")
    console.log("stompClient: "+stompClient+", selectedUser: "+ selectedUser + ", sender: "+from + ", text: "+ text);
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        sender: from,
        message: text
    }));
}


function setRegistered(){
    registered = document.getElementById("userName").value;
    registration();
}

function registration() {
    let userName = registered;
    $.get(url + "/registration/" + userName, function (response) {
        connectToChat(userName);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
    fetchAllUsers();
}

function selectUser(userName) {
    console.log("selecting users: " + userName);
    if(selectedUser === userName){
        return;
    }
    selectedUser = userName;
    let isNew = document.getElementById("default-message-tab") !== null;
    if (isNew) {
        document.getElementById("default-message-tab").outerHTML = "";
        $('#chatRoomAppender').append(Handlebars.compile($("#chat-room").html()));
        init();
    }

    fetchAllUsers();
    fetchAllMessages(userName);


    $('#selectedUserId').html('');
    $('#selectedUserId').append(userName);
}

function fetchAllUsers() {
    console.log("in chat.js fetchAllUsers func"); // TODO delete

    $.get(url + "/fetchAllUsers/" + registered, function (response) {
        let users = response;
        let usersTemplateHTML = "";

        console.log(users);

        for (let i = 0; i < users.length; i++) {
            if(selectedUser === users[i][0]){
                usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i][0] + '\')"><li class="item chat-summary active-chat">\n' +
                    '                <i class="profile-photo" href="#"> <img src="../../resources/static/img/user-image.png" alt="avatar"> </i>\n' +
                    '                <span>\n' +
                    '                    <h3 id="userNameAppender_' + users[i][0] + '" class="name">' + users[i][1] + '</h3>\n' +
                    '                    <p class="last-message">'+ users[i][2] +'</p>\n'+
                    '                </span>\n' +
                    '            </li></a>';
            }
            else {
                usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i][0] + '\')"><li class="item chat-summary">\n' +
                    '                <i class="profile-photo" href="#"> <img src="../../resources/static/img/user-image.png" alt="avatar"> </i>\n' +
                    '                <span>\n' +
                    '                    <h3 id="userNameAppender_' + users[i][0] + '" class="name">' + users[i][1] + '</h3>\n' +
                    '                    <p class="last-message">'+ users[i][2] +'</p>\n' +
                    '                </span>\n' +
                    '            </li></a>';
            }

        }

        $('#usersList').html(usersTemplateHTML);
    });
}

function fetchAllMessages(userName) {
    console.log("in chat.js fetchAllMessages func"); // TODO delete

    $.get(url + "/fetchAllMessages/" + userName, function (response) {
        let messages = response;
        console.log(messages);

        var templateResponse = Handlebars.compile($("#message-response-template").html());
        var template = Handlebars.compile($("#message-template").html());
        messageTemplateHTML = "";

        for (let i = 0; i < messages.length; i++) {
            var context = {
                content: messages[i][1],
                time: messages[i][2],
                senderProfile: "/profile/"+selectedUser
            };

            if (messages[i][0] === "0"){
                messageTemplateHTML = messageTemplateHTML + (template(context));
            }
            else if(messages[i][0] === "1"){
                messageTemplateHTML = messageTemplateHTML + (templateResponse(context));
            }
            scrollToBottom();
        }


        $('#chatList').html(messageTemplateHTML);
        scrollToBottom();
    });


}

setRegistered();
fetchAllUsers(); // mine