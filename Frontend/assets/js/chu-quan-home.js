import { localStorageAdminTokenKey } from "./config.js";
import * as api from './api-service.js';

const TOKEN = localStorage.getItem(localStorageAdminTokenKey);

var selectedTableNumber = null;
var selectedLoaiBanId = null;
var selectedThuNganId = null;
var selectedMatHangId = null;

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
var top_themThuNganBtn = document.querySelector('#them-thu-ngan');
var top_timKiemThuNganBtn = document.querySelector('#tim-kiem-thu-ngan');
var top_capNhatThongTinThuNganBtn = document.querySelector('#cap-nhat-thong-tin-thu-ngan');
var top_timKiemHoaDonBtn = document.querySelector('#tim-kiem-hoa-don');
var top_xemThongKeDoanhThuBtn = document.querySelector('#xem-thong-ke-doanh-thu');
var top_themMatHangBtn = document.querySelector('#them-mat-hang');
var top_capNhatMatHangBtn = document.querySelector('#cap-nhat-mat-han');
var top_themLoaiBanBtn = document.querySelector('#them-loai-ban');
var top_capNhatLoaiBanBtn = document.querySelector('#cap-nhat-loai-ban');
var top_themCapDoHoiVienBtn = document.querySelector('#them-cap-do');
var top_capNhatCapDoHoiVienBtn = document.querySelector('#cap-nhat-cap-do');
var cont_themBanBidaBox = document.querySelector('.content .them-ban-bida');
var cont_thuNganListBox = document.querySelector('.content .danh-sach-thu-ngan .body-thu-ngan-list');
var cont_themThuNganBox = document.querySelector('.content .them-thu-ngan');
var cont_timKiemThuNganBox = document.querySelector('.content .tim-kiem-thu-ngan');
var cont_capNhatThuNganBox = document.querySelector('.content .cap-nhat-thu-ngan');
var cont_xemThongKeDoanhThuBox = document.querySelector('.content .xem-thong-ke-doanh-thu');
var cont_timKiemHoaDonBox = document.querySelector('.content .tim-kiem-hoa-don');
var cont_themMatHangBox = document.querySelector('.content .them-mat-hang');
var cont_matHangListBox = document.querySelector('.content .danh-sach-mat-hang .body-thu-ngan-list');
var cont_capNhatMangHangBox = document.querySelector('.content .cap-nhat-mat-hang');
var cont_loaiBanListBox = document.querySelector('.content .danh-sach-loai-ban .body-thu-ngan-list');
var thongKeDoanhThuNgayChart = null;

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

function renderLoaiBanBidaListV1(loaiBanList, loaiBanListBox) {
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

function getAllThuNgan() {
    var options = {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.thuNganApi + '/getallthungan', options);
}

function renderThuNganList(thuNganList, thuNganListBox) {
    var html = thuNganList.map(thuNgan => {
        return `
            <div maThuNgan="${thuNgan.maThuNgan}" class="thu-ngan">
                <p>${thuNgan.maThuNgan}</p>
                <p>${thuNgan.hoTen}</p>
                <p>${thuNgan.email}</p>
                <p>${thuNgan.soDienThoai}</p>
                <p>${thuNgan.soCCCD}</p>
            </div>
        `
    })
    
    thuNganListBox.innerHTML = html.join('');
}

function createThuNgan(hoTen, ngaySinh, gioiTinhNu, email, soDienThoai, soCCCD, tenDangNhap, matKhau) {
    var options = {
        method: 'POST',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "hoTen": hoTen,
            "ngaySinh": ngaySinh,
            "gioiTinhNu": gioiTinhNu,
            "email": email,
            "soDienThoai": soDienThoai,
            "soCCCD": soCCCD,
            "tenDangNhap": tenDangNhap,
            "matKhau": matKhau
        }),
    }
    
    return fetch(api.thuNganApi + '/themthungan', options);
}

// tim kiem tuong doi theo ho ten
function findThuNganTheoHoTen(hoTen) {
    var options = {
        method: 'POST',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "hoTen": hoTen
        }),
    }

    return fetch(api.thuNganApi + '/timthungan', options);
}

