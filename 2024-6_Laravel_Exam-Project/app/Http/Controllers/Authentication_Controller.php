<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Users_Model;
use App\Models\Playlist_Model;
use App\Models\Songs_Model;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;

class Authentication_Controller extends Controller
{
    public function index(){

        return view('UI.userLogin');
    }

    public function register_redirect(){

        return view('UI.userRegister');
    }

    public function register(Request $request){

        $validatedData = $request->validate([
            'username' => 'required|unique:users,username',
            'email' => 'required|email|unique:users,email',
            'password' => 'required|min:6|confirmed',
        ]);
    
        $hashedPassword = Hash::make($validatedData['password']);
    
        $isArtist = $request->has('isArtist') ? true : false;
    
        $newUser = Users_Model::create([
            'username' => $validatedData['username'],
            'email' => $validatedData['email'],
            'password' => $hashedPassword,
            'isArtist' => $isArtist
        ]);
    
        return redirect()->route('songs.login')->with('success', 'Registration successful!');
    }

    public function login(Request $request){
    
        $credentials = $request->only('username', 'password');
    
        if (Auth::attempt($credentials)) {

            $userId = Auth::id();
            $randomPlaylists = Playlist_Model::where('owners_id', '!=', $userId)->get()->shuffle()->take(5);
            $usersPlaylists = Playlist_Model::where('owners_id', '=', $userId)->get();
            $totalUserPlaylists = Playlist_Model::where('owners_id', '=', $userId)->count(); 
            $songs = Songs_Model::all()->shuffle()->take(10);
            $playlist = Playlist_Model::all();

            return view('UI.index', ['songs' => $songs, 'usersPlaylist' => $usersPlaylists, 'randomPlaylist' => $randomPlaylists, 'totalUserPlaylist' => $totalUserPlaylists, 'playlists' => $playlist, 'PlayAnimation' => 1]);
        }else{
            return back()->withErrors([
                'errors' => 'Password or Username Incorrect',
            ]);
        }
    }

    public function edit_selected_user(Users_Model $user){

        if(Auth::id() == $user->id || Auth::user()->isAdmin){

            return view('UI.userEdit', ['Current_User' => $user]);

        }else{

            return redirect()->route('songs.index')->with('error', "You do not have access to this page!");

        }
    }

    public function update_selected_user(Users_Model $user, Request $request){

        if(Auth::id() == $user->id || Auth::user()->isAdmin){

            $isArtist = $request->has('isArtist') ? true : false;

            if($request->input('username') == $user->username && $request->input('email') != $user->email){
                $validatedData = $request->validate([
                    'email' => 'required|email|unique:users,email',
                ]);

                $user->update([
                    'email' => $validatedData['email'],
                ]);

            }else if($request->input('username') != $user->username && $request->input('email') == $user->email){
                $validatedData = $request->validate([
                    'username' => 'required|unique:users,username',
                ]);

                $user->update([
                    'username' => $validatedData['username'],
                ]);
            }else if($request->input('username') != $user->username && $request->input('email') != $user->email){

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

                $validatedData = $request->validate([
                    'password' => 'required|min:6|confirmed'
                ]);

                $hashedPassword = Hash::make($validatedData['password']);

                $user->update([
                    'password' => $hashedPassword,
                    'isArtist' => $isArtist
                ]);

            }

            $user->update([
                'isArtist' => $isArtist
            ]);

            return redirect(route('songs.index'));

        }else{

            return redirect()->route('songs.index')->with('error', "You do not have access to this page!");

        }
    }

    public function passwordReset(){

        return view('UI.passwordReset');
    }

}