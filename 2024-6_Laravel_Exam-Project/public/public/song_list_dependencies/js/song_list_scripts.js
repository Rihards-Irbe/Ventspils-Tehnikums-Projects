let globalVolume = 1;
let audio;
let oldAudio;
let ListOfLikedSongs;

let CurrentlyPlayling = null;

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

function likefunc(id) {

    const checkbox = document.getElementById(`check${id}`);
    const parsedId = id.replace('check', '');

    if (checkbox.checked) {
        ListOfLikedSongs.push(parsedId);
    } else {
        const index = ListOfLikedSongs.indexOf(parsedId);
        if (index !== -1) {
            ListOfLikedSongs.splice(index, 1);
        }
    }

}

function Play(id) {

    if (CurrentlyPlayling == false) {
        oldAudio = audio
        audio = document.getElementById(`Song${id}`);

        if(oldAudio != audio){
            document.getElementById(`PlayButtonWithId${id}`).classList.toggle('active');
            document.getElementById(`AudioControlsPlayButton`).classList.remove('active');
            document.getElementById(`AudioControlsPlayButton`).classList.toggle('active');
            old_id = parseInt(oldAudio.id.match(/\d+$/)[0]);
            document.getElementById(`PlayButtonWithId${old_id}`).classList.remove('active');
            oldAudio.currentTime = 0;
            oldAudio.pause();
            audio.play()
            
            const totalDuration = formatTime(audio.duration);
            document.getElementById('SongsDurationBar').max = audio.duration;
            document.getElementById("TotalDuration").innerHTML = totalDuration;
            CurrentlyPlayling = true;
        }else{
            audio.play()
            document.getElementById(`AudioControlsPlayButton`).classList.toggle('active');
            document.getElementById(`PlayButtonWithId${id}`).classList.toggle('active');
            CurrentlyPlayling = true;
        }

    }else if (CurrentlyPlayling == true){

        oldAudio = audio
        audio = document.getElementById(`Song${id}`);

        if(oldAudio != audio){
            old_id = parseInt(oldAudio.id.match(/\d+$/)[0]);
            document.getElementById(`PlayButtonWithId${old_id}`).classList.remove('active');
            oldAudio.currentTime = 0;
            oldAudio.pause();
            audio.volume = globalVolume;
            audio.play()
            document.getElementById(`PlayButtonWithId${id}`).classList.toggle('active');

            const totalDuration = formatTime(audio.duration);
            document.getElementById('SongsDurationBar').max = audio.duration;
            document.getElementById("TotalDuration").innerHTML = totalDuration;
            CurrentlyPlayling = true;

        }else{
            
            audio.pause()
            document.getElementById(`AudioControlsPlayButton`).classList.remove('active');
            document.getElementById(`PlayButtonWithId${id}`).classList.remove('active');
            CurrentlyPlayling = false;
        }

    }else if(CurrentlyPlayling == null){
        document.getElementById(`PlayButtonWithId${id}`).classList.toggle('active');
        document.getElementById("AudioControlsPlayButton").classList.toggle('active');
        document.getElementById("AudioContainerAnimation").classList.toggle("PopUpToggle");
        document.getElementById("AudioContainerAnimation").style.pointerEvents = "auto";
        audio = document.getElementById(`Song${id}`);
        audio.volume = globalVolume;
        audio.play();
        const totalDuration = formatTime(audio.duration);
        document.getElementById('SongsDurationBar').max = audio.duration;
        document.getElementById("TotalDuration").innerHTML = totalDuration;

        CurrentlyPlayling = true;
    }

    audio.addEventListener('ended', function() {
            audio_id = parseInt(audio.id.match(/\d+$/)[0]);
            document.getElementById(`PlayButtonWithId${audio_id}`).classList.remove('active');
            document.getElementById("AudioControlsPlayButton").classList.remove('active');
            CurrentlyPlayling = false;
            audio.pause();
            document.getElementById("CurrentDuration").innerHTML = "0:00";
        });

    audio.addEventListener('timeupdate', updateDurationBar);

    audio.volume = globalVolume;

}

function updateDurationBar(){

    const progressBar = document.getElementById('SongsDurationBar');
    const currentTime = audio.currentTime;
    
    progressBar.value = audio.currentTime;

    updateDurationTimer()

}

function PlayCurrentSong() {

    audio_id = parseInt(audio.id.match(/\d$/)[0]);

    if(CurrentlyPlayling == false){

        document.getElementById(`PlayButtonWithId${audio_id}`).classList.toggle('active');
        document.getElementById("AudioControlsPlayButton").classList.toggle('active');
        audio.play();
        CurrentlyPlayling = true;

    }else{

        document.getElementById(`PlayButtonWithId${audio_id}`).classList.remove('active');
        document.getElementById("AudioControlsPlayButton").classList.remove('active');
        audio.pause();
        CurrentlyPlayling = false;
        
    }

}

let intervalId;

function updateDurationTimer() {

    document.getElementById("CurrentDuration").innerHTML = formatTime(audio.currentTime);
    
}

function formatTime(seconds) { /*https://stackoverflow.com/questions/3733227/javascript-seconds-to-minutes-and-seconds*/
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    const formattedSeconds = remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds;
    return `${minutes}:${formattedSeconds}`;
}

function onload_required(list_of_liked_songs){

    ListOfLikedSongs = list_of_liked_songs;

}
