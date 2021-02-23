package com.example.extra
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    /*1. crear todos los layout con todas las opciones necesarias.
    * 2. Si desde la ventana principal necesitamos enviar datos o recibir datos, hay que mandar un intent con
    * los requestcode.
    * Para ello necesitamos crear una variable global para saber que actividad hemos enviado
    *  y/o recibido.
    * 3. En el botón primero queremos llamar a una actividad,
    *  */

    // (2.)esta es la variable global para saber la actividad.
    var enviardatos: Int = 1
    var age: Int = -1
    lateinit var name: String
    lateinit var surname: String
    lateinit var listado: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* desde aqui escogemos a que actividad vamos a ir, en funcion del botón cliqueado*/
        findViewById<Button>(R.id.Bdatos).setOnClickListener() {
            /*desde aqui tenemos que abrir la ventana de datos, para ello necesitamos
            * 1- crear una variable Intent, de clase a la que queremor ir
            * 2- comenzar la actividad -- startActivity. En este caso necesitamos recibir algo,
            *  asi que tiene que ser forResult
            * Si fuera una actividad sin resultado seria startActivity(iraDatos)
            * 3- como hemos dicho que necesitamos recibir datos, debemos tener el id (int)
            *  de la actividad
            * y tenemos que sobreescribir la funcion -- override fun onActivityResult
            * */
            val iraDatos: Intent = Intent(this, Datos::class.java)
            startActivityForResult(iraDatos, enviardatos)
        }
        findViewById<Button>(R.id.BCoches).setOnClickListener() {
            if (age<0) Toast.makeText(
                this, "Tienes que insertar antes los datos",
                Toast.LENGTH_SHORT
            ).show()
            else {
                val iraCoches: Intent = Intent(this, Coches::class.java)
                iraCoches.putExtra("mayordeedad", age)
                startActivityForResult(iraCoches, 2)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            enviardatos -> {
                if (resultCode == Activity.RESULT_OK) {
                    age = data?.getStringExtra("edad").toString().toInt()
                    name = data?.getStringExtra("nombre").toString()
                    surname = data?.getStringExtra("apellidos").toString()
                    Toast.makeText(
                        this, "hola $name edad $age ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this, "No hay datos. Insertelos antes de comprar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            2-> if (resultCode == Activity.RESULT_OK) {
                    listado = data?.getStringExtra("lista").toString()
                    Toast.makeText(this, listado, Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "mal", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
