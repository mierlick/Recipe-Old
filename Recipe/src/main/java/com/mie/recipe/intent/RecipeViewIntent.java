package com.mie.recipe.intent;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.mie.recipe.RecipeViewActivity;
import com.mie.recipe.entities.Recipe;

public class RecipeViewIntent implements AdapterView.OnItemClickListener {

    public void onItemClick(AdapterView parent, View view, int position, long id) {
        Context context = parent.getContext();
        Intent intent = new Intent(context, RecipeViewActivity.class);
        Recipe recipe = (Recipe) parent.getAdapter().getItem(position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("recipe", recipe);
        context.startActivity(intent);
    }
}
