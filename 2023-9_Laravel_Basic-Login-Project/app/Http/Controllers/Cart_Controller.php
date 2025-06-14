<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Users_Model;
use App\Models\Store_Products_Model;
use App\Models\Cart_Model;
use Illuminate\Support\Facades\Auth;

class Cart_Controller extends Controller
{
    public function index($id){

        $user = Users_Model::where('id', '=', $id)->first(); #atrod lietotāju, kura id sakrīt ar autorizēto lietotāja id, kas tika padots (index(> $id <))
        $cart = Cart_Model::where('id', '=', $user->cart_id)->first(); #atrod lietotāju, kam pieder šis cart (datubāzē users tiek saglabāts cart_id un paši cart dati tiek saglabāti atsevišā datubāze cart, tāpēc vajag atrast cart, kura id sakrīt ar user->cart_id)
        $users_cart_ids = json_decode($cart->product_ids ?? '[]', true); #decode product_ids arrayu, jo datubāzē, lai saglabātu array vjg to json_encodot.
        $array = [];
        

        foreach ($users_cart_ids as $id){ #iet cauri katrai array vērtībai

            $product = Store_Products_Model::where('id', '=', $id)->first(); #atrod produktu, kuru id sakrīt ar konkrēto array id
            array_push($array, $product); #pusho/pievieno produkta vērtības
        }

        return view('cart.view_cart', ['products' => $array, 'cart' => $cart]); #atgriež products (visus produktu datus, kurus vajadzēs accessot mājaslapā) un cart datus (cart, kas pieder useram, kjip cart id sakrīt ar $user->cart_id)
    }

    public function remove_id($id){

        $cart = Cart_Model::where('id', '=', Auth::user()->cart_id)->first(); #atrod cartu, kas sakrīt ar authorizētā user, jeb usera, kas ielogojas cart_id.
    
        $users_cart_product_ids = json_decode($cart->product_ids ?? '[]', true); #decode product_ids array no datubāzēs, lai varētu to izmantot
        $users_cart_sizes = json_decode($cart->selected_sizes ?? '[]', true); #decode selected_sizes array no datubāzēs, lai varētu to izmantot
    
        unset($users_cart_product_ids[$id]); #no šī array noņem id, kas sakrīt (["1", "2"], ja padotais id remove_id(> $id <) sakrīt ar vienu no šī array skaitļiem, tad to removos)
        unset($users_cart_sizes[$id]); #tas pats, tikai ar otru array $cart->selected_sizes/$users_cart_sizes
    
        #vjg šos arrayus pārveidot, lai tie pareizi saglabātos
        $users_cart_product_ids = array_values($users_cart_product_ids);
        $users_cart_sizes = array_values($users_cart_sizes);
        
        #encodoju atpakaļ, lai varētu datubāzē saglabāt
        $cart->product_ids = json_encode($users_cart_product_ids);
        $cart->selected_sizes = json_encode($users_cart_sizes);
    
        $cart->save(); #saglabā izmaiņas
    
        return redirect()->route('main')->with('message', 'Removed Product From Cart!');
    }
    
    

    public function add_id($id, Request $request){
        
        $user = Auth::user(); #autorizētais users, jeb users, kurš ielogojas ieksa
        $cart = Cart_Model::where('id', '=', $user->cart_id)->first(); #atrod cart, kas sakrīt ar user->cart_id

        #atkal decode arrayu, lai to varētu izmantot
        $users_cart = json_decode($cart->product_ids ?? '[]', true);
        $cart_sizes = json_decode($cart->selected_sizes ?? '[]', true);

        #pie array pievieno id un izvēlēto izmēru
        $users_cart[] = $id;
        $cart_sizes[] = $request->input('size');

        #encode arrayus, lai tos varētu saglabāt datubāže
        $cart->product_ids = json_encode($users_cart);
        $cart->selected_sizes =json_encode($cart_sizes);
        $cart->save();

        return redirect()->route('main')->with('message', 'Added Product To Cart!'); #vnk redirecto un padod message variable, kas pasaka, ka produkts ir izveidots, lai displayotu to

    }
    
}
