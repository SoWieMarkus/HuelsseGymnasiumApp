package markus.wieland.huelssegymnasiumapp.modules.time_table;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import lombok.Setter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public class TimeTableDetailsDialog extends DialogFragment {

    private static final String TIME_TABLE_SLOT_KEY = "markus.wieland.huelssegymnasiumapp.modules.time_table.TIME_TABLE_SLOT_KEY";

    @Setter
    private TimeTableDialogInteractionListener timeTableDialogInteractionListener;

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

        TimeTableSlotWithSubject timeTableSlotWithSubject = (TimeTableSlotWithSubject) getArguments().get(TIME_TABLE_SLOT_KEY);
        TextView subjectTitle = view.findViewById(R.id.dialog_time_table_slot_details_name);
        TextView teacher = view.findViewById(R.id.dialog_time_table_slot_details_teacher);
        LinearLayout color = view.findViewById(R.id.dialog_time_table_slot_details_color);
        TextView period = view.findViewById(R.id.dialog_time_table_slot_details_period);
        TextView room = view.findViewById(R.id.dialog_time_table_slot_details_room);
        TextView delete = view.findViewById(R.id.dialog_time_table_slot_details_delete);
        TextView edit = view.findViewById(R.id.dialog_time_table_slot_details_edit);
        subjectTitle.setText(timeTableSlotWithSubject.getSubject().getName());
        color.setBackgroundColor(timeTableSlotWithSubject.getSubject().getColor());
        edit.setOnClickListener(button -> {
            timeTableDialogInteractionListener.onEdit(timeTableSlotWithSubject);
            if (getDialog() == null) return;
            getDialog().dismiss();
        });
        delete.setOnClickListener(button -> {
            timeTableDialogInteractionListener.onDelete(timeTableSlotWithSubject);
            if (getDialog() == null) return;
            getDialog().dismiss();
        });
        room.setText(timeTableSlotWithSubject.getTimeTableSlot().roomToString(getContext()));
        period.setText(timeTableSlotWithSubject.getTimeTableSlot().getTimePeriod().toString());
        teacher.setText(timeTableSlotWithSubject.getSubject().teacherToString());

        if (getDialog() == null) return;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
