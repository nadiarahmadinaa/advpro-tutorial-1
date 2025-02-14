# Refleksi 1
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

# Refleksi 2
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