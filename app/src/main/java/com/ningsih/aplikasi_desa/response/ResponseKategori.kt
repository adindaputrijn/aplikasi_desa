package com.ningsih.aplikasi_desa.response

import com.google.gson.annotations.SerializedName

data class ResponseKategori(

	@field:SerializedName("kategori")
	val kategori: List<KategoriItem?>? = null
)

data class KategoriItem(

	@field:SerializedName("id_kategori")
	val idKategori: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("nama_kategori")
	val namaKategori: String? = null
)
