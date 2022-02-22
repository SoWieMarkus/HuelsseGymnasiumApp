package markus.wieland.huelssegymnasiumapp.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationResult {

    private String errorMessage;
    private boolean isValid;

}
