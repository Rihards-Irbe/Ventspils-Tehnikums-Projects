<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/public/aboutus_dependencies/styles/aboutus_styles.css">
    <script src="/public/aboutus_dependencies/js/aboutus_scripts.js"></script>
</head>
<body>
<div class="Field_Container">
    <div class="Field1" id="Field1" onmouseover="toggleFadeIn()" onmouseout="toggleFadeOut()">
        <div id="Titles_Container" class="Titles_Container">
            <p class="Page_Titles">About Us</p>
        </div>
        <div class="Paragraph_Container" id="Paragraph_Container">
            <p class="Page_Paragraphs">
                Welcome to our music listening website, where the rhythm of your life finds its perfect beat! Dive into a world of endless melodies and harmonies, curated just for you. With our intuitive platform, you can create, edit, and remove playlists with ease, sculpting your musical journey to perfection. But the fun doesn't stop there - explore the diverse playlists crafted by fellow music enthusiasts, discovering new favorites with every click. Feel like sharing your own tunes? Upload your songs for free and let your creativity echo through the digital airwaves. Whether you're into chart-topping hits, indie gems, or timeless classics, we've got you covered with a vast library spanning various genres and eras. Join us on this sonic adventure, where every note resonates with passion and possibility.
            </p>
        </div>
    </div> 
    <div class="Field2" id="Field2" onmouseover="toggleFadeIn2()" onmouseout="toggleFadeOut()">
        <div id="Titles_Container2" class="Titles_Container">
            <p class="Page_Titles">Helpful Tips</p>
        </div>
        <div class="Paragraph_Container" id="Paragraph_Container2">
            <img src="/public/aboutus_dependencies/Img_Folder/SideBar.png">
            <ul id="List_Positioning" class="Page_Paragraphs">
                <li>Main Page - Redirects You To The Main Page Of Rihify</li>
                <li>Your Account - Edit Your Account Details Here</li>
                <li>About Us - A Brief Explenation On What This Website Is About</li>
                <li>Logout - Exits You From Your Current Account</li>
                <li>Upload - Share Songs With Other Users Of This Website</li>
                <li>Volume - Volume Slider</li>
            </ul>
        </div>
    </div>
    <div class="Field3" id="Field3" onmouseover="toggleFadeIn3()" onmouseout="toggleFadeOut()">
        <div id="Titles_Container3" class="Titles_Container">
            <p class="Page_Titles">Contact Us</p>
        </div>
        <div class="Paragraph_Container" id="Paragraph_Container3">
            <form method='POST' action="{{ route('mail.support') }}">
            @csrf
            @method('POST')     
            <input name="name"  style="pointer-events: auto;" type="text" class="feedback-input" placeholder="Your Name" />   
            <input name="email"  style="pointer-events: auto;" type="text" class="feedback-input" placeholder="Your Email" />
            <textarea name="text"  style="pointer-events: auto;" class="feedback-input" placeholder="Comment"></textarea>
            <input type="submit"  style="pointer-events: auto;" value="SEND"/>
            </form>
        </div>
    </div>
</div>
<div class="SideBar" onmouseenter="toggleFadeOut()">
    <a class="SideBarButtons" style="--clr: #2774AE" onclick="likeBeforeRedirect('{{ route('songs.index') }}')"><span><i class="fa-solid fa-house"></i></span></a>
    <a class="SideBarButtons" style="--clr: #4FFFB0" onclick="likeBeforeRedirect('{{ route('songs.user_edit', ['user' => Auth::id()]) }}')"><span><i class="fa-solid fa-user"></i></span></a>
    <a class="SideBarButtons" style="--clr: #FFD700" onclick="likeBeforeRedirect('{{ route('songs.aboutus')}}')"><span><i class="fa-solid fa-question"></i></span></a>
    <a class="SideBarButtons" style="--clr: #660000" onclick="likeBeforeRedirect('{{ route('songs.user_logout')}}')"><span><i class="fa-solid fa-right-from-bracket"></i></span></a>
    @if(Auth::check() && Auth::user()->isArtist)
    <a class="SideBarButtons" style="--clr: #9370DB" onclick="likeBeforeRedirect('{{ route('songs.create') }}')"><span><i class="fa-solid fa-upload"></i></span></a>
    @endif
    @if(Auth::check() && Auth::user()->isAdmin)
    <a class="SideBarButtons" style="--clr: #E26310" onclick="likeBeforeRedirect('{{ route('songs.admin')}}')"><span><i class="fa-solid fa-user-tie"></i></span></a>
    @endif
    <a id="GetWidthValue" class="SideBarButtons" style="--clr: #39FF14" onclick="VolumeSliderAppear()"><span><i class="fa-solid fa-volume-high"></i></span></a>
    <div class="VolumeSliderContainer">
        <input type="range" class="VolumeSlider2.0" oninput="changeGlobalVolume2()" id="volumeSlider" min="0" value="1" max="1" step="0.1">
    </div>
</div>
</body>
</html>