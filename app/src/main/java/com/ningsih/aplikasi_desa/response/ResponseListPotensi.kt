package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseListPotensi(

	@field:SerializedName("potensi")
	val potensi: List<PotensiItem?>? = null
)

@Parcelize
data class PotensiItem(

	@field:SerializedName("id_potensi")
	val idPotensi: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("lang")
	val lang: Double? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) :Parcelable
