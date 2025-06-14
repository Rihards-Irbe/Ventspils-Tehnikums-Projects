<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body onload="read_prices()"> <!--kad ielādē mājaslapu runo read_prices() javascript funkciju-->
<style>
@import url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap');
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Montserrat', sans-serif; /*varbūt vajadzēs samainīt mājaslapas fontu, šeit: https://fonts.googleapis.com*/
}

table{ 
    width: 50%;
}

table th, td{
    border-bottom: 3px solid black;
    border-collapse: collapse;
    height: 50px;
}

.table_container{
    width: inherit;
    margin-top: 5%;
    display: flex;
    justify-content: center;
}

#removebtn{
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: center;
}

.top_container{
    margin-top: 10px;
    width: 100%;
    text-align: center;
}

.top_container button{
    font-size: 30px;
}

.price_container{
    width: inherit;
    margin-top: 1%;
    margin-left: 25%;
    display: flex;
    justify-content: left;
}


</style>
<div class="top_container">
    <button onclick="redirect('{{ route('login') }}')">Logout</button> <!--izmanto redirect javascript funkciju, lai redirectotu useru uz login pagu-->
    <!--pārējie to pašu dara, tiek padots route un users tiek pārvietots-->
    <button onclick="redirect('{{ route('main') }}')">Main</button>
    <!--šeit ir jāpadod 'user', jo web.php route prasa šo mainīgom-->
    <button onclick="redirect('{{ route('user_edit', ['user' => Auth::id()]) }}')">Edit User</button>
    <button onclick="redirect('{{ route('create') }}')">Create</button>
    <!--šeit ir jāpadod 'user_id', jo web.php route prasa šo mainīgom-->
    <button onclick="redirect('{{ route('cart_main', ['user_id' => Auth::id()]) }}')">Cart</button>
    </div>
<div class="table_container">
<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Size</th>
        <th>Action</th>
    </tr>
    <!--šo variable izmantoju, lai izskaitītu, cik kopā ir ieraksti tabulā un padotu routam vajadzīgo id, kas tiks removots, ja būs veikts cart_remove route reirects-->
    @php
        $index = 0;
    @endphp
    @foreach($products as $product)
     <!--iet cauri padotajam $products arrayam, lai tabulā pareizi displayotos dati. Šos datus var accessot kā $product-->
    <tr>
        <!--product->(> no datubāzes iecerētie dati <)-->
        <td>{{ $product->name }}</td>
        <td id="price{{$index}}">{{ $product->price }}</td>
        <td>{{ $product->description }}</td>
        <td>{{ json_decode($cart->selected_sizes, true)[$index] }}</td>
        <td>
            <div id="removebtn">
                <button onclick="redirect('{{ route('cart_remove', ['cart_id' => $index]) }}')">Remove</button> <!--redirecto uz route card_remove, kam vjg padot cart_id, kas ir vienāds ar index, ko pirmstam deklarēju-->
            </div>
        </td>
    </tr>
     <!--pieskaitu klat indexam +1, jo foreach loops ies pie nākamajiem datiem-->
    @php
        $index = $index + 1;
    @endphp
    @endforeach
</table>
</div>
<div class="price_container">
    <p id="total_price">Total Price: 0</p>
</div>
<script>

function read_prices(){ //šī funkcija runo, body taga (<body onload="read_prices()">)
    let row_count = <?php echo $index; ?>; //ar php echo var accessot iepriekš minēto $index variable">)
    let total_price = 0.00;

    for(let i = 0; i < row_count; i++){
        row_price = document.getElementById(`price${i}`);
        total_price = total_price + parseFloat(row_price.innerText); //pārveido tekstu no tabulas (price) uz float, lai izrēķinātu cenu">)
        document.getElementById("total_price").innerHTML = `Total Price: ${total_price}` //html elementam ar id "total_price" pārmaina tekstu uz Total Price: + > total_price <)
    }
}

function redirect(url){
    window.location.href = url; //lietotāju redirecto uz citu routu)
}

</script>
</body>
</html>