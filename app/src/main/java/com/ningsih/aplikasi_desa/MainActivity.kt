package com.ningsih.aplikasi_desa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ningsih.aplikasi_desa.databinding.ActivityMainBinding
import com.ningsih.aplikasi_desa.ui.home.HomeFragment
import com.ningsih.aplikasi_desa.ui.login.LoginActivity
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
                R.id.pemberitahuan -> {}
                R.id.profil ->{
                    if (!Prefs.contains(Constant.NIK)){
                        val loginSheet = LoginActivity()
                        loginSheet.show(supportFragmentManager, "login_sheet")
                    }
                }
            }
            return@setOnItemSelectedListener true
        }
    }
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}