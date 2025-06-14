<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Montserrat', sans-serif; /*varbūt vajadzēs samainīt mājaslapas fontu, šeit: https://fonts.googleapis.com*/
        }

        .input-box {
            position: relative;
            width: 340px;
            margin-bottom: 15px;
        }

        .input-box input {
            width: 100%;
            padding: 10px;
            border: 3px solid black;
            border-radius: 5px;
            font-size: 25px;
        }

        .input-box input:focus{
            border: 3px solid green;
        }

        body{
            background-color: white;
        }

        .block{
            position: relative;
            width: 30%;
            left: 35%;
            margin-top: 13%;
            height: 700px;
            border-radius: 4%;
            background: gray;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        /*https://getcssscan.com/css-buttons-examples*/
        /* CSS */
        .login-button {
            background-color: #13aa52;
            border: 1px solid #13aa52;
            border-radius: 4px;
            box-shadow: rgba(0, 0, 0, .1) 0 2px 4px 0;
            box-sizing: border-box;
            color: #fff;
            cursor: pointer;
            font-family: "Akzidenz Grotesk BQ Medium", -apple-system, BlinkMacSystemFont, sans-serif;
            font-size: 16px;
            font-weight: 400;
            outline: none;
            outline: 0;
            padding: 10px 25px;
            text-align: center;
            transform: translateY(0);
            transition: transform 150ms, box-shadow 150ms;
            user-select: none;
            -webkit-user-select: none;
            touch-action: manipulation;
        }

        .login-button:hover {
            box-shadow: rgba(0, 0, 0, .15) 0 3px 9px 0;
            transform: translateY(-2px);
        }

        @media (min-width: 768px) {
            .login-button {
                padding: 10px 30px;
            }
        }

        .error-msg {
            position: relative;
            font-size: 18px;
            margin-bottom: 20px;
            color: red;
        }

        .login-title {
            position: relative;
            top: -10%;
            font-size: 40px;
            color: white;
        }

        .success-msg {
            position: relative;
            font-size: 20px;
            margin-bottom: 20px;
            color: lime;
        }
    </style>
</head>
<body>
<div class="block">
    <p class="login-title">Login</p>
    <!--ja sesijas laikā būs success, jeb users tiks izveidots pareizi, tad tas tiks displayots-->
    @if(session('success'))
        <div class="success-msg">
            {{ session('success') }}
        </div>
    @endif
    <!--ja sesijas laikā būs errori, tad tie tiks displayoti-->
    @if($errors->any()) <!--ja sesijas laikā būs errori, tad tie tiks displayoti-->
        @foreach ($errors->all() as $error)
            <div class="error-msg">
                <ul>
                    <li>{{ $error }}</li>
                </ul>
            </div>
        @endforeach
    @endif
    <!--iet cauri katram erroram un to vcr accessot ar $error-->
    <form id="form" method='POST' action="{{ route('loginUser') }}"> <!--sūtīs post requestu uz {{ route('loginUser') }} no web.php-->
        @csrf
        @method('POST')
        <div class="input-box">
            <input type='text' placeholder="Username" name="username">
        </div>
        <div class="input-box">
            <input type='password' placeholder="Password" name="password">
        </div>
        <div>
            <button class="login-button" role="button" type="button" onclick="redirect('{{ route('register')}}')">Register</button>
            <button class="login-button" role="button" onclick="submitForm()" type='submit'>Login</button>
        </div>
    </form>
</div>

<script>
    function submitForm() {
        document.getElementById("login-form").submit();
    }

    function redirect(url){
        window.location.href = url
    }
</script>

</body>
</html>