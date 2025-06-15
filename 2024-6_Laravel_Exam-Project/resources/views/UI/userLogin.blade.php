<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="/public/user_login_dependencies/styles/user_login_styles.css">
    <script src="/public/user_login_dependencies/js/user_login_scripts.js"></script>
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
<p id="LoginTitle">Login</p>

@if(session('success'))
    <div class="Success">
        {{ session('success') }}
    </div>
@endif

@if($errors->any())
    @foreach ($errors->all() as $error)
    <div class="Error">
        <ul>
            <li>{{ $error }}</li>
        </ul>
    </div>
    @endforeach
@endif


<form id="Login_Form" method='POST' action="{{ route('songs.loginUser') }}">
    @csrf
    @method('POST')
    <div class="inputBox">
        <input type='text' id="input_username" onchange="freezeAnimation('input_username')" name="username"></input>
        <span>Username</span>
    </div>
    <div class="inputBox">
        <input type='password' id="input_password" onchange="freezeAnimation('input_password')" name="password"></input>
        <span>Password</span>
    </div>
    <div>
        <a href="#" id="ForgotHref" onclick="ShowEmailForm()" return false;>Forgot Your Password?</a>
    </div>
    <div>
        <a class="buttonStyle" style="--clr: #ff1867" href="{{ route('songs.register')}}"><span>Register</span><i></i></a>
        <a class="buttonStyle" style="--clr: #6eff3e" onclick="submitForm()" type='submit'><span>Login</span><i></i></a>
    </div>
</form>
</div>
<div id="EmailForm" style="display: none;" class="pop">
    <form id="EmailSubmit" class="email-form"method='POST' action="{{ route('mail') }}">
    @csrf
    @method('POST')
    <div>
    <p id="ResetTitle">Password Reset</p>
    </div>
    <div class="inputBox">
        <input type='text' name="email"></input>
        <span>E-Mail</span>
    </div>
    <div>
    <a class="buttonStyle" style="--clr: #ff1867" onclick="HideEmailForm()"><span>Return</span><i></i></a>
    <a class="buttonStyle" style="--clr: #6eff3e" onclick="submitForm2()"><span>Submit</span><i></i></a>
    </div>
    </form>
</div>
</body>
</html>
