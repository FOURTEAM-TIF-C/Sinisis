package com.example.rifqihakim.sinisis;

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan

    public static final String URL_ADD = "http://sinisis.tk/android/data/tambahNilai.php";
    public static final String URL_ADD_ID = "http://sinisis.tk/android/data/tambahID.php";
    public static final String URL_GET_ALL = "http://sinisis.tk/android/data/tampilSiswa1.php";
    public static final String URL_GET_ALL2 = "http://sinisis.tk/android/data/tampilSiswa2.php";
    public static final String URL_GET_ALL3 = "http://sinisis.tk/android/data/tampilSiswa3.php";
    public static final String URL_GET_ALL4 = "http://sinisis.tk/android/data/tampilSiswa4.php";
    public static final String URL_GET_ALL5 = "http://sinisis.tk/android/data/tampilSiswa5.php";
    public static final String URL_GET_ALL6 = "http://sinisis.tk/android/data/tampilSiswa6.php";
    public static final String URL_GET_EMP = "http://sinisis.tk/android/data/detailSiswa1.php";


    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_IDR = "id_raport";
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
