package org.emptybit.expensetracker.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Helper.RealPathUtils;
import org.emptybit.expensetracker.Model.LoginModel;
import org.emptybit.expensetracker.Model.UserModel;
import org.emptybit.expensetracker.R;

import java.util.Date;

import static org.emptybit.expensetracker.Database.DatabaseQuery.insertLoginCredential;
import static org.emptybit.expensetracker.Database.DatabaseQuery.insertUser;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText mName, mUserName, mPassword;
    Spinner mGender;
    Button mSubmit;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        mName = findViewById(R.id.sign_up_name);
        mUserName = findViewById(R.id.sign_up_user_name);
        mPassword = findViewById(R.id.sign_up_password);
        mGender = findViewById(R.id.sign_up_gender);
        mSubmit = findViewById(R.id.sign_up_submit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;

                if (mName.getText().toString().isEmpty()) {
                    mName.setError("Please enter your name");
                    flag = false;
                }

                if (mUserName.getText().toString().isEmpty()) {
                    mUserName.setError("Please enter your username");
                    flag = false;
                }

                if (mPassword.getText().toString().isEmpty()) {
                    mPassword.setError("Please enter your password");
                    flag = false;
                }

                if (flag) {
                    UserModel userModel = new UserModel(0, mName.getText().toString().trim(),
                            mUserName.getText().toString().trim(), mPassword.getText().toString().trim(),
                            mGender.getSelectedItemPosition(), String.valueOf(new Date()));

                    if (databaseHelper.insert(insertUser(userModel))) {
                        LoginModel loginModel = new LoginModel(0, new UserModel(), userModel.getUsername(), userModel.getPassword());
                        if (databaseHelper.insertToLogin(insertLoginCredential(loginModel)))
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        else
                            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
