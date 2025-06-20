<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Cart_Model extends Model
{
    use HasFactory;

    protected $table = 'cart';

    protected $fillable = [
        'product_ids',
        'selected_sizes'
    ];
}
