package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValueEdt :EditText? = null
    private var maxValueEdt :EditText? = null

    interface ActionPerformedListener {
        fun onActionPerformed(min: Int, max: Int)
    }
    private var listener :ActionPerformedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionPerformedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValueEdt = view.findViewById(R.id.min_value)
        maxValueEdt = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}" //TODO add to strings

        var min : Int?
        var max : Int?
        //min=Integer.parseInt(minValueEdt?.text.toString())


        generateButton?.setOnClickListener {
            min = minValueEdt?.text.toString().toIntOrNull()
            max = maxValueEdt?.text.toString().toIntOrNull()
            Log.d("myLogs", "$min , $max")
            if(min !=null && max !=null)
                listener?.onActionPerformed(min!!, max!!)
            //todo Check for min != max && min !> max use Toast
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}