package markus.wieland.huelssegymnasiumapp.modules.grades.database;

import androidx.room.Dao;
import androidx.room.Query;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.Grade;

@Dao
public interface GradeDataAccessObject extends BaseDataAccessObject<Grade> {

    @Query("DELETE FROM Grade")
    void deleteAll();

}
