var string;
let array;
let globalVolume = 1;
let ListOfLikedSongs;
let oldAudio;
let audio;


function onload_required(list_of_liked_songs, song_id_list){ //runs all the neccessary functions / sets the needed variables for other functions to run properly

    ListOfLikedSongs = list_of_liked_songs;
    string = JSON.stringify(song_id_list);
    array = string.split(',');
    ListOfSongs()

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

function add(songId, songsName, songsArtsit, songsGenre, songsPath) {

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

    array.push(songId);
    document.getElementById("List").value = array;
    document.getElementById(`add${songId}`).style.opacity = "0.5";
    document.getElementById(`add${songId}`).style.pointerEvents = "none";
    document.getElementById(`add${songId}`).style.pointer = "none";

    updateTableClasses()

}

function remove(button, id) {

    let row = button.closest('tr');

    if (row && row.rowIndex !== 0) {
        row.parentNode.removeChild(row);
        
        for (let i = 0; i < array.length; i++) {
            if (array[i] == id) {
                array.splice(i, 1);
                break;
            }
        }

        document.getElementById("List").value = array;
        document.getElementById(`add${id}`).style.opacity = "1";
        document.getElementById(`add${id}`).style.pointerEvents = "auto";
        document.getElementById(`add${id}`).style.pointer = "cursor";

        updateTableClasses();
    }

}

function InsertSongsData(id, songName, artistName, songGenre, songFile) {

    array_Song_Id.push(id);
    array_Song_Name.push(songName);
    array_Artist_Name.push(artistName);
    array_Song_Genre.push(songGenre);
    array_File_Path.push(songFile);

}

function ListOfSongs() {

    let table = document.getElementById("TableOfPlaylistSongs");

    let HiddenId = document.getElementById("HiddenSongIdsArray").innerHTML;
    let trimmedString5 = HiddenId.trim();
    let array_Song_Id = trimmedString5.split(",");

    let HiddenName = document.getElementById("HiddenSongNameArray").innerHTML;
    let trimmedString = HiddenName.trim();
    let array_Song_Name = trimmedString.split(",");

    let HiddenArtist = document.getElementById("HiddenSongArtistNameArray").innerHTML;
    let trimmedString2 = HiddenArtist.trim();
    let array_Artist_Name = trimmedString2.split(",");

    let HiddenGenre = document.getElementById("HiddenSongGenreArray").innerHTML;
    let trimmedString3 = HiddenGenre.trim();
    let array_Song_Genre = trimmedString3.split(",");

    let HiddenPath = document.getElementById("HiddenSongPathArray").innerHTML;
    let trimmedString4 = HiddenPath.trim();
    let array_File_Path = trimmedString4.split(",");

    //pirmais elements ir tuks, so es ar shift nonemu vinu
    array_Song_Id.shift()
    array_Song_Name.shift()
    array_Artist_Name.shift()
    array_Song_Genre.shift()
    array_File_Path.shift()

    for (let i = 0; i < array_Song_Id.length; i++) {

        let row = table.insertRow(-1);

        let c1 = row.insertCell(0);
        let c2 = row.insertCell(1);
        let c3 = row.insertCell(2);
        let c4 = row.insertCell(3);

        c1.innerText = array_Song_Name[i];
        c2.innerText = array_Artist_Name[i];
        c3.innerText = array_Song_Genre[i];
        c4.innerHTML = `<div class="Action_Container">
                            <audio preload="none"><source src="/public/Songs_Folder/${array_File_Path[i]}" type="audio/mpeg"></audio>
                            <div id="AddedPlayButtonWithId${array_Song_Id[i]}" class="PlayButton" onclick="Play(${array_Song_Id[i]}, this)">
                                <div class="bottom"></div>
                                <div class="icon">
                                    <div class="left_side"></div>
                                    <div class="right_side"></div>
                                </div>
                                <div class="pointer"></div>
                            </div>
                            <label class="RemoveStyle">
                            <i class="fa-solid fa-circle-minus" id="${array_Song_Id[i]}" onclick="remove(this, ${array_Song_Id[i]})" 
                            ></i>
                            </label>
                        </div>`;

        const element = document.getElementById(`add${array_Song_Id[i]}`);

        if (element) {
            document.getElementById(`add${array_Song_Id[i]}`).style.opacity = "0.5";
            document.getElementById(`add${array_Song_Id[i]}`).style.pointerEvents = "none";
            document.getElementById(`add${array_Song_Id[i]}`).style.pointer = "none";
        }

        updateTableClasses();
    }

    //to-do updatot sito logiku

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

function SubmitForm(){

    document.getElementById("Playlists_Form_Name").value = document.getElementById("Playlists_Name").value;

    fetch('/songs/updatelikes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            liked_songs: ListOfLikedSongs
        })
    })
    document.getElementById("Form_Submit").submit()
    //likeBeforeRedirect("{{ route('songs.playlist_update', ['playlist' => $playlist->id]) }}");
}

function SubmitDeleteForm() {

document.getElementById("Form_Delete_Submit").submit();

}