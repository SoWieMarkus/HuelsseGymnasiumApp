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

    public ValidationResult() {
        this.isValid = true;
        this.errorMessage = null;
    }

    public ValidationResult(String errorMessage) {
        this.errorMessage = errorMessage;
        this.isValid = false;
    }

}
