import { localStorageUserTokenKey } from './config.js'
import * as api from './api-service.js'

const TOKEN = localStorage.getItem(localStorageUserTokenKey);

var logoutBtn = document.querySelector('button[class = "logout"]');
var bodyBanBidaListBox = document.querySelector('.extension .content .ban-bida-list .body-ban-bida-list');
var showBidaTableListBtn = document.getElementById('show-bida-table-list');
var showBillListBtn = document.getElementById('show-bill-list');
var showMemberList = document.getElementById('show-member-list');
var functionTaskbars = [showBidaTableListBtn, showBillListBtn, showMemberList];
var xemDanhSachBanBidaExtendFuncsBox = document.querySelector('.xem-danh-sach-ban-bida');
var xemDanhSachHoiVienExtendFuncsBox = document.querySelector('.xem-danh-sach-hoi-vien');
var extendFunctionGroups = [xemDanhSachBanBidaExtendFuncsBox, xemDanhSachHoiVienExtendFuncsBox];
console.log(extendFunctionGroups)

function main() {

    checkSignedIn()
    .then(response => {
        activeMainFuntion('show-bida-table-list');
        enableExtendFuncGroup('xem-danh-sach-ban-bida');
        getAllBanBidaChuaXoa()
        .then(response => response.json())
        .then(response => {
            renderBanBidas(response.result);
        })
        .then(handleEvents);
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

// get all ban bida chua xoa
function getAllBanBidaChuaXoa() {
    var options = {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.banBidaApi + '/getallbanbidachuaxoa', options);
}

// render nhung ban bida len mang  hinh
function renderBanBidas(banBidas) {
    var html = banBidas.map((banBida, index) => {
        return `
            <div class="ban-bida">
                <p>${banBida.soBan}</p>
                <p>${banBida.trangThai}</p>
                <p>${banBida.tenLoaiBan}</p>
                <p>${banBida.donGia}</p>
            </div>
        `
    })
    bodyBanBidaListBox.innerHTML = html.join('');
}

// active a main function
function activeMainFuntion(funcId) {
    var func = document.getElementById(funcId);
    if(!func.classList.contains('active')) {
        func.classList.add('active');
    }
    functionTaskbars.forEach(func => {
        if(func.id != funcId && func.classList.contains('active')) {
            func.classList.remove('active');
        }
    })
}

// enable a extend function group
function enableExtendFuncGroup(GroupFuncClass = null) {
    if(GroupFuncClass) {
        var GroupFunc = document.querySelector('.'+GroupFuncClass);
        if(GroupFunc.classList.contains('disable')) {
            GroupFunc.classList.remove('disable');
        }
    }
    extendFunctionGroups.forEach(extendFuncGroup => {
        if(extendFuncGroup.className != GroupFuncClass && !extendFuncGroup.classList.contains('disable')) {
            extendFuncGroup.classList.add('disable');
        }
        // if(extendFuncGroup.className != GroupFuncClass) console.log(extendFuncGroup);
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
    
    showBidaTableListBtn.addEventListener('click', e => {
        activeMainFuntion(showBidaTableListBtn.id);
        enableExtendFuncGroup('xem-danh-sach-ban-bida')
    })
    
    showBillListBtn.addEventListener('click', e => {
        activeMainFuntion(showBillListBtn.id);
        enableExtendFuncGroup(null);
    })
    
    showMemberList.addEventListener('click', e => {
        activeMainFuntion(showMemberList.id);
        enableExtendFuncGroup('xem-danh-sach-hoi-vien');
    })
}