package com.aceappltd.tictactoe

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import yuku.ambilwarna.AmbilWarnaDialog

class SettingsActivity : AppCompatActivity() {

    var mColorPrimary: Int? = null
    var mColorSecondary: Int? = null
    var mPrimaryButton: Button? = null
    var mSecondaryButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mColorPrimary = ContextCompat.getColor(this@SettingsActivity, R.color.colorPrimary)
        mColorSecondary= ContextCompat.getColor(this@SettingsActivity, R.color.colorPrimaryDark)

        mPrimaryButton = findViewById(R.id.button_primary)
        mSecondaryButton = findViewById(R.id.button_secondary)


        mPrimaryButton?.setOnClickListener { openColorPicker() }
    }

    private fun openColorPicker(){
        Log.d("game", mColorPrimary.toString())
        val colorPicker = AmbilWarnaDialog(this@SettingsActivity, mColorPrimary!!, object:  AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {

            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                mColorPrimary = color
            }

        })
        colorPicker.show()
    }
}
