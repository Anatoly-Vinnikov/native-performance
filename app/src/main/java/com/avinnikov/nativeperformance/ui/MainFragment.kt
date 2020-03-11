package com.avinnikov.nativeperformance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.avinnikov.nativeperformance.R
import com.avinnikov.nativeperformance.common.equality.DiffItemCallback
import com.avinnikov.nativeperformance.extensions.inflate
import com.avinnikov.nativeperformance.extensions.showToast
import com.avinnikov.nativeperformance.ui.adapter.OnRecyclerClickListener
import com.avinnikov.nativeperformance.ui.adapter.TextListAdapter
import kotlinx.android.synthetic.main.fragment_with_recycler.*

class MainFragment : Fragment() {

    private var adapter: TextListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container?.inflate(
            R.layout.fragment_with_recycler
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
    }

    private fun setupToolbar() {
        activity?.title = getString(R.string.main_screen_title)
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            adapter = TextListAdapter(
                DiffItemCallback(),
                clickListener = object :
                    OnRecyclerClickListener<Int> {
                    override fun onItemClicked(item: Int) {
                        when (item) {
                            0 -> findNavController().navigate(R.id.mainToRecyclerTests)
                            1 -> findNavController().navigate(R.id.mainToArrayTests)
                            2 -> findNavController().navigate(R.id.mainToStringTests)
                            3 -> findNavController().navigate(R.id.mainToSubviewsTest)
                            else -> showToast(requireContext())
                        }
                    }
                }
            )
        }

        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter?.submitList(resources.getStringArray(R.array.main_menu).toMutableList())
    }
}