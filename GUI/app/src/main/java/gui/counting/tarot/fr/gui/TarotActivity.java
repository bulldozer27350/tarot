package gui.counting.tarot.fr.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TarotActivity extends AppCompatActivity {

    private Button button;
    private TextView scores;
    private TarotLauncher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot);
        this.button = (Button) findViewById(R.id.button);
        this.scores = (TextView) findViewById(R.id.scores);
        this.postInitialize();
    }

    private void postInitialize() {
        this.button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TarotActivity.this.onButtonClick(v);
            }
        });
    }

    private void onButtonClick(View v) {
        Log.e("log", "Boutton clické");
        this.launcher = new TarotLauncher();
		this.scores.setText(launcher.launch());
    }

}
