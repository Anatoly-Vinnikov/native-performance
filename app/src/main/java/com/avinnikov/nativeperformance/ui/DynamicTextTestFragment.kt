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
import com.avinnikov.nativeperformance.ui.adapter.OnRecyclerClickListener
import com.avinnikov.nativeperformance.ui.adapter.TextListAdapter
import kotlinx.android.synthetic.main.fragment_with_recycler.*
import kotlin.random.Random

class DynamicTextTestFragment : Fragment() {

    private var adapter: TextListAdapter? = null

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
        activity?.title = getString(R.string.dynamic_text_test_screen_title)
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            adapter = TextListAdapter(
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

        val string = getString(R.string.long_text)
        val stringLength = string.length
        val items = mutableListOf<String>()

        repeat(ITERATIONS_COUNT) {
            items.add(string.substring(0, Random.nextInt(stringLength)))
        }

        adapter?.submitList(items)
    }
}