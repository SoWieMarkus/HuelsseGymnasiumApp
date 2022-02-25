package markus.wieland.huelssegymnasiumapp.grades;

import java.util.ArrayList;
import java.util.Collections;

import lombok.Getter;

@Getter
public enum GradeFormat {

    ABITUR(0, 15),
    NORMAL(6, 1);

    private final int minimum;
    private final int maximum;

    GradeFormat(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Integer[] getAllPossibleGrades() {
        int minimum = Math.min(getMinimum(), getMaximum());
        int maximum = Math.max(getMinimum(), getMaximum());
        boolean reverseOrder = getMinimum() > getMaximum();

        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = minimum; i <= maximum; i++) {
            grades.add(i);
        }

        if (reverseOrder) Collections.reverse(grades);

        return grades.toArray(new Integer[0]);
    }
}
