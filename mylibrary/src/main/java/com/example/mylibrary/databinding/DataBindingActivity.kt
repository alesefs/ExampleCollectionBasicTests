package com.example.mylibrary.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import kotlinx.android.synthetic.main.layout_activity_data_binding.view.*

class DataBindingActivity : AppCompatActivity(), ClickHandler {

    private lateinit var binding: LayoutActivityDataBindingBinding

    private val personalInfo: PersonalInfo = PersonalInfo("Elena Allison", "25")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.layout_activity_data_binding)
        binding.handler = this
//        binding.doneButton.setOnClickListener { }

        binding.personalInfo = personalInfo

        with(binding.rvPersonalInfo) {
            layoutManager = LinearLayoutManager(
                this@DataBindingActivity,
                RecyclerView.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = DataBindingAdapter(personalInfoList()) { item ->
                when {
                    item.name.equals("nome 1", true) -> {
                        Toast.makeText(context, "clicked ${item.name}", Toast.LENGTH_LONG).show()
                    }
                    item.name.equals("nome 2", true) -> {
                        Toast.makeText(context, "clicked ${item.name}", Toast.LENGTH_LONG).show()
                    }
                    item.name.equals("nome 3", true) -> {
                        Toast.makeText(context, "clicked ${item.name}", Toast.LENGTH_LONG).show()
                    }
                    item.name.equals("nome 4", true) -> {
                        Toast.makeText(context, "clicked ${item.name}", Toast.LENGTH_LONG).show()
                    }
                    item.name.equals("nome 5", true) -> {
                        Toast.makeText(context, "clicked ${item.name}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onButtonClick(v: View) {
        Toast.makeText(v.context,"Button clicked",Toast.LENGTH_LONG).show();
    }

    private fun personalInfoList() : List<PersonalInfo> = listOfNotNull(
        PersonalInfo("nome 1", "05"),
        PersonalInfo("nome 2", "15"),
        PersonalInfo("nome 3", "25"),
        PersonalInfo("nome 4", "35"),
        PersonalInfo("nome 5", "45")
    )
}