package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseGaleri(

	@field:SerializedName("galeri")
	val galeri: List<GaleriItem?>? = null
)

@Parcelize
data class GaleriItem(

	@field:SerializedName("id_galeri")
	val idGaleri: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("judul_galeri")
	val judulGaleri: String? = null
) : Parcelable
