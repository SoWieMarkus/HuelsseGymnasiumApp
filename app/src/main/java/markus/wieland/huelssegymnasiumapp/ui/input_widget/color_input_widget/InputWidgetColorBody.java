package markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetColorBody extends InputWidgetBody<Color> implements SeekBar.OnSeekBarChangeListener {

    private static final int MIN_COLOR_VALUE = 0;
    private static final int MAX_COLOR_VALUE = 255;

    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;

    public InputWidgetColorBody(Context context) {
        super(context);
    }

    public InputWidgetColorBody(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputWidgetColorBody(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initialize() {
        super.initialize();
        seekBarR = findViewById(R.id.input_widget_color_body_r);
        seekBarG = findViewById(R.id.input_widget_color_body_g);
        seekBarB = findViewById(R.id.input_widget_color_body_b);

        for (SeekBar seekBar : getAllSeekBars()) {
            seekBar.setMin(MIN_COLOR_VALUE);
            seekBar.setMax(MAX_COLOR_VALUE);
            seekBar.setOnSeekBarChangeListener(this);
        }

    }

    private SeekBar[] getAllSeekBars() {
        return new SeekBar[]{seekBarR, seekBarG, seekBarB};
    }

    @Override
    public void setValue(Color color) {
        seekBarR.setProgress(color.getR());
        seekBarG.setProgress(color.getG());
        seekBarB.setProgress(color.getB());
        getValueChangeListener().onValueChanged(color);
    }

    @Override
    public Color getValue() {
        return new Color(seekBarR.getProgress(), seekBarG.getProgress(), seekBarB.getProgress());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_color_body;
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
