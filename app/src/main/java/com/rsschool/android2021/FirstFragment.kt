package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValueEdt :EditText? = null
    private var maxValueEdt :EditText? = null
    private var listener :ActionPerformedListener? = null

    interface ActionPerformedListener {
        fun onActionPerformed(min: Int, max: Int)
    }

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
        previousResult?.text = "Previous result: ${result.toString()}"

        /*
        В поля минимального и максимального значения на первом экране пользователь может ввести только
        валидные данные (целые, неотрицательные числа). Если:
        1) В одном из полей нет числа
        2) B поле минимального значения находится число, которое больше числа в поле максимального значения
        3) В любом из полей находится число превышающее максимальное число доступное для работы функции,
        генерирующей рандомное значение,то кнопка генерации не активна и/или пользователю показывается
        уведомление (toast, snackbar, dialog), что входные данные невалидны. Во всех случаях невалидных
        входных данных пользователь не может перейти на второй экран
         */
        var min : Int?
        var max : Int?

        generateButton?.setOnClickListener {
            min = minValueEdt?.text.toString().toIntOrNull()
            max = maxValueEdt?.text.toString().toIntOrNull()
            if(min !=null && max !=null) {
                if(min!! >= max!!) {
                  Toast.makeText(activity, "Max value should be > Min",Toast.LENGTH_SHORT).show()
                }
                else listener?.onActionPerformed(min!!, max!!)
            }
            else Toast.makeText(activity, "Incorrect input",Toast.LENGTH_SHORT).show()
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