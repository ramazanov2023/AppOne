package com.example.appone.ui.watched

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.appone.AnimeApplication
import com.example.appone.R
import com.example.appone.databinding.FragmentWatchLaterBinding
import com.example.appone.databinding.FragmentWatchedBinding

class WatchedFragment:Fragment() {
    private val viewModel by viewModels<WatchedViewModel>(){
        WatchedViewModelFactory((requireContext().applicationContext as AnimeApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWatchedBinding.inflate(inflater)
        setHasOptionsMenu(true)

        binding.watchedRecycler.adapter = WatchedAdapter()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.watchLaterFragment -> WatchedFragmentDirections.actionWatchedFragmentToWatchLaterFragment()
            R.id.searchFragment -> WatchedFragmentDirections.actionWatchedFragmentToSearchFragment()
        }
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }
}