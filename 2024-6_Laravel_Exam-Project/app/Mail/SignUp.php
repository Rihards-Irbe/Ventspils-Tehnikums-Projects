<?php
namespace App\Mail;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Mail\Mailable;
use Illuminate\Queue\SerializesModels;

class SignUp extends Mailable
{
    use Queueable, SerializesModels;

    public $mailData;

    /**
     * Create a new message instance.
     */
    public function __construct($mailData)
    {
        $this->mailData = $mailData;
    }

    /**
     * Build the message.
     *
     * @return $this
     */
    public function build()
    {
        $title = $this->mailData['title'];
        $body = $this->mailData['body'];

        return $this->subject('Rihify Help')
                    ->html("<body>
                                <h1>$title</h1>
                                <p>Hello</p>
                                <p>$body</p>
                                <p>Best Regards</p>
                                <p>Rihify</p>
                            </body>");
    }
}
