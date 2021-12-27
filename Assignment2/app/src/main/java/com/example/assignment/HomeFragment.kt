package com.example.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.adapter.HomeAdapter
import com.example.assignment.data.Datasource
import com.example.assignment.databinding.FragmentHomeBinding
import com.example.assignment.model.Attendance

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        activity?.setTitle("Home")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var present = mutableListOf<Attendance>()
        Datasource.attendances.forEach { attendance ->
            if(!attendance.attendance){
                present.add(attendance)
            }
        }
        recyclerView = binding.homeRecyclerView
        recyclerView.adapter = HomeAdapter(present)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}