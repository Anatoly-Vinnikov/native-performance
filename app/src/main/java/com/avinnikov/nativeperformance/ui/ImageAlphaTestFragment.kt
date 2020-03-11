package com.avinnikov.nativeperformance.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.avinnikov.nativeperformance.R
import com.avinnikov.nativeperformance.common.equality.DiffItemCallback
import com.avinnikov.nativeperformance.data.ITERATIONS_COUNT
import com.avinnikov.nativeperformance.extensions.inflate
import com.avinnikov.nativeperformance.extensions.showToast
import com.avinnikov.nativeperformance.ui.adapter.ImageListAdapter
import com.avinnikov.nativeperformance.ui.adapter.OnRecyclerClickListener
import kotlinx.android.synthetic.main.fragment_with_recycler.*

class ImageAlphaTestFragment : Fragment() {

    private var adapter: ImageListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_scroll, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mainRecyclerView.smoothScrollToPosition(999)

        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        activity?.title = getString(R.string.image_alpha_test_screen_title)
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            adapter = ImageListAdapter(
                    DiffItemCallback(),
                    clickListener = object :
                            OnRecyclerClickListener<Int> {
                        override fun onItemClicked(item: Int) {
                            showToast(requireContext())
                        }
                    }
            )
        }

        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val drawableRes = R.drawable.with_alpha
        val items = mutableListOf<Int>()

        repeat(ITERATIONS_COUNT) {
            items.add(drawableRes)
        }

        adapter?.submitList(items)
    }
}