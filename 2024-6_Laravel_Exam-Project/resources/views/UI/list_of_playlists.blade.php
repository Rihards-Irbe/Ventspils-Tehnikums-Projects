<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/public/playlist_list_dependencies/styles/playlist_list_styles.css">
    <script src="/public/playlist_list_dependencies/js/playlist_list_scripts.js"></script>
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
    <path fill="#2a52be" class="out-top-custom" d="M37-5C25.1-14.7,5.7-19.1-9.2-10-28.5,1.8-32.7,31.1-19.8,49c15.5,21.5,52.6,22,67.2,2.3C59.4,35,53.7,8.5,37-5Z"/>
    <path fill="#4B9CD3" class="in-top-custom" d="M20.6,4.1C11.6,1.5-1.9,2.5-8,11.2-16.3,23.1-8.2,45.6,7.4,50S42.1,38.9,41,24.5C40.2,14.1,29.4,6.6,20.6,4.1Z"/>
    <path fill="#1F305E" class="out-bottom-custom" d="M105.9,48.6c-12.4-8.2-29.3-4.8-39.4.8-23.4,12.8-37.7,51.9-19.1,74.1s63.9,15.3,76-5.6c7.6-13.3,1.8-31.1-2.3-43.8C117.6,63.3,114.7,54.3,105.9,48.6Z"/>
    <path fill="#002fa7" class="in-bottom-custom" d="M102,67.1c-9.6-6.1-22-3.1-29.5,2-15.4,10.7-19.6,37.5-7.6,47.8s35.9,3.9,44.5-12.5C115.5,92.6,113.9,74.6,102,67.1Z"/>
</svg>
<div class="Titles_Container">
    <p class="Title">Search Results For {{$search_prompt}}</p>
    <div class="search-box">
        <button class="btn-search"><i onclick="search()" class="fas fa-search"></i></button>
        <input onkeydown="handleKeyPress(event)" type="text" id="search_inp" class="input-search" placeholder="Songs Name:">
    </div>
</div>
    <div class="Table_Container">
    <table id="Table">
    <th>Playlist Name</th>
    <th>Made By</th>
    @php $counter = 0; @endphp
    @if (count($results) > 0)
        @foreach ($results as $result)
            @if($counter++ % 2 == 0)
                <tr class="TableEven">
            @else
                <tr class="TableOdd">
            @endif
                <td onclick="redirect('{{ route('songs.playlist_listen', ['playlist' => $result->id]) }}')">{{ $result->name }}</td>
                <td onclick="redirect('{{ route('songs.playlist_listen', ['playlist' => $result->id]) }}')">{{ $result->playlists_owner}}</td>
            </tr>
        @endforeach
    @else
        <tr>
            <td colspan="4">No results found.</td>
        </tr>
    @endif
</table>
 </div>
 <div class="SideBar">
<a  class="SideBarButtons" style="--clr: #2774AE" onclick="redirect('{{ route('songs.index') }}')"><span><i class="fa-solid fa-house"></i></span></a>
<a  class="SideBarButtons" style="--clr: #4FFFB0" onclick="redirect('{{ route('songs.user_edit', ['user' => Auth::id()]) }}')"><span><i class="fa-solid fa-user"></i></span></a>
<a  class="SideBarButtons" style="--clr: #FFD700" onclick="redirect('{{ route('songs.aboutus')}}')"><span><i class="fa-solid fa-question"></i></span></a>
<a  class="SideBarButtons" style="--clr: #660000" onclick="redirect('{{ route('songs.user_logout')}}')"><span><i class="fa-solid fa-right-from-bracket"></i></i></span></a>
@if(Auth::check() && Auth::user()->isArtist)
    <a class="SideBarButtons" style="--clr: #9370DB" onclick="redirect('{{ route('songs.create') }}')"><span><i class="fa-solid fa-upload"></i></span></a>
@endif
@if(Auth::check() && Auth::user()->isAdmin)
<a  class="SideBarButtons" style="--clr: #E26310" onclick="redirect('{{ route('songs.admin')}}')"><span><i class="fa-solid fa-user-tie"></i></i></span></a>
@endif
<a  id="GetWidthValue" class="SideBarButtons" style="--clr: #39FF14" onclick="VolumeSliderAppear()"><span><i class="fa-solid fa-volume-high"></i></i></span></a>
<div class="VolumeSliderContainer">
    <input type="range" class="VolumeSlider2.0" oninput="changeGlobalVolume2()" id="volumeSlider" min="0" value="1" max="1" step="0.1">
  </div>
</div>
<script>

function handleKeyPress(event) {

if (event.key === 'Enter') {
    search()

}

}

function likeBeforeRedirect(targetUrl) {
    window.location.href = targetUrl;

}

function search(type){
    var searchInputValue = document.getElementById("search_inp").value;
    var routeUrl = "{{ route('songs.playlist_search', ['search_prompt' => ':searchInputValue']) }}";
    routeUrl = routeUrl.replace(':searchInputValue', searchInputValue);
    likeBeforeRedirect(routeUrl);

}

</script>
</body>
</html>