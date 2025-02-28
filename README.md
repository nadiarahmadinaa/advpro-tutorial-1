Deployment Link: [Live Site](https://unaware-sharron-advprog-deploy-86f0599a.koyeb.app/)
<details>
<summary> Modul 1 </summary>


## Refleksi 1
Dari segi clean code, saya sudah menerapkan beberapa hal berikut:
- Meaningful names: nama-nama seperti untuk variabel dan function sudah cukup jelas dan mewakili.
- Function: telah terdapat beberapa function untuk mendukung object oriented operations layaknya yang ada di ProductController.
- Objects and Data Structure: saya sudah menerapkan objects dan data structure, seperti list
  dan object seperti Product.


Dari segi secure coding, belum ada implementasi dari authorization dan authentication, karena belum ada fitur yang memerlukan hal tersebut.
Untuk input data validation dan dan output data encoding juga belum dilakukan, dan ini menjadi refleksi untuk pengerjaan kedepannya.
Meskipun demikian, saya sudah menerapkan uuid untuk id number dari input product.


Dari modul minggu ini, saya dapat melakukan improvement dari segi:
- Comments: belum ada dokumentasi kode untuk functions yang sudah diterapkan.
- Error handling & input data validation: masih terdapat beberapa validasi yang belum diterapkan, seperti ketika melakukan edit dan mengubah
  quantity menjadi karakter non-bilangan, sehingga menghasilkan error.


## Refleksi 2
1. Setelah membuat unit test, saya merasa lebih percaya akan kode yang telah dibuat, baik dalam positive maupun negative case.
   Dalam suatu class, sebaiknya jumlah unit test disesuaikan dengan kerumitan kode dan dapat dibagi secara umum menjadi positive atau happy path, negative path, dan edge cases.
   Meskipun suatu kode memiliki code coverage 100%, tidak berarti bahwa kode tersebut error free. Code coverage hanya memastikan bahwa semua line code telah di test.
   Edge cases, serta positive negative test tidak termasuk ke dalam code coverage, sehingga kode harus ditinjau kembali testnya agar sesuai.
2. Ketika kita membuat functional test baru yang memverifikasi jumlah produk dalam product list, maka itu akan menghasilkan code redundancy
   dan mengurangi maintainability. Hal ini terjadi karena logic yang terdapat di functional test ini adalah duplikat dari functional test yang sudah ada.
   Hal tersebut akan membingungkan dan membuat kode sulit di-maintain karena harus mengupdate kedua kode ketika ingin di maintain. Selain itu, code duplication tidak efektif dengan menjalankan kode yang sama dua kali.
   Improvement yang dapat dilakukan adalah:
- Membuat base class untuk setup test code: functional test classes akan extend dari base class ini untuk menjaga readability,
  menghindari redundancy, dan menjaga keefektifan kode.
- Parameterized testing: menghindari banyaknya test yang serupa dan mengurangi redundancy code. Dalam hal ini, kita bisa pakai `@ParameterizedTest` dari JUnit
</details>
<details>
<summary>Modul 2</summary>


## Refleksi 1
Code quality issues yang saya solve:
1. Unused import dari org.springframework.web.bind.annotation.*
   yang mengimport semua modul, namun hanya dipakai beberapa. Untuk readability dan best practice juga, saya ubah menjadi import masing-masing modul secara independent.
2. Non-private constructor di EshopApplication dengan `@SuppressWarnings("java:S1118")`. Warning ini saya supress karena class yang dinyatakan bermasalah
   bukan utility class.
3. Menghapus public modifier dari ProductService karena tidak dibutukan. Hal ini saya lakukan juga untuk menghindari pemberian akses berlebih.


## Refleksi 2
Dari CI/CD yang telah dibuat pada modul ini, sepertinya sudah sesuai dengan definisi dari Continuous Integration dan Continuous Deployment.
Secara integration, saya telah menerapkan workflow ci.yml yang berfungsi untuk melakukan testing setiap ada perubahan di repository.
Hal ini membantu integration karena jika unit test gagal, maka branch tidak dapat di merge sebelum di resolve. Dengan adanya CI, setiap perubahan kode di tes secara otomatis untuk memastikan bahwa tidak ada error sebelum kode tersebut dapat digabungkan ke dalam branch utama. Jika unit test gagal, maka merge tidak dapat dilakukan sebelum masalahnya di resolve. Hal ini membantu menjaga kualitas kode dan mencegah error dari masuk ke dalam kode produksi.


Secara deployment, github ini telah terhubung ke koyeb sehingga akan auto deploy setiap terdapat changes di main. Hal ini memastikan bahwa changes yang sudah lolos tes
akan dapat disediakan ke pengguna dengan cepat dan efisien, menghindari kesalahan deployment.
</details>
<details open>
<summary>Modul 3</summary>


## Refleksi 1
#### SOLID principles yang saya terapkan dalam modul ini:
- SRP: Setiap class harus punya single responsibility. Dalam kode sebelumnya, CarRepository dan ProductRepository memiliki
  2 responsibility, yaitu UUID generation dan data storage handling. Saya menerapkan prinsip SRP dengan memisahkan kedua responsibility ini,
  sehingga UUID generation dilakukan on instantiation di model.
- OCP: Setiap class harus open terhadap extension, tapi closed terhadap modification. Untuk prinsip ini, saya membuat GenericService yang diimplement oleh
CarServiceImpl dan ProductServiceImpl. Mekanisme ini memudahkan bagi programmer selanjutnya untuk membuat implementasi service lain yang serupa, tanpa harus buat
interface yang baru.
- LSP: Sebuah subclass harus bisa mewakili superclassnya tanpa merusak sistem. Saya mengimplementasikan prinsip ini dengan memisahkan ProductController 
dan CarController, serta membuat CarController tidak mengimplemen ProductController karena CarController 
tidak bisa mewakili ProductController.
- ISP: Implementasi tidak dapat dipaksakan kepada user. Maka dari itu, saya implementasi writable dan readable repository, sehingga
bisa membantu untuk pemisahan user privilege nantinya.
- DIP: High level module seharusnya tidak depend ke low level module. Dalam prinsip ini, saya menerapkan interface yang akan diimplement oleh solid class,
seperti halnya dalam GenericService atau Writable dan Readable Repository.


## Refleksi 2
Secara umum, SOLID principle membuat kode lebih mudah dimaintain dan dibaca. Programmer selanjutnya juga dapat menambahkan fitur
tanpa merusak kode yang sudah ada. Selain itu, modifikasi kode yang sudah ada juga bisa dibuat lebih mudah dan aman. Implementasi yang
menggunakan interface akan memudahkan dalam membuat implementasi serupa dari yang sudah ada, sehingga kode efektif. SOLID principle
juga mencegah adanya kode yang tidak terpakai, menjaga codebase tetap rapih untuk seterusnya.

## Refleksi 3
Kekurangan ketika tidak mengimplementasi prinsip SOLID adalah usaha yang signifikan ketika ingin memperbaiki kode, sesedikit apapun itu.
Seperti halnya mengubah tampilan UI dapat mengubah logika bisnis, dan mengubah codebase secara signifikan. Selain itu, ada resiko
merusak sistem ketika menambahkan atau memperbaiki fitur. Dalam hal pengguna, akan sulit apabila ingin dibuat segmentasi seperti
user privileges, dan harus mengubah codebase secara signifikan lagi. Selain mengubah codebase, perubahan yang ingin dibuat juga dapat
mempengaruhi database, sehingga membuat proses maintenance atau penambahan fitur menjadi lama.
</details>



