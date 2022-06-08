package com.ningsih.aplikasi_desa.ui.pengaduan

import android.R
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ningsih.aplikasi_desa.databinding.ActivityPengaduanBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.GeneralResponse
import com.ningsih.aplikasi_desa.response.ResponseListLayanan
import com.ningsih.aplikasi_desa.response.ResponseListPotensi
import com.ningsih.aplikasi_desa.response.ResponsePengaduan
import com.ningsih.aplikasi_desa.utils.Constant
import com.pixplicity.easyprefs.library.Prefs
import retrofit2.Call
import retrofit2.Response

class PengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaduanBinding

    var listLayanan : ArrayList<Map<String,String>> = arrayListOf()
    var namaLayanan : ArrayList<String> = arrayListOf()
    var idLayanan : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listLayanan(context = this)

        binding.btnKirim.setOnClickListener {
            validasiInput()
        }

        listLayanan.forEach{ value ->
            namaLayanan.add(value["layanan"].toString())
        }

        val adapter = ArrayAdapter(this,
            R.layout.simple_spinner_item, namaLayanan)
        binding.slctLayanan.adapter = adapter

        binding.slctLayanan.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                idLayanan = listLayanan[position]["id_layanan"].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun listLayanan(context: Context){
        listLayanan.clear()
        namaLayanan.clear()

        NetworkConfig.getService().getAllLayanan().enqueue(object : retrofit2.Callback<ResponseListLayanan>{
            override fun onResponse(
                call: Call<ResponseListLayanan>,
                response: Response<ResponseListLayanan>
            ) {
                if (response.isSuccessful){
                    listLayanan.add(
                        mapOf(
                            "id_layanan" to "0",
                            "layanan" to "Pilih Layanan"
                        )
                    )
                    namaLayanan.add("Pilih Layanan")

                    response.body()?.layanan?.forEach { layanan ->
                        var data =
                            mapOf(
                                "id_layanan" to layanan?.idLayanan.toString(),
                                "layanan" to layanan?.namaLayanan.toString()
                            )

                        namaLayanan.add(layanan!!.namaLayanan.toString())
                        listLayanan.add(data)
                    }
                    val adapter = ArrayAdapter(context,
                        R.layout.simple_spinner_item, namaLayanan)
                    binding.slctLayanan.adapter = adapter
                }
            }
            override fun onFailure(call: Call<ResponseListLayanan>, t: Throwable) {

            }

        })
    }


    private fun validasiInput() {
        if (binding.pengaduan.text.toString().isEmpty()) {
            Toast.makeText(this, "Keluhan Harus Di isi", Toast.LENGTH_SHORT).show()
        }else if(idLayanan == null){
            Toast.makeText(this, "Layanan Harus Di isi", Toast.LENGTH_SHORT).show()
        }else if (idLayanan == "0"){
            Toast.makeText(this, "Layanan Harus Diisi", Toast.LENGTH_SHORT).show()
            return
        }else{
            kirimData()
        }
    }

    private fun kirimData() {
        NetworkConfig.getService().pengaduanSubmit(
            pengaduan = binding.pengaduan.text.toString(),
            nama = Prefs.getString(Constant.NIK),
            idLayanan = idLayanan.toString()
        ).enqueue(object : retrofit2.Callback<GeneralResponse>{
            override fun onResponse(
                call: Call<GeneralResponse>,
                response: Response<GeneralResponse>
            ) {
                Toast.makeText(this@PengaduanActivity, response.body()?.message.toString(), Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Toast.makeText(this@PengaduanActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}