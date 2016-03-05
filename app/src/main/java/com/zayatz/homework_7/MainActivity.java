package com.zayatz.homework_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvItemBar1, tvItemBar2, tvItemBar3, tvItemText1, tvItemText2, tvItemText3;
    private Button btnItemMenu1, btnItemMenu2, btnItemMenu3;
    private SharedPreferences mSPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_menu_am, menu);

        MenuItem item1 = menu.findItem(R.id.ab_item1);
        MenuItem item2 = menu.findItem(R.id.ab_item2);
        MenuItem item3 = menu.findItem(R.id.ab_item3);

        loadActionBarState(item1,item2, item3);

        itemSetEnabled(1, item1.isChecked());
        itemSetEnabled(2, item2.isChecked());
        itemSetEnabled(3, item3.isChecked());

        setCheckIcon(item1);
        setCheckIcon(item2);
        setCheckIcon(item3);
        return true;
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        Log.d("TAG", "onClosePanel");
        MenuItem item1 = menu.findItem(R.id.ab_item1);
        MenuItem item2 = menu.findItem(R.id.ab_item2);
        MenuItem item3 = menu.findItem(R.id.ab_item3);

        if (item1.getIntent() != null) {
            item2.setChecked(false);
            item3.setChecked(false);
        } else if (item2.getIntent() != null) {
            item1.setChecked(false);
            item3.setChecked(false);
        } else if (item3.getIntent() != null) {
            item1.setChecked(false);
            item2.setChecked(false);
        }

        saveActionBarState(item1,item2, item3);

        item1.setIntent(null);
        item2.setIntent(null);
        item3.setIntent(null);

        itemSetEnabled(1, item1.isChecked());
        itemSetEnabled(2, item2.isChecked());
        itemSetEnabled(3, item3.isChecked());

        setCheckIcon(item1);
        setCheckIcon(item2);
        setCheckIcon(item3);
        super.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_item1:
                item.setChecked(true);
                sendStateByIntent(item);

                return true;

            case R.id.ab_item2:
                item.setChecked(true);
                sendStateByIntent(item);

                return true;

            case R.id.ab_item3:
                item.setChecked(true);
                sendStateByIntent(item);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void sendStateByIntent (MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_ITEM_STATE, true);
        item.setIntent(intent);
    }

    private  void findViews() {
        tvItemBar1 = (TextView) findViewById(R.id.itemBar1_AM);
        tvItemBar2 = (TextView) findViewById(R.id.itemBar2_AM);
        tvItemBar3 = (TextView) findViewById(R.id.itemBar3_AM);

        tvItemText1 = (TextView) findViewById(R.id.itemText1_AM);
        tvItemText2 = (TextView) findViewById(R.id.itemText2_AM);
        tvItemText3 = (TextView) findViewById(R.id.itemText3_AM);

        btnItemMenu1 = (Button) findViewById(R.id.itemButton1_AM);
        btnItemMenu2 = (Button) findViewById(R.id.itemButton2_AM);
        btnItemMenu3 = (Button) findViewById(R.id.itemButton3_AM);
    }

    private void itemSetEnabled (int item, boolean enable) {
        switch (item) {
            case 1:
                tvItemBar1.setEnabled(enable);
                tvItemText1.setEnabled(enable);
                btnItemMenu1.setEnabled(enable);

                break;
            case 2:
                tvItemBar2.setEnabled(enable);
                tvItemText2.setEnabled(enable);
                btnItemMenu2.setEnabled(enable);

                break;
            case 3:
                tvItemBar3.setEnabled(enable);
                tvItemText3.setEnabled(enable);
                btnItemMenu3.setEnabled(enable);

                break;
        }
    }


    private void setCheckIcon (MenuItem item) {

        SpannableStringBuilder builder = new SpannableStringBuilder(getTitleForBuilder(item));
        boolean check = item.isChecked();
        // replace "*" with icon
        if (check) {
            builder.setSpan(new ImageSpan(this, R.drawable.ic_checked), 0, 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            builder.setSpan(new ImageSpan(this, R.drawable.ic_unchecked), 0, 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        item.setTitle(builder);
    }

    private String getTitleForBuilder (MenuItem item) {
        String title = String.valueOf(item.getTitle());
        if (title.charAt(0) == '*') return title;
        else return "* " + title;
    }

    private void saveActionBarState (MenuItem item1, MenuItem item2, MenuItem item3) {

        mSPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = mSPref.edit();
        ed.putBoolean(Constant.SPREF_ITEM_1_CHECKED, item1.isChecked());
        ed.putBoolean(Constant.SPREF_ITEM_2_CHECKED, item2.isChecked());
        ed.putBoolean(Constant.SPREF_ITEM_3_CHECKED, item3.isChecked());
        ed.apply();
    }

    private void loadActionBarState (MenuItem item1, MenuItem item2, MenuItem item3) {
        mSPref = getPreferences(MODE_PRIVATE);

        boolean item1state = mSPref.getBoolean(Constant.SPREF_ITEM_1_CHECKED, true);
        boolean item2state =  mSPref.getBoolean(Constant.SPREF_ITEM_2_CHECKED, false);
        boolean item3state = mSPref.getBoolean(Constant.SPREF_ITEM_3_CHECKED, false);

        item1.setChecked(item1state);
        item2.setChecked(item2state);
        item3.setChecked(item3state);
    }

    private void defaultActionBarState () {

        mSPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = mSPref.edit();
        ed.putBoolean(Constant.SPREF_ITEM_1_CHECKED, true);
        ed.putBoolean(Constant.SPREF_ITEM_2_CHECKED, false);
        ed.putBoolean(Constant.SPREF_ITEM_3_CHECKED, false);
        ed.apply();
    }
}
