let globalVolume = 1;
let ListOfLikedSongs;
let oldAudio;
let audio;

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

function toggleVolumeSlider(songId) {

  const volumeSlider = document.getElementById(`volumeSlider${songId}`);
  volumeSlider.classList.toggle('slider-visible');

}

function PlayWelcomeAnimation(bool, list_of_liked_songs) {

  var playAnimation = bool;
  var welcomeMessage = document.querySelector('.WelcomeMessage');
  var welcomeMessageText = document.querySelector('.WelcomeMessage p');
  var sideBar = document.querySelector('.SideBar');
  var songs = document.querySelector('.Songs');
  var songs2 = document.querySelector('.Songs2');
  var playlists = document.querySelector('.Playlists');
  var pageTitles = document.querySelector('.Page_Titles');
  var searchBtn = document.querySelector('.btn-search');
  var searchBtnIcon = document.querySelector('.input-search');

  if (playAnimation !== 1) {
    welcomeMessage.classList.add('stopAnimation');
    welcomeMessageText.classList.add('stopAnimation');
    welcomeMessage.style.opacity = '0';
    welcomeMessage.style.zIndex = '-1';
  } else {
    welcomeMessage.classList.remove('stopAnimation');
    welcomeMessageText.classList.remove('stopAnimation');
    welcomeMessage.style.opacity = '';
    welcomeMessage.style.zIndex = '';
  }

  var elementsToChange = document.querySelectorAll('.btn-search, .input-search, .Page_Titles, .Playlists, .Songs, .Songs2, .SideBar');

  elementsToChange.forEach(function(element) {
    if (playAnimation !== 1) {
      element.classList.add('stopAnimation');
      element.style.opacity = '1';
    } else {
      element.classList.remove('stopAnimation');
      element.style.opacity = '';
    }
  });

  ListOfLikedSongs = list_of_liked_songs; // Setting the value of liked songs on page load. Since this function runs on *windows.load*

}

function ChangeSongsPlayDuration(id) {

  var audio = document.getElementById(`Song${id}`);
  audio.currentTime = document.getElementById(`durationBar${id}`).value;

}

function Play(id, current) {

  audio = document.getElementById(`Song${id}`);

  if (oldAudio != audio && oldAudio != null){
    oldAudio.pause();
    oldAudio.currentTime = 0;
    oldAudio_id = parseInt(oldAudio.id.match(/\d+$/)[0]); //extracts the old audios (Song{{id}} id value, to properly animate previous buttons stopping/pausing)
    document.getElementById(`PlayButtonId${oldAudio_id}`).classList.remove('active');
  }

  if (current.className === "PlayButton active") {
    current.classList.remove('active');
    audio.pause();
  } else {
    current.classList.toggle('active');
    audio.currentTime = document.getElementById(`durationBar${id}`).value;
    audio.volume = globalVolume;
    audio.play();
  }

  audio.addEventListener('ended', function() {
    current.classList.remove('active');
    audio.currentTime = 0;
    audio.pause();
  });

  oldAudio = audio;

}

function likefunc(id){

    if(document.getElementById(`${id}`).checked){
        ListOfLikedSongs.push(id);
    }else{
        for(let i = 0; i < ListOfLikedSongs.length; i++){
            if(ListOfLikedSongs[i] == id){
                ListOfLikedSongs.splice(i, 1);
                break;
            }
        }
    }

}

function likeBeforeRedirect(targetUrl) {

    fetch('/songs/updatelikes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="csrf-token"]').getAttribute('content')
        },
        body: JSON.stringify({
            liked_songs: ListOfLikedSongs
        })
    })
    .then(response => {
        if (response.ok) {
            window.location.href = targetUrl;
        } else {
            console.error('Failed to update liked songs.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });

}

function search(){

  console.log("Searching!")

}
