package com.hoya.ddory.coconut.view.addaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hoya.ddory.coconut.databinding.FragmentAddaccountBinding

class AddAccountFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentAddaccountBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentAddaccountBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewModel = AddAccountViewModel()
        }
        return viewDataBinding.root
    }
}
