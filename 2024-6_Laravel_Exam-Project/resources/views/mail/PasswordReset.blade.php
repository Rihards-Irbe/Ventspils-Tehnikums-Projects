<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

<style>

/*https://www.youtube.com/watch?v=vH9J8fZ11Yg*/

@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
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
        background: #880808;
        margin: 0 4px;
        border-radius: 50%;
        box-shadow: 0 0 0 10px #6E260E,
        0 0 50px #880808,
        0 0 100px #880808;
        animation: animateB 15s linear infinite;
        animation-duration: calc(300s / var(--i));
    }
    .bubbles span:nth-child(even){
        background: #D2042D;
        box-shadow: 0 0 0 10px #811331,
        0 0 50px #D2042D,
        0 0 100px #D2042D;
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
        background: linear-gradient(45deg, #880808, #AA4A44, #EE4B2B, #D22B2B, #C41E3A, #D70040, #C41E3A, #D22B2B, #EE4B2B, #AA4A44, #880808);
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

    #ResetTitle {
    position: relative;
    top: -10%;
    font-size: 40px;
    letter-spacing: 0.1em;
    font-weight: 600;
    color: #ff2d75; /* Adjusted reddish color */
    animation: animateE 6s linear infinite;
    }

@keyframes animateE {
    0%{
            color: #800020;
            text-shadow: 0 0 4px #800020, 0 0 8px #800020, 0 0 12px #800020;
        }
        50%{
            color: #880808;
            text-shadow: 0 0 15px #880808, 0 0 25px #880808, 0 0 35px #880808;
        }
        100%{
            color: #800020;
            text-shadow: 0 0 4px #800020, 0 0 8px #800020, 0 0 12px #800020;
        }
    }

    .otp-field{
        display: felx;
    }

    .otp-field input{
        width: 58px;
        font-size: 32px;
        padding: 10px;
        text-align: center;
        border-radius: 5px;
        margin: 2px;
        border: 2px solid #555;
        background: #21232d;
        font-weight: bold;
        color: #fff;
        outline: none;
        transition: all 0.1s;
    }
    
    .otp-field input:focus{
        border: 2px solid #880808;
        box-shadow: 0 0 2px 2px #800020;
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
        margin-left: 29%;
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
    <div>
        <p></p>
    </div>
<form id="otpInput" method='POST' action="{{ route('mail.validate') }}">
    @csrf
    @method('POST')
    <div>
        <p id="ResetTitle">Enter Your Code:</p>
    </div>
    <div class="otp-field">
        <input type='text' name="input1" maxlength="1"></input>
        <input type='text' name="input2" maxlength="1"></input>
        <input type='text' name="input3" maxlength="1"></input>
        <input type='text' name="input4" maxlength="1"></input>
        <input type='text' name="input5" maxlength="1"></input>
        <input type='text' name="input6" maxlength="1"></input>
    </div>
    <div>
        <a class="buttonStyle" style="--clr: #ADFF2F" onclick="refresh()"><span>Resend</span></a>
    </div>
</form>
</div>

<script>
const inputs = document.querySelectorAll(".otp-field input");

inputs.forEach((input, index) => {
    input.dataset.index = index;
    input.addEventListener("paste", handleOnPasteOtp);
    input.addEventListener("keyup", handleOtp);
});

function handleOtp(e) {
    const input = e.target;
    let value = input.value;
    input.value = "";
    input.value = value ? value[0] : "";

    let fieldIndex = parseInt(input.dataset.index);

    if (value.length > 0 && fieldIndex < inputs.length - 1) {
        inputs[fieldIndex + 1].focus();
    }

    if (e.key === "Backspace" && fieldIndex > 0) {
        inputs[fieldIndex - 1].focus();
    }

    if (fieldIndex === inputs.length - 1) {
        submit();
    }
}

function handleOnPasteOtp(e) {
    const data = e.clipboardData.getData("text");
    const value = data.split("");
    if (value.length === inputs.length) {
        inputs.forEach((input, index) => (input.value = value[index]));
        submit();
    }
}

function submit() {
    document.getElementById("otpInput").submit();
}

function refresh(){
    location.reload();
}
</script>


</script>

</body>
</html>