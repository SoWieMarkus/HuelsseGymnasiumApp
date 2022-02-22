package markus.wieland.huelssegymnasiumapp.api.models;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;

@Getter
@Setter
public class Substitution {

    private String lesson;
    private String course;
    private String subject;
    private String teacher;
    private String room;
    private String info;

}
