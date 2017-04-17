package gui.counting.tarot.fr.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import fr.tarot.counting.control.ApplicationControlServices;
import fr.tarot.counting.control.DoneControlServices;
import fr.tarot.counting.control.PlayerControlServices;
import fr.tarot.counting.control.internal.ApplicationControlServicesImpl;
import fr.tarot.counting.control.internal.DoneControlServicesImpl;
import fr.tarot.counting.control.internal.PlayerControlServicesImpl;
import fr.tarot.counting.model.Application;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.DoneType;
import fr.tarot.counting.model.Player;

public class DoneActivity extends AppCompatActivity {

    private Button okButton;
    private TextView scores;
    private TextView currentScore;
    private TarotLauncher launcher;
    private SeekBar seekScore;
    private Spinner taker;
    private Spinner called;
    private ToggleButton toggle1;
    private ToggleButton toggle21;
    private ToggleButton toggleExcuse;
    private RadioButton petite;
    private RadioButton garde;
    private RadioButton gardeSans;
    private RadioButton gardeContre;
    private Done done;
    private Application application;
    private ApplicationControlServices applicationControlServices = new ApplicationControlServicesImpl();
    private PlayerControlServices playerControlServices = new PlayerControlServicesImpl();
    private DoneControlServices doneControlServices = new DoneControlServicesImpl(playerControlServices);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        this.okButton = (Button) findViewById(R.id.button);
        this.scores = (TextView) findViewById(R.id.scores);
        this.seekScore = (SeekBar) findViewById(R.id.seekScore);
        this.currentScore = (TextView) findViewById(R.id.currentScore);
        this.called = (Spinner) findViewById(R.id.spinnerCalled);
        this.taker = (Spinner) findViewById(R.id.spinnerTaker);
        this.toggle1 = (ToggleButton) findViewById(R.id.toggle1);
        this.toggle21 = (ToggleButton) findViewById(R.id.toggle21);
        this.toggleExcuse = (ToggleButton) findViewById(R.id.toggleExcuse);
        this.petite = (RadioButton) findViewById(R.id.radioPetite);
        this.garde = (RadioButton) findViewById(R.id.radioGarde);
        this.gardeSans = (RadioButton) findViewById(R.id.radioGardeSans);
        this.gardeContre = (RadioButton) findViewById(R.id.radioGardeContre);
        initDone();
        this.postInitialize();
    }

    private void postInitialize() {
        this.okButton.setOnClickListener(v->DoneActivity.this.onOkClicked(v));
        currentScore.setText("");
        this.seekScore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  currentScore.setText(String.valueOf(progress));
              }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {

              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {

              }
          }
        );
        String[] playersNames = new String[5];
        List<Player> players = new ArrayList<>(done.getPlayers());
        players.removeAll(done.getDeads());
        for (int i = 0 ; i < 5 ; i++) {
            playersNames[i] = players.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playersNames);
        taker.setAdapter(adapter);
        called.setAdapter(adapter);
    }

    private void onOkClicked(View v) {
        this.launcher = new TarotLauncher();
        done.setCalled(doneControlServices.getPlayer(done, called.getSelectedItem().toString()));
        done.setTaker(doneControlServices.getPlayer(done, taker.getSelectedItem().toString()));
        done.setLittleForTaker(toggle1.isChecked());
        done.setTwentyOneForPlayer(toggle21.isChecked());
        done.setExcuseForPlayer(toggleExcuse.isChecked());
        done.setPointsForTakerTeam(seekScore.getProgress());
        done.setType(getDoneType());
        doneControlServices.computePoints(done);
		this.scores.setText(doneControlServices.showResults(done));
        Intent intent = new Intent(this, ApplicationActivity.class);
        intent.putExtra(ApplicationActivity.DONE, done);
        intent.putExtra(ApplicationActivity.APPLICATION, application);
        startActivity(intent);
        finish();
    }

    private DoneType getDoneType(){
        DoneType type = DoneType.PETITE;
        if (petite.isChecked()){
            type = DoneType.PETITE;
        } else if (garde.isChecked()){
            type = DoneType.GARDE;
        } else if (gardeSans.isChecked()){
            type = DoneType.GARDE_SANS;
        } else if (gardeContre.isChecked()){
            type = DoneType.GARDE_CONTRE;
        }
        return type;
    }

    private void initDone(){
        application = Application.getInstance();
        done = application.getDones().get(getIntent().getExtras().getInt(ApplicationActivity.DONE));

        for (Player player : application.getPlayers()) {
            if ("Antoine".equals(player.getName()) || "Alexandre".equals(player.getName())) {
                doneControlServices.addDead(done, new Player(player));
            } else {
                doneControlServices.addPlayer(done, new Player(player));
            }
        }
    }
}
