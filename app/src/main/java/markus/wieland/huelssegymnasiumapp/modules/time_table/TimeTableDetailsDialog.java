package markus.wieland.huelssegymnasiumapp.modules.time_table;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public class TimeTableDetailsDialog extends DialogFragment {

    private static final String TIME_TABLE_SLOT_KEY = "markus.wieland.huelssegymnasiumapp.modules.time_table.TIME_TABLE_SLOT_KEY";

    private TimeTableSlotWithSubject timeTableSlotWithSubject;
    private TextView subjectTitle;

    public static TimeTableDetailsDialog build(TimeTableSlotWithSubject timeTableSlotWithSubject) {
        TimeTableDetailsDialog instance = new TimeTableDetailsDialog();
        Bundle arguments = new Bundle();
        arguments.putSerializable(TIME_TABLE_SLOT_KEY, timeTableSlotWithSubject);
        instance.setArguments(arguments);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_time_table_slot_details, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) return;
        timeTableSlotWithSubject = (TimeTableSlotWithSubject) getArguments().get(TIME_TABLE_SLOT_KEY);
        subjectTitle = view.findViewById(R.id.dialog_time_table_slot_details_subject);
        subjectTitle.setText(timeTableSlotWithSubject.getSubject().getName());
    }
}
