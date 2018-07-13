# CatalogMovie

Belajar Menggunakan Retrofit untuk membuat aplikasi android menampilkan koleksi movie dari themoviedb.org menggunakan API

Buat account pada www.themoviedb.org kemudian masuk ke account -> dan API untuk mendapatkan API Key.
Gunakankanlah API Key(v3 auth)

    
Gunakan url ini untuk mencari data movie
https://api.themoviedb.org/3/search/movie?api_key=<API KEY ANDA>&language=en-US&query=<INPUTAN NAMA MOVIE>

Gunakan url ini untuk mendapatkan poster movie.

http://image.tmdb.org/t/p/w185/5N20rQURev5CNDcMjHVUZhpoCNC.jpg

tw185 pada link di atas merupakan ukuran poster. Tersedia beberapa ukuran yang dapat Anda gunakan w92, w154, w185, w342, w500, w780, dan original. 

5N20rQURev5CNDcMjHVUZhpoCNC.jpg dapat anda dapatkan dari json poster_path dari hasil search movie anda.

Penjelasan mengenai poster dapat dilihat pada halaman ini.
https://developers.themoviedb.org/3/configuration/get-api-configuration .


 


[1]Halaman Utama.(https://user-images.githubusercontent.com/646431/42681774-d66bd36c-86b2-11e8-8228-ae399f3bc018.JPG)



[2]Halaman Tampilan RecyclerView dari koleksi movies berdasarkan rate .(https://user-images.githubusercontent.com/646431/42681776-d69b7f9a-86b2-11e8-8e5e-7393feb4c28d.JPG)



[3]Halaman Search Moviews.(https://user-images.githubusercontent.com/646431/42681773-d63f1cc8-86b2-11e8-840c-ea0152599791.JPG)

