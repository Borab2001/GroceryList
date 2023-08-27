package comp5216.sydney.edu.au.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> items;
    private List<String> filteredItems;
    private String filterDate;

    public CustomAdapter(@NonNull Context context, ArrayList<String> list) {
        super(context, 0 , list);
        mContext = context;
        items = list;
        filteredItems = new ArrayList<>(items);
    }

    public void setFilterDate(String date) {
        filterDate = date;
        filter();
    }

    private void filter() {
        filteredItems.clear();
        if (filterDate == null || filterDate.isEmpty()) {
            filteredItems.addAll(items);
        } else {
            for (String item : items) {
                if (item.startsWith(filterDate)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,parent,false);

        String currentItem = filteredItems.get(position);

        TextView text = (TextView) listItem.findViewById(android.R.id.text1);
        text.setText(currentItem);

        return listItem;
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }
}
