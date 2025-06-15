<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/public/create_dependencies/styles/create_styles.css">
    <script src="/public/create_dependencies/js/create_scripts.js"></script>
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
    <path fill="#32174D" class="out-top-custom" d="M37-5C25.1-14.7,5.7-19.1-9.2-10-28.5,1.8-32.7,31.1-19.8,49c15.5,21.5,52.6,22,67.2,2.3C59.4,35,53.7,8.5,37-5Z"/>
    <path fill="#301934" class="in-top-custom" d="M20.6,4.1C11.6,1.5-1.9,2.5-8,11.2-16.3,23.1-8.2,45.6,7.4,50S42.1,38.9,41,24.5C40.2,14.1,29.4,6.6,20.6,4.1Z"/>
    <path fill="#9867C5" class="out-bottom-custom" d="M105.9,48.6c-12.4-8.2-29.3-4.8-39.4.8-23.4,12.8-37.7,51.9-19.1,74.1s63.9,15.3,76-5.6c7.6-13.3,1.8-31.1-2.3-43.8C117.6,63.3,114.7,54.3,105.9,48.6Z"/>
    <path fill="#BE93E4" class="in-bottom-custom" d="M102,67.1c-9.6-6.1-22-3.1-29.5,2-15.4,10.7-19.6,37.5-7.6,47.8s35.9,3.9,44.5-12.5C115.5,92.6,113.9,74.6,102,67.1Z"/>
</svg>

<div class="Titles_Container">
    <p class="Page_Titles">Upload Your Song</p>
</div>
<div class="Div_Container">
        <form method='POST' action="{{ route('songs.store') }}" enctype="multipart/form-data">
            @csrf
            @method('POST')
        <div class="inputfield_container">
            <div class="inputBox">
                <input type='text' onchange="removeSpan('Song_Name')" id="Song_Name" name="Song_Name" value="{{ old('Song_Name') }}" autocomplete="off"/>
                <span id="Songs_Name_Span">Songs Name</span>
                @error('Song_Name')
                    <div>{{ $message }}</div>
                @enderror
            </div>

            <div class="inputBox">
                <input type='text' onchange="removeSpan('Artists_Name')" id="Artists_Name" value="{{ old('Artists_Name') }}" name="Artists_Name"/>
                <span id="Artists_Name_Span" >Artists Name</span>
                @error('Artists_Name')
                    <div>{{ $message }}</div>
                @enderror
            </div>

            <div class="inputBox">
                <input type='text' onchange="removeSpan('Songs_Genre')" id="Songs_Genre" value="{{ old('Songs_Genre') }}" name="Songs_Genre"/>
                <span id="Songs_Genre_Span">Songs Genre</span>
                @error('Songs_Genre')
                    <div>{{ $message }}</div>
                @enderror
            </div>

            <div class="inputFile">
                <input type='file' onchange="UploadSong()" id="file" name="Song" accept="audio/*"/>
                <label id="filesLabel" onmouseenter="changeIcon()" onmouseleave="changeBack()" for="file">Upload File</label>
                @error('Song')
                    <div id="song_error_pos">{{ $message }}</div>
                @enderror
            </div>
        </div>
            <div class="submit_button">
                <input class="button-37" type='submit' value="Submit"/>
            </div>
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
<script>

removeSpan("Song_Name"); //These will run when page loads, so that if the user doesn't meet the validation requirements and gets redirected back here I want to keep the animation frozen, since there will be old values inserted here
removeSpan("Artists_Name");
removeSpan("Songs_Genre");

document.getElementById("file").addEventListener("click", function (event) {
    if(alreadyUploaded == true){
    event.preventDefault();
    UploadSong()
    }
});

function removeSpan(id) {
    const inputElement = document.getElementById("Song_Name");
    const spanElement = document.getElementById("Songs_Name_Span");

    const inputElement2 = document.getElementById("Artists_Name");
    const spanElement2 = document.getElementById("Artists_Name_Span");

    const inputElement3 = document.getElementById("Songs_Genre");
    const spanElement3 = document.getElementById("Songs_Genre_Span");

    if (id === "Song_Name") {
        if (inputElement.value !== "") {
            inputElement.classList.add("focused");
        } else {
            inputElement.classList.remove("focused");
        }
    }else if(id === "Artists_Name"){
        if (inputElement2.value !== "") {
            inputElement2.classList.add("focused");
        } else {
            inputElement2.classList.remove("focused");
        }
    }else if(id === "Songs_Genre"){
        if (inputElement3.value !== "") {
            inputElement3.classList.add("focused");
        } else {
            inputElement3.classList.remove("focused");
        }
    }
}

</script>
</body>
</html>
