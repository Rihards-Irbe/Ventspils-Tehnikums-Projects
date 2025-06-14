<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<style>

@import url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Montserrat', sans-serif; /*varbūt vajadzēs samainīt mājaslapas fontu, šeit: https://fonts.googleapis.com*/
}

.top_container{
    margin-top: 10px;
    width: 100%;
    text-align: center;
}

.top_container button{
    font-size: 30px;
}

.product_container {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    margin-top: 50px;
    margin-left: 50px;
}

.product_block {
    text-align: center;
    background-color: gray;
    width: 400px;
    height: 500px;
    margin: 0 300px 20px 0;
}

.product_block p {
    padding-top: 20px;
    font-size: 20px;
    color: white;
}

</style>

<body>
    <!--ja message nav vieāds ar null vai ja sesijā ir padots message, tad tas tiks displayots-->
    @if($message != null || session()->has('message'))
    <p>{{ $message }}</p>
    @endif
    <div class="top_container">
    <button onclick="redirect('{{ route('login') }}')">Logout</button>
    <button onclick="redirect('{{ route('main') }}')">Main</button>
    <button onclick="redirect('{{ route('user_edit', ['user' => Auth::id()]) }}')">Edit User</button>
    @if(Auth::user()->admin)
    <button onclick="redirect('{{ route('create') }}')">Create</button>
    @endif
    <button onclick="redirect('{{ route('cart_main', ['user_id' => Auth::id()]) }}')">Cart</button>
    </div>
    <div class="product_container">
    @foreach($products as $product)
    <div onclick="redirect('{{ route('view_product', ['product_id' => $product->id]) }}')" class="product_block">
        <p>Product Name: {{$product->name}}</p>
        <img src="/public/store_images/{{ $product->img_name }}" width="300" height="300">
        <p>Price: {{$product->price}}</p>
    </div>
    @endforeach
    </div>

</body>

<script>
    function redirect(url){
        window.location.href = url;
    }
</script>

</html>