package com.ningsih.aplikasi_desa.ui.register

import android.R
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ningsih.aplikasi_desa.databinding.ActivityRegisterBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseListPotensi
import com.ningsih.aplikasi_desa.response.ResponseRegister
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

//  list untuk nampung namapadukuhan dan id_potensi
    var listPadukuhan : ArrayList<Map<String,String>> = arrayListOf()
//  list untuk nampung nama padukuhan
    var namaPadukuhan : ArrayList<String> = arrayListOf()

//  untuk menampungkan padukuhan yang dipilih
    var idPotensi : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      panggil data padukuhan dari API
        listPadukuhan(context = this)

        binding.btnRegister.setOnClickListener {
//            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
            validasiInput()
        }


//      menambahkan item namaPadukuhan dari listPadukuhan yang isinya padukuhan dan idPotensi
        listPadukuhan.forEach{ value ->
            namaPadukuhan.add(value["padukuhan"].toString())
        }

//      menambahkan ArrayAdapter pada spinner
        val adapter = ArrayAdapter(this,
            R.layout.simple_spinner_item, namaPadukuhan)
        binding.slctPedukuhan.adapter = adapter
//      ----------------------------------------


//      kondisi ketika user pilih salah satu item pada slctPadukuhan
        binding.slctPedukuhan.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
//                Toast.makeText(this@RegisterActivity, listPadukuhan[position]["id_potensi"].toString(), Toast.LENGTH_SHORT).show()
//              nilai yang dipilih akan masuk ke dalam variabel idPotensi
                idPotensi = listPadukuhan[position]["id_potensi"].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
    
    private fun listPadukuhan(context: Context){
//      clear semua listPadukuhan dan namaPadukuhan
        listPadukuhan.clear()
        namaPadukuhan.clear()

//      ambil nilai list potensi dari API
        NetworkConfig.getService().getAllPotensi().enqueue(object : retrofit2.Callback<ResponseListPotensi>{
            override fun onResponse(
                call: Call<ResponseListPotensi>,
                response: Response<ResponseListPotensi>
            ) {
                if (response.isSuccessful){

//                  tambahkan item pertama untuk placeholder
                    listPadukuhan.add(
                        mapOf(
                            "id_potensi" to "0",
                            "padukuhan" to "Pilih padukuhan"
                        )
                    )
                    namaPadukuhan.add("Pilih Padukuhan")
//                  ==================================

//                  perulangan untuk menambahkan nilai ke masing masing array
                    response.body()?.potensi?.forEach { potensi ->
                        var data =
                            mapOf(
                                "id_potensi" to potensi?.idPotensi.toString(),
                                "padukuhan" to potensi?.judul.toString()
                            )

                        namaPadukuhan.add(potensi!!.judul.toString())
                        listPadukuhan.add(data)
                    }

//                  update adapter array untuk spinner
                    val adapter = ArrayAdapter(context,
                        R.layout.simple_spinner_item, namaPadukuhan)
                    binding.slctPedukuhan.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseListPotensi>, t: Throwable) {
                
            }

        })
    }

    private fun validasiInput() {
        if (binding.nik.text.toString().isEmpty()){
            Toast.makeText(this, "NIK Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if (binding.namaLengkap.text.toString().isEmpty()) {
            Toast.makeText(this, "Nama Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if(binding.password.text.toString().isEmpty()){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()

        }else if (binding.alamat.text.toString().isEmpty()){
            Toast.makeText(this, "Alamat Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if (idPotensi == null){

            Toast.makeText(this, "Pedukuhan Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if (idPotensi == "0"){
            Toast.makeText(this, "Pedukuhan Harus Diisi", Toast.LENGTH_SHORT).show()
            return
        }

        else {
            kirimData()
        }
    }

    private fun kirimData() {
        NetworkConfig.getService().registrasi(
            nik = binding.nik.text.toString(),
            nama = binding.namaLengkap.text.toString(),
            password = binding.password.text.toString(),
            alamat = binding.alamat.text.toString(),
            pedukuhan = idPotensi!!
        ).enqueue(object: retrofit2.Callback<ResponseRegister>{
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                val res =response.body()?.message
                Toast.makeText(this@RegisterActivity, res, Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Toast.makeText(this@RegisterActivity,t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}