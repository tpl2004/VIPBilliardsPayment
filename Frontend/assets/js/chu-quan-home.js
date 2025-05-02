import { localStorageAdminTokenKey } from "./config.js";
import * as api from './api-service.js';

const TOKEN = localStorage.getItem(localStorageAdminTokenKey);

var logOutBtn = document.querySelector('#log-out');
var functionTaskbar = document.querySelector('.function-taskbar');
var showBidaTableList = document.querySelector('#show-bida-table-list');
var showBidaCashierList = document.querySelector('#show-bida-cashier-list');
var showBillList = document.querySelector('#show-bill-list');
var showBillList = document.querySelector('#show-bill-list');
var showGoodsList = document.querySelector('#show-goods-list');
var showTableTypeList = document.querySelector('#show-table-type-list');
var showLevelList = document.querySelector('#show-level-list');
var showMemberList = document.querySelector('#show-member-list');

function main() {

    checkSignedIn()
    .then(response => {
        handleEvents();
        showBidaTableList.click();
    })
    .catch(ms => {
        window.location = 'chu-quan-login.html';
    })
}

main();

// check signed in
function checkSignedIn() {
    return new Promise((resolve, reject) => {
        if(!TOKEN) {
            reject("not signed in");
        } else {
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
                }
                else {
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

function activeMainFunction(id) {
    var functionTaskbarChildrens = functionTaskbar.children;
    var functionTaskbarChildrensLen = functionTaskbarChildrens.length;
    for(var i = 0; i < functionTaskbarChildrensLen; i++) {
        var children = functionTaskbarChildrens.item(i);
        if(children.id == id) {
            children.classList.add('active');
        } else {
            children.classList.remove('active');
        }
    }
}

// handle events
function handleEvents() {

    logOutBtn.onclick = function(e) {
        if(confirm("Đăng xuất?")) {
            localStorage.removeItem(localStorageAdminTokenKey);
            window.location.reload();
        }
    }
    
    showBidaTableList.addEventListener('click', e => {
        activeMainFunction('show-bida-table-list');
    })
    
    showBidaCashierList.addEventListener('click', e => {
        activeMainFunction('show-bida-cashier-list')
    })
    
    showBillList.addEventListener('click', e => {
        activeMainFunction('show-bill-list');
    })
    
    showGoodsList.addEventListener('click', e => {
        activeMainFunction('show-goods-list');
    })
    
    showTableTypeList.addEventListener('click', e => {
        activeMainFunction('show-table-type-list');
    })
    
    showLevelList.addEventListener('click', e => {
        activeMainFunction('show-level-list');
    })
    
    showMemberList.addEventListener('click', e => {
        activeMainFunction('show-member-list');
    })
}