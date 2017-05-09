package com.example.android.weeklytips.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.weeklytips.R;

public class EditTextDialog extends DialogFragment {

    private static final String PROMPT_KEY = "prompt_key";
    private static final String VALUE_KEY = "value_key";

    private EditTextDialogListener mListener;
    private EditText etValue;

    public static EditTextDialog newInstance(String prompt, String currentValue) {

        Bundle args = new Bundle();
        args.putString(PROMPT_KEY, prompt);
        args.putString(VALUE_KEY, currentValue);

        EditTextDialog fragment = new EditTextDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditTextDialogListener) {
            mListener = (EditTextDialogListener) context;
        } else {
            throw new ClassCastException("Caller must implement Listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_edit_text, container, false);

        String prompt = getArguments().getString(PROMPT_KEY);
        String initialValue = getArguments().getString(VALUE_KEY);
        if (initialValue == null) {
            initialValue = "";
        }

        TextView tvPrompt = (TextView) view.findViewById(R.id.tvPrompt);
        tvPrompt.setText(prompt);

        etValue = (EditText) view.findViewById(R.id.etValue);
        etValue.setText(initialValue);
        etValue.setSelection(etValue.length());

        Button btnOk = (Button) view.findViewById(R.id.btnOk);
        final String finalInitialValue = initialValue;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newValue = etValue.getText().toString();
                if (!newValue.equals(finalInitialValue)) {
                    mListener.onEditTextDialogOK(newValue, getTag());
                }
                dismiss();
            }
        });

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    public interface EditTextDialogListener {
        void onEditTextDialogOK(String newValue, String tag);
    }

}