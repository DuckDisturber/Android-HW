package com.example.android_hw

import android.R.id.shareText
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var EditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        EditText = findViewById(R.id.editText)

        findViewById<Button>(R.id.openSecondButton).setOnClickListener { initSecondActivity() }

        findViewById<Button>(R.id.callButton).setOnClickListener { openCall() }

        findViewById<Button>(R.id.shareButton).setOnClickListener { shareText() }
    }

    private fun readText(): String {
        val text = EditText.text.toString()
        return text
    }
    fun shareText() {
        val text = readText()
        val sendIntent = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, text)
        val c = Intent.createChooser(sendIntent, null)
        startActivity(c)
    }

    fun openCall() {
        val text = readText().replace(Regex("""[\s()-]"""), "")
        val phoneRegex = Regex("""^\+?[0-9]{7,15}$""")
        if (!phoneRegex.matches(text)) {
            return
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", text, null))
        startActivity(intent)
    }

    fun initSecondActivity() {
        val text = readText()
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra(MainActivity2.EXTRA_TEXT, text)
        startActivity(intent)
    }


}