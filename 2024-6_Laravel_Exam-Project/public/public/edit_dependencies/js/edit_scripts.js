function VolumeSliderAppear() {

    const volumeSliderContainer = document.querySelector('.VolumeSliderContainer');
    const visible = volumeSliderContainer.style.opacity;

    const target = document.querySelector('.SideBar');
    const sliderswidth = window.getComputedStyle(target).width;

    if (visible == 0) {
        volumeSliderContainer.style.width = `${sliderswidth}`;
        volumeSliderContainer.style.opacity = 1;
    } else {
        volumeSliderContainer.style.width = '0px';
        volumeSliderContainer.style.opacity = 0;
    }

}

function likeBeforeRedirect(targetUrl) {

    window.location.href = targetUrl;

}

function SubmitForm(){

    console.log("From Submitted")

}

function Edit_Styles_On_Load(){

    removeSpan('Song_Name')
    removeSpan('Artists_Name')
    removeSpan('Songs_Genre')

}

function removeSpan(id) {

    const inputElement = document.getElementById("Song_Name");
    const spanElement = document.getElementById("Songs_Name_Span");

    const inputElement2 = document.getElementById("Artists_Name");
    const spanElement2 = document.getElementById("Artists_Name_Span");

    const inputElement3 = document.getElementById("Songs_Genre");
    const spanElement3 = document.getElementById("Songs_Genre_Span");

    if (id === "Song_Name") {
        if (inputElement.value !== "") {
            inputElement.classList.add("focused");
        } else {
            inputElement.classList.remove("focused");
        }
    }else if(id === "Artists_Name"){
        if (inputElement2.value !== "") {
            inputElement2.classList.add("focused");
        } else {
            inputElement2.classList.remove("focused");
        }
    }else if(id === "Songs_Genre"){
        if (inputElement3.value !== "") {
            inputElement3.classList.add("focused");
        } else {
            inputElement3.classList.remove("focused");
        }
    }

}

function changeGlobalVolume2(){

    globalVolume = document.getElementById("volumeSlider").value;
  
    var elements = document.getElementsByClassName("AudioClass");
    var ids = Array.from(elements).map((element) => element.id);
  
      ids.forEach((id) => {
        document.getElementById(id).volume = globalVolume;
      });
  
}
