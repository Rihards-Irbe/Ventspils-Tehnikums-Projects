let globalVolume = 1;

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

function changeGlobalVolume2(){

globalVolume = document.getElementById("volumeSlider").value;

var elements = document.getElementsByClassName("AudioClass");
var ids = Array.from(elements).map((element) => element.id);

  ids.forEach((id) => {
    document.getElementById(id).volume = globalVolume;
  });

}

function redirect(url){
    window.location.href = url;
}
