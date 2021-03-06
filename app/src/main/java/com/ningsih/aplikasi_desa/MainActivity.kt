package com.ningsih.aplikasi_desa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ningsih.aplikasi_desa.databinding.ActivityMainBinding
import com.ningsih.aplikasi_desa.ui.home.HomeFragment
import com.ningsih.aplikasi_desa.ui.login.LoginActivity
import com.ningsih.aplikasi_desa.ui.pengaduan.ListPengaduanActivity
import com.ningsih.aplikasi_desa.ui.pengaduan.PengaduanActivity
import com.ningsih.aplikasi_desa.utils.Constant
import com.pixplicity.easyprefs.library.Prefs

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    changeFragment(HomeFragment())
                }
                R.id.pemberitahuan -> {
                    if (Prefs.contains(Constant.NIK)) {
                        val pengaduan = PengaduanActivity()
//                    pengaduan.show(supportFragmentManager, "pengaduan")
                        val intent = Intent(this, pengaduan::class.java)
                        startActivity(intent)
                    }else {
                        val loginSheet = LoginActivity()
                        loginSheet.show(supportFragmentManager, "login_sheet")
                    }
                }
                R.id.profil ->{
                    if (Prefs.contains(Constant.NIK)){
                        
                        val listpengaduan = ListPengaduanActivity()
//                    pengaduan.show(supportFragmentManager, "pengaduan")
                        val intent = Intent(this, listpengaduan::class.java)
                        startActivity(intent)
//                        val loginSheet = LoginActivity()
//                        loginSheet.show(supportFragmentManager, "login_sheet")
                    }else {
                        val loginSheet = LoginActivity()
                        loginSheet.show(supportFragmentManager, "login_sheet")
                    }
                }
                R.id.logout ->{
                    Prefs.clear()
                    recreate()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}