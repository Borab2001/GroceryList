package comp5216.sydney.edu.au.groceryapp;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private TextView header;
    private Button addItemButton;
    private Button nextButton;
    private Button addButton;
    private Button filterButton;
    private Button filterDateButton;
    private Button resetFilterButton;
    private DatePicker datePicker;
    private EditText groceryItem;
    private ListView listView;
    private ArrayList<String> items;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        header = findViewById(R.id.header);
        addItemButton = findViewById(R.id.addItemButton);
        nextButton = findViewById(R.id.nextButton);
        addButton = findViewById(R.id.addButton);
        filterButton = findViewById(R.id.filterButton);
        filterDateButton = findViewById(R.id.filterDateButton);
        resetFilterButton = findViewById(R.id.resetFilterButton);
        datePicker = findViewById(R.id.datePicker);
        groceryItem = findViewById(R.id.groceryItem);
        listView = findViewById(R.id.listView);

        // Initialize ArrayList and CustomAdapter
        items = new ArrayList<>();
        adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);

        // Set onClick listeners
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide
                listView.setVisibility(View.GONE);
                addItemButton.setVisibility(View.GONE);
                filterButton.setVisibility(View.GONE);

                // Display
                datePicker.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);

                // Update header text
                header.setText("Add a date");
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide
                datePicker.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);

                // Display
                groceryItem.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);

                // Update header text
                header.setText("Name your item");
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

                    // Sort the list
                    Collections.sort(items, new Comparator<String>() {
                        @Override
                        public int compare(String item1, String item2) {
                            return item1.compareTo(item2);
                        }
                    });

                    adapter.notifyDataSetChanged();
                }

                // Reset to initial
                adapter.setFilterDate(null);
                header.setText("My Grocery List");

                // Hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(groceryItem.getWindowToken(), 0);
                }

                groceryItem.setText("");

                // Hide
                groceryItem.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);

                // Display
                listView.setVisibility(View.VISIBLE);
                addItemButton.setVisibility(View.VISIBLE);
                filterButton.setVisibility(View.VISIBLE);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide
                listView.setVisibility(View.GONE);
                addItemButton.setVisibility(View.GONE);
                filterButton.setVisibility(View.GONE);

                // Display
                datePicker.setVisibility(View.VISIBLE);
                filterDateButton.setVisibility(View.VISIBLE);

                // Update header text
                header.setText("Select a date to filter");
            }
        });

        filterDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter grocery items by selected date
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;
                adapter.setFilterDate(date);

                // Update header text
                header.setText("List for " + date);

                // Hide
                datePicker.setVisibility(View.GONE);
                filterDateButton.setVisibility(View.GONE);
                addItemButton.setVisibility(View.GONE);
                filterButton.setVisibility(View.GONE);

                // Display
                listView.setVisibility(View.VISIBLE);
                resetFilterButton.setVisibility(View.VISIBLE);
                groceryItem.setText("");
            }
        });

        resetFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset adapter filter
                adapter.setFilterDate(null);

                // Reset header text
                header.setText("My Grocery List");

                // Hide
                resetFilterButton.setVisibility(View.GONE);

                //Display
                listView.setVisibility(View.VISIBLE);
                addItemButton.setVisibility(View.VISIBLE);
                filterButton.setVisibility(View.VISIBLE);

                groceryItem.setText("");
            }
        });
    }
}