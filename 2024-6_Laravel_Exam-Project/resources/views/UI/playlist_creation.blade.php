<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/public/playlist_create_dependencies/styles/playlist_create_styles.css">
    <script src="/public/playlist_create_dependencies/js/playlist_create_scripts.js"></script>
</head>
<body>
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
    <path fill="#40916c" class="out-bottom-custom" d="M105.9,48.6c-12.4-8.2-29.3-4.8-39.4.8-23.4,12.8-37.7,51.9-19.1,74.1s63.9,15.3,76-5.6c7.6-13.3,1.8-31.1-2.3-43.8C117.6,63.3,114.7,54.3,105.9,48.6Z"/>
    <path fill="#52b788" class="in-bottom-custom" d="M102,67.1c-9.6-6.1-22-3.1-29.5,2-15.4,10.7-19.6,37.5-7.6,47.8s35.9,3.9,44.5-12.5C115.5,92.6,113.9,74.6,102,67.1Z"/>
</svg>
<div class="ButtonContainer">
        <a class="ButtonsStyle" style="--clr: #2774AE" onclick="ShowForm()"><span>Create</span></a>
</div>
<div class="Titles_Container">
    <p class="Title">List Of Songs You've Liked</p>
</div>
<div class="Table_Container">
    <table id="Table">
        <th>Songs Name</th>
        <th>Artists Name</th>
        <th>Songs Genre</th>
        <th>Action</th>
        @php $counter = 0; @endphp
        @foreach($array as $id)
            @foreach($songs as $song)
                @if($song->id == $id)
                    @if($counter++ % 2 == 0)
                        <tr class="TableEven">
                    @else
                        <tr class="TableOdd">
                    @endif
                        <td>{{$song->Song_Name}}</td>
                        <td>{{$song->Artists_Name}}</td>
                        <td>{{$song->Songs_Genre}}</td>
                        <td>
                        <div class="Action_Container">
                        <audio class="AudioClass" id="Song{{$song->id}}" src="/public/Songs_Folder/{{ $song->Files_Name }}" preload="none"></audio>
                        <div id="PlayButtonWithId{{$song->id}}" class="PlayButton" onclick="Play({{$song->id}}, this)">
                                <div class="bottom"></div>
                                <div class="icon">
                                    <div class="left_side"></div>
                                    <div class="right_side"></div>
                                </div>
                                <div class="pointer"></div>
                            </div>
                            <label>
                            <input type="checkbox" name="liked_songs[]" id="check{{ $song->id }}"
                        {{ in_array($song->id, Auth::user()->Liked_Songs) ? 'checked' : '' }}
                        onchange="likefunc('{{ $song->id }}')">
                                <span><i class="fa-regular fa-heart"></i></span>
                            </label>
                            <label class="Add">
                            <i class="fa-solid fa-circle-plus" id="{{$song->id}}" onclick="Add(this.getAttribute('data-songid'), this.getAttribute('data-songname'), this.getAttribute('data-artistname'), this.getAttribute('data-songgenre'), this.getAttribute('data-songpath'))" 
                            data-songid="{{$song->id}}" 
                            data-songname="{{$song->Song_Name}}" 
                            data-artistname="{{$song->Artists_Name}}" 
                            data-songgenre="{{$song->Songs_Genre}}" 
                            data-songpath="{{$song->Files_Name}}"
                            ></i>
                            </label>
                        </div>
                    </div>
                        </td>
                    </tr>
                @endif
            @endforeach
        @endforeach
    </table>
</div>
<div class="Titles_Container">
    <p class="Title">Current Playlist:</p>
</div>
<div class="Table_Container">
    <table id="TableOfPlaylistSongs" class="CurrentTable">
        <th>Songs Name</th>
        <th>Artists Name</th>
        <th>Songs Genre</th>
        <th>Action</th>
    </table>
</div>
<div class="Info_Container">
    <p id="InfoValue"class="Info">You Currently Have An Empty Playlist, Click Plus To Add Songs To Your Playlist</p>
</div>
<div id="formPos" class="Form_bg">
<form id="PlaylistCreateForm" method='POST' action="{{ route('songs.playlist_store') }}">
    @csrf
    @method('POST')
            <div class="Form_Pos">
                <p class="Title">Lastly What Would You Call Your Playlist:</p>
                <div class="inputBox">
                <input id="PlaylistsName" type='text'name="Playlists_Name"></input>
                <span>Playlists Name:</span>
                </div>
                        <a id="fixMargin" class="SideBarButtons" style="--clr: #800000" onclick="HideForm()"><span>Return</span></a>
                        <a class="SideBarButtons" style="--clr: #39FF14" onclick="SubmitValidation()"><span>Submit</span></a>
                </div>
        </div>
        <input type='text' id="List" style="display: none; opacity: 0;" name="ListOfSongs"></input>
    </form>
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

    <div id="Error" class="Form_bg2">
            <div class="Error_Pos">
                <p id="ErrorTitle" class="Title">Error Message:</p>
            </div>
            <div>
                <p id="ErrorMessage" class="Title">Something Went Wrong</p>
            </div>
    </div>

<script>

window.onload = function() {
    onload_required(<?php echo json_encode(Auth::user()->Liked_Songs); ?>, <?php echo json_encode($array); ?>);
};

</script>
</body>
</html>