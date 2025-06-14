<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
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
            margin-bottom: 10px;
            color: red;
        }

        .register-title {
            position: relative;
            top: -10%;
            font-size: 40px;
            color: white;
        }

        .input-area{
            width: 340px;
            height: 150px;
            font-size: 20px;
            border: 3px solid black;
        }

        .file-input-box{
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .file-input-box input{
            font-size: 15px;
        }

        .top_container{
            margin-top: 10px;
            width: 100%;
            text-align: center;
        }

        .top_container button{
            font-size: 30px;
        }

        #delete_form .login-button{
            position: absolute;
            top: 76%;
            left: 45%;
        }
</style>
<div class="top_container">
    <button onclick="redirect('{{ route('login') }}')">Logout</button>
    <button onclick="redirect('{{ route('main') }}')">Main</button>
    <button onclick="redirect('{{ route('user_edit', ['user' => Auth::id()]) }}')">Edit User</button>
    <button onclick="redirect('{{ route('create') }}')">Create</button>
    <button onclick="redirect('{{ route('cart_main', ['user_id' => Auth::id()]) }}')">Cart</button>
    </div>
<div class="block">
<p class="register-title">Update Product</p>
    <div>
@if($errors->any())
    <div>
        <ul>
            @foreach ($errors->all() as $error)
                <li class="error-msg">{{ $error }}</li>
            @endforeach
        </ul>
    </div>
@endif
    </div>
    <form id="form" method='POST' enctype="multipart/form-data" action="{{ route('edit_product', ['product_id' => $product->id]) }}">
        @csrf
        @method('PUT')
        <div class="input-box">
            <input type='text' name="name" placeholder="Product Name" value="{{ $product->name }}">
        </div>
        <div class="input-box">
            <input type='text' name="price" placeholder="Product Price" value="{{ $product->price }}">
        </div>
        <div>
            <textarea class="input-area" placeholder="Description" id="desc" name="description">{{ $product->description }}</textarea>
        </div>
        <div class="file-input-box">
            <input type='file' name="img">
        </div>
        <div>
            <button class="login-button" role="button" type="submit">Update</button>
        </div>
    </form>

    <form id="delete_form" method='POST' enctype="multipart/form-data" action="{{ route('delete_product', ['product_id' => $product->id]) }}">
        @csrf
        @method('DELETE')
        <button class="login-button" role="button" type="submit">Delete</button>
    </form>
</div>

<script>

document.getElementById("desc").textContent = "<?php echo $product->description ?>"; //ar php echo var accessot iepriekš minēto $index variable">)

function submitForm(){
    document.getElementById("form").submit();
}

function redirect(url){
    window.location.href = url
}

</script>

</body>
</html>