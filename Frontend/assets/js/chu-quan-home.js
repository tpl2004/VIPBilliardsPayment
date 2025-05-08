import { localStorageAdminTokenKey } from "./config.js";
import * as api from './api-service.js';

const TOKEN = localStorage.getItem(localStorageAdminTokenKey);

var selectedTableNumber = null;
var selectedLoaiBanId = null;

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
var banBidaListBox = document.querySelector('.content .ban-bida-list .body-ban-bida-list');
var top_themBanBtn = document.querySelector('#them-ban');
var top_xoaBanBtn = document.querySelector('#xoa-ban');
var cont_themBanBidaBox = document.querySelector('.content .them-ban-bida');
console.log(cont_themBanBidaBox);

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
    var html = banBidaList.map((banBida) => {
        var trangThai = banBida.trangThai;
        return `
            <div soBan="${banBida.soBan}" class="ban-bida ${trangThai == 1? 'unavailable' : (trangThai == 2? 'deleted' : '')}">
                <p>${banBida.soBan}</p>
                <p>${trangThai == 0? 'Khả dụng' : (trangThai == 1? 'Đang bận' : 'Đã xóa')}</p>
                <p>${banBida.tenLoaiBan}</p>
                <p>${banBida.donGia}</p>
            </div>
        `
    });
    banBidaListBox.innerHTML = html.join('');
}

function deleteBanBida() {
    var options = {
        method: 'PUT',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.banBidaApi + '/xoabanbida/' + selectedTableNumber, options);
}

function getAllLoaiBanBida() {
    var options = {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.loaiBanApi, options);
}

function renderLoaiBanBidaList(loaiBanList, loaiBanListBox) {
    var html = loaiBanList.map(loaiBan => {
        return `
            <div loaiBan="${loaiBan.loaiBan}" class="loai-ban">
                <div class="ban-image">
                    <img src="./assets/images/ban-bida.png" alt="Ảnh bàn bida">
                </div>

                <div class="thong-tin-loai-ban">
                    <div>
                        <p>Tên loại:</p>
                        <p>${loaiBan.tenLoai}</p>
                    </div>
                    <div>
                        <p>Giá giờ chơi:</p>
                        <p>${loaiBan.donGia}</p>
                    </div>
                </div>
            </div>
        `
    })
    
    loaiBanListBox.innerHTML = html.join('');
}

function createBanBida() {
    var options = {
        method: 'POST',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
        body: JSON.stringify({
            "loaiBan": selectedLoaiBanId
        }),
    }
    return fetch(api.banBidaApi + '/thembanbida', options);
}

// handle events
function handleEvents() {

    logOutBtn.onclick = function(e) {
        if(confirm("Đăng xuất?")) {
            localStorage.removeItem(localStorageAdminTokenKey);
            window.location.reload();
        }
    }
    
    functionTaskbar.addEventListener('click', e => {
        selectedTableNumber = null;
        selectedLoaiBanId = null;
        console.log(selectedTableNumber);
    })
    
    func_showBidaTableListBtn.addEventListener('click', e => {
        activeMainFunction('show-bida-table-list');
        enableExtensionFuncGroup('xem-danh-sach-ban-bida');
        enableContent('ban-bida-list');
        getAllBanBida()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                console.log(response.message);
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
    
    banBidaListBox.addEventListener('click', e => {
        var activedTable = banBidaListBox.querySelector('.ban-bida.active');
        if(activedTable) {
            activedTable.classList.remove('active');
        }
        var selectedTable = e.target.closest('.ban-bida');
        selectedTable.classList.add('active');
        selectedTableNumber = selectedTable.getAttribute('soBan');
        console.log(selectedTableNumber);
    })
    
    top_themBanBtn.addEventListener('click', e => {
        enableContent('them-ban-bida');
        getAllLoaiBanBida()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                console.log(response.message);
                return;
            }
            // thanh cong
            var loaiBanList = response.result;
            var loaiBanListBox = document.querySelector('.content .them-ban-bida .danh-sach-loai-ban');
            renderLoaiBanBidaList(loaiBanList, loaiBanListBox);
        })
        .catch(err => {
            console.log(err);
        })
    })
    
    top_xoaBanBtn.addEventListener('click', e => {
        if(selectedTableNumber == null) {
            createAlert('Vui lòng chọn bàn', 'Bạn chưa chọn bàn', 'warning');
            return;
        }

        if(confirm('Xóa bàn ' + selectedTableNumber + ' ?')) {
            deleteBanBida()
            .then(response => response.json())
            .then(response => {
                if(response.code != 1000) {
                    // console.log(response.message);
                    createAlert('Xóa thất bại', response.message, 'error');
                    return;
                }
                // xoa thanh cong
                // alert('Xoa thanh cong');
                createAlert('Xóa thành công', `Đã xóa bàn ${response.result.soBan}`, 'success');
                func_showBidaTableListBtn.click();
            })
            .catch(err => {
                console.log(err);
            })
        }
    })
    
    cont_themBanBidaBox.querySelector('.danh-sach-loai-ban').addEventListener('click', e => {
        var activedLoaiBan = cont_themBanBidaBox.querySelector('.danh-sach-loai-ban .loai-ban.active');
        if(activedLoaiBan) {
            activedLoaiBan.classList.remove('active');
        }
        var selectedLoaiBan = e.target.closest('.loai-ban');
        selectedLoaiBan.classList.add('active');
        selectedLoaiBanId = selectedLoaiBan.getAttribute('loaiban');
        console.log(selectedLoaiBanId);
    })
    
    cont_themBanBidaBox.querySelector('.xac-nhan-them-ban button[name="huy"').onclick = e => {
        func_showBidaTableListBtn.click();
    }
    
    cont_themBanBidaBox.querySelector('.xac-nhan-them-ban button[name="xac-nhan"]').onclick = e => {
        if(selectedLoaiBanId == null) {
            createAlert('Vui lòng chọn loại bàn', 'Bạn chưa chọn loại bàn', 'warning');
            return;
        }

        createBanBida()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                // console.log(response.message);
                createAlert('Tạo bàn thất bại', response.message, 'error');
                return;
            }
            // tao ban bida thanh cong
            // alert('Tao thanh cong');
            createAlert('Tạo bàn thành công', `Đã tạo bàn ${response.result.soBan}`, 'success');
            func_showBidaTableListBtn.click();
        })
        .catch(err => {
            console.log(err);
        })
    }
}