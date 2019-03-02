package uz.dasturlash.html;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class CodeEditor extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private  AlertDialog dialog;

    Button check;
    CodeEditText output;
    WebView outputnext;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_editor);
        toolbar = (Toolbar) findViewById(R.id.toolbarOutput1);

        setSupportActionBar(toolbar);
        output = (CodeEditText) findViewById(R.id.editCode);
        output.setText("<html>\n<body>\n<p align=\"center\">Salom dunyo :)</p>\n</body>\n</html>");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.code_editor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid){
          case R.id.run:
                Intent intent = new Intent(this,CodeEditorOutput.class);
                intent.putExtra("code",String.valueOf(output.getText()));
                startActivity(intent);
                return true;
          case R.id.save:
              final SharedPreferences pref1 = getSharedPreferences("codes",MODE_PRIVATE);
              final SharedPreferences.Editor shEdit = pref1.edit();
              dialogBuilder = new AlertDialog.Builder(this);
              View view1 = getLayoutInflater().inflate(R.layout.save_code_popup,null);
              final Button save = (Button) view1.findViewById(R.id.save_code_button);
              final TextView errors = (TextView) view1.findViewById(R.id.errors_save_code);
              final EditText code_name = (EditText) view1.findViewById(R.id.save_code_name);
              save.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                          if (pref1.getString(String.valueOf(code_name.getText()), "") == "") {
                              Log.e("testlash","bu yerga kid");
                              shEdit.putString(String.valueOf(code_name.getText()), String.valueOf(output.getText()));
                              shEdit.apply();
                              dialog.cancel();
                          }else{
                            errors.setText("Bu nomdagi fayl oldin saqlangan. O'rniga saqlansinmi ?");
                            save.setText("Davom ettirish");
                            save.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    shEdit.putString(String.valueOf(code_name.getText()), String.valueOf(output.getText()));
                                    shEdit.apply();
                                    dialog.cancel();
                                }
                            });
                          }


                  }
              });
              dialogBuilder.setView(view1);
              dialog = dialogBuilder.create();
              dialog.show();
              return true;
            case R.id.mycodes:
                final SharedPreferences pref = getSharedPreferences("codes",MODE_PRIVATE);
                final ArrayList<String> code_names = new ArrayList<>();
                Map<String, ?> allEntries = pref.getAll();
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    //Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                    code_names.add(entry.getKey());
                }

                dialogBuilder = new AlertDialog.Builder(this);
                View view2 = getLayoutInflater().inflate(R.layout.saved_codes_list_popup,null);
                ListView codes_list = (ListView) view2.findViewById(R.id.my_codes_list);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,code_names);
                if(code_names.size()!=0) {

                    codes_list.setAdapter(adapter);

                    codes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            output.setText(String.valueOf(pref.getString(String.valueOf(code_names.get(position)),"Malumot yo'q")));
                        }
                    });

                }
                dialogBuilder.setView(view2);
                dialog = dialogBuilder.create();
                dialog.show();
                return true;


            case R.id.delete_file:


                return true;

            default:
                return super.onOptionsItemSelected(item);

             }
        }


}
