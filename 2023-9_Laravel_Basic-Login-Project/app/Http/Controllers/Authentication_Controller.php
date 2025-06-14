<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Users_Model;
use App\Models\Store_Products_Model;
use App\Models\Cart_Model;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;

class Authentication_Controller extends Controller
{
    public function index(){
        return view('login.userLogin');
    }

    public function register_redirect(){
        return view('login.userRegister');
    }

    public function register(Request $request){
        $validatedData = $request->validate([ #validē iedotos datus 
            'username' => 'required|unique:users,username', #pārbauda, vai username jau eksistē
            'email' => 'required|email|unique:users,email', #pārbauda, vai email jau eksistē
            'password' => 'required|min:6|confirmed', #parolei jābūt vismaz 6 burtiem un jāsakrīt ar kjip password confirmation
        ]);
    
        $hashedPassword = Hash::make($validatedData['password']); #paroli enkriptē

        $latestId = Users_Model::latest()->first(); #atrod pedejo id no datubazes

        $newId = $latestId->id + 1; #pedejais id + 1 = current id (kads bus jaunajam useram)
    
        $newUser = Users_Model::create([ #izveido ierakstu datubāzē ar šiem datiem, kas tika validēti
            'username' => $validatedData['username'],
            'email' => $validatedData['email'],
            'password' => $hashedPassword, #saglabā enkriptēto paroli
            'cart_id' => $newId,
        ]);

        Cart_Model::create([ #izveido tuksu cartu prieks jauno useri
            'product_ids' => json_encode([]),
            'selected_sizes' => json_encode([])
        ]);
    
        return redirect()->route('login')->with('success', 'Registration successful!'); #atgriež login pagu ar īziņu Registration successful
    }

    public function login(Request $request){

        $products = Store_Products_Model::all(); #šis ir domāts, lai varētu šos produktus accessot, ja parole būs ievadīta pareizi
        $credentials = $request->only('username', 'password');
    
        if (Auth::attempt($credentials)) { #ievadītos credidental salīdzina

            return view('store.main', ['message' => "Authentication Successful", 'products' => $products]); #atgriež message, kā arī iepriekš minētos produktus, lai tos displayotu
        }else{
            return back()->withErrors([
                'errors' => 'Password or Username Incorrect', #error handling, ja nesakrīt atgriež šo īzziņu
            ]);
    }
    }

    public function edit_selected_user(Users_Model $user){
        return view('login.userEdit', ['Current_User' => $user]); #atgriež view ar 'Current_User'datiem
    }

    public function update_selected_user(Users_Model $user, Request $request){

        if($request->input('username') == $user->username && $request->input('email') != $user->email){

            //Pārbauda vai input email sakrīt ar datubāzē esošā user email, ja nē, tad updeito tikai username

            $validatedData = $request->validate([
                'email' => 'required|email|unique:users,email',
            ]);
    
            $user->update([
                'email' => $validatedData['email'],
            ]);

        }else if($request->input('username') != $user->username && $request->input('email') == $user->email){

            //Pārbauda vai input username sakrīt ar datubāzē esošā user username, ja nē, tad updeito tikai username

            $validatedData = $request->validate([
                'username' => 'required|unique:users,username',
            ]);
    
            $user->update([
                'username' => $validatedData['username'],
            ]);

        }else if($request->input('username') != $user->username && $request->input('email') != $user->email){

            //Ja username un email nesakrīt ar datubāzes datiem, tad updeito abus ar jauniem datiem

            $validatedData = $request->validate([
                'username' => 'required|unique:users,username',
                'email' => 'required|email|unique:users,email',
            ]);
    
            $user->update([
                'username' => $validatedData['username'],
                'email' => $validatedData['email'],
            ]);
        }

        if($request->input('password') != null){

            //vnk pārbauda vai password input ir veikts ieraksts, ja jā tad validato un updeito

            $validatedData = $request->validate([
                'password' => 'required|min:6|confirmed'
            ]);
    
            $hashedPassword = Hash::make($validatedData['password']);
    
            $user->update([
                'password' => $hashedPassword,
            ]);
    
        }

        return redirect(route('login'));
    }
}