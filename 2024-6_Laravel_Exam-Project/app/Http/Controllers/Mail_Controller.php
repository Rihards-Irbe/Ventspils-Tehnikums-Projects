<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Mail;
use App\Mail\SignUp;
use App\Models\Users_Model;
use Illuminate\Support\Facades\Hash;

class Mail_Controller extends Controller
{
    function sendMail(Request $request) {
        
        $user = Users_Model::where('email', $request->input('email'))->first();
    
        if ($user) {

            $code = mt_rand(100000, 999999);

            $mailData = [
                'title' => 'Password Reset Code',
                'body' => "We've received a request to reset your password, here's your code: ". $code
            ];
    
            Mail::to($request->input('email'))->send(new SignUp($mailData));
    
            $request->session()->put('generated_code', $code);
            $request->session()->put('users_data', $user);

            return view('mail.PasswordReset');

        }else {

            return view('UI.userLogin')->withErrors([
                'errors' => "Provided Email Doesn't Exsist",
            ]);
            
        }
    }

    function validateCode(Request $request){

        $generatedCode = $request->session()->get('generated_code');
        $user = $request->session()->get('users_data');

        $inputCode = $request->input('input1') .
                     $request->input('input2') .
                     $request->input('input3') .
                     $request->input('input4') .
                     $request->input('input5') .
                     $request->input('input6');
    
        if ($inputCode == $generatedCode) {
    
            $request->session()->forget('generated_code');
            return $this->redirectUser($request);
    
        } else {
            return view('UI.userLogin')->withErrors([
                'errors' => 'Provided Code Is Incorrect',
            ]);
        }
    }

    function redirectUser($request){

        $user = $request->session()->get('users_data');
        return view('mail.ChangePassword', ['user' => $user]);
    }

    function updatePassword(Request $request){

        $user = $request->session()->get('users_data');

        $validatedData = $request->validate([
            'password' => 'required|min:6|confirmed'
        ]);

        $hashedPassword = Hash::make($validatedData['password']);

        $user->update([
            'password' => $hashedPassword,
        ]);

        $request->session()->forget('users_data');

        return redirect()->route('songs.login')->with('success', 'User Updated successfully!');
    }

    function send_support_message(Request $request){

        $mailData = [
            'title' => 'Message From ' . $request->input('name'),
            'body' => "Send response back to: " . $request->input('email') . ". Message states: " . $request->input('text')
        ];        

        Mail::to("rihifymail@gmail.com")->send(new SignUp($mailData));

        return redirect()->route('songs.index');

    }
}
