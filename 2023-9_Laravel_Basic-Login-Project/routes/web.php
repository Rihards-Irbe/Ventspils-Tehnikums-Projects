<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Authentication_Controller;
use App\Http\Controllers\Store_Controller;
use App\Http\Controllers\Cart_Controller;

#Route::>metode<('> url <', [> controller, kur atrodās funkcijas, kas tiks runotas <, '> funkcijas, ko vēlies runot nosaukums <'])->name(> route nosaukums, ko izmantoju, lai redirectotu <)
Route::get('/login', [Authentication_Controller::class, 'index'])->name('login');
Route::get('/register', [Authentication_Controller::class, 'register_redirect'])->name('register');
Route::post('/createUser', [Authentication_Controller::class, 'register'])->name('createUser');
Route::post('/loginUser', [Authentication_Controller::class, 'login'])->name('loginUser');
#šeit tiek padots {> user <} mainīgas uz edit_selected_user funkciju. {{ route('user_edit', ['user' => Auth::id()]) }} -- Šādi tiek pareizi padots 'user' mainīgais.
#middleware('auth') nozīmē to, ka jābūt ir ielogotam, lai veiktu šīs darbības.
Route::get('edit_user/{user}', [Authentication_Controller::class, 'edit_selected_user'])->name('user_edit')->middleware('auth');
Route::put('edit_user/{user}', [Authentication_Controller::class, 'update_selected_user'])->name('user_update')->middleware('auth');
Route::get('/store', [Store_Controller::class, 'index'])->name('main')->middleware('auth');;
Route::get('/create', [Store_Controller::class, 'create_redirect'])->name('create')->middleware('auth');;
Route::post('/createProduct', [Store_Controller::class, 'create_product'])->name('createProduct')->middleware('auth');;
Route::get('view/{product_id}/product', [Store_Controller::class, 'view_product'])->name('view_product')->middleware('auth');;
Route::get('edit/{product_id}/product', [Store_Controller::class, 'edit_product_reirect'])->name('edit_product_redirect')->middleware('auth');;
Route::put('edit/{product_id}/product', [Store_Controller::class, 'edit_product'])->name('edit_product')->middleware('auth');;
Route::delete('delete/{product_id}/product', [Store_Controller::class, 'delete_product'])->name('delete_product')->middleware('auth');;
Route::get('cart/{user_id}', [Cart_Controller::class, 'index'])->name('cart_main')->middleware('auth');;
Route::get('cart_remove/{cart_id}', [Cart_Controller::class, 'remove_id'])->name('cart_remove')->middleware('auth');;
Route::post('cart_add/{product_id}', [Cart_Controller::class, 'add_id'])->name('cart_add')->middleware('auth');;

#default route.
Route::get('/', function () {
    return view('login.userLogin');
});
