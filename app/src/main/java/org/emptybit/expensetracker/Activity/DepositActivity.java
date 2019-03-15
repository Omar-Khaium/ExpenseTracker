package org.emptybit.expensetracker.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.Model.SubCategoryModel;
import org.emptybit.expensetracker.Model.UserModel;
import org.emptybit.expensetracker.R;

import java.util.Date;

import static org.emptybit.expensetracker.Activity.LoginActivity.USER_ID;
import static org.emptybit.expensetracker.Database.DatabaseQuery.insertTransaction;
import static org.emptybit.expensetracker.Enum.DEPOSIT;

public class DepositActivity extends AppCompatActivity {

    EditText mAmount;
    ImageView mSubmit;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        mAmount = findViewById(R.id.deposit_amount);
        mSubmit = findViewById(R.id.deposit_submit);
        helper = new DatabaseHelper(getApplicationContext());
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount is required");
                } else {
                    AccountModel model = new AccountModel(0, new UserModel(USER_ID, "", "", "", 0, ""), new SubCategoryModel(), Integer.parseInt(mAmount.getText().toString()), DEPOSIT, String.valueOf(new Date()));
                    if (helper.insert(insertTransaction(model))) {
                        Toast.makeText(DepositActivity.this, "Deposited successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
