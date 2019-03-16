package org.emptybit.expensetracker.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.emptybit.expensetracker.Fragment.CreateTransactionFragment;
import org.emptybit.expensetracker.Fragment.DashboardFragment;
import org.emptybit.expensetracker.Fragment.HistoryFragment;
import org.emptybit.expensetracker.R;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mNavigator = findViewById(R.id.dashboard_navigation);

        mNavigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_dashboard_home:
                        selectedFragment = new DashboardFragment();
                        break;
                    case R.id.nav_dashboard_history:
                        selectedFragment = new HistoryFragment();
                        break;
                    case R.id.nav_dashboard_new_transaction:
                        selectedFragment = new CreateTransactionFragment();
                        break;
                    default:
                        selectedFragment = new DashboardFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, selectedFragment).addToBackStack(null).commit();
                return false;
            }
        });

        if (getIntent().getStringExtra("To Dashboard") != null) {
            switch (getIntent().getStringExtra("To Dashboard")) {
                case "From ABC":
                    break;

                default:
                    getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, new DashboardFragment()).addToBackStack(null).commit();
            }
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, new DashboardFragment()).addToBackStack(null).commit();
        }
    }
}
