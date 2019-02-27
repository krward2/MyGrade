package edu.utep.cs.mygrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import java.util.List;

public class GradeActivity extends AppCompatActivity {

    private TextView textGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        //Will display grades or error message
        textGrades = findViewById(R.id.textGrades);

        WebClient web = new WebClient(new WebClient.GradeListener() {

            /** Called when a requested grade is found. */
            public void onGrade(String date, Grade grade) {
                //Display the grades
                textGrades.setText(date);
                textGrades.append(Html.fromHtml("<br/>Course Grade: " + grade.total + " " + grade.grade));

                List<Grade.Score> scores = grade.scores();
                for(int i = 0; i < scores.size(); i++){
                    textGrades.append(Html.fromHtml("<br/>" + scores.get(i).name +
                            " " + " " + scores.get(i).earned + "/" + scores.get(i).max));
                }

            }

            /** Called when an error is encountered. */
            public void onError(String msg) {
                //Display error message
                textGrades.setText(msg);
            }
        });
        //Unbundle the username and password from main activity
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        //Query the server using the username and password given in main activity
        web.queryAsync(username, password);
    }
}
