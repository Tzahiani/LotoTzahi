package com.app.tzahi.lototzahiv02;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;

    TextView num1,num2,num3,num4,num5,num6,numStrong;
    String strNum1,strNum2,strNum3,strNum4,strNum5,strNum6,strNumStrong,tempNum,numToDelete;

    Button btnRun,btnAdd,btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        num1 = (TextView)findViewById(R.id.txtNum1);
        num2 = (TextView)findViewById(R.id.txtNum2);
        num3 = (TextView)findViewById(R.id.txtNum3);
        num4 = (TextView)findViewById(R.id.txtNum4);
        num5 = (TextView)findViewById(R.id.txtNum5);
        num6 = (TextView)findViewById(R.id.txtNum6);
        numStrong = (TextView)findViewById(R.id.txtNumStrong);
        btnRun = (Button)findViewById(R.id.btnRun);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnShow = (Button)findViewById(R.id.btnShow);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mydb.insertData(strNum1,strNum2,strNum3,strNum4,strNum5,strNum6,strNumStrong)){
                   Toast.makeText(MainActivity.this,"המספרים נשמרו בהצלחה",Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(MainActivity.this,"המספרים לא נשמרו",Toast.LENGTH_LONG).show();
               }
            }
        });

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strNum1 = genretNumber();
                tempNum = genretNumber();
                strNum2 = genretNumber();
                strNum3 = genretNumber();
                strNum4 = genretNumber();
                strNum5 = genretNumber();
                strNum6 = genretNumber();
                strNumStrong = genretNumber();

                num1.setText(strNum1);
                num2.setText(strNum2);
                num3.setText(strNum3);
                num4.setText(strNum4);
                num5.setText(strNum5);
                num6.setText(strNum6);
                numStrong.setText(strNumStrong);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll();
            }
        });
    }
    //Show All Data was save
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void viewAll(){

        Cursor res = mydb.getAllData();
        if(res.getCount() == 0){
            showMessage("Error","No data found");
            return;
        }
     StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID: " + res.getString(0) + " Number's : " + res.getString(1) + " ,"+ res.getString(2) +
                    " ,"+ res.getString(3) + " ,"+ res.getString(4) + " ,"+ res.getString(5) + " ,"
                    + res.getString(6) + " ,"+ res.getString(7) + "\n\n");
        }
        //showMessage("Data",buffer.toString());
        shwMessageWithInput("Data",buffer.toString());
    }
    //Message of the data
    public void showMessage(String Title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
    //genrate Numbers
    private String genretNumber(){
        final Random r = new Random();
        return Integer.toString(r.nextInt(37)+1);
    }
    //message withe input
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void shwMessageWithInput(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setMessage(Message);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("מחק", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numToDelete = input.getText().toString();
                mydb.deleteData(numToDelete);
                Toast.makeText(MainActivity.this,"הצירוף נמחק בהצלחה",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("סגור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
