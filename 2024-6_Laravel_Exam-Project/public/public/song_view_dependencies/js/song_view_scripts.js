
let globalVolume = 1;
let CurrentlyPlayling = null;
let audio;
let ListOfLikedSongs;

function onload_required(list_of_liked_songs){ //runs all the neccessary functions / sets the needed variables for other functions to run properly

    ListOfLikedSongs = list_of_liked_songs;
    
}

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

function changeGlobalVolume2(){

    globalVolume = document.getElementById("volumeSlider").value;

    var elements = document.getElementsByClassName("AudioClass");
    var ids = Array.from(elements).map((element) => element.id);

    ids.forEach((id) => {
        document.getElementById(id).volume = globalVolume;
    });

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

function Play(id, current) {

    audio = document.getElementById(`Song${id}`);

    if (CurrentlyPlayling == true) {
        current.classList.remove('active');
        audio.pause();
        CurrentlyPlayling = false;
        document.getElementById("Animated_Box").classList.remove("animateBox");
        document.getElementById("Animated_Box").classList.toggle("playback_Box_animation");
    }else if (CurrentlyPlayling == false){
        document.getElementById("Animated_Box").classList.remove("playback_Box_animation");
        document.getElementById("Animated_Box").classList.toggle("animateBox");
        current.classList.toggle('active');
        audio.volume = globalVolume;
        audio.play();
        CurrentlyPlayling = true;
    }else if(CurrentlyPlayling == null){
        setTimeout(function() {
            document.getElementById("DurationAnimation").classList.toggle("PopUpToggle");
            document.getElementById("DurationAnimation").style.display = 'block';
        }, 500);
        document.getElementById("PlayButtonAnimation").classList.toggle("SlideDown");
        current.classList.toggle('active');
        audio.volume = globalVolume;
        audio.play();
        
        document.getElementById("Animated_Box").classList.toggle("animateBox");
        const totalDuration = formatTime(audio.duration);
        document.getElementById('SongsDurationBar').max = audio.duration;
        document.getElementById("TotalDuration").innerHTML = totalDuration;
        updateDurationTimer();

        CurrentlyPlayling = true;
    }

    audio.addEventListener('ended', function() {
            CurrentlyPlayling = false;
            current.classList.remove('active');
            audio.pause();
            document.getElementById("CurrentDuration").innerHTML = "0:00";
            document.getElementById("Animated_Box").classList.remove("animateBox");
            document.getElementById("Animated_Box").classList.toggle("playback_Box_animation");
        });

    audio.addEventListener('timeupdate', updateDurationBar);

}

function formatTime(seconds) { /*https://stackoverflow.com/questions/3733227/javascript-seconds-to-minutes-and-seconds*/

    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    const formattedSeconds = remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds;
    return `${minutes}:${formattedSeconds}`;

}

function updateDurationBar(){

    const progressBar = document.getElementById('SongsDurationBar');
    const currentTime = audio.currentTime;

    progressBar.value = audio.currentTime;

}

function updateDurationTimer() {

    document.getElementById("CurrentDuration").innerHTML = "0:00";

    intervalId = setInterval(() => {
        if (CurrentlyPlayling === true) {
            document.getElementById("CurrentDuration").innerHTML = formatTime(audio.currentTime);
        }
    }, 1000);

}