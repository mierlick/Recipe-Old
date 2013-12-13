package com.mie.recipe;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

public class BaseRecipeActivity extends FragmentActivity {

    protected void toggleEmptyView(int currentViewId, int newViewId){
        ListView list = (ListView) findViewById(currentViewId);
        View eView = list.getEmptyView();
        if (eView != null)
            eView.setVisibility(View.GONE);
        View newEView = findViewById(newViewId);
        list.setEmptyView(newEView);
    }

    protected void createRecord() {
        Intent intent = new Intent(this, CreateRecipeActivity.class);
        startActivity(intent);
    }
}
