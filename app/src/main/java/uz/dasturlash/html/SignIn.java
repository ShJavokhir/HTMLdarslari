package uz.dasturlash.html;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    private MaterialEditText email, password;
    private Button signin, register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        init();
        isSignedBefore();
    }
    private void init(){
        email = (MaterialEditText) findViewById(R.id.email);
        password = (MaterialEditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        register = (Button) findViewById(R.id.register);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,Registration.class);
            }
        });
    }
    private void signin(){
        final ProgressDialog progressDialog = ProgressDialog.show(SignIn.this,"Iltimos kutib turing","Yuklanyapdi.....",true);


        final FirebaseAuth sign = FirebaseAuth.getInstance();
        sign.signInWithEmailAndPassword(String.valueOf(email.getText()),String.valueOf(password.getText())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    SharedPreferences pref = getSharedPreferences("baza", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_name", sign.getCurrentUser().getDisplayName());
                    editor.commit();

                    Log.e("salom","signed");
                    Log.e("salom",sign.getCurrentUser().getDisplayName());

                    signed();
                }else{
                    Log.e("salom","notSigned");
                }
            }
        });

    }
    private void signed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
        builder.setTitle("Xabar")
                .setMessage("Akkauntga muvaffaqiyatli kirildi !")
                .setIcon(R.drawable.checked)
                .setCancelable(false)
                .setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(SignIn.this,MainActivity.class);
                                startActivity(intent);
                                //dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void isSignedBefore(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      //  Log.e("salom","USER : "+String.valueOf(user.getEmail()));
        if(user!=null){
            Toast.makeText(SignIn.this,"Siz o'z akkauntingizga allaqachon kirgansiz !",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignIn.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
