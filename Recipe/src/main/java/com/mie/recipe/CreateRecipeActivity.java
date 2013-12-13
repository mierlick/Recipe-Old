package com.mie.recipe;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mie.recipe.db.CategoryDAO;
import com.mie.recipe.db.RecipeDAO;
import com.mie.recipe.entities.Category;
import com.mie.recipe.entities.Recipe;

public class CreateRecipeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);

        }

        setContentView(R.layout.create_recipe);
        populateCategoryList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();

//        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateCategoryList() {
        Spinner categorySpinner = (Spinner) findViewById(R.id.editCategory);

        CategoryDAO categoryDAO = new CategoryDAO(this);

        Category[] results = categoryDAO.categorySearch();

        ArrayAdapter<Category> adapter =
                new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, android.R.id.text1, results);
        categorySpinner.setAdapter(adapter);
    }

    public void cancel(View view) {
        finish();
    }

    public void createRecipe(View view) {
        EditText nameEditText = (EditText)findViewById(R.id.editName);
        Spinner categorySpinner = (Spinner)findViewById(R.id.editCategory);
        EditText totalTimeEditText = (EditText)findViewById(R.id.editTotalTime);
        EditText ingredientsEditText = (EditText)findViewById(R.id.editIngredients);
        EditText instructionsEditText = (EditText)findViewById(R.id.editInstructions);

        Recipe recipe = new Recipe();

        if (nameEditText.getText() != null) {
            recipe.setName(nameEditText.getText().toString());
        }

        Category category = (Category)categorySpinner.getSelectedItem();
        recipe.setCategory(category);

        if (totalTimeEditText.getText() != null) {
            int totalTime = Integer.parseInt(totalTimeEditText.getText().toString());
            recipe.setMinutes(totalTime);
        }

        if (ingredientsEditText.getText() != null) {
            recipe.setIngredients(ingredientsEditText.getText().toString());
        }

        if (instructionsEditText.getText() != null) {
            recipe.setInstructions(instructionsEditText.getText().toString());
        }

        RecipeDAO recipeDAO = new RecipeDAO(this);

        recipeDAO.createRecipe(recipe);

        Intent intent = new Intent(this, RecipeViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("recipe", recipe);
        startActivity(intent);

    }
}
