function onload_required(){

    checkInputFields()

}

function submitForm(){

    document.getElementById("RegisterForm").submit();

}

function freezeAnimation(id) {

    const inputElement = document.getElementById(id);

    if (inputElement.value !== "") {
        inputElement.classList.add("focused");
    } else {
        inputElement.classList.remove("focused");
    }

}

function checkInputFields(){

    freezeAnimation('input_username')
    freezeAnimation('input_email')
    
}
