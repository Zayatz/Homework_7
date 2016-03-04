package com.zayatz.homework_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
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
        MenuItem item1 = menu.findItem(R.id.ab_item1);
        MenuItem item2 = menu.findItem(R.id.ab_item2);
        MenuItem item3 = menu.findItem(R.id.ab_item3);

        setCheckIcon(item1);
        setCheckIcon(item2);
        setCheckIcon(item3);

        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_item1:
                if (!item.isChecked()) item.setChecked(true);
                else item.setChecked(false);
                setCheckIcon(item);
                return true;

            case R.id.ab_item2:
                if (!item.isChecked()) item.setChecked(true);
                else item.setChecked(false);
                setCheckIcon(item);
                return true;

            case R.id.ab_item3:
                if (!item.isChecked()) item.setChecked(true);
                else item.setChecked(false);
                setCheckIcon(item);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
