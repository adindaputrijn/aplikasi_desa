package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseListProgram(

	@field:SerializedName("program")
	val program: List<ProgramItem?>? = null
)

@Parcelize
data class ProgramItem(

	@field:SerializedName("nama_program")
	val namaProgram: String? = null,

	@field:SerializedName("deskripsi_program")
	val deskripsiProgram: String? = null,

	@field:SerializedName("id_program")
	val idProgram: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null
) : Parcelable
