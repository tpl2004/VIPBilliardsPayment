function createToastMessage(options) {
    Swal.fire({
        ...options,
        toast: true
    });
}

function createAlert(title, descriptions, type) {
    Swal.fire({
        title: title,
        text: descriptions,
        icon: type,
        confirmButtonColor: "#15919b",
        position: "top"
    });
}

function formatNullValue() {
    return 'N/A';
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