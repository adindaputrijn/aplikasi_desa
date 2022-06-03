package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseBerita(

	@field:SerializedName("berita")
	val berita: List<BeritaItem?>? = null
)

@Parcelize
data class BeritaItem(

	@field:SerializedName("deskripsi_berita")
	val deskripsiBerita: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("id_berita")
	val idBerita: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("judul_berita")
	val judulBerita: String? = null
) : Parcelable
