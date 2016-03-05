package com.zayatz.homework_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_menu_am, menu);
        MenuItem item1 = menu.findItem(R.id.ab_item1);
        MenuItem item2 = menu.findItem(R.id.ab_item2);
        MenuItem item3 = menu.findItem(R.id.ab_item3);

        Log.d("TAG", "onCreate");
        //uncheckOther(item1, item2, item3);

        return true;
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

    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("TAG", "onPrepare");

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

        item1.setIntent(null);
        item2.setIntent(null);
        item3.setIntent(null);

        setCheckIcon(item1);
        setCheckIcon(item2);
        setCheckIcon(item3);
        return true;
    }

    public void setCheckIcon (MenuItem item) {

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

}
