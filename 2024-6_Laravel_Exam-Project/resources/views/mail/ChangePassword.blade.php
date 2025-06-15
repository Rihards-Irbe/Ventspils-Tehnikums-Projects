<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<style>
/* https://www.youtube.com/watch?v=828Qsr7-I0g&t=4s */
/* https://www.youtube.com/watch?v=BMphVl9suxA */
/* https://www.youtube.com/watch?v=wuUSVEcK-kM */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

.inputBox {
    position: relative;
    width: 340px;
    margin-bottom: 15px;
}

.inputBox input {
    width: 100%;
    padding: 10px;
    border: 1px solid rgba(255, 255, 255, 0.25);
    background: #1d2b3a;
    border-radius: 5px;
    outline: none;
    color: #fff;
    font-size: 1em;
}

.inputBox span {
    position: absolute;
    left: 0;
    padding: 10px;
    pointer-events: none;
    font-size: 1em;
    color: rgba(255, 255, 255, 0.25);
    text-transform: uppercase;
    transition: 0.5s;
}

.inputBox input.focused ~ span {
    color: #48A860;
    transform: translateX(10px) translateY(-7px);
    font-size: 0.65em;
    padding: 0 10px;
    background: #1d2b3a;
    border-left: 1px solid #48A860;
    border-right: 1px solid #48A860;
    letter-spacing: 0.2em;
}

.inputBox input.focused{
    border: 2px solid #48A860;
}

.inputBox input:focus ~ span {
    color: #48A860;
    transform: translateX(10px) translateY(-7px);
    font-size: 0.65em;
    padding: 0 10px;
    background: #1d2b3a;
    border-left: 1px solid #48A860;
    border-right: 1px solid #48A860;
    letter-spacing: 0.2em;
}

