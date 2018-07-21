package com.example.utilisateur.qrthing.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.example.utilisateur.qrthing.R;

import java.util.Calendar;
import java.util.zip.Inflater;

public class SelectTimeDia extends DialogFragment {

    NumberPicker npHour;
    NumberPicker npMinute;
    NumberPicker npSecond;

    public interface SelectTimeListener {
        void onSelectTime(int hour, int minute, int second);
    }

    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_select_time, null);

        npHour = view.findViewById(R.id.npHour);
        npHour.setMinValue(0);
        npHour.setMaxValue(23);
        npMinute = view.findViewById(R.id.npMinute);
        npMinute.setMinValue(0);
        npMinute.setMaxValue(59);
        npSecond = view.findViewById(R.id.npSecond);
        npSecond.setMinValue(0);
        npSecond.setMaxValue(59);

        Calendar calendar = Calendar.getInstance();
        npHour.setValue(calendar.get(Calendar.HOUR_OF_DAY));
        npMinute.setValue(calendar.get(Calendar.MINUTE));
        npSecond.setValue(calendar.get(Calendar.SECOND));

        builder.setView(view)
                .setPositiveButton(R.string.bSet, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                        SelectTimeListener act = (SelectTimeListener) getActivity();
                        act.onSelectTime(npHour.getValue(), npMinute.getValue(), npSecond.getValue());
                    }
                })
                .setNegativeButton(R.string.bCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SelectTimeDia.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
