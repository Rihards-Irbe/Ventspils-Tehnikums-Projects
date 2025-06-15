<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/public/liked_playlist_dependencies/styles/liked_playlist_styles.css">
    <script src="/public/liked_playlist_dependencies/js/liked_playlist_scripts.js"></script>
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
    <path fill="#9b111e" class="out-bottom-custom" d="M105.9,48.6c-12.4-8.2-29.3-4.8-39.4.8-23.4,12.8-37.7,51.9-19.1,74.1s63.9,15.3,76-5.6c7.6-13.3,1.8-31.1-2.3-43.8C117.6,63.3,114.7,54.3,105.9,48.6Z"/>
    <path fill="#CB373D" class="in-bottom-custom" d="M102,67.1c-9.6-6.1-22-3.1-29.5,2-15.4,10.7-19.6,37.5-7.6,47.8s35.9,3.9,44.5-12.5C115.5,92.6,113.9,74.6,102,67.1Z"/>
</svg>
<div class="Titles_Container">
    <p class="Title">Songs That You've Liked</p>
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
                        <label>
                            <input type="checkbox" name="liked_songs[]" id="check{{ $song->id }}"
                        {{ in_array($song->id, Auth::user()->Liked_Songs) ? 'checked' : '' }}
                        onchange="likefunc('{{ $song->id }}')">
                                <span><i class="fa-regular fa-heart"></i></span>
                            </label>
                        <audio class="AudioClass" id="Song{{$song->id}}" src="/public/Songs_Folder/{{ $song->Files_Name }}"></audio>
                        <div class="PlayButton" id="PlayButtonWithId{{$song->id}}" onclick="Play({{$song->id}})">
                                <div class="bottom"></div>
                                <div class="icon">
                                    <div class="left_side"></div>
                                    <div class="right_side"></div>
                                </div>
                                <div class="pointer"></div>
                            </div>
                        </div>
                    </div>
                        </td>
                    </tr>
                @endif
            @endforeach
        @endforeach
    </table>
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
<div id="AudioContainerAnimation" class="AudioControls">
    <div class="DurationContainer">
        <input type="range" value="0" style="z-index: 2;" id="SongsDurationBar" class="DurationSlider">
    </div>
    <div class="DurationTimerContainer">
        <p id="CurrentDuration"></p>
        <p id="TotalDuration"></p>
    </div>
    <div class="Buttons">
            <i onclick="Shuffle()" id="ShuffleButton" class="fa-solid fa-shuffle"></i>
            <i id="Skip_Left" onclick="PreviousSong()" class="fa-solid fa-circle-chevron-right"></i>
        <div id="AudioControlsPlayButton" class="PlayButton" onclick="PlayCurrentSong()">
            <div class="bottom"></div>
            <div class="icon">
                <div class="left_side"></div>
                <div class="right_side"></div>
            </div>
            <div class="pointer"></div>
    </div>
            <i id="Skip_Right" onclick="NextSong()" class="fa-solid fa-circle-chevron-right"></i>
            <i id="RepeatButton" onclick="Repeat()" class="fa-solid fa-repeat"></i>
    </div>
</div>

<script>

window.onload = function() {
    onload_required(<?php echo json_encode(Auth::user()->Liked_Songs); ?>, <?php echo json_encode($array); ?>);
};

</script>
</body>
</html>