function updateThuNgan(hoTen, ngaySinh, gioiTinhNu, email, soDienThoai, soCCCD, matKhau) {
    var options = {
        method: 'PUT',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "hoTen": hoTen,
            "ngaySinh": ngaySinh,
            "gioiTinhNu": gioiTinhNu,
            "email": email,
            "soDienThoai": soDienThoai,
            "soCCCD": soCCCD,
            "matKhau": matKhau
        }),
    }

    return fetch(api.thuNganApi + '/updatethungan/' + selectedThuNganId, options);
}

function getAllHoaDon() {
    var options = {
        method: 'GET',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }

    return fetch(api.hoaDonApi, options);
}

function renderHoaDonList(hoaDonList, hoaDonListBox) {
    var html = hoaDonList.map(hoaDon => {
        return `
            <div class="bill">
                <p>${hoaDon.maHoaDon}</p>
                <p>${formatDate(hoaDon.thoiDiemVao)}</p>
                <p>${hoaDon.soGioChoi != null? hoaDon.soGioChoi : formatNullValue()}</p>
                <p>${hoaDon.soBan}</p>
                <p>${hoaDon.donGia}</p>
                <p>${hoaDon.tongTien != null? hoaDon.tongTien : formatNullValue()}</p>
            </div>
        `
    })
    
    hoaDonListBox.innerHTML = html.join('');
}

function thongKeDoanhThuTheoNgay(ngayBatDau, ngayKetThuc) {
    var options = {
        method: 'POST',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "ngayBatDau": ngayBatDau,
            "ngayKetThuc": ngayKetThuc
        }),
    }

    return fetch(api.hoaDonApi + '/thongke', options);
}

function findHoaDonsTheoNgay(ngay) {
    var options = {
        method: 'POST',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify(ngay),
    }
    return fetch(api.hoaDonApi + '/findbydate', options);
}

function getAllMatHang() {
    var options = {
        method: 'GET',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.matHangApi, options);
}

function renderMatHangList(matHangList, matHangListBox) {
    var html = matHangList.map(matHang => {
        return `
            <div maHang="${matHang.maHang}" class="thu-ngan">
                <p>${matHang.maHang}</p>
                <p>${matHang.tenHang}</p>
                <p>${matHang.donGia}</p>
            </div>
        `
    })
    matHangListBox.innerHTML = html.join('');
}

function createMatHang(tenHang, donGia) {
    var options = {
        method: 'POST',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "tenHang": tenHang,
            "donGia": donGia
        }),
    }
    return fetch(api.matHangApi, options);
}

function updateMatHang(maHang, tenHang, donGia) {
    var options = {
        method: 'PUT',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },

        body: JSON.stringify({
            "tenHang": tenHang,
            "donGia": donGia
        }),
    }

    return fetch(api.matHangApi + '/' + maHang, options);
}

function getAllLoaiBan() {
    var options = {
        method: 'GET',
        
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.loaiBanApi, options);
}

function renderLoaiBanBidaListV2(loaiBanList, loaiBanListBox) {

    var html = loaiBanList.map(loaiBan => {
        return `
            <div maLoaiBan="${loaiBan.loaiBan}" class="thu-ngan">
                <p>${loaiBan.loaiBan}</p>
                <p>${loaiBan.tenLoai}</p>
                <p>${loaiBan.donGia}</p>
            </div>
        `
    })
    
    loaiBanListBox.innerHTML = html.join('');
}

