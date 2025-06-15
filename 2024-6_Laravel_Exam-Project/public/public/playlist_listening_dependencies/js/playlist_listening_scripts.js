
let globalVolume = 1;
let audio;
let oldAudio;
let IdsArray;
let ShuffleIds = [];
let ShuffleToggle = false;
let RepeatToggle = false;
let ListOfLikedSongs;
let CurrentlyPlayling = null;

function onload_required(array_of_ids, list_of_liked_songs){ //runs all the neccessary functions / sets the needed variables for other functions to run properly

    ListOfLikedSongs = list_of_liked_songs;
    IdsArray = array_of_ids;
    
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
            if(RepeatToggle == true){
                audio.currentTime = 0;
                audio.play();
            }else{

                audio = document.getElementById(`Song${id}`);
                CurrentlyPlayling = false;
                NextSong()
            }

        });

    audio.addEventListener('timeupdate', updateDurationBar);

    audio.volume = globalVolume;

    if(oldAudio != null){
        oldAudio.closest('tr').style.backgroundColor = "";
        audio.closest('tr').style.backgroundColor = "#299617";
    }else{
        audio.closest('tr').style.backgroundColor = "#299617"; //#00AB66
    }

}

function updateDurationBar(){

    const progressBar = document.getElementById('SongsDurationBar');
    const currentTime = audio.currentTime;
    
    progressBar.value = audio.currentTime;

    updateDurationTimer()

}


function PlayCurrentSong() {

    audio_id = parseInt(audio.id.match(/\d+/)[0]);

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

function PreviousSong(){

    if(ShuffleToggle == true){

        for(let i = 0; i < ShuffleIds.length; i++){
            if(ShuffleIds[i] == audio.id.replace('Song', '')){
                document.getElementById("PlayButtonWithId" + audio.id.replace('Song', '')).classList.remove('active');
                audio.currentTime = 0;
                audio.pause();
                if (ShuffleIds[i - 1] == null){
                    Play(ShuffleIds[ShuffleIds.length -1])
                }else{
                    document.getElementById("PlayButtonWithId" + ShuffleIds[i - 1]).classList.remove('active');
                    Play(ShuffleIds[i - 1]); 
                }
                break;
            }
        }

    }else{

        for(let i = 0; i < IdsArray.length; i++){
            if(IdsArray[i] == audio.id.replace('Song', '')){
                document.getElementById("PlayButtonWithId" + audio.id.replace('Song', '')).classList.remove('active');
                audio.currentTime = 0;
                audio.pause();
                if (IdsArray[i - 1] == null){
                    document.getElementById("PlayButtonWithId" + IdsArray[IdsArray.length -1]).classList.remove('active');
                    Play(IdsArray[0])
                }else{
                    document.getElementById("PlayButtonWithId" + IdsArray[i - 1]).classList.remove('active');
                    Play(IdsArray[i - 1]); 
                }
                break;
            }
        }

    }

}

function NextSong(){

    if(ShuffleToggle == true){

        for(let i = 0; i < ShuffleIds.length; i++){
            if(ShuffleIds[i] == audio.id.replace('Song', '')){
                document.getElementById("PlayButtonWithId" + audio.id.replace('Song', '')).classList.remove('active');
                audio.currentTime = 0;
                audio.pause();
                if (i+1 == ShuffleIds.length){
                    Shuffle()
                    console.log("Shuffle Ending")
                }else{
                    document.getElementById("PlayButtonWithId" + ShuffleIds[i + 1]).classList.remove('active');
                    Play(ShuffleIds[i + 1]); 
                }
                break;
            }
        }

    }else{
        for(let i = 0; i < IdsArray.length; i++){
            if(IdsArray[i] == audio.id.replace('Song', '')){
                document.getElementById("PlayButtonWithId" + audio.id.replace('Song', '')).classList.remove('active');
                audio.currentTime = 0;
                audio.pause();
                if (i+1 == IdsArray.length){
                    document.getElementById("PlayButtonWithId" + IdsArray[0]).classList.remove('active');
                    Play(IdsArray[0])
                }else{
                    console.log("I value: " + i)
                    console.log("IdsArray len" + IdsArray.length)
                    document.getElementById("PlayButtonWithId" + IdsArray[i + 1]).classList.remove('active');
                    Play(IdsArray[i + 1]); 
                }
                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                Why do I have to add this classList to toggle play/pause buttons animation, only when the song ends.
                Removing these classList toggles will somehow not play the play buttons animation once the song ends.
                */
                break;
            }
        }
    }

}

let intervalId;

function updateDurationTimer() {

    document.getElementById("CurrentDuration").innerHTML = formatTime(audio.currentTime);
    
}

function Shuffle() {

    if (ShuffleToggle == true) {
        if (CurrentlyPlayling == true) {
            document.getElementById("PlayButtonWithId" + audio.id.replace('Song', '')).classList.remove('active');
            document.getElementById("AudioControlsPlayButton").classList.remove('active');
            document.getElementById("ShuffleButton").classList.remove("ToggledControlButton");
            audio.pause();
            CurrentlyPlayling = false;
        }
        ShuffleToggle = false;
    } else {
        if (CurrentlyPlayling == true) {
            audio.pause();
        }
        ShuffleIds = [...IdsArray];
        shuffleArray(ShuffleIds);
        ShuffleToggle = true;
        document.getElementById("ShuffleButton").classList.toggle("ToggledControlButton");
        Play(ShuffleIds[0]);
    }

}

function Repeat(){

    if (RepeatToggle == true) {
        document.getElementById("RepeatButton").classList.remove("ToggledControlButton");
        RepeatToggle = false;
    } else {

        RepeatToggle = true;
        document.getElementById("RepeatButton").classList.toggle("ToggledControlButton");
    }

}

function shuffleArray(array) { /*https://stackoverflow.com/questions/2450954/how-to-randomize-shuffle-a-javascript-array*/

        let len = array.length,
            currentIndex;
        for (currentIndex = len - 1; currentIndex > 0; currentIndex--) {
            let randIndex = Math.floor(Math.random() * (currentIndex + 1));
            var temp = array[currentIndex];
            array[currentIndex] = array[randIndex];
            array[randIndex] = temp;
        }
        return array;

}

function formatTime(seconds) { /*https://stackoverflow.com/questions/3733227/javascript-seconds-to-minutes-and-seconds*/

    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    const formattedSeconds = remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds;
    return `${minutes}:${formattedSeconds}`;
    
}

function changeSongsDuration() {
    const duration_value = document.getElementById("SongsDurationBar").value;
    console.log("Changed, duration bars value: " + duration_value);
    audio.currentTime = duration_value;
    CurrentlyPlayling = false;
    PlayCurrentSong();
}

document.addEventListener('DOMContentLoaded', function() { //? idk kp vjg
    const duration_bar = document.getElementById("SongsDurationBar");

    duration_bar.addEventListener('mousedown', function(event) {
        //Pause
        CurrentlyPlayling = true;
        PlayCurrentSong();
    });
});
