<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Songs_Model;
use App\Models\Playlist_Model;
use App\Models\Users_Model;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Storage;

class Playlists_Controller extends Controller
{

        public function index(){

            $songs = Songs_Model::all();
            $userId = Auth::id();

            $userData = Users_Model::find($userId);
            
            $songIds = $userData->Liked_Songs ?? [];
            return view('UI.playlist_creation', ['songs' => $songs, 'array' => $songIds]);
        }

        public function store_playlist(Request $request){

            $validatedData = $request->validate([
                'Playlists_Name' => 'required',
                'ListOfSongs' => 'required'
            ]);
        
            $playlist = new Playlist_Model();
            $playlist->name = $validatedData['Playlists_Name'];
            $playlist->playlists_owner = Auth::user()->username;
            $playlist->song_id_list = $request->input('ListOfSongs');
            $playlist->Owners_ID = Auth::id();
        
            $playlist->save();
        
            return redirect(route('songs.index'));
        }

        public function listen_to_selected_playlist(Playlist_Model $playlist){

            $play_list = Playlist_Model::findOrFail($playlist->id);

            $string = $play_list->song_id_list;
            
            $string = str_replace(['[', ']', ' '], '', $string);
            $array = explode(',', $string);
            
            $array = array_map('intval', $array);
        
            $songIds = $array;
            $songs = Songs_Model::all();
            
            return view('UI.playlist_listening', ['songs' => $songs, 'array' => $songIds, 'playlist' => $play_list]);
        }

        public function listen_to_liked_songs(Playlist_Model $playlist){

            $user = Auth::user();
        
            $likedSongs = $user->Liked_Songs;
        
            $songs = Songs_Model::whereIn('id', $likedSongs)->get();
        
            return view('UI.playlist_liked_songs', ['songs' => $songs, 'array' => $likedSongs]);
        }
        

        public function edit_selected_playlist(Playlist_Model $playlist){
            
            if(Auth::id() == $playlist->Owners_ID || Auth::user()->isAdmin){

            $songs = Songs_Model::all();

            $play_list = Playlist_Model::findOrFail($playlist->id);

            $string = $play_list->song_id_list;
            
            $string = str_replace(['[', ']', ' '], '', $string);
            $array = explode(',', $string);
        
            $array = array_map('intval', $array);

            $userId = Auth::id();
            $userData = Users_Model::find($userId);
            
            $songIds = $userData->Liked_Songs ?? [];

            return view('UI.playlist_edit', ['songs' => $songs, 'playlist' => $playlist, 'array' => $songIds, 'current_playlists_data' => $array]);

            }else{

                return redirect()->route('songs.index')->with('error', "You do not have access to this page!");

            }
        }

        public function update_selected_playlist(Playlist_Model $playlist, Request $request){

            if(Auth::id() == $playlist->Owners_ID || Auth::user()->isAdmin){

            $request->validate([
                'Playlists_Name' => 'required',
                'Playlists_Owner' => 'required',
                'ListOfSongs' => 'required',
            ]);

            $playlist->update([
                'name' => $request->input('Playlists_Name'),
                'playlists_owner' => $request->input('Playlists_Owner'),
                'song_id_list' => $request->input('ListOfSongs')
            ]);

            return redirect(route('songs.index'));

            }else{

                return redirect()->route('songs.index')->with('error', "You do not have access to this page!");
    
            }
        }
    
        public function store_songs(Request $request){
            
            $request->validate([
                'Song_Name' => 'required',
                'Artists_Name' => 'required',
                'Songs_Genre' => 'required',
                'Song' => 'required'
            ]);
        
            $Songs_Directory = $request->file('Song')->getClientOriginalName();
            $Song_File = file_get_contents($request->file('Song')); 
        
            $newSong = Songs_Model::create([
                'Song_Name' => $request->input('Song_Name'),
                'Artists_Name' => $request->input('Artists_Name'),
                'Songs_Genre' => $request->input('Songs_Genre'),
                'Files_Name' => $Songs_Directory
            ]);
    
            Storage::disk('public')->put('Songs/'.$Songs_Directory, $Song_File);
            
            return redirect(route('songs.index'));
        }

        public function delete_playlist_by_id($id){

            $playlist = Playlist_Model::find($id);

            if(Auth::id() == $playlist->Owners_ID || Auth::user()->isAdmin){

            $playlist->delete();
            
            return redirect(route('songs.index'));

            }else{

                return redirect()->route('songs.index')->with('error', "You do not have access to this page!");
    
            }
        }

        public function search_playlist_by_name(Request $request, $search_prompt){

            $results = Playlist_Model::where('name', 'like', "%$search_prompt%")->get();
        
            return view('UI.list_of_playlists', ['results' => $results, 'search_prompt' => $search_prompt]);
        }
        
}
