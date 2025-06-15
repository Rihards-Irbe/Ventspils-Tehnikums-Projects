<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/public/index_dependencies/styles/index_styles.css">
    <script src="/public/index_dependencies/js/index_scripts.js"></script>
</head>
<svg preserveAspectRatio="xMidYMid slice" viewBox="10 10 80 80">
    <defs>
        <style>
            @keyframes rotate {
                0% {
                    transform: rotate(0deg);
                }
                100% {
                    transform: rotate(360deg);
                }
            }
            .out-top-custom {
                animation: rotate 20s linear infinite;
                transform-origin: 13px 25px;
            }
            .in-top-custom {
                animation: rotate 10s linear infinite;
                transform-origin: 13px 25px;
            }
            .out-bottom-custom {
                animation: rotate 25s linear infinite;
                transform-origin: 84px 93px;
            }
            .in-bottom-custom {
                animation: rotate 15s linear infinite;
                transform-origin: 84px 93px;
            }
        </style>
    </defs>
    <path fill="#2a52be" class="out-top-custom" d="M37-5C25.1-14.7,5.7-19.1-9.2-10-28.5,1.8-32.7,31.1-19.8,49c15.5,21.5,52.6,22,67.2,2.3C59.4,35,53.7,8.5,37-5Z"/>
    <path fill="#4B9CD3" class="in-top-custom" d="M20.6,4.1C11.6,1.5-1.9,2.5-8,11.2-16.3,23.1-8.2,45.6,7.4,50S42.1,38.9,41,24.5C40.2,14.1,29.4,6.6,20.6,4.1Z"/>
    <path fill="#1F305E" class="out-bottom-custom" d="M105.9,48.6c-12.4-8.2-29.3-4.8-39.4.8-23.4,12.8-37.7,51.9-19.1,74.1s63.9,15.3,76-5.6c7.6-13.3,1.8-31.1-2.3-43.8C117.6,63.3,114.7,54.3,105.9,48.6Z"/>
    <path fill="#002fa7" class="in-bottom-custom" d="M102,67.1c-9.6-6.1-22-3.1-29.5,2-15.4,10.7-19.6,37.5-7.6,47.8s35.9,3.9,44.5-12.5C115.5,92.6,113.9,74.6,102,67.1Z"/>
</svg>

@if(session('error'))

    <div id="Error" class="Form_bg2">
                <div class="Error_Pos">
                    <p id="ErrorTitle" class="Title">Error Message:</p>
                </div>
                <div>
                    <p id="ErrorMessage" class="Title">Something Went Wrong</p>
                </div>
    </div>

    <script>
        function Error(message){
            const error = document.getElementById('Error');
            document.getElementById("ErrorMessage").innerHTML = message;
            error.classList.add('slideIn');
            error.classList.remove('slideOut');

            setTimeout(() => {
                error.classList.add('slideOut');
                error.classList.remove('slideIn');
        }, 5000);
            
        }

        Error('{{ session('error') }}');
    </script>
@endif

@if($PlayAnimation == 1)
  <div class="WelcomeMessage">
    <p id="WelcomeUser">Welcome {{Auth::user()->username}}</p>
  </div>
@else
<div class="WelcomeMessage">
    <p id="WelcomeUser"></p>
  </div>
@endif
<body>
<div class="Titles_Container">
    <p class="Page_Titles">Explore Songs</p>
    <div class="search-box">
        <button class="btn-search"><i onclick="search('song')" class="fas fa-search"></i></button>
        <input onkeydown="handleKeyPress(event, 'song')" type="text" id="search_inp" class="input-search" placeholder="Songs Name:">
    </div>
</div>
<div class="Container_Values">
@php
    $fixedSongAmount = 0;
    $displayedSongIds = [];
@endphp
@foreach($songs as $song)
    @if($fixedSongAmount < 5)
    @php $displayedSongIds[] = $song->id; @endphp
    <div id="SongsUniqueId{{$song->id}}" class="Songs">
        @if(Auth::id() == $song->Owners_ID || Auth::user()->isAdmin)
        <div id="EditIcon">
        <a onclick="likeBeforeRedirect('{{ route('songs.edit', ['song' => $song->id]) }}')"><i class="fas fa-edit"></i></a>
        </div>
        @endif
        <div id="SongsPos" onclick="likeBeforeRedirect('{{ route('songs.view', ['song' => $song->id]) }}')">
        <p class="Title">{{$song->Song_Name}}</p>
        </div>
        <div id="ArtistsPos">
        <p class="ArtistText">Artist: {{$song->Artists_Name}}</p>
        </div>
        <label>
        <input type="checkbox" name="liked_songs[]" id="{{ $song->id }}"
        {{ in_array($song->id, Auth::user()->Liked_Songs) ? 'checked' : '' }}
        onchange="likefunc(this.getAttribute('data-likedid'))" data-likedid="{{$song->id}}">
            <span><i class="fa-regular fa-heart"></i></span>
        </label>
        <audio class="AudioClass" id="Song{{$song->id}}" src="/public/Songs_Folder/{{ $song->Files_Name }}" preload="none"></audio>
        <div class="PlayButton" id="PlayButtonId{{$song->id}}" onclick="Play({{$song->id}}, this)">
            <div class="bottom"></div>
            <div class="icon">
                <div class="left_side"></div>
                <div class="right_side"></div>
            </div>
            <div class="pointer"></div>
        </div>
          <input type="range" id="durationBar{{$song->id}}" style="display: none" min="0" onchange=" ChangeSongsPlayDuration({{$song->id}})">

    </div>
    @php
        $fixedSongAmount = $fixedSongAmount + 1;
    @endphp
    @endif
    @if($fixedSongAmount > 5)
    @break;
    @endif
