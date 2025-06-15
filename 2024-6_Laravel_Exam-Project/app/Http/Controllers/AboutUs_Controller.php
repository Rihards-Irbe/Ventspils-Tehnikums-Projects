<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class AboutUs_Controller extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function home_view(){
        
        return view('UI.about_us');
    }
}
