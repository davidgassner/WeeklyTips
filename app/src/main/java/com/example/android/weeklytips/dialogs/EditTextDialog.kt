package com.example.android.weeklytips.dialogs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.example.android.weeklytips.R

class EditTextDialog : DialogFragment() {

    private var mListener: EditTextDialogListener? = null
    private var etValue: EditText? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is EditTextDialogListener) {
            mListener = context as EditTextDialogListener?
        } else {
            throw ClassCastException("Caller must implement Listener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.dialog_edit_text, container, false)

        val prompt = arguments.getString(PROMPT_KEY)
        var initialValue = arguments.getString(VALUE_KEY)
        if (initialValue == null) {
            initialValue = ""
        }

        val tvPrompt = view.findViewById(R.id.tvPrompt) as TextView
        tvPrompt.text = prompt

        etValue = view.findViewById(R.id.etValue) as EditText
        etValue!!.setText(initialValue)
        etValue!!.setSelection(etValue!!.length())

        val btnOk = view.findViewById(R.id.btnOk) as Button
        val finalInitialValue = initialValue
        btnOk.setOnClickListener {
            val newValue = etValue!!.text.toString()
            if (newValue != finalInitialValue) {
                mListener!!.onEditTextDialogOK(newValue, tag)
            }
            dismiss()
        }

        val btnCancel = view.findViewById(R.id.btnCancel) as Button
        btnCancel.setOnClickListener { dismiss() }

        return view
    }

    interface EditTextDialogListener {
        fun onEditTextDialogOK(newValue: String, tag: String)
    }

    companion object {

        private val PROMPT_KEY = "prompt_key"
        private val VALUE_KEY = "value_key"

        fun newInstance(prompt: String, currentValue: String): EditTextDialog {

            val args = Bundle()
            args.putString(PROMPT_KEY, prompt)
            args.putString(VALUE_KEY, currentValue)

            val fragment = EditTextDialog()
            fragment.arguments = args
            return fragment
        }
    }

}