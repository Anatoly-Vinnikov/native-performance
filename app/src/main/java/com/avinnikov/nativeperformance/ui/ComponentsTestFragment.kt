package com.avinnikov.nativeperformance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avinnikov.nativeperformance.R
import com.avinnikov.nativeperformance.data.ITERATIONS_COUNT
import com.avinnikov.nativeperformance.extensions.inflate
import com.avinnikov.nativeperformance.extensions.launchInCoroutineScope
import kotlinx.android.synthetic.main.fragment_with_text.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
class ComponentsTestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container?.inflate(
            R.layout.fragment_with_text
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
    }

    override fun onResume() {
        super.onResume()

        launchInCoroutineScope(
            onRun = { doMeasure() },
            onPreRun = { mainTextView.text = getString(R.string.calculating) },
            onPostRun = { mainTextView.text = it }
        )
    }

    private fun setupToolbar() {
        activity?.title = getString(R.string.components_test_screen_title)
    }

    private fun doMeasure(): String {
        val times = mutableListOf<Double>()
        val string = getString(R.string.huge_text)

        repeat(ITERATIONS_COUNT) {
            times.add(measureTime {
                string.split(" ")
            }.inSeconds)
        }

        return getString(R.string.components_time).format(times.average())
    }
}