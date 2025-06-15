function ShowEmailForm() {

    document.getElementById('EmailForm').style.display = 'block';
    document.getElementById('EmailForm').style.animation = 'slideIn 1s forwards';

}

function HideEmailForm() {

    document.getElementById('EmailForm').style.animation = 'slideOut 1s forwards';
    document.getElementById('ForgotHref').style.pointerEvents = 'none';
    setTimeout(function () {
        document.getElementById('EmailForm').style.display = 'none';
        document.getElementById('ForgotHref').style.pointerEvents = '';
    }, 1000);

}

function submitForm() {

    document.getElementById("Login_Form").submit();

}

function submitForm2() {

    document.getElementById("EmailSubmit").submit();

}

function freezeAnimation(id) {

    const inputElement = document.getElementById(id);

    if (inputElement.value !== "") {
        inputElement.classList.add("focused");
    } else {
        inputElement.classList.remove("focused");
    }

}