import { localStorageUserTokenKey } from './config.js'
import * as api from './api-service.js'

const TOKEN = localStorage.getItem(localStorageUserTokenKey);

var myInfo = null;
var selectedTableNumber = null;

var maThuNganBox = document.getElementById('ma-thu-ngan');
var hoTenThuNganBox = document.getElementById('ho-ten-thu-ngan');
var logoutBtn = document.querySelector('button[class = "logout"]');
var bodyBanBidaListBox = document.querySelector('.extension .content .ban-bida-list .body-ban-bida-list');
var showBidaTableListBtn = document.getElementById('show-bida-table-list');
var showBillListBtn = document.getElementById('show-bill-list');
var showMemberList = document.getElementById('show-member-list');
// var functionTaskbars = [showBidaTableListBtn, showBillListBtn, showMemberList];
var functionTaskbars = document.querySelector('.main-taskbar .function-taskbar').children;
var xemDanhSachBanBidaExtendFuncsBox = document.querySelector('.xem-danh-sach-ban-bida');
var xemDanhSachHoiVienExtendFuncsBox = document.querySelector('.xem-danh-sach-hoi-vien');
// var extendFunctionGroups = [xemDanhSachBanBidaExtendFuncsBox, xemDanhSachHoiVienExtendFuncsBox];
var extendFunctionGroups = document.querySelector('.extension .top-taskbar').children;
var banBidaListBox = document.querySelector('.content .ban-bida-list');
var contentBoxchilds = document.querySelector('.content').children;
var functionTaskbarBox = document.querySelector('.function-taskbar');
var moBanBtn = document.getElementById('mo-ban');
var capNhatHoaDonBtn = document.getElementById('cap-nhat-hoa-don');
console.log(capNhatHoaDonBtn);

function main() {

    checkSignedIn()
    .then(response => {
        getMyInfo()
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                myInfo = response.result;
                console.log(myInfo);
                renderMyInfo();
            }
        })
        .then(() => {
            activeMainFuntion('show-bida-table-list');
            enableExtendFuncGroup('xem-danh-sach-ban-bida');
            enableContent('ban-bida-list');
            getAllBanBidaChuaXoa()
            .then(response => response.json())
            .then(response => {
                renderBanBidas(response.result);
            })
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

// get my info
function getMyInfo() {
    var options = {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.authApi + '/thungan/profile', options)
}

// render my info
function renderMyInfo() {
    maThuNganBox.innerHTML = `
        <p>Mã thu ngân:</p>
        <p>${myInfo.maThuNgan}</p>
    `;
    hoTenThuNganBox.innerHTML = `
        <p>Họ tên thu ngân:</p>
        <p>${myInfo.hoTen}</p>
    `
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
            <div class="ban-bida ${banBida.trangThai == 1? 'unavailable' : ''}">
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
    // functionTaskbars.forEach(func => {
    //     if(func.id != funcId && func.classList.contains('active')) {
    //         func.classList.remove('active');
    //     }
    // })
    for(var i = 0; i < functionTaskbars.length; i++) {
        if(functionTaskbars[i].id != funcId && functionTaskbars[i].classList.contains('active'))
            functionTaskbars[i].classList.remove('active');
    }
}

// enable a extend function group
function enableExtendFuncGroup(GroupFuncClass = null) {
    if(GroupFuncClass) {
        var GroupFunc = document.querySelector('.extension .top-taskbar .' + GroupFuncClass);
        if(GroupFunc.classList.contains('disable')) {
            GroupFunc.classList.remove('disable');
        }
    }
    // extendFunctionGroups.forEach(extendFuncGroup => {
    //     if(!extendFuncGroup.classList.contains(GroupFuncClass) && !extendFuncGroup.classList.contains('disable')) {
    //         extendFuncGroup.classList.add('disable');
    //     }
    //     // if(extendFuncGroup.className != GroupFuncClass) console.log(extendFuncGroup);
    // })
    for(var i = 0; i < extendFunctionGroups.length; i++) {
        
        if(!extendFunctionGroups[i].classList.contains(GroupFuncClass) && !extendFunctionGroups[i].classList.contains('disable')) {
            extendFunctionGroups[i].classList.add('disable');
        }
    }
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

// lay hoa don chua thanh toan theo so ban
function layHoaDonChuaThanhToanCuaBan() {
    var options = {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.hoaDonApi + '/' + selectedTableNumber, options);
}

function layDSMatHangTrongMotHoaDon(maHoaDon) {
    var options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.matHangTrongHoaDonApi + '/' + maHoaDon, options);
}

// cap nhat hoi vien cho hoa don
function updateHoiVienForHoaDon(maHoaDon, maHoiVien) {
    var options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
        body: JSON.stringify({
            "maHoiVien": maHoiVien
        }),
    }
    return fetch(api.hoaDonApi + '/' + maHoaDon, options);
}

// get all mat hang
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

// tao mat hang trong hoa don
function createMatHangTrongHoaDon(maHang, maHoaDon, soLuong) {
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
        body: JSON.stringify({
            "maHang": maHang,
            "maHoaDon": maHoaDon,
            "soLuong": soLuong
        }),
    }
    return fetch(api.matHangTrongHoaDonApi, options);
}

