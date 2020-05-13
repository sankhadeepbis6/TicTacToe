package com.aceappltd.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private val gameMode = "swap"
    private val pvc = "pvc"
    private val pvp = "pvp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonListener(view: View){

        when(view.id){
            R.id.button_pvc -> {
                var intent = Intent(this@MainActivity, GameActivity::class.java)
                intent.putExtra(gameMode, pvc)
                startActivity(intent)
            }
            R.id.button_pvp -> {
                var intent = Intent(this@MainActivity, GameActivity::class.java)
                intent.putExtra(gameMode, pvp)
                startActivity(intent)
            }
            R.id.button_settings -> {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }
        }

    }

}
