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
        Schema::create('products', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->string('price');
            $table->string('description');
            $table->string('img_name');
            $table->timestamps();
        });

        DB::table('products')->insert([
            [
                'name' => 'Basic Shirt', 'price' => '5.30',
                'description' => 'Shirts Description',
                'img_name' => 'Shirt.png'
            ],
        ]);
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        //
    }
};
