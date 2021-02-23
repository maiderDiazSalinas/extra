package com.example.extra
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
class Datos : AppCompatActivity() {
    lateinit var edadDatos: EditText
    lateinit var nombreDatos: EditText
    lateinit var apellidosDatos: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos) // ----- este es el layout correspondiente.
        /*En esta ventana, solicitamos datos. Tendremos que enviar datos a la ventana principal y
        en este caso es nombre + apellidos (para mostrar un mensaje por pantalla de "hola XXX" y
        la edad la necesitamos para enviar a la pantalla de coches/motos
        He creado una funcion a la que llamo desde el botón. */
        /*Enviar datos a la ventana principal*/
        findViewById<Button>(R.id.bAddDatos).setOnClickListener() {
            if (comprobarDatos()) {
                val respuesta: Intent = Intent()
                respuesta.putExtra("nombre", findViewById<EditText>(R.id.txtNombre).text.toString())
                respuesta.putExtra(
                    "apellidos",
                    findViewById<EditText>(R.id.txtApellidos).text.toString()
                )
                respuesta.putExtra("edad", findViewById<EditText>(R.id.txtEdad).text.toString())
                setResult(Activity.RESULT_OK, respuesta)

                // println("esta todo ok.")
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
    fun comprobarDatos(): Boolean {
        /*Recoger datos en VAL. No utilizar VAR*/
        nombreDatos = findViewById<EditText>(R.id.txtNombre)
        apellidosDatos = findViewById<EditText>(R.id.txtApellidos)
        edadDatos = findViewById<EditText>(R.id.txtEdad)
        /*escribo para ver que aparece. Estos 3 print se pueden omitir*/
        //   println(nombreDatos.text)
        //    println(apellidosDatos.text)
        //    println(edadDatos.text)
        if (nombreDatos.text.isEmpty() || apellidosDatos.text.isEmpty() || edadDatos.text.isEmpty()) {
            // Toast.makeText(this, "Falta algun dato", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }
}