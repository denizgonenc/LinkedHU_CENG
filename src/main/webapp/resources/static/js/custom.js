let $chatHistory;
let $button;
let $textarea;

function init() {
    cacheDOM();
    bindEvents();
}

function bindEvents() {
    $button.on('click', addMessage.bind(this));
    $textarea.on('keyup', addMessageEnter.bind(this));
}

function cacheDOM() {
    $chatHistory = $('.chat'); // .chat is from message.css and it is in the html file as a class
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
}


function render(message, userName) {
    scrollToBottom();
    fetchAllUsers();
    fetchAllMessages(selectedUser);
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function sendMessage(message) {
    let userName = document.getElementById("userName").value;
    sendMsg(userName, message);
    if (message.trim() !== '') {
        $textarea.val('');
        await sleep( 1500);
        fetchAllUsers();
        fetchAllMessages(selectedUser);
        scrollToBottom();
    }
}

function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0].scrollHeight);
}


function addMessage() {
    sendMessage($textarea.val());
}

function addMessageEnter(event) {
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage();
    }
}


