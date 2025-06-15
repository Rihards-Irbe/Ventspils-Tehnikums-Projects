function onload_required(isArtist_value){

    if (isArtist_value == 1) {
        document.getElementById("cbx").checked = true;
    }

    checkInputFields()

}

function submitForm(){
    document.getElementById("UpdateForm").submit();
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