@endforeach
</div>
<div class="Container_Values">
    @foreach($songs as $song)
        @if(!in_array($song->id, $displayedSongIds))
        <div class="Songs2">
        @if(Auth::id() == $song->Owners_ID || Auth::user()->isAdmin)
        <div id="EditIcon">
        <a onclick="likeBeforeRedirect('{{ route('songs.edit', ['song' => $song->id]) }}')"><i class="fas fa-edit"></i></a>
        </div>
        @endif
        <div id="SongsPos" onclick="likeBeforeRedirect('{{ route('songs.view', ['song' => $song->id]) }}')">
        <p class="Title">{{$song->Song_Name}}</p>
        </div>
        <div id="ArtistsPos">
        <p class="ArtistText">Artist: {{$song->Artists_Name}}</p>
        </div>
        <label>
        <input type="checkbox" name="liked_songs[]" id="{{ $song->id }}"
        {{ in_array($song->id, Auth::user()->Liked_Songs) ? 'checked' : '' }}
        onchange="likefunc(this.getAttribute('data-likedid'))" data-likedid="{{$song->id}}">
            <span><i class="fa-regular fa-heart"></i></span>
        </label>
        <audio class="AudioClass" id="Song{{$song->id}}" src="/public/Songs_Folder/{{ $song->Files_Name }}" preload="none"></audio>
        <div class="PlayButton" id="PlayButtonId{{$song->id}}" onclick="Play({{$song->id}}, this)">
            <div class="bottom"></div>
            <div class="icon">
                <div class="left_side"></div>
                <div class="right_side"></div>
            </div>
            <div class="pointer"></div>
        </div>

        <input type="range" id="durationBar{{$song->id}}" style="display: none" min="0" onchange=" ChangeSongsPlayDuration({{$song->id}})">

    </div>
        @endif
    @endforeach
</div>
<div class="Titles_Container">
    <p class="Page_Titles">Playlists Made By Other Users</p>
    <div class="search-box">
        <button class="btn-search"><i onclick="search('playlist')" class="fas fa-search"></i></button>
        <input onkeydown="handleKeyPress(event, 'playlist')" type="text" id="search_inp2" class="input-search" placeholder="Songs Name:">
    </div>
</div>
<div class="Container_Values">
@foreach ($randomPlaylist as $playlist)
    <div class="Playlists" onclick="likeBeforeRedirect('{{ route('songs.playlist_listen', ['playlist' => $playlist->id]) }}')">
        <p class="Title">{{$playlist->name}}</p>
        <p class="ArtistText">Creator: {{$playlist->playlists_owner}}</p>
        <p class="PlaylistArtists">Contains Artists like:</p>

        @php
        $stringId = $playlist->song_id_list;
        $idsArray = explode(',', $stringId);
        $ArtistNames = [];
        $ArtistNamesShorter = [];

        foreach ($songs as $song) {
            if (in_array($song->id, $idsArray)) {
                if (in_array($song->Artists_Name, $ArtistNames) == false) {
                    $ArtistNames[] = $song->Artists_Name;
                }
            }
        }

        foreach ($ArtistNames as $artist) {
            if (count($ArtistNamesShorter) < 5) {
                $ArtistNamesShorter[] = $artist;
            }
        }
        @endphp

        @foreach ($ArtistNamesShorter as $artistsname)
            <p class="ArtistText">{{$artistsname}}</p>
        @endforeach
    </div>
@endforeach
</div>
<div class="Titles_Container">
    <p class="Page_Titles">Your Playlists</p>
