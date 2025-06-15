function likeBeforeRedirect(url) {

    window.location.href = url;

}

function toggleFadeIn() {

    toggleFadeOut()

    var element = document.getElementById("Paragraph_Container");
    element.classList.remove("fade-out");
    setTimeout(function() {
        element.classList.add("fade-in");
    }, 500);
    document.getElementById("Titles_Container").classList.remove("slide-down");
    document.getElementById("Titles_Container").classList.add("slide-up");
    document.getElementById("Field1").onmouseout = null;
    document.getElementById("Field1").onmouseover = null;
    
}

function toggleFadeOut() {

    if(document.getElementById("Titles_Container").classList.contains("slide-up")){
        var element = document.getElementById("Paragraph_Container");
        element.classList.remove("fade-in");
        element.classList.add("fade-out");
        document.getElementById("Titles_Container").classList.remove("slide-up");
        document.getElementById("Titles_Container").classList.add("slide-down");
        document.getElementById("Field1").onmouseout = toggleFadeOut;
        document.getElementById("Field1").onmouseover = toggleFadeIn;

    }
    
    if(document.getElementById("Titles_Container2").classList.contains("slide-up")){
        var element = document.getElementById("Paragraph_Container2");
        element.classList.remove("fade-in");
        element.classList.add("fade-out");
        document.getElementById("Titles_Container2").classList.remove("slide-up");
        document.getElementById("Titles_Container2").classList.add("slide-down");
        document.getElementById("Field2").onmouseout = toggleFadeOut;
        document.getElementById("Field2").onmouseover = toggleFadeIn2;

    }
    
    if(document.getElementById("Titles_Container3").classList.contains("slide-up")){
        var element = document.getElementById("Paragraph_Container3");
        element.classList.remove("fade-in");
        element.classList.add("fade-out");
        document.getElementById("Titles_Container3").classList.remove("slide-up");
        document.getElementById("Titles_Container3").classList.add("slide-down");
        document.getElementById("Field3").onmouseout = toggleFadeOut;
        document.getElementById("Field3").onmouseover = toggleFadeIn3;

    }

}

function toggleFadeIn2() {

    toggleFadeOut()

    var element = document.getElementById("Paragraph_Container2");
    element.classList.remove("fade-out");
    setTimeout(function() {
        element.classList.add("fade-in");
    }, 400);
    document.getElementById("Titles_Container2").classList.remove("slide-down");
    document.getElementById("Titles_Container2").classList.add("slide-up");
    document.getElementById("Field2").onmouseout = null;
    document.getElementById("Field2").onmouseover = null;

}

function toggleFadeIn3() {

    toggleFadeOut()

    var element = document.getElementById("Paragraph_Container3");
    element.classList.remove("fade-out");
    setTimeout(function() {
        element.classList.add("fade-in");
    }, 400);
    document.getElementById("Titles_Container3").classList.remove("slide-down");
    document.getElementById("Titles_Container3").classList.add("slide-up");
    document.getElementById("Field3").onmouseout = null;
    document.getElementById("Field3").onmouseover = null;
}


//https://stackoverflow.com/questions/923299/how-can-i-detect-when-the-mouse-leaves-the-window
document.addEventListener("mouseleave", function(event){

if(event.clientY <= 0 || event.clientX <= 0 || (event.clientX >= window.innerWidth || event.clientY >= window.innerHeight))
{
    toggleFadeOut()
}
});

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
