package com.example.mylibrary.fragmentdata.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mylibrary.R
import com.example.mylibrary.models.MockDataModel

class FragmentPage1(val data: MockDataModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var txtCenterName = view.findViewById<TextView>(R.id.txt_center_name)
        txtCenterName.text = "${data.mail} \n ${data.phone};"
    }
}
