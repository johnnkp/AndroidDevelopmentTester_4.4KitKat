package hkcc.ccn3165.advancelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.development.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the Item Name from the Bundle
        Bundle bundle = this.getIntent().getExtras();
        String itemName = bundle.getString("ITEM_NAME");

        // Display the item name
        TextView tItemName = (TextView) findViewById(R.id.tItem);
        tItemName.setText("Selected Item: " + itemName);

    }
}
