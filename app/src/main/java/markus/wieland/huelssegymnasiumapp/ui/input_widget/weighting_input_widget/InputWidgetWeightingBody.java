package markus.wieland.huelssegymnasiumapp.ui.input_widget.weighting_input_widget;

import android.content.Context;
import android.widget.SeekBar;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetWeightingBody extends InputWidgetBody<Integer> implements SeekBar.OnSeekBarChangeListener {

    private static final int MIN_EXAM_WEIGHTING = 0;
    public static final int MAX_EXAM_WEIGHTING = 100;
    private static final int STEP_SIZE = 5;

    private SeekBar seekBar;

    public InputWidgetWeightingBody(Context context) {
        super(context);
    }

    @Override
    protected void initialize() {
        super.initialize();
        seekBar = findViewById(R.id.input_widget_weighting_body_seek_bar);
        seekBar.setMin(MIN_EXAM_WEIGHTING / STEP_SIZE);
        seekBar.setMax(MAX_EXAM_WEIGHTING / STEP_SIZE);

        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onValueSet(Integer integer) {
        seekBar.setProgress(integer / STEP_SIZE);
    }

    @Override
    public Integer getValue() {
        return seekBar.getProgress() * STEP_SIZE;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_weighting_body;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        getValueChangeListener().onValueChanged(getValue());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // has to be implemented because of the OnSeekBarChangeListener
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // has to be implemented because of the OnSeekBarChangeListener
    }
}
