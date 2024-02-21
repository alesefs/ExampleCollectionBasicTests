package com.example.mylibrary.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.databinding.FragmentViewBindingBinding

class ViewBindingFragment : Fragment() {

    companion object {
        fun newInstance() = ViewBindingFragment()
    }

    private lateinit var viewModel: ViewBindingViewModel
    private lateinit var binding: FragmentViewBindingBinding //nome do layout com cameoCase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentViewBindingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewBindingViewModel::class.java)
        // TODO: Use the ViewModel

        //we can access our UI components, as shown below
        binding.message.text = "Hello there"
        binding.content.text = "This is an example of view binding in Android"
    }

}