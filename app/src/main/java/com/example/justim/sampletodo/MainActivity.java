package com.example.justim.sampletodo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ListView lvItems;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private Button btnAddItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddItems = (Button)findViewById(R.id.btnAddItem);
        btnAddItems.setOnClickListener(onAddItem);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1
            , items);
        lvItems.setAdapter(itemsAdapter);
        lvItems.setOnItemLongClickListener(deleteItems);
        items.add("First Item");
        items.add("Second Item");
    }



    private AdapterView.OnItemLongClickListener deleteItems =
            new AdapterView.OnItemLongClickListener() {


                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("確認");
                    dialog.setMessage("確定要刪除資料嗎？");
                    final int pos = position;
                    dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteListViewItem(pos);
                        }
                    });

                    dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();

                    return true;
                }
            };

    private void deleteListViewItem(int position) {
        items.remove(position);
        itemsAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener onAddItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText edItems = (EditText)findViewById(R.id.edNewItem);
            itemsAdapter.add(edItems.getText().toString());
            edItems.setText("");

        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