.inputBox input:focus{
    border: 2px solid #48A860;
}

    .button-link {
        padding: 5px 10px;
        background-color: gray;
        color: #fff;
        text-decoration: none;
        border-radius: 5px;
    }

    body{
        margin: 0;
        padding: 0;
        min-height: 100vh;
        background-color: #0c192c;
        overflow-y: hidden;
        overflow: hidden;
    }

    .container {
    position: relative;
    width: 100%;
    min-height: 100vh;
    overflow: hidden;
}
    .bubbles{
        position: relative;
        display: flex;
        filter: blur(8px);
    }
    .bubbles span{
        position: relative;
        width: 30px;
        height: 30px;
        background: #50C878;
        margin: 0 4px;
        border-radius: 50%;
        box-shadow: 0 0 0 10px #043927,
        0 0 50px #50C878,
        0 0 100px #50C878;
        animation: animateB 15s linear infinite;
        animation-duration: calc(300s / var(--i));
    }
    .bubbles span:nth-child(even){
        background: #7CB9E8;
        box-shadow: 0 0 0 10px  #00308F,
        0 0 50px #7CB9E8,
        0 0 100px #7CB9E8;
    }

    @keyframes animateB{
        0%{
            transform: translateY(100vh) scale(0);
        }
        100%{
            transform: translateY(-10vh) scale(1);
        }
    }

    .block{
        position: relative;
        margin: 10% auto 0;
        width: 30%;
        height: 700px;
        border-radius: 3.3%;
        background: linear-gradient(180deg, #1d2b3a, #0e1821);
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    .block > * {
        margin-top: -20px;
    }

    .glow::before, .glow::after{
        content: '';
        position: absolute;
        left: -2px;
        top: -2px;
        border-radius: 3.3%;
        background: linear-gradient(45deg, #50C878, #39FF14, #00A86B, #50C878, #138808, #7CB9E8, #6CB4EE, #007FFF, #0066b2);
        background-size: 400%;
        width: calc(100% + 5px);
        height: calc(100% + 5px);
        z-index: -2;
        animation: animate 60s linear infinite;
    }

    @keyframes animate{
        0%{
            background-position: 0 0;
        }
        50%{
            background-position: 400% 0;
        }
        100%{
            background-position: 0 0;
        }
    }

    .glow::after{
        filter: blur(30px);
        opacity: 0.99;
    }

    .buttonStyle{
        position: relative;
        background: #fff;
        top: 20px;
        color: #fff;
        text-decoration: none;
        text-transform: uppercase;
        font-size: 1.5em;
        letter-spacing: 0.1em;
        font-weight: 400;
        padding: 10px 30px;
        transition: 0.5s;
        margin-right: 10px;
        cursor: pointer;
    }

    .buttonStyle:hover{
        background: var(--clr);
        color: var(--clr);
        letter-spacing: 0.25em;
        box-shadow: 0 0 35px var(--clr);
    }

    .buttonStyle:before{
        content: '';
        position: absolute;
        inset: 2px;
        background: #27282c;
    }

    .buttonStyle span{
        position: relative;
        z-index: 1;
    }

    .buttonStyle i{
        position: absolute;
        inset: 0;
        display: block;
    }
    
    .buttonStyle i::before{
        content: '';
        position: absolute;
        top: 0;
        left: 80%;
        width: 10px;
        height: 4px;
        background: #27282c;
        transform: translateX(-50%) skewX(325deg);
        transition: 0.5s;
    }

    .buttonStyle:hover i::before{
        width: 20px;
        left: 20%;
    }

    .buttonStyle i::after{
        content: '';
        position: absolute;
        bottom: 0;
        left: 20%;
        width: 10px;
        height: 4px;
        background: #27282c;
        transform: translateX(-50%) skewX(325deg);
        transition: 0.5s;
    }

    .buttonStyle:hover i::after{
        width: 20px;
        left: 80%;
    }

    .Error{
        transition: 0.5s;
        position: relative;
        font-size: 20px;
        letter-spacing: 0.1em;
        font-weight: 200;
        animation: animateC 5s linear forwards;
        margin-bottom: 20px;
    }

    @keyframes animateC{
        0%{
            color: #27282c;
        }
        100%{
            color: #ff1867;
            text-shadow: 0 0 10px #ff1867, 0 0 20px #ff1867, 0 0 30px #ff1867;
        }
    }

    #LoginTitle {
        position: relative;
        top: -10%;
        font-size: 40px;
        letter-spacing: 0.1em;
        font-weight: 600;
        color: #1d2b3a;
        animation: animateD 10s linear infinite;
    }

    
    @keyframes animateD{
        0%{
            color: #00dfc4;
            text-shadow: 0 0 4px #00665c, 0 0 8px #00665c, 0 0 12px #00665c;
        }
        50%{
            color: #00dfc4;
            text-shadow: 0 0 10px #00dfc4, 0 0 20px #00dfc4, 0 0 30px #00dfc4;
        }
        100%{
            color: #00dfc4;
            text-shadow: 0 0 4px #00665c, 0 0 8px #00665c, 0 0 12px #00665c;
        }
    }

    @keyframes slideIn {
        from {
            opacity: 0;
            transform: translateY(0%);
        }
        to {
            opacity: 1;
            transform: translateY(-136%);
        }
    }

    @keyframes slideOut {
    from {
        opacity: 1;
        transform: translateY(-136%);
    }
    to {
        opacity: 0;
        transform: translateY(0%);
    }
}

    #EmailForm {
        animation: slideIn 1s forwards;
        z-index: 1;
    }

    .pop {
    position: relative;
    z-index: 1;
    overflow: hidden;
    margin: 10% auto 0;
    max-width: 30%;
    height: 700px;
    border-radius: 3.3%;
    background: linear-gradient(180deg, #1d2b3a, #0e1821);
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 7% 7%;
    display: none;
}

#UpdateTitle {
    position: relative;
    top: -10%;
    font-size: 40px;
    letter-spacing: 0.1em;
    font-weight: 600;
    color: #ff2d75;
    animation: animateE 6s linear infinite;
    }

@keyframes animateE {
    0%{
            color: #50C878;
            text-shadow: 0 0 4px #50C878, 0 0 8px #50C878, 0 0 12px #50C878;
        }
        50%{
            color: #39FF14;
            text-shadow: 0 0 15px #39FF14, 0 0 25px #39FF14, 0 0 35px #39FF14;
        }
        100%{
            color: #50C878;
            text-shadow: 0 0 4px #50C878, 0 0 8px #50C878, 0 0 12px #50C878;
        }
    }

.Success{
        transition: 0.5s;
        position: relative;
        font-size: 20px;
        letter-spacing: 0.1em;
        font-weight: 200;
        animation: animateF 5s linear forwards;
        margin-bottom: 20px;
}
    @keyframes animateF{
        0%{
            color: #50C878;
        }
        100%{
            color: #39FF14;
            text-shadow: 0 0 10px #39FF14, 0 0 20px #39FF14, 0 0 30px #39FF14;
        }
    }

    label{
    position: relative;
    top: 1px;
    width: 30px;
    height: 30px;
    border: 1px solid #121212;
    border-radius: 3px;
    cursor: pointer;
    display: block;
    background-color: #1d2b3a;
}

label:after{
    content: "";
    position: absolute;
    top: 3px;
    left: 10px;
    width: 7px;
    height: 14px;
    border-right: 2px solid white;
    border-bottom:2px solid white;
    opacity: 0;
    transition: all 0.3s ease;
    transition-delay: 0.15s;
    transform: rotate(45deg) scale(0);
}

#cbx:checked ~ label{
    background-color: #29AB87;
    animation: checky 0.5s ease;
}

