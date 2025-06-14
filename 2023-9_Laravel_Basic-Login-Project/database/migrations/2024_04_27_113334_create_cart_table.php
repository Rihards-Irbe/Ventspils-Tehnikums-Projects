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
        Schema::create('cart', function (Blueprint $table) {
            $table->id();
            $table->json('product_ids')->default(json_encode([]));
            $table->json('selected_sizes')->default(json_encode([]));
            $table->timestamps();
        });

        DB::table('cart')->insert([
            [
                'product_ids' => json_encode(["1"]), 'selected_sizes' => json_encode(["XL"]),
            ],
        ]);
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('cart');
    }
};
