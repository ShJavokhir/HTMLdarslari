//Bosh menyularni ko'rsatuvchi class
package uz.dasturlash.html;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uz.dasturlash.html.models.RegisterUserFireStore;

public class MainActivity extends AppCompatActivity {
    String temp = "";
    LinearLayout openCats,editor,colorShow,tags;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle draweToggle;
    private NavigationView navigationView;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        test();
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        draweToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(draweToggle);
        draweToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);//categirues
                startActivity(intent);
            }
        });

        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CodeEditor.class);
                startActivity(intent);

            }
        });
        colorShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Colors.class);
                startActivity(intent);

            }
        });
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllTags.class);
                startActivity(intent);
            }
        });
        System.out.println(temp);

    }
    void init(){
        SharedPreferences pref = getSharedPreferences("baza", Context.MODE_PRIVATE);

        if(!pref.contains("isFirstRunning")){
            WhenFirstRun first = new WhenFirstRun(MainActivity.this);
        }
        navigationView = (NavigationView) findViewById(R.id.navigation_view1);
        updateUserInfoDrawer();
        initDrawerMenu();
        colorShow = (LinearLayout) findViewById(R.id.colorsShow);
        editor = (LinearLayout) findViewById(R.id.editor);
        openCats = (LinearLayout) findViewById(R.id.openCats);
        toolbar =  (Toolbar) findViewById(R.id.toolbar1);
        tags = (LinearLayout) findViewById(R.id.tags);


    }
    private void initDrawerMenu(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent intent;
                switch (id) {
                    case R.id.dashboard:
                         intent = new Intent(MainActivity.this, User_Dashboard.class);
                        startActivity(intent);
                        break;
                    case R.id.about_app:
                         intent = new Intent(MainActivity.this, AboutApp.class);
                         startActivity(intent);
                         break;
                    case R.id.registration:
                        intent = new Intent(MainActivity.this, Registration.class);
                        startActivity(intent);
                        break;
                    case R.id.notifications :
                        intent = new Intent(MainActivity.this, Notification_body.class);
                        startActivity(intent);
                        break;
                    case R.id.sign_out:
                        FirebaseAuth.getInstance().signOut();
                        signOut();
                        updateUserInfoDrawer();


                }

                return false;
            }
        });
    }
    private void test(){
        FirebaseApp.initializeApp(this);

        FirebaseUser a = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put("user_name","Javohirbek");
        dataToSave.put("familiya","shomuradov");
        CollectionReference collectionReference = firestore.collection("asda/salom/alik");
        RegisterUserFireStore model = new RegisterUserFireStore("Javohir","Shomuradov");
        firestore.collection("adsa").add(dataToSave).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Log.e("salom", "bo'ldi!!!!");
                }else{
                    Log.e("salom","bo'lmadi!!!!");
                }
            }
        });

        /*   collectionReference.add(model).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Log.e("salom", "bo'ldi!!!!");
                }else{
                    Log.e("salom","bo'lmadi!!!!");
                }
            }
        });*/
       /* a.createUserWithEmailAndPassword("Javohir595@mail.ru","Javohir595").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.e("salom","reg boldi");
                }else{
                    Log.e("salom","reg yooooooooq");

                }
            }
        });

        a.signInWithEmailAndPassword("Javohir595@mail.ru","Javohir595").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.e("salom","kirdi");
                }else{
                    Log.e("salom","kirmadi");

                }
            }
        });*/





        //fOXF2JvJrIo:APA91bFF1qHqr1_5rqxcgR-YzvkOIY087pF-ELg32TcRY_jz_LKLRe8JanHMzto5diUyScZLhLF3JvEGz75N9o7RpaZ5UXvb-SWya3RhryAaFmM8Pk1-11_6wnnYoAKu1vj6Sn-VlDLJ
        //MyFirebaseInstanceService temp = new MyFirebaseInstanceService();
        //temp.onTokenRefresh();
       ///Log.e("TOKEN", FirebaseInstanceId.getInstance().getToken());
        temp = "Token: " + FirebaseInstanceId.getInstance().getToken();
        //System.out.println(temp);
        /*  database = FirebaseDatabase.getInstance();
         databaseReference = database.getReference("message");
        databaseReference.setValue("hello firebase");
    */}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        Intent intent;
        if(draweToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (itemid){
            case R.id.about_app:
                 intent = new Intent(MainActivity.this,AboutApp.class);
                startActivity(intent);
                return true;
            case R.id.editor:
                 intent = new Intent(MainActivity.this,CodeEditor.class);
                startActivity(intent);
            case R.id.registration:
                intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateUserInfoDrawer(){
        SharedPreferences pref = getSharedPreferences("baza", Context.MODE_PRIVATE);
        View header = navigationView.getHeaderView(0);
        TextView user_name = (TextView) header.findViewById(R.id.user_name);
        if(!pref.contains("user_name")){
            user_name.setText("Tizimga kirilmagan");
        }else{
            String _user_name = pref.getString("user_name","");
            user_name.setText(_user_name);
        }
    }
    private void signOut(){
        SharedPreferences pref = getSharedPreferences("baza", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("user_name");
        editor.apply();

    }
}
