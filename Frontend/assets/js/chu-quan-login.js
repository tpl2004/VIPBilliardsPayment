import { localStorageAdminTokenKey } from "./config.js";
import * as api from './api-service.js';
var usernameInput = document.querySelector('input[name = "username"]');
var passwordInput = document.querySelector('input[name = "password"]');
var loginBtn = document.querySelector('button');

var username = null;
var password = null;

function main() {

    checkSignedIn()
    .then(response => {
        window.location = 'chu-quan-home.html';
    })
    .catch(message => {
        // console.log(ms);
        createToastMessage({
            text: message,
            position: "top-end",
            showConfirmButton: false,
            icon: "info",
            timer: 2000
        })
        handleEvents();
    })
}

main();

// check signed in
function checkSignedIn() {
    var ADMIN_TOKEN = localStorage.getItem(localStorageAdminTokenKey);
    return new Promise((resolve, reject) => {
        if(!ADMIN_TOKEN) {
            reject("not signed in");
        } else {
            var options = {
                method: 'POST',

                headers: {
                    'Content-Type': 'application/json',
                },

                body: JSON.stringify({
                    "token": ADMIN_TOKEN
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
            .catch(err => {
                console.log(err);
                reject();
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
        fetch(api.authApi + '/chuquantoken', options)
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                localStorage.setItem(localStorageAdminTokenKey, response.result.token);
                window.location = 'chu-quan-home.html';
            } else {
                // alert(response.message);
                createToastMessage({
                    text: response.message,
                    position: "top-end",
                    showConfirmButton: false,
                    icon: "warning",
                    timer: 5000
                })
            }
        })
    })
}