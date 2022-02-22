package markus.wieland.huelssegymnasiumapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.BaseViewModel;
import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

@Getter
@Setter
public abstract class CreateItemActivity<T extends Serializable> extends DefaultActivity {

    public static final String OBJECT_TO_EDIT = "markus.wieland.defaultappelements.uielements.activities.DefaultActivity.OBJECT_TO_EDIT";
    public static final String RESULT = "markus.wieland.defaultappelements.uielements.activities.DefaultActivity.RESULT";

    private T item;

    private boolean isEditMode;

    @StringRes
    private final int titleString;

    public CreateItemActivity(int layout, int title) {
        super(layout);
        this.titleString = title;
        this.isEditMode = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_create_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        } else if (menuItem.getItemId() == R.id.menu_activity_create_item_commit) {
            check();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void initializeViews() {
        if (getSupportActionBar() == null)
            throw new IllegalStateException("SupportActionBar can't be null");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_activity_create_item_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(titleString);

        item = (T) getIntent().getSerializableExtra(OBJECT_TO_EDIT);
        if (item == null) {
            item = getDefaultItem();
            isEditMode = false;

        }

        initializeWidgets();

    }

    @Override
    public void execute() {
        // isn't used that often in the CreateItemActivities
    }

    public void check(){
        ValidationResult validationResult = validate();
        if (validationResult.isValid()) {
            setValues(item);
            if (isEditMode) {
                update(item);
            } else {
                insert(item);
            }
            finish();
            return;
        }
        Toast.makeText(this, validationResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    public abstract void initializeWidgets();

    public abstract ValidationResult validate();

    public abstract T getDefaultItem();

    public abstract void setValues(T t);

    public abstract void insert(T t);

    public abstract void update(T t);

}
