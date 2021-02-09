package sr.unasat.country.rest.financehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.country.rest.financehelper.database.FinancialDAO;
import sr.unasat.country.rest.financehelper.entities.User;

import static sr.unasat.country.rest.financehelper.database.FinancialDAO.USER_PASSWORD;
import static sr.unasat.country.rest.financehelper.database.FinancialDAO.USER_TABLE;
import static sr.unasat.country.rest.financehelper.database.FinancialDAO.USER_USERNAME;

public class MainActivity extends AppCompatActivity {

    private FinancialDAO financialDAO;
    private List credentialsUsernames = new ArrayList<>();
    private List credentialsPasswords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        financialDAO = new FinancialDAO(this);
        User user = financialDAO.findOneRecordByUsername("admin");
        List<ContentValues> records = new ArrayList<>();

        //insert record
        ContentValues record1 = new ContentValues();
        record1.put(USER_USERNAME, "spiderman");
        record1.put(USER_PASSWORD, "pwd");
        records.add(record1);
        //  long result =  financialDAO.insertOneRecord(USER_TABLE, record1);
        //  user = financialDAO.findOneRecordByUsername("spiderman");
        ContentValues record2 = new ContentValues();
        record2.put(USER_USERNAME, "superman");
        record2.put(USER_PASSWORD, "pwd");
        records.add(record2);

        ContentValues record3 = new ContentValues();
        record3.put(USER_USERNAME, "wonderwoman");
        record3.put(USER_PASSWORD, "pwd");
        records.add(record3);

        financialDAO.insertMultipleRecord(USER_TABLE, records);

        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (containsUsername(credentialsUsernames, username.getText().toString())  && containsPassword(credentialsPasswords, password.getText().toString())
                && credentialsUsernames.indexOf(username.getText().toString()) == credentialsPasswords.indexOf(password.getText().toString())){
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView credentialsTextView = (TextView) findViewById(R.id.credentials);
        List<User> users = financialDAO.findAllRecords(USER_TABLE);
        for (User foundUser : users) {
            credentialsUsernames.add(foundUser.getUserName());
            credentialsPasswords.add(foundUser.getPassword());
        }
        credentialsTextView.setText(credentialsPasswords.toString());
    }
    public static boolean containsUsername(List credentialsUsernames, String findString) {
            return credentialsUsernames.contains(findString);
    }

    public static boolean containsPassword(List credentialsPasswords, String findString) {
        return credentialsPasswords.contains(findString);
    }
}