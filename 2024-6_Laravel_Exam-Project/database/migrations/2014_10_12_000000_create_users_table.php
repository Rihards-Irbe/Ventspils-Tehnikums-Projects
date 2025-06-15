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
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('username')->unique();
            $table->string('email')->unique();
            $table->string('password');
            $table->boolean('isArtist')->default(false);
            $table->boolean('isAdmin')->default(false);
            $table->json('Liked_Songs')->default(json_encode([]));
            $table->integer('upload_limit')->default(10);
            $table->integer('songs_uploaded')->default(0);
            $table->timestamps();
        });

        DB::table('users')->insert([
            [
                'username' => 'Rihards', 'email' => 'ZeeusGT@gmail.com',
                'password' => '$2y$12$ca/ulq5fSj6tyKyCKjjNtuPgGRCC3fqR4Y1tm1bgjM7o2q7AasQkS', 'isArtist' => True,
                'isAdmin' => False, 'Liked_Songs' => json_encode(["4","10","6"]),
                'upload_limit' => 10, 'songs_uploaded' => 10,
            ],
            [
                'username' => 'Admin', 'email' => 'Rihify@gmail.com',
                'password' => '$2y$12$ca/ulq5fSj6tyKyCKjjNtuPgGRCC3fqR4Y1tm1bgjM7o2q7AasQkS', 'isArtist' => True,
                'isAdmin' => True, 'Liked_Songs' => json_encode(["1","2","3","4","5"]),
                'upload_limit' => 10, 'songs_uploaded' => 0,
            ],
        ]);
    }

    /**
     * Reverse the migrations
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};
