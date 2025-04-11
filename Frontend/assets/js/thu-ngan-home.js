import { localStorageUserTokenKey } from './config.js'
import * as api from './api-service.js'

const TOKEN = localStorage.getItem(localStorageUserTokenKey);

var logoutBtn = document.querySelector('button[class = "logout"]');

function main() {

    checkSignedIn()
    .then(response => {
        handleEvents();
    })
    .catch(ms => {
        window.location = 'index.html';
    })
}

main();

// check signed in
function checkSignedIn() {
    return new Promise((resolve, reject) => {
        if(!TOKEN) {
            reject("not signed in");
        }
        else {
            var options = {
                method: 'POST',

                headers: {
                    'Content-Type': 'application/json',
                },

                body: JSON.stringify({
                    "token": TOKEN
                }),
            }
            fetch(api.authApi + '/introspect', options)
            .then(response => response.json())
            .then(response => {
                if(response.code == 1000 && response.result.valid) {
                    resolve("signed in");
                } else {
                    reject("not signed in");
                }
            })
        }
    })
}

// handle events
function handleEvents() {

    logoutBtn.addEventListener('click', e => {
        var isLogOut = confirm("Bạn muốn đăng xuất?");
        console.log(isLogOut);
        if(isLogOut) {
            localStorage.removeItem(localStorageUserTokenKey);
            window.location.reload();
        }
    })
}