// cap nhat mat hang trong hoa don
function updateMatHangTrongHoaDon(maHang, maHoaDon, soLuong) {
    var options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
        body: JSON.stringify({
            "maHang": maHang,
            "maHoaDon": maHoaDon,
            "soLuong": soLuong
        }),
    }
    return fetch(api.matHangTrongHoaDonApi, options);
}

// xoa mat hang trong hoa don
function deleteMatHangTrongHoaDon(maHang, maHoaDon) {
    var options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.matHangTrongHoaDonApi + '/' + maHoaDon + '/' + maHang, options);
}

// format date
function formatDate(dateString) {
    var date = new Date(dateString);
    return date.toLocaleString('vi-VN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false
    });
}

// get all hoaDon
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

// get all hoi vien
function getAllHoiVien() {
    var options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${TOKEN}`
        },
    }
    return fetch(api.hoiVienApi, options);
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
        var billBox = document.querySelector('.content .hoa-don-list .body-bill-list');
        console.log(billBox);
        getAllHoaDon()
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                var dsHoaDon = response.result;
                var html = dsHoaDon.map(hoadon => {
                    return `
                        <div class="bill">
                            <p>${hoadon.maHoaDon}</p>
                            <p>${formatDate(hoadon.thoiDiemVao)}</p>
                            <p>${hoadon.soGioChoi? hoadon.soGioChoi : 'N/A'}</p>
                            <p>${hoadon.soBan}</p>
                            <p>${hoadon.donGia}</p>
                            <p>${hoadon.tongTien? hoadon.tongTien : 'N/A'}</p>
                        </div>
                    `
                })
                billBox.innerHTML = html.join('');
            }
        })
    })
    
    showMemberList.addEventListener('click', e => {
        activeMainFuntion(showMemberList.id);
        enableExtendFuncGroup('xem-danh-sach-hoi-vien');
        enableContent('hoi-vien-list');
        var hoiVienBox = document.querySelector('.content .hoi-vien-list .body-hoi-vien-list');
        console.log(hoiVienBox)
        getAllHoiVien()
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                var dsHoiVien = response.result;
                var html = dsHoiVien.map(hoiVien => {
                    return `
                        <div class="hoi-vien">
                            <p>${hoiVien.maHoiVien}</p>
                            <p>${hoiVien.hoTen}</p>
                            <p>${hoiVien.soDienThoai}</p>
                            <p>${hoiVien.soGioChoi}</p>
                            <p>${formatDate(hoiVien.ngayDangKy)}</p>
                            <p>${hoiVien.tenCapDo}</p>
                        </div>
                    `
                })
                hoiVienBox.innerHTML = html.join('');
            }
        })
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
        if(selectedTableNumber == null) {
            alert('Vui lòng chọn bàn!');
            return;
        } 
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
    
    capNhatHoaDonBtn.addEventListener('click', e => {
        if(selectedTableNumber == null) {
            alert('Vui lòng chọn bàn!');
            return;
        }
        enableContent('update-hoa-don');
        var updateHoaDonBox = document.querySelector('.content .update-hoa-don');
        var billInfo = updateHoaDonBox.querySelector('.bill-info')
        layHoaDonChuaThanhToanCuaBan()
        .then(response => response.json())
        .then(response => {
            if(response.code == 1000) {
                var hoadon = response.result;
                return hoadon;
                    
            }
            else {
                alert(response.message);
                return Promise.reject(response.massage);
            }
        })
        .then(hoadon => {
            getAllMatHang()
            .then(response => response.json())
            .then(response => {
                if(response.code == 1000) {
                    var dsAllMatHang = response.result;
                    return dsAllMatHang;
                }
                else {
                    return Promise.reject(response.message);
                }
            })
            .then(dsAllMatHang => {
                var matHangSelect = updateHoaDonBox.querySelector('select[name="mat-hang-update-list"]');
                console.log(matHangSelect);
                var html = dsAllMatHang.map((matHang, index) => {
                    return `
                        <option ma-hang="${matHang.maHang}">
                            <p>${index+1}</p>
                            <p>${matHang.tenHang}</p>
                        </option>
                    `
                })
                matHangSelect.innerHTML = html.join('');
                        
                // xu ly khi them mat hang vao hoa don
                document.getElementById('them-mat-hang').onclick = e => {
                    var selectedOption = matHangSelect.options[matHangSelect.selectedIndex];
                    console.log(selectedOption)
                    var maHang = Number.parseInt(selectedOption.getAttribute('ma-hang'));
                    var soLuong = matHangSelect.nextElementSibling.value;
                    if(soLuong == '') soLuong = 1;
                    else soLuong = Number.parseInt(soLuong);
                    createMatHangTrongHoaDon(maHang, hoadon.maHoaDon, soLuong)
                    .then(response => response.json())
                    .then(response => {
                        if(response.code == 1000) {
                            alert('Đã thêm thanh công!');
                            capNhatHoaDonBtn.click();
                        }
                        else {
                            alert('Thêm thất bại!');
                        }
                    })
                }
                
                // xu ly khi cap nhat mat hang trong hoa don
                document.getElementById('sua-mat-hang').onclick = e => {
                    var selectedOption = matHangSelect.options[matHangSelect.selectedIndex];
                    console.log(selectedOption)
                    var maHang = Number.parseInt(selectedOption.getAttribute('ma-hang'));
                    var soLuong = matHangSelect.nextElementSibling.value;
                    if(soLuong == '') soLuong = 1;
                    else soLuong = Number.parseInt(soLuong);
                    updateMatHangTrongHoaDon(maHang, hoadon.maHoaDon, soLuong)
                    .then(response => response.json())
                    .then(response => {
                        if(response.code == 1000) {
                            alert('Đã cập nhật thanh công!');
                            capNhatHoaDonBtn.click();
                        }
                        else {
                            alert('Cập nhật thất bại thất bại!');
                        }
                    })
                }
                        
                // xu ly khi xoa mat hang trong hoa don
                document.getElementById('xoa-mat-hang').onclick = e => {
                    var selectedOption = matHangSelect.options[matHangSelect.selectedIndex];
                    console.log(selectedOption)
                    var maHang = Number.parseInt(selectedOption.getAttribute('ma-hang'));
                    deleteMatHangTrongHoaDon(maHang, hoadon.maHoaDon)
                    .then(response => response.json())
                    .then(response => {
                        if(response.code == 1000) {
                            alert('Đã xóa thành công!');
                            capNhatHoaDonBtn.click();
                        }
                        else {
                            alert('Xóa thất bại!');
                        }
                    })
                }

            })

            document.getElementById('hoi-vien-update-button').onclick = e => {
                var maHoiVien = document.getElementById('hoi-vien-update').value;
                if(maHoiVien == '') maHoiVien = null;
                else maHoiVien = Number.parseInt(maHoiVien);
                updateHoiVienForHoaDon(hoadon.maHoaDon, maHoiVien)
                .then(response => response.json())
                .then(response => {
                    if(response.code == 1000) {
                        alert('Cập nhật thành công!');
                        console.log(response.result);
                        capNhatHoaDonBtn.click();
                    }
                    else {
                        alert('Cập nhật không thành công!');
                    }
                })
            }

            layDSMatHangTrongMotHoaDon(hoadon.maHoaDon)
            .then(response => response.json())
            .then(response => {
                if(response.code == 1000) {
                    var dsMatHang = response.result;
                    var html = `
                        <p>THÔNG TIN HÓA ĐƠN</p>
                        <table>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Mã hóa đơn:</td>
                                    <td>${hoadon.maHoaDon}</td>
                                </tr>
                                <tr>
                                    <td>Thời điểm vào:</td>
                                    <td>${formatDate(hoadon.thoiDiemVao)}</td>
                                </tr>
                                <tr>
                                    <td>Thời điểm ra:</td>
                                    <td>${hoadon.thoiDiemRa? hoadon.thoiDiemRa : 'N/A'}</td>
                                </tr>
                                <tr>
                                    <td>Số giờ chơi:</td>
                                    <td>${hoadon.soGioChoi? hoadon.soGioChoi : 'N/A'}</td>
                                </tr>
                                <tr>
                                    <td>Số bàn:</td>
                                    <td>${hoadon.soBan}</td>
                                </tr>
                                <tr>
                                    <td>Loại bàn:</td>
                                    <td>${hoadon.tenLoaiBan}</td>
                                </tr>
                                <tr>
                                    <td>Đơn giá:</td>
                                    <td>${hoadon.donGia}</td>
                                </tr>
                                <tr>
                                    <td>Mặt hàng:</td>
                                    <td>
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>Tên mặt hàng</th>
                                                    <th>Đơn giá</th>
                                                    <th>Số lượng</th>
                                                </tr>
                                            </thead>
                                            <tbody name="list-mat-hang">
                                                <tr>
                                                    <td>Coca</td>
                                                    <td>20000</td>
                                                    <td>3</td>
                                                </tr>
                                                <tr>
                                                    <td>Pepsi</td>
                                                    <td>20000</td>
                                                    <td>4</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tên thu ngân:</td>
                                    <td>${hoadon.tenThuNgan}</td>
                                </tr>
                                <tr>
                                    <td>Tên hội viên:</td>
                                    <td>${hoadon.tenHoiVien? hoadon.tenHoiVien : 'N/A'}</td>
                                </tr>
                                <tr>
                                    <td>Cấp độ:</td>
                                    <td>${hoadon.tenCapDo? hoadon.tenCapDo : 'N/A'}</td>
                                </tr>
                                <tr>
                                    <td>Ưu đãi:</td>
                                    <td>${hoadon.uuDai? hoadon.uuDai : 'N/A'}</td>
                                </tr>
                                <tr>
                                    <td>Tổng tiền:</td>
                                    <td>${hoadon.tongTien? hoadon.tongTien : 'N/A'}</td>
                                </tr>
                            </tbody>
                        </table>
                    `
                    billInfo.innerHTML = html;
                    var listMatHangBox = billInfo.querySelector('tbody[name="list-mat-hang"]');
                    console.log(listMatHangBox);
                    var listMatHangHtml = dsMatHang.map(matHang => {
                        return `
                            <tr>
                                <td>${matHang.tenHang}</td>
                                <td>${matHang.donGia}</td>
                                <td>${matHang.soLuong}</td>
                            </tr>
                        `
                    })
                    listMatHangBox.innerHTML = listMatHangHtml.join('');
                }
                else {
                    alert(response.massage);
                }
            })
        })
    })
}