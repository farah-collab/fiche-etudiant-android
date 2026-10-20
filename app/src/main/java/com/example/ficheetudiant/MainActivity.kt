package com.example.ficheetudiant

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var nom: EditText
    private lateinit var date: EditText
    private lateinit var email: EditText
    private lateinit var spinnerClasse: Spinner
    private lateinit var layoutNom: TextInputLayout
    private lateinit var layoutDate: TextInputLayout
    private lateinit var layoutEmail: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nom = findViewById(R.id.id_nom)
        date = findViewById(R.id.id_date)
        email = findViewById(R.id.id_email)
        spinnerClasse = findViewById(R.id.spinnerClasse)
        layoutNom = findViewById(R.id.input_layout_nom)
        layoutDate = findViewById(R.id.input_layout_date)
        layoutEmail = findViewById(R.id.input_layout_email)

        // DatePickerDialog sur le champ date
        date.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, annee, mois, jour ->
                    date.setText("$jour/${mois + 1}/$annee")
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { valider() }
    }

    private fun valider() {
        var ok = true

        if (nom.text.isNullOrBlank()) {
            layoutNom.error = "Nom obligatoire"; ok = false
        } else layoutNom.error = null

        if (date.text.isNullOrBlank()) {
            layoutDate.error = "Date obligatoire"; ok = false
        } else layoutDate.error = null

        if (email.text.isNullOrBlank()) {
            layoutEmail.error = "Email obligatoire"; ok = false
        } else layoutEmail.error = null

        if (ok) confirmer()
    }

    private fun confirmer() {
        val classeChoisie = spinnerClasse.selectedItem.toString()

        AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Enregistrer ces données ?\n${nom.text} - $classeChoisie")
            .setPositiveButton("Oui") { _, _ ->
                val i = Intent(this, SecondActivity::class.java)
                i.putExtra("nom", nom.text.toString())
                i.putExtra("email", email.text.toString())
                i.putExtra("classe", classeChoisie)
                startActivity(i)
            }
            .setNegativeButton("Non", null)
            .show()
    }
}