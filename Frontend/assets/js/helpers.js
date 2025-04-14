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