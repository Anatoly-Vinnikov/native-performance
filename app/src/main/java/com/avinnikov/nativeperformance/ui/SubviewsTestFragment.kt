package com.avinnikov.nativeperformance.ui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.avinnikov.nativeperformance.R
import com.avinnikov.nativeperformance.extensions.inflate
import com.avinnikov.nativeperformance.extensions.randomColor
import kotlinx.android.synthetic.main.fragment_subview.*

class SubviewsTestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container?.inflate(
            R.layout.fragment_subview
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        drawView()
    }

    private fun setupToolbar() {
        activity?.title = getString(R.string.subviews_test_screen_title)
    }

    private fun drawView() {
        (1000 downTo 1).forEach {
            subviewLayout.addView(getCustomView(it * 2))
        }
    }

    private fun getCustomView(size: Int) = View(requireContext()).apply {
        layoutParams = RelativeLayout.LayoutParams(size, size).apply {
            addRule(RelativeLayout.CENTER_IN_PARENT)
        }
        elevation = 4f
        background = getBackground(size)
    }

    private fun getBackground(alpha: Int) = GradientDrawable().apply {
        cornerRadius = 10f
        setColor(randomColor(alpha))
    }
}