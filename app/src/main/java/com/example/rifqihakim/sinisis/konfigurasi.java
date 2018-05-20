package com.example.rifqihakim.sinisis;

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA

    //public static final String URL_ADD="http://192.168.1.11/Android/pegawai/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.1.11/SinisisAndroid/tampilSiswa1.php";
    public static final String URL_GET_EMP = "http://192.168.1.11/Android/pegawai/detailSiswa1.php?id=";
    //public static final String URL_UPDATE_EMP = "http://192.168.1.11/Android/pegawai/updatePgw.php";
    //public static final String URL_DELETE_EMP = "http://192.168.1.11/Android/pegawai/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_NIS = "nis";
    public static final String KEY_EMP_NAMA_SISWA = "nama";
    public static final String KEY_EMP_TEMPAT_LAHIR = "tempat";
    public static final String KEY_EMP_TGL_LAHIR = "lahir";
    public static final String KEY_EMP_TAHUN_AJARAN = "tahun";
    public static final String KEY_EMP_JK = "jenis";
    public static final String KEY_EMP_AGAMA = "agama";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_NIS = "nis";
    public static final String TAG_NAMA_SISWA = "nama";
    public static final String TAG_TEMPAT_LAHIR = "tempat";
    public static final String TAG_TGL_LAHIR = "lahir";
    public static final String TAG_TAHUN_AJARAN = "tahun";
    public static final String TAG_JK = "jenis";
    public static final String TAG_AGAMA = "agama";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_NIS = "emp_nis";
}
