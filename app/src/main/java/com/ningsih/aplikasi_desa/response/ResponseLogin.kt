package com.ningsih.aplikasi_desa.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class User(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("pedukuhan")
	val pedukuhan: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
