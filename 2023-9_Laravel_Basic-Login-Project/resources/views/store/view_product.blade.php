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

        .edit_button{
            position: absolute;
            font-size: 20px;
            left: 77%;
            top: 3%;
        }

        .product-title {
            position: relative;
            top: -5%;
            font-size: 40px;
            color: white;
        }

        .price_container{
            font-size: 30px;
        }

        .top_container{
            margin-top: 10px;
            width: 100%;
            text-align: center;
        }

        .top_container button{
            font-size: 30px;
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
    <button class="edit_button" onclick="redirect('{{ route('edit_product_redirect', ['product_id' => $product->id]) }}')">Edit Product</button>
    <p class="product-title">Viewing: {{$product->name}}</p>
    <div class="price_container">
    <p>Cost: {{$product->price}}</p>
    <p>Description: {{$product->description}}</p>
    <div>
    <img src="/public/store_images/{{ $product->img_name }}" width="400" height="400"></img>
    </div>
    <label for="size">Choose your size:</label>
    <form id="form" method='POST' action="{{ route('cart_add', ['product_id' => $product->id]) }}">
    @csrf
    @method('POST')
    <select name="size" id="size">
        <option value="XS">XS</option>
        <option value="S">S</option>
        <option value="M">M</option>
        <option value="L">L</option>
        <option value="XL">XL</option>
        <option value="XXL">XXL</option>
    </select>
    <button type="submit">Add To Cart</button>
    </form>
    </div>
    <div>

    </div>
</div>
</form>
</div>

<script>

function redirect(url){
    window.location.href = url
}

</script>

</body>
</html>