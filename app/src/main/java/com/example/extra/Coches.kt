package com.example.extra

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class Coches : AppCompatActivity() {
    var lista: String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coches)
        val edad: Int = intent.extras?.getInt("mayordeedad") ?: -1
        Toast.makeText(this, "edad $edad", Toast.LENGTH_SHORT).show()
        if (edad.toInt() < 18) {
            findViewById<CheckBox>(R.id.cbC1).isClickable = false
            findViewById<CheckBox>(R.id.cbC2).isClickable = false
            findViewById<CheckBox>(R.id.cbC3).isClickable = false
        }
        findViewById<Button>(R.id.bComprar).setOnClickListener() {
            lista = listaCompra()
            if (lista.isEmpty()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val respuestaCoches: Intent = Intent()
                respuestaCoches.putExtra("lista", lista.toString())
                setResult(Activity.RESULT_OK, respuestaCoches)
            }
            finish()
        }
    }
    fun listaCompra(): String {
        if (findViewById<CheckBox>(R.id.cbC1).isChecked) {
            lista = "coche1\n"
        }
        if (findViewById<CheckBox>(R.id.cbC2).isChecked) {
            lista = "coche2\n"
        }
        if (findViewById<CheckBox>(R.id.cbC3).isChecked) {
            lista = "coche3\n"
        }
        if (findViewById<CheckBox>(R.id.cbM1).isChecked) {
            lista += "moto1\n"
        }
        if (findViewById<CheckBox>(R.id.cbM2).isChecked) {
            lista += "moto2\n"
        }
        if (findViewById<CheckBox>(R.id.cbM3).isChecked) {
            lista += "moto3\n"
        }
        return lista
    }
}




