package com.rentme.rentme.ui.main.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.TypesAdapter
import com.rentme.rentme.databinding.FragmentTypesBinding
import com.rentme.rentme.model.Types


class TypesFragment : Fragment() {

    private var _binding: FragmentTypesBinding? = null
    private val adapter by lazy { TypesAdapter() }

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTypesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getAllTypes()

    }


    private fun initViews() {
        binding.rvTypes.layoutManager = GridLayoutManager(requireContext(),1)
        binding.rvTypes.adapter = adapter
        adapter.onClick = { types ->
            findNavController().navigate(R.id.resultFragment)
        }

        binding.apply {
            icBackToHome.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }

    }

    private fun getAllTypes(){
        val items: ArrayList<Types> = ArrayList()
        items.add(Types(R.drawable.im_malibu,"Tesla","15"))
        items.add(Types(R.drawable.im_malibu,"GM","10"))
        items.add(Types(R.drawable.im_malibu,"BMW","5"))
        items.add(Types(R.drawable.im_malibu,"Mersades Benz","18"))

        adapter.submitData(items)

    }

}