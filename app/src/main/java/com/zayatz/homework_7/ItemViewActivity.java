package com.zayatz.homework_7;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Zayatz on 06.03.2016.
 */
public class ItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        TextView tvItemSubject = (TextView) findViewById(R.id.itemSubject_AIV);
        TextView tvItemText = (TextView) findViewById(R.id.itemText_AIV);

        /*get strings from MainActivity an set it to text views*/
        tvItemSubject.setText(this.getIntent().getStringExtra(Constant.EXTRA_ITEM_SUBJECT));
        tvItemText.setText(this.getIntent().getStringExtra(Constant.EXTRA_ITEM_TEXT));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enable actionBar home button
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {  //check if activity has parent activity
                    onBackPressed();                      //if hasn't - simulate press the back button on the device
                } else {
                    NavUtils.navigateUpFromSameTask(this); // if has - navigate to it's parent
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
