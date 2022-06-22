package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseListLembaga(

	@field:SerializedName("lembaga")
	val lembaga: Lembaga? = null
)

@Parcelize
data class LembagaItem(

	@field:SerializedName("id_lembaga")
	val idLembaga: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("nama_lembaga")
	val namaLembaga: String? = null
) : Parcelable

data class Lembaga(

	@field:SerializedName("lembaga")
	val lembaga: List<LembagaItem?>? = null
)
