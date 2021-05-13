package com.hoya.ddory.coconut.view.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hoya.ddory.coconut.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentAccountBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentAccountBinding.inflate(inflater, container, false).apply {
            viewModel = AccountViewModel().apply {
                addAccountEvent.observe(viewLifecycleOwner) {
                    findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToAddAccountFragment())
                }
            }
        }
        return viewDataBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewModel?.onResume(requireContext())
    }

    override fun onPause() {
        super.onPause()
        viewDataBinding.viewModel?.onPause()
    }

    companion object {
        fun newInstance() = AccountFragment()
    }
}
