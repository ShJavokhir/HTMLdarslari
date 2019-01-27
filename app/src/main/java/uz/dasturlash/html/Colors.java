package uz.dasturlash.html;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Colors extends AppCompatActivity implements ColorPickerCallback{
    ColorPicker colorPicker;
    Button showPicker;
    TextView showColor;
    ListView colorsList;
    ArrayList<String> colors = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors);

        colorPicker = new ColorPicker(this,255, 127, 123);



        init();
        getColors();

        ColorsAdapter adapter = new ColorsAdapter(this,colors);
        colorsList.setAdapter(adapter);
    }
    void getColors(){

        BufferedReader reader;

        try{
            final InputStream file = getAssets().open("colors.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while(line != null){
                colors.add(line);
                line = reader.readLine();
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    void init(){
        showColor = (TextView) findViewById(R.id.showColor);
        showPicker = (Button) findViewById(R.id.showPicker);
        colorsList = (ListView) findViewById(R.id.colors);
        showPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              colorPicker.show();
            }
        });
    }


    @Override
    public void onColorChosen(int color) {
        Log.d("Pure Hex", Integer.toHexString(color));
        Log.d("#Hex no alpha", String.format("#%06X", (0xFFFFFF & color)));
        Log.d("#Hex with alpha", String.format("#%08X", (0xFFFFFFFF & color)));
        String tempColor = "RGB("+Integer.toString(Color.red(color))+","+Integer.toString(Color.green(color))+","+Integer.toString(Color.blue(color))+")\n"+String.format("#%06X", (0xFFFFFF & color));
        showColor.setText(tempColor);
        showPicker.setBackgroundColor(Color.parseColor(String.format("#%06X", (0xFFFFFF & color))));
    }
}

