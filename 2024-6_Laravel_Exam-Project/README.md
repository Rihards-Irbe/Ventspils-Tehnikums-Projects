# 2024-6_Laravel_Exam-Project

## Demonstration

[![Watch Rihify Demo on YouTube](https://img.youtube.com/vi/AH4-PYL9rf0/hqdefault.jpg)](https://www.youtube.com/watch?v=AH4-PYL9rf0)


## Overview  
This is my **Ventspils Tehnikums Exam project**, the project is called: *"Rihify"* as a mix between *Rihards* and *Spotify*. **Rihify** is a music listening website, where you can interact with music, that other people have uploaded, create playlists and save your favorites by liking them. The UI is user friendly and has some QOL features, like shuffling playlists, playing songs on repeat and being able to skip songs.

## Features
- Clean and user-friendly interface for seamless music browsing.
- Registration and login system with secure authentication and the ability to reset your password via email.
- Upload functionality for users to share their own music.
- Full CRUD operations for music, playlists and user data.
- Playlist system that allows creating, editing, deleting, and shuffling songs inside.
- Like system to save favorite songs for quick access.
- Music player with support for play/pause, skip, repeat, and shuffle.
- Real-time validation with input feedback and form memory for better UX.
- Admin dashboard (if applicable) for managing users and content.

## How to Run

Follow the steps below to set up and run this Laravel project using Visual Studio Code, XAMPP, and MySQL Workbench.

1. **Open the project folder** in Visual Studio Code.

2. **Launch XAMPP Control Panel**, then start **Apache** and **MySQL**.

3. **Open MySQL Workbench** and connect to your MySQL server.

4. **Run the following SQL script** to create the database and user:

   ```sql
   CREATE DATABASE your_database_name;
   USE your_database_name;
   CREATE USER 'your_username'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON your_database_name.* TO 'your_username'@'localhost';
   FLUSH PRIVILEGES;
   ```

5. **Inside VS create a copy of .env.example and rename it to .env** Then edit it like this:

    ```DB_CONNECTION=mysql
    DB_HOST=127.0.0.1
    DB_PORT=your_MySQL_port
    DB_DATABASE=your_database_name
    DB_USERNAME=your_username
    DB_PASSWORD=your_password
    ```

6. **Optional (for mailing functionality):** Edit the following fields in `.env`:

    ```MAIL_MAILER=smtp
    MAIL_HOST=smtp.gmail.com
    MAIL_PORT=587
    MAIL_USERNAME=RihifyMail@gmail.com
    MAIL_PASSWORD="my_password"
    MAIL_ENCRYPTION=tls
    MAIL_FROM_ADDRESS="RihifyMail@gmail.com"
    MAIL_FROM_NAME="RihifyHelp"
    ```

    ℹ️ This is an email I've specifically made for this project. The **MAIL_PASSWORD** contains sensitive data and is intentionally excluded.

7. **Update your php.ini file** (run php --ini to find it), and make sure the following lines are uncommented (remove ;):

    ```extension=pdo_mysql
    extension=mysqli
    extension=fileinfo
    ```

8. **To upload songs** make sure the following lines look accordingly in your php.ini file:

    ```file_uploads = On
    upload_max_filesize = 10M
    post_max_size = 12M
    ```

9. **Run these scripts inside VS terminal in order:**

    ```composer install
    php artisan key:generate
    php artisan migrate
    php artisan serve
    ```

10. **Open your web browser** and visit the local development URL provided.
