package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseListLayanan(

	@field:SerializedName("layanan")
	val layanan: List<LayananItem?>? = null
)
@Parcelize
data class LayananItem(

	@field:SerializedName("id_layanan")
	val idLayanan: Int? = null,

	@field:SerializedName("deskripsi_layanan")
	val deskripsiLayanan: String? = null,

	@field:SerializedName("nama_layanan")
	val namaLayanan: String? = null
) : Parcelable
