package com.example.mynews.repos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.mynews.R
import com.example.mynews.databinding.HomeFragmentBinding
import com.example.mynews.databinding.ReposFragmentBinding
import com.example.mynews.home.HomeFragmentDirections
import com.example.mynews.home.HomeViewModel

class ReposFragment : Fragment() {

    companion object {
        fun newInstance() = ReposFragment()
    }

    private lateinit var binding: ReposFragmentBinding

    private lateinit var viewModelFactory: ReposViewModelFactory

    lateinit var viewModel: ReposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.repos_fragment, container, false)

        val adapter = MyNewsAdapter(MyNewsAdapter.OnClickListener{
            viewModel.openNews(it)
        })
        binding.infoList.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReposViewModel::class.java)

        binding.lifecycleOwner = this

        //viewModelFactory = ReposViewModelFactory()

        binding.viewModel =  viewModel

    }

}
