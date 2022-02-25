package markus.wieland.huelssegymnasiumapp.database.entities.grade;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.grades.Grade;

@Dao
public interface GradeDataAccessObject extends BaseDataAccessObject<Grade> {

    @Query("DELETE FROM Grade")
    void deleteAll();

}
