import { localStorageUserTokenKey } from './config.js'
import * as api from './api-service.js'

const TOKEN = localStorage.getItem(localStorageUserTokenKey);

var myInfo = {
    maThuNgan: 1
}
var selectedTableNumber = null;

var logoutBtn = document.querySelector('button[class = "logout"]');
var bodyBanBidaListBox = document.querySelector('.extension .content .ban-bida-list .body-ban-bida-list');
var showBidaTableListBtn = document.getElementById('show-bida-table-list');
var showBillListBtn = document.getElementById('show-bill-list');
var showMemberList = document.getElementById('show-member-list');
var functionTaskbars = [showBidaTableListBtn, showBillListBtn, showMemberList];
var xemDanhSachBanBidaExtendFuncsBox = document.querySelector('.xem-danh-sach-ban-bida');
var xemDanhSachHoiVienExtendFuncsBox = document.querySelector('.xem-danh-sach-hoi-vien');
var extendFunctionGroups = [xemDanhSachBanBidaExtendFuncsBox, xemDanhSachHoiVienExtendFuncsBox];
var banBidaListBox = document.querySelector('.content .ban-bida-list');
var contentBoxchilds = document.querySelector('.content').children;
var functionTaskbarBox = document.querySelector('.function-taskbar');
var moBanBtn = document.getElementById('mo-ban');

function main() {

    checkSignedIn()
    .then(response => {
        activeMainFuntion('show-bida-table-list');
        enableExtendFuncGroup('xem-danh-sach-ban-bida');
        enableContent('ban-bida-list');
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
                <p name="so-ban">${banBida.soBan}</p>
                <p name="trang-thai">${(!banBida.trangThai)? 'Khả dụng' : (banBida.trangThai == 1? 'Đang bận' : 'Đã xóa')}</p>
                <p name="ten-loai-ban">${banBida.tenLoaiBan}</p>
                <p name="don-gia">${banBida.donGia}</p>
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
        var GroupFunc = document.querySelector('.' + GroupFuncClass);
        if(GroupFunc.classList.contains('disable')) {
            GroupFunc.classList.remove('disable');
        }
    }
    extendFunctionGroups.forEach(extendFuncGroup => {
        if(!extendFuncGroup.classList.contains(GroupFuncClass) && !extendFuncGroup.classList.contains('disable')) {
            extendFuncGroup.classList.add('disable');
        }
        // if(extendFuncGroup.className != GroupFuncClass) console.log(extendFuncGroup);
    })
}

// enable a content
function enableContent(contentClass) {
    var content = document.querySelector('.' + contentClass);
    if(content.classList.contains('disable')) {
        content.classList.remove('disable');
    }
    for(var i = 0; i < contentBoxchilds.length; i++) {
        if(!contentBoxchilds[i].classList.contains(contentClass)
            && !contentBoxchilds[i].classList.contains('disable')) {
            contentBoxchilds[i].classList.add('disable');
        }
    }
}

// tao hoa don
function createBill() {
    var options = {
        method: 'POST',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "soBan": selectedTableNumber,
            "maThuNgan": myInfo.maThuNgan
        }),
    }
    return fetch(api.hoaDonApi, options)
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
    
    functionTaskbarBox.addEventListener('click', e=> {
        selectedTableNumber = null;
        console.log(selectedTableNumber);
    })
    
    showBidaTableListBtn.addEventListener('click', e => {
        activeMainFuntion(showBidaTableListBtn.id);
        enableExtendFuncGroup('xem-danh-sach-ban-bida');
        enableContent('ban-bida-list');
        getAllBanBidaChuaXoa()
        .then(response => response.json())
        .then(response => {
            renderBanBidas(response.result);
        })

    })
    
    showBillListBtn.addEventListener('click', e => {
        activeMainFuntion(showBillListBtn.id);
        enableExtendFuncGroup(null);
        enableContent('hoa-don-list');
    })
    
    showMemberList.addEventListener('click', e => {
        activeMainFuntion(showMemberList.id);
        enableExtendFuncGroup('xem-danh-sach-hoi-vien');
        enableContent('hoi-vien-list');
    })
    
    banBidaListBox.addEventListener('click', e => {
        var banBida = e.target.closest('.ban-bida');
        
        // xu ly khi clich vao mot ban bida
        if(banBida) {
            var banDangActive = banBidaListBox.querySelector('.body-ban-bida-list .active');
            if(banDangActive) banDangActive.classList.remove('active');
            
            banBida.classList.add('active');
            selectedTableNumber = Number.parseInt(banBida.children[0].innerText);
        }
    })
    
    moBanBtn.addEventListener('click', e=> {
        if(!confirm("Mở bàn?")) return;
        createBill()
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                console.log(response.result);
                alert('Đã mở bàn');
                showBidaTableListBtn.click();
            } else {
                return Promise.reject('Mở bàn thất bại');
            }
        })
        .catch(err => {
            console.log(err);
            alert('Mở bàn thất bại');
        })
    })
}