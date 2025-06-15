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
        Schema::create('songs', function (Blueprint $table) {
            $table->id();
            $table->string('Song_Name');
            $table->string('Artists_Name');
            $table->string('Songs_Genre');
            $table->string('Files_Name');
            $table->integer('Owners_ID');
            $table->timestamps();
        });

        DB::table('songs')->insert([
            [
                'Song_Name' => 'The Way You Kiss Me', 'Artists_Name' => 'Artemas',
                'Songs_Genre' => 'Alternative pop', 'Files_Name' => '1.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => ' Magnolia', 'Artists_Name' => 'Playboi Carti',
                'Songs_Genre' => 'Hip Hop / Rap', 'Files_Name' => '2.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'Keep It Tucked', 'Artists_Name' => 'ThxSoMch',
                'Songs_Genre' => 'Alternative Indie', 'Files_Name' => '3.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'If We Being Rëal', 'Artists_Name' => 'Yeat',
                'Songs_Genre' => 'Hip hop / Rap', 'Files_Name' => '4.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'One Call', 'Artists_Name' => 'Rich Amiri',
                'Songs_Genre' => 'Hip hop / Rap', 'Files_Name' => '5.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'Mr. Rager', 'Artists_Name' => 'Kid Cudi',
                'Songs_Genre' => 'Hip hop', 'Files_Name' => '6.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'Intelligency', 'Artists_Name' => 'August',
                'Songs_Genre' => 'Electronic', 'Files_Name' => '7.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'Tell Më', 'Artists_Name' => 'Yeat',
                'Songs_Genre' => 'Hip hop / Rap', 'Files_Name' => '8.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => ' I Smoked Away My Brain', 'Artists_Name' => 'a$ap rocky',
                'Songs_Genre' => 'Hip hop / Rap', 'Files_Name' => '9.mp3',
                'Owners_ID' => 1,
            ],
            [
                'Song_Name' => 'Come Through', 'Artists_Name' => 'The Weeknd',
                'Songs_Genre' => 'R&B', 'Files_Name' => '10.mp3',
                'Owners_ID' => 1,
            ],
        
        ]);
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('songs');
    }
};
