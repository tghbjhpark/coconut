package com.hoya.ddory.coconut.view.addaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.hoya.ddory.coconut.R
import com.hoya.ddory.coconut.databinding.FragmentAddaccountBinding

class AddAccountFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentAddaccountBinding

    @LayoutRes
    fun getAdapterItemLayout(): Int {
        return R.layout.add_account_dropdown_popup_item
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
            requireContext(),
            getAdapterItemLayout(),
            resources.getStringArray(R.array.target_currency)
        )
        viewDataBinding.outlinedExposedDropdown.setAdapter(adapter)
    }
}
