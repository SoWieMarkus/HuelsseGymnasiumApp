package markus.wieland.huelssegymnasiumapp.api.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubstitutionPlan {

    private String id;

    private int year;
    private int month;
    private int day;

    private long timestamp;

    private List<Substitution> substitutions;

    private LocalDate getDate() {
        return LocalDate.of(year, month, day);
    }
}
