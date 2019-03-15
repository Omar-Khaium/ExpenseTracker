package org.emptybit.expensetracker.Activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.emptybit.expensetracker.Adapter.RemarkAdapter;
import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.Model.CategoryModel;
import org.emptybit.expensetracker.Model.SubCategoryModel;
import org.emptybit.expensetracker.Model.UserModel;
import org.emptybit.expensetracker.R;

import java.util.ArrayList;
import java.util.Date;

import static org.emptybit.expensetracker.Activity.LoginActivity.USER_ID;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSubCategories;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSubCategoryList;
import static org.emptybit.expensetracker.Database.DatabaseQuery.insertTransaction;
import static org.emptybit.expensetracker.Enum.WITHDRAW;

public class ExpenseActivity extends AppCompatActivity {

    Spinner mCategoryList, mSubCategoryList;
    EditText mRemarks, mAmount;
    ImageView mSubmit;
    RecyclerView mRemarkListView;
    DatabaseHelper databaseHelper;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<SubCategoryModel> subCategoryArrayList = new ArrayList<>();
    ArrayAdapter<SubCategoryModel> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        mCategoryList = findViewById(R.id.expense_category_list);
        mSubCategoryList = findViewById(R.id.expense_sub_category_list);
        mRemarks = findViewById(R.id.expense_remarks);
        mRemarkListView = findViewById(R.id.expense_remark_list);
        mAmount = findViewById(R.id.expense_amount);
        mSubmit = findViewById(R.id.expense_submit);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        mRemarkListView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

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
                            mRemarkListView.setAdapter(new RemarkAdapter(getApplicationContext(), arrayList));
                            mRemarks.setText("");
                        }
                    } else if (charSequence.toString().contains(",")) {
                        if (!charSequence.toString().substring(0, charSequence.toString().indexOf(",")).isEmpty()) {
                            arrayList.add(charSequence.toString().substring(0, charSequence.toString().indexOf(",")));
                            mRemarkListView.setAdapter(new RemarkAdapter(getApplicationContext(), arrayList));
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

        mCategoryList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mSubCategoryList.setVisibility(View.GONE);
                        break;

                    case 1:
                        subCategoryArrayList = getSubCategoryList(databaseHelper.getData(getSubCategories(i)));
                        adapter = new ArrayAdapter<SubCategoryModel>(getApplicationContext(), android.R.layout.simple_spinner_item, subCategoryArrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSubCategoryList.setAdapter(adapter);
                        mSubCategoryList.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        subCategoryArrayList = getSubCategoryList(databaseHelper.getData(getSubCategories(i)));
                        adapter = new ArrayAdapter<SubCategoryModel>(getApplicationContext(), android.R.layout.simple_spinner_item, subCategoryArrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSubCategoryList.setAdapter(adapter);
                        break;

                    case 3:
                        subCategoryArrayList = getSubCategoryList(databaseHelper.getData(getSubCategories(i)));
                        adapter = new ArrayAdapter<SubCategoryModel>(getApplicationContext(), android.R.layout.simple_spinner_item, subCategoryArrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSubCategoryList.setAdapter(adapter);
                        mSubCategoryList.setVisibility(View.VISIBLE);
                        break;

                    case 4:
                        subCategoryArrayList = getSubCategoryList(databaseHelper.getData(getSubCategories(i)));
                        adapter = new ArrayAdapter<SubCategoryModel>(getApplicationContext(), android.R.layout.simple_spinner_item, subCategoryArrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        mSubCategoryList.setAdapter(adapter);
                        mSubCategoryList.setVisibility(View.VISIBLE);
                        break;

                    case 5:
                        subCategoryArrayList = getSubCategoryList(databaseHelper.getData(getSubCategories(i)));
                        adapter = new ArrayAdapter<SubCategoryModel>(getApplicationContext(), android.R.layout.simple_spinner_item, subCategoryArrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSubCategoryList.setAdapter(adapter);
                        mSubCategoryList.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCategoryList.getSelectedItemPosition()==0) {
                    Snackbar.make(view,"Select a category", Snackbar.LENGTH_SHORT).show();
                } else if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount is required");
                } else {
                    SubCategoryModel subCategoryModel = subCategoryArrayList.get(mSubCategoryList.getSelectedItemPosition());
                    AccountModel model = new AccountModel(0, new UserModel(USER_ID, "", "", "", 0, ""), subCategoryModel, Integer.parseInt(mAmount.getText().toString()), WITHDRAW, String.valueOf(new Date()));
                    if (databaseHelper.insert(insertTransaction(model))) {
                        Toast.makeText(ExpenseActivity.this, "Expended successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
