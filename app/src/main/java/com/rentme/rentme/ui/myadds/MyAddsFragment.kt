package com.rentme.rentme.ui.myadds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.MyAddAdapter
import com.rentme.rentme.databinding.FragmentMyAddsBinding
import com.rentme.rentme.model.Result


class MyAddsFragment : Fragment() {
    private val adapter by lazy { MyAddAdapter() }

    private var _binding: FragmentMyAddsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyAddsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllResult()

        adapter.onClick = {result -> }

    }

    private fun getAllResult() {
        val items:ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","350$",))
        items.add(Result(R.drawable.im_mersades,"AMG","","400$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","300$",))
        items.add(Result(R.drawable.im_mersades,"AMG 2","","350$",))
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$"))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","350$"))
        items.add(Result(R.drawable.im_mersades,"AMG","","400$"))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","300$"))
        items.add(Result(R.drawable.im_mersades,"AMG 2","","350$"))
        adapter.submitData(items)

        binding.rvMyAdds.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.rvMyAdds.adapter = adapter
    }

}