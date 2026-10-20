package com.example.ficheetudiant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Récupérer les données envoyées par MainActivity
        val nom = intent.getStringExtra("nom")
        val email = intent.getStringExtra("email")
        val classe = intent.getStringExtra("classe")

        val texte = "Etudiant: $nom - Classe: $classe - Email: $email"
        findViewById<TextView>(R.id.tvInfos).text = texte

        // Partager avec un intent IMPLICITE
        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, texte)
            startActivity(Intent.createChooser(sendIntent, "Partager via"))
        }
    }
}