package com.ningsih.aplikasi_desa.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponsePengaduan(

	@field:SerializedName("pengaduan")
	val pengaduan: List<PengaduanItem?>? = null
)
@Parcelize
data class PengaduanItem(

	@field:SerializedName("isi_pengaduan")
	val isiPengaduan: String? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("ditambahkan_pada")
	val ditambahkanPada: String? = null,

	@field:SerializedName("is_selesai")
	val isSelesai: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("id_layanan")
	val idLayanan: Int? = null,

	@field:SerializedName("pedukuhan")
	val pedukuhan: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("ditambahkan_oleh")
	val ditambahkanOleh: String? = null,

	@field:SerializedName("deskripsi_layanan")
	val deskripsiLayanan: String? = null,

	@field:SerializedName("nama_layanan")
	val namaLayanan: String? = null,

	@field:SerializedName("id_pengaduan")
	val idPengaduan: Int? = null
): Parcelable
