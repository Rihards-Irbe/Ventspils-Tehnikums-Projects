<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Store_Products_Model;
use App\Models\Cart_Model;
use Illuminate\Support\Facades\Storage;

class Store_Controller extends Controller
{
    public function index(){

        $products = Store_Products_Model::all();  #Izvēlās visus produktus no datubāzes
        
        if(session('message') != null){ #pārbauda, vai tika padots message
            $message = session('message');
        }else{
            $message = null; #ja netika, tad šis message netiks parādīts uz mājaslapas
        }
        return view('store.main', ['message' => $message, 'products' => $products]); #un atgriež šos visus produktus, kā array, ko var accessot šinī viewa
    }

    public function create_redirect(){
        return view('store.create_product'); #vnk redirect
    }

    public function view_product($id){

        $product = Store_Products_Model::where('id', '=', $id)->first(); #atrod no datubāzes produktu, kam sakrīt id
        return view('store.view_product', ['product' => $product]); #un atgriež šo kā variable
    }

    public function create_product(Request $request){
        $request->validate([
            'name' => 'required|unique:products,name', #validē, vai formas ievadītais name sakrīt ar kādu citu jau esošu name datubāzē
            'price' => [
                'required',
                'numeric',
                'gt:0',
                'regex:/^\d+(\.\d{2})$/', #vnk regex, kas pārbauda vai pareizi ievadīts cenas formāts
            ],
            'description' => 'required', #obligāti jāizpilda
            'img' => 'required', #obligāti jāizpilda
        ]);
    
        $img_name = $request->input('name');
    
        if ($request->hasFile('img')) { #ja requesta, jeb formā ir padots/izvēlēts fails
            $file = $request->file('img'); #tad šo failu pārveido par variable
            $fileName = $img_name . '.png';
            $file->move('public/store_images', $fileName); #un saglabā public/public/store_images folderī ar vārdu $fileName
        }
        
        Store_Products_Model::create([ #datubāzē izveido jaunu ierakstu
            'name' => $request->input('name'), #vārdu saņem no request / form ievadīto elementu ar name="name"
            'price' => $request->input('price'), #vārdu saņem no request / form ievadīto elementu ar name="price"
            'description' => $request->input('description'), #vārdu saņem no request / form ievadīto elementu ar name="description"
            'img_name' => $img_name . '.png' #vārdu saņem no request / form ievadīto elementu ar name="name", bet pievieno beigās .png, lai vēlāk varētu accessot šo ar <img src="img_name">
                
        ]);
    
        return redirect()->route('main')->with('message', 'Product Created!'); #vnk redirecto un padod message variable, kas pasaka, ka produkts ir izveidots, lai displayotu to
    }

    public function edit_product_reirect($id){ #id tiek saņemts to route, kjip route: edit/{id}/product, lai accessotu to šinī funkcijā
        $product = Store_Products_Model::where('id', '=', $id)->first(); #atrod produktu, kur sakrīt id ar padoto id
        return view('store.edit_product', ['product' => $product]); #returno šo produktu, lai varētu accessot mājaslapā
    }

    public function edit_product($id, Request $request){

        $product = Store_Products_Model::find($id); #atrod id, kas sakrīt no db

        $validated_data = $request->validate([ #šoreiz nevalidē name un img elementus no form, lai varētu vnk mainīt bildi, kas ielikta nemainot visus pārējos datus par šo produktu
            'price' => [
                'required',
                'numeric',
                'gt:0',
                'regex:/^\d+(\.\d{2})$/',
            ],
            'description' => 'required',
        ]);

        if($request->input('name') != $product->name){ #ja ierakstītais vārds nav vienāds ar datubāzes vārdu, tikai tad tas tiks mainīts. Ja editojot produktu vārdu atstāsi, tev neizmetīsies error, ja vārds jau sakrīt ar datubāzē esošu
            $validated_name = $request->validate([
                'name' => 'required|unique:products,name' #validē, vai šāds name jau nav datubāzē
            ]);
        }else{
            $validated_name = ['name' => $product->name]; #ja editojot name sakrīt ar datubāzē esošu, tad vnk no datubāzes paņem nosaukumu
        }

        if($request->file('img') != null){ #ja pildot formu ir ievietots fails, tad veco izdzēš
            if (file_exists('public/store_images/' . $product->img_name)) { #pārbauda, vai šāds fails eksistē, ja nē tad izmet erroru
                unlink('public/store_images/' . $product->img_name); #izdzēš
            }else{
                dd("Couldn't find the provided path");
            }

            $product->update(['img_name' => $validated_name['name'] . '.png']); #dēļ to, ka ir ievietots jauns fails vajag datubāzē updeitot uz jauno failu/nosaukumu

            $file = $request->file('img'); #tas pats, kas pirmstam vnk no requesta paņem failu un saglabā ar datubāzes vārdu + .png
            $fileName = $validated_name['name'] . '.png';
            $file->move('public/store_images', $fileName); #ievieto šo jauno failu 
        }

        $product->update([ #updato palikušos datus uz jaunajiem
            'name' => $validated_name['name'],
            'price' => $validated_data['price'],
            'description' => $validated_data['description'],
        ]);
        
        return redirect()->route('main')->with('message', 'Product Updated!'); #vnk atgriež main route un pasaka, ka produkts ir updeitots.

    }

    public function delete_product($id){

        $product = Store_Products_Model::find($id); #atrod produktu, kam sakrīt id
        $cart_data = Cart_Model::all();


        if (file_exists('public/store_images/' . $product->img_name)) { #ja šāds fails eksistē
            unlink('public/store_images/' . $product->img_name); #tad to izdzēš
        }

        $product->delete(); #no datubāzes izdzēš šo produktu, kas tika primstam atrast by id

        foreach($cart_data as $cart_values){ #atlicis izdzēst no cartiem produktu, kas tikko tika izdzēsts, lai nemestos errors.

            $product_ids_array = json_decode($cart_values->product_ids, true); #datubāzē dati tiek saglabāti ar json_encode, tāpēc šos datus vjg decodot, lai tos izmantotu tālāk php valodā
            $cart_sizes = json_decode($cart_values->selected_sizes, true);
        
            foreach($product_ids_array as $key => $ids){ #šo no interneta paņēmu, bet vnk iet cauri šim arrayam.
                if($ids == $id){ #ja id sakrīt ar to id, kas tika idzēsts (kas tika padots delete_product(> $id <))
                    unset($product_ids_array[$key]); #tad to removo no array
                    unset($cart_sizes[$key]);
                }
            }
        
            $cart_values->product_ids = json_encode($product_ids_array); #šis arrays tiek atpakaļ encodots, lai to varētu saglabāt pareizi datubāzē
            $cart_values->save(); #un saglabā.
        }

        return redirect()->route('main'); #vnk atgriež main

    }
    
}
