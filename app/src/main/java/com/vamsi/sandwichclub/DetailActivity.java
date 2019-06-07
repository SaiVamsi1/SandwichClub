package com.vamsi.sandwichclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.vamsi.sandwichclub.model.SandwichData;
import com.vamsi.sandwichclub.utils.JsonUtils;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final int DEFAULT_POSITION = -1;
    public static final String EXTRA_POSITION = "extra_position";


    private ImageView IngredientsImageView;
    private TextView OriginTextView;
    private TextView AlsoKnowAsTextView;
    private TextView IngredientsTextView;
    private TextView DescriptionTextView;

    private SandwichData Sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        IngredientsImageView = findViewById(R.id.image_iv);
        OriginTextView = findViewById(R.id.tv_origin);
        AlsoKnowAsTextView = findViewById(R.id.tv_also_known);
        IngredientsTextView = findViewById(R.id.tv_ingredients);
        DescriptionTextView = findViewById(R.id.tv_description);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich = JsonUtils.parseSandwichJson(json);
        if (Sandwich == null) {
            closeOnError();
            return;
        }

        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        Picasso.with(this)
                .load(Sandwich.getImage())
                .into(IngredientsImageView);

        setTitle(Sandwich.getMainName());
        OriginTextView.setText(Sandwich.getPlaceOfOrigin());
        AlsoKnowAsTextView.setText(TextUtils.join(", ", Sandwich.getAlsoKnownAs()));
        IngredientsTextView.setText(TextUtils.join(", ", Sandwich.getIngredients()));
        DescriptionTextView.setText(Sandwich.getDescription());
    }

}
