package comp5216.sydney.edu.au.groceryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addItemButton;
    private Button nextButton;
    private Button addButton;
    private DatePicker datePicker;
    private EditText groceryItem;
    private ListView listView;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        addItemButton = findViewById(R.id.addItemButton);
        nextButton = findViewById(R.id.nextButton);
        addButton = findViewById(R.id.addButton);
        datePicker = findViewById(R.id.datePicker);
        groceryItem = findViewById(R.id.groceryItem);
        listView = findViewById(R.id.listView);

        // Initialize ArrayList and ArrayAdapter
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Set onClick listeners
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide header, list, and Add Item button, then show date picker and Next button
                findViewById(R.id.header).setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                addItemButton.setVisibility(View.GONE);
                datePicker.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide date picker and next button, show grocery item input and add button
                datePicker.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);
                groceryItem.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add grocery item to list with the selected date
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;
                String item = date + " - " + groceryItem.getText().toString();
                if (!item.isEmpty()) {
                    items.add(item);
                    adapter.notifyDataSetChanged();
                }

                // Reset views
                groceryItem.setText("");
                groceryItem.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);
                findViewById(R.id.header).setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                addItemButton.setVisibility(View.VISIBLE);
            }
        });
    }
}
