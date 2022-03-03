package markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Color {

    private int r;
    private int g;
    private int b;

    public int getColorValue() {
        return android.graphics.Color.rgb(r, g, b);
    }

}
