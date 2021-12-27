package com.example.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.adapter.LecturesAdapter
import com.example.assignment.databinding.FragmentLecturesBinding

class LecturesFragment : Fragment() {
    private var _binding: FragmentLecturesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLecturesBinding.inflate(inflater, container, false)
        activity?.setTitle("Lectures")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.lecturesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = LecturesAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}