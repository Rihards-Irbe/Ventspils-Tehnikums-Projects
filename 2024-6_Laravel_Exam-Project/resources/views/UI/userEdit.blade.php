<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/public/user_edit_dependencies/styles/user_edit_styles.css">
    <script src="/public/user_edit_dependencies/js/user_edit_scripts.js"></script>
</head>
<body>
<div class="container">
    <div class="bubbles">
        <span style="--i:11;"></span>
        <span style="--i:12;"></span>
        <span style="--i:24;"></span>
        <span style="--i:10;"></span>
        <span style="--i:14;"></span>
        <span style="--i:23;"></span>
        <span style="--i:18;"></span>
        <span style="--i:16;"></span>
        <span style="--i:19;"></span>
        <span style="--i:20;"></span>
        <span style="--i:22;"></span>
        <span style="--i:25;"></span>
        <span style="--i:18;"></span>
        <span style="--i:21;"></span>
        <span style="--i:15;"></span>
        <span style="--i:13;"></span>
        <span style="--i:26;"></span>
        <span style="--i:17;"></span>
        <span style="--i:13;"></span>
        <span style="--i:28;"></span>
        <span style="--i:11;"></span>
        <span style="--i:12;"></span>
        <span style="--i:24;"></span>
        <span style="--i:10;"></span>
        <span style="--i:14;"></span>
        <span style="--i:23;"></span>
        <span style="--i:18;"></span>
        <span style="--i:16;"></span>
        <span style="--i:19;"></span>
        <span style="--i:20;"></span>
        <span style="--i:22;"></span>
        <span style="--i:25;"></span>
        <span style="--i:18;"></span>
        <span style="--i:21;"></span>
        <span style="--i:15;"></span>
        <span style="--i:13;"></span>
        <span style="--i:26;"></span>
        <span style="--i:17;"></span>
        <span style="--i:13;"></span>
        <span style="--i:28;"></span>
        <span style="--i:11;"></span>
        <span style="--i:12;"></span>
        <span style="--i:24;"></span>
        <span style="--i:10;"></span>
        <span style="--i:14;"></span>
        <span style="--i:23;"></span>
        <span style="--i:18;"></span>
        <span style="--i:16;"></span>
        <span style="--i:19;"></span>
        <span style="--i:20;"></span>
        <span style="--i:22;"></span>
        <span style="--i:25;"></span>
        <span style="--i:18;"></span>
        <span style="--i:21;"></span>
        <span style="--i:15;"></span>
        <span style="--i:13;"></span>
        <span style="--i:26;"></span>
        <span style="--i:17;"></span>
        <span style="--i:13;"></span>
        <span style="--i:28;"></span>
        <span style="--i:21;"></span>
        <span style="--i:15;"></span>
        <span style="--i:13;"></span>
        <span style="--i:26;"></span>
        <span style="--i:17;"></span>
        <span style="--i:13;"></span>
        <span style="--i:28;"></span>
    </div>
<div class="block glow">
<p id="UpdateTitle">Update User Data</p>

@if(session('success'))
    <div class="Success">
        {{ session('success') }}
    </div>
@endif
@if($errors->any())
    <div>
        <ul>
            @foreach ($errors->all() as $error)
                <li class="Error">{{ $error }}</li>
            @endforeach
        </ul>
    </div>
@endif
<form id="UpdateForm" method='POST' action="{{ route('songs.user_update', ['user' => $Current_User->id]) }}">
    @csrf
    @method('PUT')
    <div class="inputBox">
        <input type='text' name="username" id= "input_username" onchange="freezeAnimation('input_username')" value="{{$Current_User->username}}"></input>
        <span>Username</span>
    </div>
    <div class="inputBox">
    <input type='text' name="email" id= "input_email" onchange="freezeAnimation('input_email')" value="{{$Current_User->email}}"></input>
        <span>Username</span>
    </div>
    <div class="inputBox">
    <input type='password' id="Password1" onchange="freezeAnimation('Password1')" name="password"></input>
        <span>Password</span>
    </div>
    <div class="inputBox">
    <input type='password' id="Password2"  onchange="freezeAnimation('Password2')" name="password_confirmation">
        <span>Re-Enter Password</span>
    </div>
    <main>
        <input type='checkbox' id="cbx"name="isArtist">
        <label for="cbx"></label>
        <p id="Label_Pos">Are You An Artist?</p>
    </main>
    <div>
        <a class="buttonStyle" style="--clr: #ff1867" href="{{ route('songs.index')}}"><span>Return</span><i></i></a>
        <a class="buttonStyle" style="--clr: #6eff3e" onclick="submitForm()" type='submit'><span>Save</span><i></i></a>
    </div>
</form>
</div>

<script>

window.onload = function() {
    onload_required(<?php echo $Current_User->isArtist; ?>);
};

</script>

</body>
</html>
