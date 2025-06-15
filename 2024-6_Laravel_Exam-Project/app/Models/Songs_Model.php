<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Songs_Model extends Model
{
    use HasFactory;

    protected $table = 'songs';

    protected $fillable = [
        'Song_Name',
        'Artists_Name',
        'Songs_Genre',
        'Files_Name'
    ];
}
