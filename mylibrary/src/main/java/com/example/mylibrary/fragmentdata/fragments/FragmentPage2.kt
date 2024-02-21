package com.example.mylibrary.fragmentdata.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.databinding.FragmentViewBindingBinding
import com.example.mylibrary.models.MockDataModel
import com.example.mylibrary.viewbinding.ViewBindingViewModel

class FragmentPage2() : Fragment() {

    companion object {
        fun newInstance() = FragmentPage2()
    }

    private lateinit var data: MockDataModel
    private lateinit var viewModel: ViewBindingViewModel
    private lateinit var binding: FragmentViewBindingBinding //nome do layout com cameoCase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentViewBindingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewBindingViewModel::class.java]
        // TODO: Use the ViewModel

        arguments?.let { bundle ->
            if (bundle.containsKey(Constants.EXTRA_DATA)) {
                data = arguments?.get(Constants.EXTRA_DATA) as MockDataModel
            }
        }
//        data = requireArguments().getParcelable(Constants.EXTRA_DATA)!!

        //we can access our UI components, as shown below
        binding.message.text = "Hello there ${data.name}"
        binding.content.text = "This is an example of view binding in Android ${data.favouriteColor}"
    }
}