#cbx:checked ~ label::after{
    opacity: 1;
    transform: rotate(45deg) scale(1);
}

#cbx{
    display: none;
}

@keygrames checky{
    from{
        transform: scale(1, 1);
    }
    30%{
        transform: scale(1.3, 0.75)
    }
    40%{
        transform: scale(0.75, 1.2)
    }
    to{
        transform: scale(1, 1)
    }
}

#Label_Pos {
    position: relative;
    margin-left: 40px;
    margin-top: -27px;
    top: 64%;
    color: white;
}


</style>
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
<p id="UpdateTitle">Password Reset</p>

@if(session('success'))
    <div class="Success">
        {{ session('success') }}
    </div>
@endif
    <div class="Error">
            <p id="Errors"> </p>
    </div>
<form id="UpdateForm" method='POST' action="{{ route('mail.update') }}">
    @csrf
    @method('PUT')
    <div class="inputBox">
        <input type='text' name="username" disabled value="{{$user->username}}"></input>
        <span></span>
    </div>
    <div class="inputBox">
    <input type='text' name="email" disabled value="{{$user->email}}"></input>
        <span></span>
    </div>
    <div class="inputBox">
    <input type='password' id="Password1" onchange="freezeAnimation('Password1')" name="password"></input>
        <span>Password</span>
    </div>
    <div class="inputBox">
    <input type='password' id="Password2" onchange="freezeAnimation('Password2')"  name="password_confirmation">
        <span>Re-Enter Password</span>
    </div>
    <main>
        <input type='checkbox' disabled id="cbx"name="isArtist">
        <label for="cbx"></label>
        <p id="Label_Pos">Are You An Artist?</p>
    </main>
    <div>
        <a class="buttonStyle" style="--clr: #ff1867" href="{{ route('songs.login')}}"><span>Return</span><i></i></a>
        <a class="buttonStyle" style="--clr: #6eff3e" onclick="submitForm()" type='submit'><span>Save</span><i></i></a>
    </div>
</form>
</div>

<script>
if ("{{$user->isArtist }}" == 1) {
    document.getElementById("cbx").checked = true;
}

function submitForm(){
    if(document.getElementById("Password1").value.length < 6){
        Errors.innerHTML = "Password Too Short"
    }else if(document.getElementById("Password1").value != document.getElementById("Password2").value){
        Errors.innerHTML = "Passwords Doesn't Match"
    }else{
        document.getElementById("UpdateForm").submit();
    }
}

function PasswordRegex(){
    if(document.getElementById("")){
        Errors.innerHTML = "Password Too Short"
    }
}

function freezeAnimation(id) {
    const inputElement = document.getElementById(id);

        if (inputElement.value !== "") {
            inputElement.classList.add("focused");
        } else {
            inputElement.classList.remove("focused");
        }
}

function checkInputFields(){
    freezeAnimation('input_username')
    freezeAnimation('input_email')
}

</script>

</body>
</html>