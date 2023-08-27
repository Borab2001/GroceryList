package comp5216.sydney.edu.au.groceryapp;

import android.os.Bundle;
import android.view.View;
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
                // Hide date picker and next button
                datePicker.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);

                // Show grocery item input and add button
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

                // Reset filter
                adapter.setFilterDate(null);

                // Reset views
                groceryItem.setText("");
                groceryItem.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);
                findViewById(R.id.header).setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                addItemButton.setVisibility(View.VISIBLE);
                filterButton.setVisibility(View.VISIBLE);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide header, list, addItem and filter buttons
                listView.setVisibility(View.GONE);
                addItemButton.setVisibility(View.GONE);
                filterButton.setVisibility(View.GONE);

                // Show date picker and filterDate button
                datePicker.setVisibility(View.VISIBLE);
                filterDateButton.setVisibility(View.VISIBLE);
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

                // Reset views
                datePicker.setVisibility(View.GONE);
                filterDateButton.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                addItemButton.setVisibility(View.GONE);
                filterButton.setVisibility(View.GONE);
                resetFilterButton.setVisibility(View.VISIBLE);
                groceryItem.setText("");
            }
        });

        resetFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset adapter filter
                adapter.setFilterDate(null);

                // Reset views
                listView.setVisibility(View.VISIBLE);
                addItemButton.setVisibility(View.VISIBLE);
                filterButton.setVisibility(View.VISIBLE);
                resetFilterButton.setVisibility(View.GONE);
                groceryItem.setText("");
            }
        });
    }
}