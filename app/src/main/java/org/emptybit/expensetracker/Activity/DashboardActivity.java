package org.emptybit.expensetracker.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import org.emptybit.expensetracker.Fragment.CreateTransactionFragment;
import org.emptybit.expensetracker.Fragment.DashboardFragment;
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
//                    case R.id.building_menu_information:
//                        selectedFragment = new BuildingInformationFragment();
//                        break;
                    case R.id.nav_dashboard_new_transaction:
                    selectedFragment = new CreateTransactionFragment();
                        break;
//                    case R.id.building_menu_occupant:
//                        selectedFragment = new BuildingOccupantFragment();
//                        break;
//                    case R.id.building_menu_more:
//                        selectedFragment = new BuildingMoreFragment();
//                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, selectedFragment).addToBackStack(null).commit();
                return false;
            }
        });
    }
}
