import { localStorageUserTokenKey } from './config.js';
import * as api from './api-service.js'

var usernameInput = document.querySelector('input[name = "username"]');
var passwordInput = document.querySelector('input[name = "password"]');
var loginBtn = document.querySelector('button');
var username = null;
var password = null;

function main() {
    checkSignedIn()
    .then(result => {
        window.location = 'thu-ngan-home.html';
    })
    .catch(message => {
        createToastMessage({
            text: message,
            showConfirmButton: false,
            position: "top-end",
            icon: "info",
            timer: 1500,
            showCloseButton: true,
        })
        handleEvents();
    })
}

main();

// check signed in
function checkSignedIn() {
    const USER_TOKEN = localStorage.getItem(localStorageUserTokenKey);
    return new Promise((resolve, reject) => {
        if(!USER_TOKEN) {
            reject("not signed in");
        } else {
            var options = {
                method: 'POST',

                headers: {
                    'Content-Type': 'application/json',
                },

                body: JSON.stringify({
                    "token": USER_TOKEN
                }),
            }
            fetch(api.authApi + '/introspect', options)
            .then(response => response.json())
            .then(response => {
                if(response.code == 1000 && response.result.valid) resolve("signed in");
                else reject("not signed in");
            })
            .catch(err => {
                console.error(err);
                reject("cannot introspect token");
            })
        }
    })
}

// handle events
function handleEvents() {

    usernameInput.addEventListener('change', e => {
        username = usernameInput.value;
        console.log(username);
    })
    
    passwordInput.addEventListener('change', e => {
        password = passwordInput.value;
        console.log(password);
    })
    
    loginBtn.addEventListener('click', e => {
        var options = {
            method: 'POST',

            headers: {
                'Content-Type': 'application/json',
            },

            body: JSON.stringify({
                "tenDangNhap": username,
                "matKhau": password
            }),
        }
        fetch(api.authApi + "/thungantoken", options)
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                localStorage.setItem(localStorageUserTokenKey, response.result.token);
                window.location = 'thu-ngan-home.html';
            }
            else {
                // alert(response.message);
                createToastMessage({
                    text: response.message,
                    showConfirmButton: false,
                    position: "top-end",
                    icon: "warning",
                    timer: 2000,
                    showCloseButton: true,
                })        
            }
        })
    })
}