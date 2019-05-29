package hkcc.ccn3165.courseinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.development.R;

public class menu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info_menu);
        
        // Get the reference to the ListView item in the main.xml layout
        ListView menuList = (ListView) findViewById(R.id.ListView_menu);
        
        // Create an array of strings and populate it with the items from the string array declared in strings.xml
        String[] items = getResources().getStringArray(R.array.menu_items);
        
        // Create an array adapter to create the menu with the list items being laid out as per list_item.xml
        // and the item names from the items[] array created above
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.course_info_list_item, items);
        menuList.setAdapter(adapt);        
        
        // make the menu items actionable by declaring an onclick listener for each of them
        // Use "Toast" to display the name of the menu item for a short period on the screen
        // in a message box
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                int position, long id) {
              // When clicked, show a toast with the TextView text
              Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                  Toast.LENGTH_SHORT).show();
            }
        });
    }
}