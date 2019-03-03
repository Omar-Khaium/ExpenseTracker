package org.emptybit.expensetracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.R;

import static org.emptybit.expensetracker.Database.DatabaseQuery.getLoginUserCredential;
import static org.emptybit.expensetracker.Database.DatabaseQuery.loginQueryBuilder;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText mUsername, mPassword;
    Button mLogin, mCreateAccount;

    public static int USER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        mUsername = findViewById(R.id.login_username);
        mPassword = findViewById(R.id.login_password);
        mLogin = findViewById(R.id.login_submit);
        mCreateAccount = findViewById(R.id.login_sign_up);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USER_ID = getLoginUserCredential(databaseHelper.getData(loginQueryBuilder(mUsername.getText().toString().trim(), mPassword.getText().toString().trim())));
                if (USER_ID != 0)
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                else
                    Toast.makeText(LoginActivity.this, "Not found", Toast.LENGTH_SHORT).show();
            }
        });

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}
