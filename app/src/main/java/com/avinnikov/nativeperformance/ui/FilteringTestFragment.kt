package com.avinnikov.nativeperformance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avinnikov.nativeperformance.R
import com.avinnikov.nativeperformance.data.ELEMENTS_COUNT
import com.avinnikov.nativeperformance.data.ITERATIONS_COUNT
import com.avinnikov.nativeperformance.data.Structure
import com.avinnikov.nativeperformance.extensions.inflate
import com.avinnikov.nativeperformance.extensions.launchInCoroutineScope
import kotlinx.android.synthetic.main.fragment_with_text.*
import kotlin.math.truncate
import kotlin.random.Random
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
class FilteringTestFragment : Fragment() {

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
        activity?.title = getString(R.string.filtering_test_screen_title)
    }

    private fun doMeasure(): String {
        val times = mutableListOf<Double>()
        val items = mutableListOf<Structure>()

        repeat(ITERATIONS_COUNT) {
            items.clear()
            repeat(ELEMENTS_COUNT) {
                val number = Random.nextDouble(10_000.0)
                items.add(Structure(number, "String value: $number"))
            }
            times.add(measureTime { items.filter { truncate(it.number.rem(2)).toInt() == 0 } }.inSeconds)
        }

        return getString(R.string.filtering_time).format(times.average())
    }
}