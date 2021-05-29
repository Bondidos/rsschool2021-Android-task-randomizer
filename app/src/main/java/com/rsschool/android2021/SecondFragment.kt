package com.rsschool.android2021

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        Log.d("myLogs","$min")
//        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0
//        Log.d("myLogs","$max")

       // result?.text = generate(min, 90).toString()

        backButton?.setOnClickListener {
            // TODO: implement back
        }
    }

//    private fun generate(min: Int, max: Int): Int {
//        return Random.nextInt(min,max)
//    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            Log.d("myLogs"," min is $min max is $max")
            args.putInt(MIN_VALUE_KEY,min)
            args.putInt(MAX_VALUE_KEY,max)
            fragment.arguments = args

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}