package hkcc.ccn3165.advancelistview;

import android.app.Activity;
import android.view.*;
import android.widget.*;

import com.android.development.R;

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] city;
    private final String[] descr;
    private final Integer[] imageId;

    public CustomList(Activity context, String[] city, String[] descr, Integer[] imageId) {
        super(context, R.layout.list_single, city);
        this.context = context;
        this.city = city;
        this.descr = descr;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView txtContent = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtContent.setText(city[position] + System.getProperty("line.separator") + descr[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
