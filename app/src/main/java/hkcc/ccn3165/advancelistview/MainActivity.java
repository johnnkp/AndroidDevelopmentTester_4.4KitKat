package hkcc.ccn3165.advancelistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.development.R;

public class MainActivity extends AppCompatActivity { // ListActivity
    private ListView list;

    // Declare the city array
    private String[] cityArray = {
            "Amsterdam",
            "Austin",
            "Barcelona",
            "Capetown",
            "Dublin",
            "London",
            "New York",
            "Paris",
            "San Francisco",
            "Stockholm",
            "Sydney",
            "Tokyo",
            "Wellington",
    };

    // Declare the description array
    private String[] descrArray = {
            "Amsterdam is ......",
            "Austin is ......",
            "Barcelona is ......",
            "Capetown is ......",
            "Dublin is ......",
            "London, England’s capital, set on the River Thames, is a 21st-century city ......",
            "New York is ......",
            "Paris is ......",
            "San Francisco, in northern California, is a city on the tip of a peninsula ......",
            "Stockholm is ......",
            "Sydney is ......",
            "Tokyo is ......",
            "Wellington is ......"
    };

    // Declare the image array
    private Integer[] imageIdArray = {
            R.drawable.amsterdam,
            R.drawable.austin,
            R.drawable.barcelona,
            R.drawable.capetown,
            R.drawable.dublin,
            R.drawable.london,
            R.drawable.newyork,
            R.drawable.paris,
            R.drawable.sanfrancisco,
            R.drawable.stockholm,
            R.drawable.sydney,
            R.drawable.tokyo,
            R.drawable.wellington,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advance_listview);

        /* String[] items = new String[]{"Business", "Engineering", "Science", "Art", "Medicine", "Design", "Marine", "Architecture", "Laws", "Agriculture"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter); */
        CustomList adapter = new CustomList(MainActivity.this, cityArray, descrArray, imageIdArray);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You clicked at " + cityArray[+position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ITEM_NAME", descrArray[position]);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    /* @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " is selected", Toast.LENGTH_LONG).show();
    } */
}