</div>
<div class="User_Personal_Playlists_Container">
  <div class="Container_UserPlaylist">
    <div>
    <button class="NavigationButton" id="leftNav" onclick="scrollPlaylists(-1)"><</button>
    <button class="NavigationButton" id="rightNav" onclick="scrollPlaylists(1)">></button>
    </div>
    <div class="PlaylistsWrapper">
        @foreach($usersPlaylist as $playlist)
            <div class="User_Playlists" onclick="likeBeforeRedirect('{{ route('songs.playlist_listen', ['playlist' => $playlist->id]) }}')">
                <p class="Title">{{$playlist->name}}</p>
                <p class="ArtistText">Creator: {{$playlist->playlists_owner}}</p>
                <p class="PlaylistArtists">Contains Artists like:</p>
                @php
                $stringId = $playlist->song_id_list;
                $idsArray = explode(',', $stringId);
                $ArtistNames = [];
                $ArtistNamesShorter = [];

                foreach ($songs as $song) {
                    if (in_array($song->id, $idsArray)) {
                        if (in_array($song->Artists_Name, $ArtistNames) == false) {
                            $ArtistNames[] = $song->Artists_Name;
                        }
                    }
                }

                    foreach($ArtistNames as $artist){
                        if(count($ArtistNamesShorter) < 5){
                            $ArtistNamesShorter[] = $artist;
                        }
                    }
                @endphp

                @foreach($ArtistNamesShorter as $artistsname)
                    <p class="ArtistText">{{$artistsname}}</p>
                @endforeach
            </div>
    @endforeach
      </div>
  </div>
      <div class="PlaylistCreatePos">
      <p id="PlaylistCreatePos2" class="Title">Create A New Playlist?</p>
      <div id="PlayButtonContainer">
      <a id="PlaylistCreatePos" style="font-size: 30px;"><i onclick="likeBeforeRedirect('{{ route('songs.playlist_store') }}')" class="fa-solid fa-square-plus"></i></a>
      </div>     
      </div>
  <div class="Container_Values" id="Liked_Songs_Pos">
    <div class="Liked_Playlist" onclick="likeBeforeRedirect('{{ route('songs.playlist_liked_songs') }}')">
      <p class="Title">Songs You've Liked</p>
      <p class="ArtistText">Playlist Full Of Your Favorites</p>
      </div>
  </div>
</div>
<div class="SideBar">
<a  class="SideBarButtons" style="--clr: #2774AE" onclick="likeBeforeRedirect('{{ route('songs.index') }}')"><span><i class="fa-solid fa-house"></i></span></a>
<a  class="SideBarButtons" style="--clr: #4FFFB0" onclick="likeBeforeRedirect('{{ route('songs.user_edit', ['user' => Auth::id()]) }}')"><span><i class="fa-solid fa-user"></i></span></a>
<a  class="SideBarButtons" style="--clr: #FFD700" onclick="likeBeforeRedirect('{{ route('songs.aboutus')}}')"><span><i class="fa-solid fa-question"></i></span></a>
<a  class="SideBarButtons" style="--clr: #660000" onclick="likeBeforeRedirect('{{ route('songs.user_logout')}}')"><span><i class="fa-solid fa-right-from-bracket"></i></i></span></a>
@if(Auth::check() && Auth::user()->isArtist)
    <a class="SideBarButtons" style="--clr: #9370DB" onclick="likeBeforeRedirect('{{ route('songs.create') }}')"><span><i class="fa-solid fa-upload"></i></span></a>
@endif
@if(Auth::check() && Auth::user()->isAdmin)
<a  class="SideBarButtons" style="--clr: #E26310" onclick="likeBeforeRedirect('{{ route('songs.admin')}}')"><span><i class="fa-solid fa-user-tie"></i></i></span></a>
@endif
<a  id="GetWidthValue" class="SideBarButtons" style="--clr: #39FF14" onclick="VolumeSliderAppear()"><span><i class="fa-solid fa-volume-high"></i></i></span></a>
<div class="VolumeSliderContainer">
    <input type="range" class="VolumeSlider2.0" oninput="changeGlobalVolume2()" id="volumeSlider" min="0" value="1" max="1" step="0.1">
  </div>
</div>

<script>

window.onload = function() {
    PlayWelcomeAnimation(<?php echo $PlayAnimation; ?>, <?php echo json_encode(Auth::user()->Liked_Songs); ?>);
};

const playlistCreate = document.getElementById('PlaylistCreatePos');
const playlistCreatePos2 = document.getElementById('PlaylistCreatePos2');

playlistCreate.addEventListener('mouseenter', () => {
    playlistCreatePos2.classList.add('animateText');
});

playlistCreate.addEventListener('mouseleave', () => {
    playlistCreatePos2.classList.remove('animateText');
});

function handleKeyPress(event, search_type) {

    if (event.key === 'Enter') {
        search(search_type)

    }

}

function search(type){

    if(type == "song"){
        var searchInputValue = document.getElementById("search_inp").value;
        var routeUrl = "{{ route('songs.search', ['search_prompt' => ':searchInputValue']) }}";
        routeUrl = routeUrl.replace(':searchInputValue', searchInputValue);
        likeBeforeRedirect(routeUrl);

    }else if(type == 'playlist'){
        var searchInputValue = document.getElementById("search_inp2").value;
        var routeUrl = "{{ route('songs.playlist_search', ['search_prompt' => ':searchInputValue']) }}";
        routeUrl = routeUrl.replace(':searchInputValue', searchInputValue);
        likeBeforeRedirect(routeUrl);

    }

}

let currentIndex = 0;
const playlistWrapper = document.querySelector('.PlaylistsWrapper');
const playlists = document.querySelectorAll('.User_Playlists');

function scrollPlaylists(direction) {
    const playlistWidth = playlists[0].offsetWidth + 36;
    const maxIndex = playlists.length - 1;
  
    currentIndex = Math.min(Math.max(currentIndex + direction, 0), maxIndex);
    const newPosition = -currentIndex * playlistWidth;
  
    playlistWrapper.style.transform = `translateX(${newPosition}px)`;
}

</script>

</body>
</html>