import { localStorageAdminTokenKey } from "./config.js";
import * as api from './api-service.js';

const TOKEN = localStorage.getItem(localStorageAdminTokenKey);

var logOutBtn = document.querySelector('#log-out');
var functionTaskbar = document.querySelector('.function-taskbar');
var func_showBidaTableListBtn = document.querySelector('#show-bida-table-list');
var func_showBidaCashierListBtn = document.querySelector('#show-bida-cashier-list');
var func_showBillListBtn = document.querySelector('#show-bill-list');
var func_showGoodsListBtn = document.querySelector('#show-goods-list');
var func_showTableTypeListBtn = document.querySelector('#show-table-type-list');
var func_showLevelListBtn = document.querySelector('#show-level-list');
var func_showMemberListBtn = document.querySelector('#show-member-list');
var topTaskbar = document.querySelector('.extension .top-taskbar');
var content = document.querySelector('.extension .content');
console.log(content)

function main() {

    checkSignedIn()
    .then(response => {
        handleEvents();
        func_showBidaTableListBtn.click();
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

// enable a top taskbar func group
function enableExtensionFuncGroup(groupClass) {
    var topTaskbarChilds = topTaskbar.children;
    var topTaskbarChildsLen = topTaskbarChilds.length;
    for(var i = 0; i < topTaskbarChildsLen; i++) {
        var child = topTaskbarChilds.item(i);
        if(child.classList.contains(groupClass))
            child.classList.remove('disable');
        else
            child.classList.add('disable');
    }
}

function enableContent(contentClass) {
    var contentChilds = content.children;
    var contentChildsLen = contentChilds.length;
    for(var i = 0; i < contentChildsLen; i++) {
        var child = contentChilds.item(i);
        if(child.classList.contains(contentClass))
            child.classList.remove('disable');
        else
            child.classList.add('disable');
    }
}

// lay tat ca ban bida(bao gom ca ban da xoa)
function getAllBanBida() {
    var options = {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.banBidaApi + '/getallbanbida', options);
}

function renderBanBidaList(banBidaList) {
    var banBidaListBox = document.querySelector('.content .ban-bida-list .body-ban-bida-list');
    var html = banBidaList.map((banBida) => {
        return `
            <div class="ban-bida">
                <p>${banBida.soBan}</p>
                <p>${banBida.trangThai}</p>
                <p>${banBida.tenLoaiBan}</p>
                <p>${banBida.donGia}</p>
            </div>
        `
    });
    banBidaListBox.innerHTML = html.join('');
}

// handle events
function handleEvents() {

    logOutBtn.onclick = function(e) {
        if(confirm("Đăng xuất?")) {
            localStorage.removeItem(localStorageAdminTokenKey);
            window.location.reload();
        }
    }
    
    func_showBidaTableListBtn.addEventListener('click', e => {
        activeMainFunction('show-bida-table-list');
        enableExtensionFuncGroup('xem-danh-sach-ban-bida');
        enableContent('ban-bida-list');
        getAllBanBida()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                
                return;
            }
            var banBidaList = response.result;
            renderBanBidaList(banBidaList);
        })
    })
    
    func_showBidaCashierListBtn.addEventListener('click', e => {
        activeMainFunction('show-bida-cashier-list')
        enableExtensionFuncGroup('xem-danh-sach-thu-ngan');
    })
    
    func_showBillListBtn.addEventListener('click', e => {
        activeMainFunction('show-bill-list');
        enableExtensionFuncGroup('xem-danh-sach-hoa-don');
    })
    
    func_showGoodsListBtn.addEventListener('click', e => {
        activeMainFunction('show-goods-list');
        enableExtensionFuncGroup('xem-danh-sach-mat-hang');
    })
    
    func_showTableTypeListBtn.addEventListener('click', e => {
        activeMainFunction('show-table-type-list');
        enableExtensionFuncGroup('xem-danh-sach-loai-ban');
    })
    
    func_showLevelListBtn.addEventListener('click', e => {
        activeMainFunction('show-level-list');
        enableExtensionFuncGroup('xem-danh-sach-cap-do-hoi-vien');
    })
    
    func_showMemberListBtn.addEventListener('click', e => {
        activeMainFunction('show-member-list');
        enableExtensionFuncGroup('xem-danh-sach-hoi-vien');
    })
}