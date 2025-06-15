<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Users_Model;
use App\Models\Playlist_Model;
use App\Models\Songs_Model;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;

class Admin_Controller extends Controller
{
    public function index(){

        if(Auth::user()->isAdmin){

        $users = Users_Model::all();

        return view('UI.admin', ['users' => $users]);

        }else{

            return redirect()->route('songs.index')->with('error', "Account id and yours didn't match!");

        }
    }

    public function view_user_details($id){

        if(Auth::user()->isAdmin){

        $users_details = Users_Model::find($id);
        $users_playlist_details = Playlist_Model::where('Owners_ID', $id)->get();
        $users_song_details = Songs_Model::where('Owners_ID', $id)->get();


        return view('UI.admin_user_details', ['user' => $users_details, 'playlist' => $users_playlist_details, 'song' => $users_song_details]);

        }else{

            return redirect()->route('songs.index')->with('error', "Account id and yours didn't match!");

        }
    }

    public function login($id){

        if(Auth::user()->isAdmin){

        Auth::loginUsingId($id);
        $userId = $id;
        $randomPlaylists = Playlist_Model::where('owners_id', '!=', $userId)->get()->shuffle()->take(5);
        $usersPlaylists = Playlist_Model::where('owners_id', '=', $userId)->get();
        $totalUserPlaylists = Playlist_Model::where('owners_id', '=', $userId)->count(); 
        $songs = Songs_Model::all()->shuffle()->take(10);
        $playlist = Playlist_Model::all();

        return view('UI.index', ['songs' => $songs, 'usersPlaylist' => $usersPlaylists, 'randomPlaylist' => $randomPlaylists, 'totalUserPlaylist' => $totalUserPlaylists, 'playlists' => $playlist, 'PlayAnimation' => 0]);

        }else{

            return redirect()->route('songs.index')->with('error', "Account id and yours didn't match!");

        }
    }

    public function delete_user_by_id($id){

        if(Auth::user()->isAdmin){

        $user = Users_Model::find($id);
        $user->delete();

        return redirect()->route('songs.admin');

        }else{

            return redirect()->route('songs.index')->with('error', "Account id and yours didn't match!");

        }
    }
}
