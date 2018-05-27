package com.example.rifqihakim.sinisis;

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA

    public static final String URL_ADD = "http://192.168.1.11/SinisisAndroid/tambahNilai.php";
    public static final String URL_GET_ALL = "http://192.168.1.11/SinisisAndroid/tampilSiswa1.php";
    public static final String URL_GET_ALL2 = "http://192.168.1.11/SinisisAndroid/tampilSiswa2.php";
    public static final String URL_GET_ALL3 = "http://192.168.1.11/SinisisAndroid/tampilSiswa3.php";
    public static final String URL_GET_ALL4 = "http://192.168.1.11/SinisisAndroid/tampilSiswa4.php";
    public static final String URL_GET_ALL5 = "http://192.168.1.11/SinisisAndroid/tampilSiswa5.php";
    public static final String URL_GET_ALL6 = "http://192.168.1.11/SinisisAndroid/tampilSiswa6.php";
    public static final String URL_GET_EMP = "http://192.168.1.11/SinisisAndroid/detailSiswa1.php";


    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_SMT = "semester";
    public static final String KEY_EMP_RATA = "nilaiRata";
    public static final String KEY_EMP_GRADE = "grade";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_NIS = "nis";
    public static final String TAG_NAMA_SISWA = "nama";
    public static final String TAG_TEMPAT_LAHIR = "tempat";
    public static final String TAG_TGL_LAHIR = "lahir";
    public static final String TAG_TAHUN_AJARAN = "tahun";
    public static final String TAG_JK = "jenis";
    public static final String TAG_AGAMA = "agama";

    //ID siswa
    public static final String EMP_NIS = "emp_nis";
}
