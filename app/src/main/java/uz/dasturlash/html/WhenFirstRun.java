package uz.dasturlash.html;

import android.content.Context;
import android.content.SharedPreferences;

public class WhenFirstRun {

    public WhenFirstRun(Context context){

        SharedPreferences pref = context.getSharedPreferences("base", context.MODE_PRIVATE);

    }
}
