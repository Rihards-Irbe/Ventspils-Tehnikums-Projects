const List_Of_Song_Ids = [];
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

function onload_required(list_of_liked_songs){

    ListOfLikedSongs = list_of_liked_songs

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

function Add(songId, songsName, songsArtsit, songsGenre, songsPath) {

    let table = document.getElementById("TableOfPlaylistSongs");

    let row = table.insertRow(-1);

    let c1 = row.insertCell(0);
    let c2 = row.insertCell(1);
    let c3 = row.insertCell(2);
    let c4 = row.insertCell(3);

    c1.innerText = songsName
    c2.innerText = songsArtsit
    c3.innerText = songsGenre
    c4.innerHTML = `<div class="Action_Container">
                        <div id="AddedPlayButtonWithId${songId}" class="PlayButton" onclick="Play(${songId}, this)">
                            <div class="bottom"></div>
                            <div class="icon">
                                <div class="left_side"></div>
                                <div class="right_side"></div>
                            </div>
                            <div class="pointer"></div>
                        </div>
                        <label class="RemoveStyle">
                        <i class="fa-solid fa-circle-minus" id="${songId}" onclick="remove(this, ${songId})" 
                        ></i>
                        </label>
                    </div>`;

    List_Of_Song_Ids.push(songId);
    document.getElementById("List").value = List_Of_Song_Ids;
    document.getElementById(`${songId}`).style.opacity = "0.5";
    document.getElementById(`${songId}`).style.pointerEvents = "none";
    document.getElementById(`${songId}`).style.pointer = "none";

    document.getElementById("InfoValue").style.display= 'none';

    updateTableClasses();

}

function changeGlobalVolume2(){

    globalVolume = document.getElementById("volumeSlider").value;

    var elements = document.getElementsByClassName("AudioClass");
    var ids = Array.from(elements).map((element) => element.id);

    ids.forEach((id) => {
    document.getElementById(id).volume = globalVolume;
    });

}


function remove(button, id) {
    let row = button.closest('tr');

    if (row && row.rowIndex !== 0) {
        row.parentNode.removeChild(row);
        
        for (let i = 0; i < List_Of_Song_Ids.length; i++) {
            if (List_Of_Song_Ids[i] == id) {
                List_Of_Song_Ids.splice(i, 1);
                break;
            }
        }

        document.getElementById("List").value = List_Of_Song_Ids;
        document.getElementById(`${id}`).style.opacity = "1";
        document.getElementById(`${id}`).style.pointerEvents = "auto";
        document.getElementById(`${id}`).style.pointer = "cursor";

        updateTableClasses();
    }

}


function updateTableClasses() {

    const table = document.getElementById("TableOfPlaylistSongs");
    const rows = table.getElementsByTagName("tr");

    for (let i = 0; i < rows.length; i++) {
        if (i % 2 === 0) {
            rows[i].classList.add("TableEven2");
            rows[i].classList.remove("TableOdd2");
        } else {
            rows[i].classList.add("TableOdd2");
            rows[i].classList.remove("TableEven2");
        }
    }

    if(List_Of_Song_Ids.length == 0){
    document.getElementById("InfoValue").style.display= 'block';
    }

}

function ShowForm() {

    if(List_Of_Song_Ids.length < 1){
        Error("Playlist contains no values");
    }else{
        const formBg = document.getElementById('formPos');

        formBg.classList.remove('slideOut');
        formBg.classList.add('slideIn');
        formBg.style.zIndex = "1";  
    }

}

function HideForm(){

    const formBg = document.getElementById('formPos');

    formBg.classList.remove('slideIn');
    formBg.classList.add('slideOut');
    formBg.style.zIndex = "-1";

}

function Error(message){

    const error = document.getElementById('Error');
    document.getElementById("ErrorMessage").innerHTML = message;
    error.classList.add('slideIn');
    error.classList.remove('slideOut');

    setTimeout(() => {
        error.classList.add('slideOut');
        error.classList.remove('slideIn');
    }, 3000);

}

function Play(id, current) {

    audio = document.getElementById(`Song${id}`);
  
    if (oldAudio != audio && oldAudio != null){
      oldAudio.pause();
      oldAudio.currentTime = 0;
      oldAudio_id = parseInt(oldAudio.id.match(/\d+$/)[0]); //extracts the old audios (Song{{id}} id value, to properly animate previous buttons stopping/pausing)
      document.getElementById(`PlayButtonWithId${oldAudio_id}`).classList.remove('active');
      if(document.getElementById(`AddedPlayButtonWithId${id}`) != null){

        document.getElementById(`AddedPlayButtonWithId${oldAudio_id}`).classList.remove('active');

      }
    }
  
    if (current.className === "PlayButton active") {
      current.classList.remove('active');
      audio.pause();
    } else {
      current.classList.toggle('active');
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

function SubmitValidation(){

    if(document.getElementById('PlaylistsName').value === ''){
        Error("Playlists Name Is Empty")
        console.log(List_Of_Song_Ids);
    }else if(List_Of_Song_Ids.length < 1){
        Error("Playlist contains no values");
    }else{
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
            document.getElementById("PlaylistCreateForm").submit();
        } else {
            console.error('Failed to update liked songs.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
    }

}