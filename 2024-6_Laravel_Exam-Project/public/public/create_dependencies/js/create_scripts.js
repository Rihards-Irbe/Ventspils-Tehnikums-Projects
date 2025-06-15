let alreadyUploaded = false;

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
    console.log("Form Submitted")
}

function UploadSong() {
    let currentFile = document.getElementById("file").value;

    if (currentFile !== "") {
        if (alreadyUploaded == false) {
            let filesLabel = document.getElementById("filesLabel");

            filesLabel.style.padding = "20% 45% 20% 45%";
            filesLabel.style.color = "green";
            filesLabel.style.borderColor = "green";
            filesLabel.title = currentFile;
            filesLabel.classList.toggle("addedHover");
            filesLabel.innerHTML = `<i style="font-size: 30px" class="fa-solid fa-circle-check"></i>`;

            alreadyUploaded = true;
        } else {
            let filesLabel = document.getElementById("filesLabel");

            filesLabel.style.padding = "";
            filesLabel.style.color = "";
            filesLabel.style.borderColor = "";
            filesLabel.title = "";
            filesLabel.classList.remove("addedHover");
            filesLabel.innerHTML = `Upload File`;

            document.getElementById("file").value = "";
            alreadyUploaded = false;
        }
    }
}

function changeIcon() {
    let filesLabel = document.getElementById("filesLabel");

    if (alreadyUploaded == true) {
        filesLabel.innerHTML = `<i style="font-size: 30px" class="fa-solid fa-circle-minus"></i>`;
    }
}

function changeBack() {
    let filesLabel = document.getElementById("filesLabel");

    if (alreadyUploaded == true) {
        filesLabel.innerHTML = `<i style="font-size: 30px" class="fa-solid fa-circle-check"></i>`;
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
