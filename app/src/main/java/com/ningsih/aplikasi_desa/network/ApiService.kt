package com.ningsih.aplikasi_desa.network

import com.ningsih.aplikasi_desa.response.*
import retrofit2.http.*

interface ApiService {

    @GET("berita")
    fun getAllBerita(): retrofit2.Call<ResponseBerita>

    @GET("detailBerita")
    fun getAllDetailBerita(@Path("id_berita")idBerita: String?) : retrofit2.Call<ResponseBerita>

    @GET("kategori")
    fun getAllKategori() : retrofit2.Call<ResponseKategori>

    @GET("galeri")
    fun getAllGaleri() : retrofit2.Call<ResponseGaleri>

    @GET("layanan")
    fun getAllLayanan() : retrofit2.Call<ResponseListLayanan>

    @GET("detailLayanan")
    fun getAllDetailLayanan(@Path("id_layanan")idLayanan: String?) : retrofit2.Call<ResponseListLayanan>

    @GET("lembaga")
    fun getAllLembaga() : retrofit2.Call<ResponseListLembaga>

    @GET("detailLembaga")
    fun getAllDetailLembaga(@Path("id_lembaga")idLembaga: String?) : retrofit2.Call<ResponseListLembaga>

    @GET("potensi")
    fun getAllPotensi() : retrofit2.Call<ResponseListPotensi>

    @GET("detailPotensi")
    fun getAllDetailPotensi(@Path("id_potensi")idPotensi: String?) : retrofit2.Call<ResponseListPotensi>

    @GET("program")
    fun getAllProgram() : retrofit2.Call<ResponseListProgram>

    @GET("detailProgram")
    fun getAllDetailProgram(@Path("id_program")idProgram: String?) : retrofit2.Call<ResponseListProgram>

    @FormUrlEncoded
    @POST("user/register")
    fun registrasi(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("pedukuhan") pedukuhan: String,
        ): retrofit2.Call<ResponseRegister>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("nik") nik: String,
    ): retrofit2.Call<ResponseLogin>

    @FormUrlEncoded
    @POST("pengaduan")
    fun pengaduan(
        @Field("pengaduan") pengaduan: String,
        @Field("nama") nama: String

    ): retrofit2.Call<ResponsePengaduan>


    @FormUrlEncoded
    @POST("pengaduan/submit")
    fun pengaduanSubmit(
        @Field("pengaduan") pengaduan: String,
        @Field("nama") nama: String

    ): retrofit2.Call<GeneralResponse>

}