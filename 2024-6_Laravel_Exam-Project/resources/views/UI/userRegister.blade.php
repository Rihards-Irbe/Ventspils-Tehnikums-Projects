<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/public/user_register_dependencies/styles/user_register_styles.css">
    <script src="/public/user_register_dependencies/js/user_register_scripts.js"></script>
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
<p id="RegisterTitle">Register</p>
    <div>
@if($errors->any())
    <div>
        <ul>
            @foreach ($errors->all() as $error)
                <li class="Error">{{ $error }}</li>
            @endforeach
        </ul>
    </div>
@endif
    </div>
<form id="RegisterForm" method='POST' action="{{ route('songs.createUser') }}">
    @csrf
    @method('POST')
    <div id="FixErrorPos" class="inputBox">
        <input type='text' name="username" id="input_username" onchange="freezeAnimation('input_username')" value="{{ old('username') }}"></input>
        <span>Username</span>
    </div>
    <div class="inputBox">
        <input type='text' name="email" id="input_email" onchange="freezeAnimation('input_email')" value="{{ old('email') }}"></input>
        <span>E-Mail</span>
    </div>
    <div class="inputBox">
        <input type='password' id="input_password" onchange="freezeAnimation('input_password')" name="password"></input>
        <span>Password</span>
    </div>
    <div class="inputBox">
        <input type='password' id="input_password_confirmation" onchange="freezeAnimation('input_password_confirmation')" name="password_confirmation"></input>
        <span>Re-Enter Password</span>
    </div>
    <main>
        <input type='checkbox' id="cbx"name="isArtist">
        <label for="cbx"></label>
        <p id="Label_Pos">Are You An Artist?</p>
    </main>
    <div>
        <a class="buttonStyle" style="--clr: #ff1867" href="{{ route('songs.login')}}"><span>Return</span><i></i></a>
        <a class="buttonStyle" style="--clr: #6eff3e" onclick="submitForm()"><span>Register</span><i></i></a>
    </div>
</form>
</div>

<script>

window.onload = function() {
        onload_required();
};

</script>

</body>
</html>