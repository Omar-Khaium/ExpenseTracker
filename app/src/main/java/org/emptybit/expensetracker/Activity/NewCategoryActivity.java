package org.emptybit.expensetracker.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.emptybit.expensetracker.Adapter.RemarkAdapter;
import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.CategoryModel;
import org.emptybit.expensetracker.Model.SubCategoryModel;
import org.emptybit.expensetracker.R;

import java.util.ArrayList;
import java.util.Date;

import static org.emptybit.expensetracker.Database.DatabaseQuery.insertSubCategory;
import static org.emptybit.expensetracker.Database.DatabaseQuery.insertUser;
import static org.emptybit.expensetracker.Enum.ACTIVE;

public class NewCategoryActivity extends AppCompatActivity {

    EditText mName, mRemarks;
    Spinner mCategory;
    RecyclerView mRemarkList;
    Button mSubmit;
    DatabaseHelper databaseHelper;

    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        mName = findViewById(R.id.new_category_name);
        mRemarks = findViewById(R.id.new_category_remarks);
        mRemarkList = findViewById(R.id.new_category_remark_list);
        mCategory = findViewById(R.id.new_category_actual_category);
        mSubmit = findViewById(R.id.new_category_submit);

        mRemarkList.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

        mRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (charSequence.toString().contains("\n")) {
                        if (!charSequence.toString().substring(0, charSequence.toString().indexOf("\n")).isEmpty()) {
                            arrayList.add(charSequence.toString().substring(0, charSequence.toString().indexOf("\n")));
                            mRemarkList.setAdapter(new RemarkAdapter(getApplicationContext(), arrayList));
                            mRemarks.setText("");
                        }
                    } else if (charSequence.toString().contains(",")) {
                        if (!charSequence.toString().substring(0, charSequence.toString().indexOf(",")).isEmpty()) {
                            arrayList.add(charSequence.toString().substring(0, charSequence.toString().indexOf(",")));
                            mRemarkList.setAdapter(new RemarkAdapter(getApplicationContext(), arrayList));
                            mRemarks.setText("");
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mName.getText().toString().isEmpty())
                {
                    mName.setError("Name is required");
                } else if(mCategory.getSelectedItemPosition()==0) {
                    TextView errorText = (TextView)mCategory.getSelectedView();
                    errorText.setError("Category is required");
                } else {
                    SubCategoryModel model = new SubCategoryModel(0,mName.getText().toString(),new CategoryModel(mCategory.getSelectedItemPosition(), String.valueOf(mCategory.getSelectedItem().toString()), ACTIVE, String.valueOf(new Date())), ACTIVE, String.valueOf(new Date()));
                    if (databaseHelper.insert(insertSubCategory(model))) {
                        Toast.makeText(NewCategoryActivity.this, "Sub category creeated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
