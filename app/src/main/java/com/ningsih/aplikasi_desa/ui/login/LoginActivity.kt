package com.ningsih.aplikasi_desa.ui.login

import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseLogin
import com.ningsih.aplikasi_desa.ui.home.HomeFragment
import com.ningsih.aplikasi_desa.ui.register.RegisterActivity
import com.ningsih.aplikasi_desa.utils.Constant
import com.pixplicity.easyprefs.library.Prefs
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BottomSheetDialogFragment() {

    private var behavior: BottomSheetBehavior<View>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheets = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(requireContext(), R.layout.activity_login, null)
        val root = view.findViewById<LinearLayout>(R.id.root)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,

            )

        layoutParams.height = getScreenHeight()
        root.layoutParams = layoutParams
        bottomSheets.setContentView(view)

        behavior = BottomSheetBehavior.from(view.parent as View)

        bottomSheets.setOnShowListener {
            val btmSheet = (it as BottomSheetDialog)
                .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behave = BottomSheetBehavior.from(btmSheet!!)
            behave.state = BottomSheetBehavior.STATE_EXPANDED

            behave.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behave.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

            })
        }

        initViewview(view)

        return bottomSheets

    }

    private fun initViewview(view: View?) {
        val nik = view?.findViewById<TextInputEditText>(R.id.nik)
        val btnSignIn = view?.findViewById<Button>(R.id.btn_kirim)
        val textViewRegister = view?.findViewById<TextView>(R.id.register)

        textViewRegister?.setOnClickListener {
            Intent(requireActivity(), RegisterActivity::class.java).apply {
                startActivity(this)
            }
        }

        btnSignIn?.setOnClickListener {
            doLogin(nik?.text.toString())
        }
    }

    private fun doLogin(nik: String) {
        NetworkConfig.getService().login(nik)
            .enqueue(object : retrofit2.Callback<ResponseLogin>{
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful){
                        Prefs.putString(Constant.NIK, response.body()?.user?.nik)
                        Prefs.putString(Constant.NAMA, response.body()?.user?.nama)
                        dismiss()
                        requireActivity().recreate()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }

    private fun getScreenHeight(): Int{
        return Resources.getSystem().displayMetrics.heightPixels
    }
}

