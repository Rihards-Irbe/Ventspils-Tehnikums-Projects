<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
    Schema::create('playlists', function (Blueprint $table) {
        $table->id();
        $table->string('name');
        $table->string('playlists_owner');
        $table->integer('Owners_ID');
        $table->string('song_id_list')->nullable();
        $table->timestamps();
    });

    DB::table('playlists')->insert([
        [
            'name' => 'Background Playlist', 'playlists_owner' => 'Rihards',
            'Owners_ID' => 1, 'song_id_list' => '1, 2, 3',
        ],
        [
            'name' => 'Rap Playlist', 'playlists_owner' => 'Rihards',
            'Owners_ID' => 1, 'song_id_list' => '5, 9, 3, 4',
        ],
        [
            'name' => 'Hip hop Playlist', 'playlists_owner' => 'Rihards',
            'Owners_ID' => 1, 'song_id_list' => '1, 7, 6, 10',
        ],
        [
            'name' => '1 Hour Of Bangers', 'playlists_owner' => 'Rihards',
            'Owners_ID' => 1, 'song_id_list' => '1, 2, 3, 4, 5, 6, 7, 8, 9, 10',
        ],
        [
            'name' => 'Songs For Everyone', 'playlists_owner' => 'Rihards',
            'Owners_ID' => 1, 'song_id_list' => '5, 9, 8, 7',
        ],
        [
            'name' => 'Carti Playlist', 'playlists_owner' => 'Admin',
            'Owners_ID' => 2, 'song_id_list' => '1, 2, 3, 4',
        ],
        [
            'name' => 'Random Playlist', 'playlists_owner' => 'Admin',
            'Owners_ID' => 2, 'song_id_list' => '3, 4, 5',
        ],
        [
            'name' => 'Lofi Playlist', 'playlists_owner' => 'Admin',
            'Owners_ID' => 2, 'song_id_list' => '7, 9, 10, 4',
        ],
        [
            'name' => 'Time killer', 'playlists_owner' => 'Admin',
            'Owners_ID' => 2, 'song_id_list' => '1, 9, 6, 3',
        ],
        [
            'name' => 'Only The Best', 'playlists_owner' => 'Admin',
            'Owners_ID' => 2, 'song_id_list' => '9, 5, 4, 3',
        ],
    ]);

    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('playlists');
    }
};
