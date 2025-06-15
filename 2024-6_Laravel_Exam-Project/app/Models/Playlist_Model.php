<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Playlist_Model extends Model
{
    use HasFactory;

    protected $table = 'playlists';

    protected $fillable = [
        'name',
        'playlists_owner',
        'song_id_list'
    ];
}
