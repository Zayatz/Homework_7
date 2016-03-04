package com.zayatz.homework_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_menu_am, menu);
        /*MenuItem item1 = (MenuItem) findViewById(R.id.ab_item1);
        item1.setChecked(true);
        MenuItem item2 = (MenuItem) findViewById(R.id.ab_item2);
        MenuItem item3 = (MenuItem) findViewById(R.id.ab_item3);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_item1:
                item.setIcon(R.drawable.ic_checked);
                item.setChecked(true);
                return true;

            case R.id.ab_item2:

                return true;

            case R.id.ab_item3:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
