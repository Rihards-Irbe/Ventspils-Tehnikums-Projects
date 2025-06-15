<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<style>

    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }

    body{
        background-color: #1d2b3a;
    }

    .Titles_Container{
            width: inherit;
            padding: 5px;
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: justify;
            -webkit-justify-content: center;
            -ms-flex-pack: justify;
    }

    .Title{
            color: white;
            font-size: 35px;
            margin-bottom: 10px;
            font-weight: bold;
    }

    .Table_Container {
    width: inherit;
    margin-left: 4%;
    padding: 5px;
    display: flex;
    justify-content: center;
    }

    #Table {
    margin-bottom: 6%;
    border-collapse: collapse;
    width: 80%;

    }


    #Table td,
    #Table th {
    border-top: 3px solid #ddd;
    border-bottom: 3px solid #ddd;
    font-size: 23px;
    font-weight: bold;
    padding-left: 8px;
    padding-top: 24px;
    padding-bottom: 24px;
    color: white;
    }

    #Table tr:hover{
    background-color: #5D8AA8;
    cursor: pointer;
    }

    #Table th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #0a2351;
    color: red;
    }

    .SideBar{
        position: fixed;
        width: 7%;
        top: 0;
        bottom: 0;
        overflow: auto;
        padding: 5px;
        display: flex;
        align-items: top;
        flex-direction: column;
        background: linear-gradient(180deg, #36454F, #010127);
    }

    .SideBarButtons{
        display: block;
        border: 3px solid white;
        background: #191C27;
        top: 20px;
        color: #fff;
        text-decoration: none;
        text-transform: uppercase;
        font-size: 1.5em;
        letter-spacing: 0.1em;
        font-weight: 400;
        padding: 10px 30px;
        transition: 0.5s;
        margin-bottom: 15px;
        cursor: pointer;
        width: 100%;
        text-align: center;
    }

    .SideBarButtons:hover{
        background: var(--clr);
        box-shadow: 0 0 35px var(--clr);
    }

    .SideBarButtons:before{
        content: '';
        inset: 2px;
        background: #27282c;
    }

    .SideBarButtons span{
        position: relative;
        z-index: 1;
    }

    .VolumeSlider {
    position: absolute;
    bottom: 0;
    width: 100%;
    transform: translateY(100%);
    transition: transform 0.3s ease;
    }

    .controls .slider-visible {
    transform: translateY(0);
    }

    .VolumeSliderContainer {
    overflow: hidden;
    display: flex;
    transition: all 0.5s ease-in-out;
    opacity: 0;
    }
    </style>

    <div class = "Titles_Container">
    <p class="Title">List Of Accounts (Admin Only)</p>
    </div>
    <div class="Table_Container">
    <table id="Table">
            <tr>
                <th>id</th>
                <th>Username</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            @foreach($users as $user)
            <tr>
                <td onclick="redirect('{{ route('songs.admin_user_view', ['id' => $user->id]) }}')">{{$user->id}}</td>
                <td onclick="redirect('{{ route('songs.admin_user_view', ['id' => $user->id]) }}')">{{$user->username}}</td>
                <td onclick="redirect('{{ route('songs.admin_user_view', ['id' => $user->id]) }}')">{{$user->email}}</td>
                <td>
                    <button onclick="redirect('{{ route('songs.admin_user_login', ['id' => $user->id]) }}')">Enter Account</button>
                    <form id="deleteForm{{$user->id}}" action="{{ route('songs.admin_user_delete', ['id' => $user->id]) }}" method="POST">
                        @csrf
                        @method('DELETE')
                        <button type="button" onclick="deleteUser({{$user->id}}, '{{$user->username}}')">Delete Account</button>
                    </form>
                </td>
            </tr>
            @endforeach
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
</div>

<script>

function redirect(url){
    window.location.href = url;
}

function deleteUser(userId, username) {
    if (confirm(`The data for ${username} will be lost forever, are you sure?`)) {
        var form = document.getElementById("deleteForm" + userId);
        form.submit();
    }
}

</script>

</body>
</html>