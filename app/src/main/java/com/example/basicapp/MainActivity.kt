package com.example.basicapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mylibrary.commons.AppOpenExternalListener
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.models.MockDataModel
import com.example.mylibrary.routers.BasicAppAplication
import com.example.mylibrary.routers.BasicAppDeeplinks

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Main Activity"

        //openLibrary()

        val button: Button = findViewById(R.id.mainButton)
        button.setOnClickListener {
            openLibrary()
        }
    }

    private fun openLibrary() {
        this.let { context ->
            val build = BasicAppAplication.Builder(
                context = context,
                dataModel = mockData()
            )
            build.openSPA(OpenSPA(context, mockData()))
            build.openMainMenu()
            build.build()
        }
    }

    private fun mockData() = MockDataModel(
        name = "michael jackson",
        age = 50,
        mail = "mj@productions.com",
        phone = "911112222",
        favouriteColor = "light blue",
        isFirstLogin = true
    )

    class OpenSPA(
        private val context: Context, private val mockData: MockDataModel
    ) : AppOpenExternalListener {

        private var authorize: String = "App"

        override fun onOpenSpaHelp() {
            BasicAppAplication.Builder(
                context = context,
                dataModel = mockData
            ).build().openSPA(this.apply {
                Toast.makeText(context, "$authorize Ajuda", Toast.LENGTH_SHORT).show()
            })
        }

    }
}