// handle events
function handleEvents() {
    
    var updateThuNganForm = {
        hoTenInput: cont_capNhatThuNganBox.querySelector('.thong-tin-them input[name="ho-ten-thu-ngan"]'),
        ngaySinhInput: cont_capNhatThuNganBox.querySelector('.thong-tin-them input[name="ngay-sinh-thu-ngan"]'),
        gioiTinhSelect: cont_capNhatThuNganBox.querySelector('.thong-tin-them select[name="gioi-tinh-thu-ngan"]'),
        emailInput: cont_capNhatThuNganBox.querySelector('.thong-tin-them input[name="email-thu-ngan"]'),
        soDienThoaiInput: cont_capNhatThuNganBox.querySelector('.thong-tin-them input[name="sdt-thu-ngan"]'),
        soCCCDInput: cont_capNhatThuNganBox.querySelector('.thong-tin-them input[name="so-cccd"]'),
        matKhauInput: cont_capNhatThuNganBox.querySelector('.thong-tin-them input[name="mat-khau-thu-ngan"]'),
    }
    
    var updateMatHangForm = {
        tenHangInput: cont_capNhatMangHangBox.querySelector('.thong-tin-them input[name="ten-mat-hang"]'),
        donGiaInput: cont_capNhatMangHangBox.querySelector('.thong-tin-them input[name="don-gia-hang"]')
    }

    logOutBtn.onclick = function(e) {
        if(confirm("Đăng xuất?")) {
            localStorage.removeItem(localStorageAdminTokenKey);
            window.location.reload();
        }
    }
    
    functionTaskbar.addEventListener('click', e => {
        selectedTableNumber = null;
        selectedLoaiBanId = null;
        selectedThuNganId = null;
        selectedMatHangId = null;
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
        enableContent('danh-sach-thu-ngan');
        getAllThuNgan()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                console.log(response.message);
                return;
            }
            // thanh cong
            var thuNganList = response.result;
            renderThuNganList(thuNganList, cont_thuNganListBox);
        })
        .catch(err => {
            console.log(err);
        })
    })
    
    cont_thuNganListBox.addEventListener('click', e => {
        var activedThuNgan = cont_thuNganListBox.querySelector('.thu-ngan.active');
        if(activedThuNgan)
            activedThuNgan.classList.remove('active');
        var selectedThuNgan = e.target.closest('.thu-ngan');
        selectedThuNgan.classList.add('active');
        selectedThuNganId = selectedThuNgan.getAttribute('mathungan');
    })
    
    func_showBillListBtn.addEventListener('click', e => {
        activeMainFunction('show-bill-list');
        enableExtensionFuncGroup('xem-danh-sach-hoa-don');
        enableContent('hoa-don-list');
        getAllHoaDon()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var DSHoaDon = response.result;
            var hoaDonListBox = document.querySelector('.content .hoa-don-list .body-bill-list');
            renderHoaDonList(DSHoaDon, hoaDonListBox);
        })
        .catch(err => {
            console.log(err);
        })
    })
    
    func_showGoodsListBtn.addEventListener('click', e => {
        activeMainFunction('show-goods-list');
        enableExtensionFuncGroup('xem-danh-sach-mat-hang');
        enableContent('danh-sach-mat-hang');
        getAllMatHang()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var dsMatHang = response.result;
            renderMatHangList(dsMatHang, cont_matHangListBox);
        })
        .catch(err => {
            console.log(err);
        })
    })
    
    func_showTableTypeListBtn.addEventListener('click', e => {
        activeMainFunction('show-table-type-list');
        enableExtensionFuncGroup('xem-danh-sach-loai-ban');
        enableContent('danh-sach-loai-ban');
        getAllLoaiBan()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var dsLoaiBan = response.result;
            renderLoaiBanBidaListV2(dsLoaiBan, cont_loaiBanListBox);
        })
        .catch(err => {
            console.log(err);
        })
    })
    
    func_showLevelListBtn.addEventListener('click', e => {
        activeMainFunction('show-level-list');
        enableExtensionFuncGroup('xem-danh-sach-cap-do-hoi-vien');
        enableContent('danh-sach-cap-do-hoi-vien');
    })
    
    func_showMemberListBtn.addEventListener('click', e => {
        activeMainFunction('show-member-list');
        enableExtensionFuncGroup('');
        enableContent('danh-sach-hoi-vien');
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
            renderLoaiBanBidaListV1(loaiBanList, loaiBanListBox);
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
    
    top_themThuNganBtn.addEventListener('click', e => {
        enableContent('them-thu-ngan');
    })
    
    top_timKiemThuNganBtn.addEventListener('click', e => {
        enableContent('tim-kiem-thu-ngan');
    })
    
    top_capNhatThongTinThuNganBtn.addEventListener('click', e => {
        if(selectedThuNganId == null) {
            createAlert('Vui lòng chọn thu ngân', 'Bạn chưa chọn thu ngân', 'warning');
            return;
        }
        getAllThuNgan()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var DSThuNgan = response.result;
            var selectedthuNgan = DSThuNgan.find((thuNgan) => {
                return thuNgan.maThuNgan == selectedThuNganId;
            })
            updateThuNganForm.hoTenInput.value = selectedthuNgan.hoTen;
            var ngaySinh = new Date(selectedthuNgan.ngaySinh);
            var format = `${ngaySinh.getFullYear()}-${String(ngaySinh.getMonth()+1).padStart(2, '0')}-${String(ngaySinh.getDay()).padStart(2, '0')}`;
            updateThuNganForm.ngaySinhInput.value = format;
            updateThuNganForm.gioiTinhSelect.selectedIndex = selectedthuNgan.gioiTinhNu? 0 : 1;
            updateThuNganForm.emailInput.value = selectedthuNgan.email;
            updateThuNganForm.soDienThoaiInput.value = selectedthuNgan.soDienThoai;
            updateThuNganForm.soCCCDInput.value = selectedthuNgan.soCCCD;
            updateThuNganForm.matKhauInput.value = '';
        })
        .catch(err => {
            console.log(err);
        })
        enableContent('cap-nhat-thu-ngan');
    })
    
    top_timKiemHoaDonBtn.addEventListener('click', e => {
        enableContent('tim-kiem-hoa-don');
    })
    
    top_xemThongKeDoanhThuBtn.addEventListener('click', e => {
        enableContent('xem-thong-ke-doanh-thu');
    })
    
    top_themMatHangBtn.addEventListener('click', e => {
        enableContent('them-mat-hang');
    })
    
    top_capNhatMatHangBtn.addEventListener('click', e => {
        if (selectedMatHangId == null) {
            createAlert('Vui lòng chọn mặt hàng', 'Bạn chưa chọn mặt hàng', 'warning');
            return;
        }
        getAllMatHang()
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var dsMatHang = response.result;
            var selectedMatHang = dsMatHang.find(matHang => {
                return matHang.maHang == selectedMatHangId;
            })
            updateMatHangForm.tenHangInput.value = selectedMatHang.tenHang;
            updateMatHangForm.donGiaInput.value = selectedMatHang.donGia;
        })
        .catch(err => {
            console.log(err);
        })

        enableContent('cap-nhat-mat-hang');
    })
    
    top_themLoaiBanBtn.addEventListener('click', e => {
        enableContent('them-loai-ban');
    })
    
    top_capNhatLoaiBanBtn.addEventListener('click', e => {
        enableContent('cap-nhat-loai-ban');
    })
    
    top_themCapDoHoiVienBtn.addEventListener('click', e => {
        enableContent('them-cap-do');
    })
    
    top_capNhatCapDoHoiVienBtn.addEventListener('click', e => {
        enableContent('cap-nhat-cap-do');
    })
    
    cont_themThuNganBox.querySelector('.xac-nhan-them-thu-ngan button[name="huy"]').onclick = e => {
        func_showBidaCashierListBtn.click();
    }
    
    cont_themThuNganBox.querySelector('.xac-nhan-them-thu-ngan button[name="xac-nhan"]').onclick = e => {
        var thongTinThemBox = cont_themThuNganBox.querySelector('.thong-tin-them');
        var hoTen = thongTinThemBox.querySelector('input[name="ho-ten-thu-ngan"]').value;
        var ngaySinh = thongTinThemBox.querySelector('input[name="ngay-sinh-thu-ngan"]').value;
        var goiTinh = thongTinThemBox.querySelector('select[name="gioi-tinh-thu-ngan"]');
        goiTinh = goiTinh.options[goiTinh.selectedIndex]; // lay ra the option dang duoc chon
        goiTinh = goiTinh.value;
        var gioiTinhNu = goiTinh? true : false;
        var email = thongTinThemBox.querySelector('input[name="email-thu-ngan"]').value;
        var soDienThoai = thongTinThemBox.querySelector('input[name="sdt-thu-ngan"]').value;
        var soCCCD = thongTinThemBox.querySelector('input[name="so-cccd"]').value;
        var tenDangNhap = thongTinThemBox.querySelector('input[name="ten-dang-nhap-thu-ngan"]').value;
        var matKhau = thongTinThemBox.querySelector('input[name="mat-khau-thu-ngan"]').value;
        createThuNgan(hoTen, ngaySinh, gioiTinhNu, email, soDienThoai, soCCCD, tenDangNhap, matKhau)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                // alert('Tạo Thất bại');
                createToastMessage({
                    text: response.message,
                    icon: 'error',
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 5000
                })
                return;
            }
            // tao thanh cong
            // alert('Tạo thành công');
            var newThuNgan = response.result;
            createToastMessage({
                text: 'Đã tạo thu ngân ' + newThuNgan.hoTen,
                icon: 'success',
                position: 'top-end',
                showConfirmButton: false,
                timer: 5000
            })
            func_showBidaCashierListBtn.click();
        })
        .catch(err => {
            console.log(err);
        })
    }
    
    cont_timKiemThuNganBox.querySelector('.search-box button[name="search-cancle"]').onclick = e => {
        func_showBidaCashierListBtn.click();
    }
    cont_timKiemThuNganBox.querySelector('.search-box button[name="search-button"]').onclick = e => {
        var hoTenMuonTim = cont_timKiemThuNganBox.querySelector('.search-box input[name="search-thu-ngan"]').value;
        findThuNganTheoHoTen(hoTenMuonTim)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var DSThuNgan = response.result;
            var thuNganListBox = cont_timKiemThuNganBox.querySelector('.body-thu-ngan-list');
            renderThuNganList(DSThuNgan, thuNganListBox);
        })
        .catch(err => {
            console.log(err);
        })
    }
    
    cont_capNhatThuNganBox.querySelector('.xac-nhan-them-thu-ngan button[name="huy-cap-nhat"]').onclick = e => {
        func_showBidaCashierListBtn.click();
    }

    cont_capNhatThuNganBox.querySelector('.xac-nhan-them-thu-ngan button[name="xac-nhan-cap-nhat"]').onclick = e => {
        var hoTen = updateThuNganForm.hoTenInput.value;
        var ngaysinh = updateThuNganForm.ngaySinhInput.value;
        var goiTinh = updateThuNganForm.gioiTinhSelect.options[updateThuNganForm.gioiTinhSelect.selectedIndex]; // lay the option da chon
        var gioiTinhNu = (goiTinh.value == "1")? true : false;
        var email = updateThuNganForm.emailInput.value;
        var soDienThoai = updateThuNganForm.soDienThoaiInput.value;
        var soCCCD = updateThuNganForm.soCCCDInput.value;
        var matKhau = updateThuNganForm.matKhauInput.value;
        updateThuNgan(hoTen, ngaysinh, gioiTinhNu, email, soDienThoai, soCCCD, matKhau)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                // alert(response.message);
                createToastMessage({
                    text: response.message,
                    icon: 'error',
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 5000
                })

                return;
            }
            // cap nhat thanh cong
            // alert('Cập nhật thành công');
            createAlert('Cập nhật thành công', `Đã cập nhật thu ngân ${selectedThuNganId}`, 'success');
            func_showBidaCashierListBtn.click();
        })
        .catch(err => {
            console.log(err);
        })
    }
    
    cont_xemThongKeDoanhThuBox.querySelector('.search-box button[name="search-cancle"]').onclick = e => {
        func_showBillListBtn.click();
    }

    cont_xemThongKeDoanhThuBox.querySelector('.search-box button[name="search-button"]').onclick = e => {
        var ngayBatDau = cont_xemThongKeDoanhThuBox.querySelector('.search-box input[name="ngay-bat-dau"]').value;
        var ngayKetThuc = cont_xemThongKeDoanhThuBox.querySelector('.search-box input[name="ngay-ket-thuc"]').value;
        thongKeDoanhThuTheoNgay(ngayBatDau, ngayKetThuc)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var ketQuaThongKe = response.result;
            var dsNgay = ketQuaThongKe.map(ketQua => {
                return ketQua.ngay;
            })
            var dsDoanhThu = ketQuaThongKe.map(ketQua => {
                return ketQua.doanhThu;
            })
            var thongKeDoanhThuNgayChartCanvas = document.getElementById('thong-ke-doanh-thu-ngay-chart');
            if(thongKeDoanhThuNgayChart) {
                thongKeDoanhThuNgayChart.destroy();
            }
            thongKeDoanhThuNgayChart = new Chart(thongKeDoanhThuNgayChartCanvas, {
                type: 'bar',
                data: {
                    labels: dsNgay,
                    datasets: [{
                        label: '# Doanh thu',
                        data: dsDoanhThu,
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Đồng' // Tên trục Y
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Ngày' // Tên trục X
                            }
                        }
                    }
                }
            });
        })
        .catch(err => {
            console.log(err);
        })
    }
    
    cont_timKiemHoaDonBox.querySelector('.search-box button[name="search-cancle"]').onclick = e => {
        func_showBillListBtn.click();
    }

    cont_timKiemHoaDonBox.querySelector('.search-box button[name="search-button"]').onclick = e => {
        var ngayCanTim = cont_timKiemHoaDonBox.querySelector('.search-box input[name="search-hoa-don"]').value;
        console.log(ngayCanTim);
        findHoaDonsTheoNgay(ngayCanTim)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                return;
            }
            // thanh cong
            var dsHoaDon = response.result;
            var hoaDonListBox = cont_timKiemHoaDonBox.querySelector('.body-thu-ngan-list');
            renderHoaDonList(dsHoaDon, hoaDonListBox);
        })
        .catch(err => {
            console.log(err);
        })
    }
    
    cont_themMatHangBox.querySelector('.xac-nhan-them-thu-ngan button[name="huy-them-hang"]').onclick = e => {
        func_showGoodsListBtn.click();
    }

    cont_themMatHangBox.querySelector('.xac-nhan-them-thu-ngan button[name="them-hang"]').onclick = e => {
        var tenMatHang = cont_themMatHangBox.querySelector('.thong-tin-them input[name="ten-mat-hang"]').value;
        var donGia = cont_themMatHangBox.querySelector('.thong-tin-them input[name="don-gia-hang"]').value;
        donGia = Number.parseFloat(donGia);
        createMatHang(tenMatHang, donGia)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                // alert(response.message);
                createToastMessage({
                    text: response.message,
                    icon: 'error',
                    showConfirmButton: false,
                    showCloseButton: true,
                    position: "top-end",
                    timer: 5000,
                })
                return;
            }
            // tao thanh cong
            // alert('Đã thêm mặt hàng ' + response.result.tenHang);
            createAlert('Đã thêm mặt hàng thành công', 'Đã tạo mặt hàng ' + response.result.tenHang, 'success');
            func_showGoodsListBtn.click();
        })
        .catch(err => {
            console.log(err);
        })
    }
    
    cont_matHangListBox.addEventListener('click', e => {
        var activedMatHang = cont_matHangListBox.querySelector('.thu-ngan.active');
        if(activedMatHang) activedMatHang.classList.remove('active');
        var selectedMatHang = e.target.closest('.thu-ngan');
        selectedMatHang.classList.add('active');
        selectedMatHangId = selectedMatHang.getAttribute('maHang');
    })
    
    cont_capNhatMangHangBox.querySelector('.xac-nhan-them-thu-ngan button[name="huy-cap-nhat-mat-hang"]').onclick = e => {
        func_showGoodsListBtn.click();
    }

    cont_capNhatMangHangBox.querySelector('.xac-nhan-them-thu-ngan button[name="cap-nhat-mat-hang"]').onclick = e => {
        var tenHang = updateMatHangForm.tenHangInput.value;
        var donGia = updateMatHangForm.donGiaInput.value;
        var donGia = Number.parseFloat(donGia);
        updateMatHang(selectedMatHangId, tenHang, donGia)
        .then(response => response.json())
        .then(response => {
            if(response.code != 1000) {
                createToastMessage({
                    text: response.message,
                    icon: 'error',
                    showConfirmButton: false,
                    showCloseButton: true,
                    position: "top-end",
                    timer: 5000,
                })
                return;
            }
            // cap nhat mat hang thanh cong
            createAlert('Cập nhật thành công', 'Đã cập nhật mặt hàng ' + response.result.maHang, 'success');
            func_showGoodsListBtn.click();
        })
        .catch(err => {
            console.log(err);
        })
    }
}