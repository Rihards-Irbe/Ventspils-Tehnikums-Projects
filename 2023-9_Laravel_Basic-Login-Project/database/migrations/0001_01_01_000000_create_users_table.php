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
            $table->integer('cart_id')->unique();
            $table->boolean('admin')->default(false);
            $table->timestamps();
        });

        DB::table('users')->insert([
            [
                'username' => 'Rihards', 'email' => 'RihardsIrbe@gmail.com',
                'password' => '$2y$12$ca/ulq5fSj6tyKyCKjjNtuPgGRCC3fqR4Y1tm1bgjM7o2q7AasQkS',
                'cart_id' => 1, 'admin' => true
            ],
        ]);
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};
