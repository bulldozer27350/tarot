package gui.counting.tarot.fr.gui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.tarot.counting.control.ApplicationControlServices;
import fr.tarot.counting.control.DoneControlServices;
import fr.tarot.counting.control.PlayerControlServices;
import fr.tarot.counting.control.internal.ApplicationControlServicesImpl;
import fr.tarot.counting.control.internal.DoneControlServicesImpl;
import fr.tarot.counting.control.internal.PlayerControlServicesImpl;
import fr.tarot.counting.model.Application;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

public class ApplicationActivity extends AppCompatActivity {

    public static final String DONE = "done";
    public static final String APPLICATION = "application";

    private TableLayout scoresTable;
    private Button newDone;
    private Button updateDone;
    private RadioGroup radioGroup;

    private DoneControlServices doneControlServices;
    private PlayerControlServices playerControlServices;
    private ApplicationControlServices applicationControlServices;

    Application application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        initServices();

        scoresTable = (TableLayout) findViewById(R.id.scoresTable);
        newDone = (Button) findViewById(R.id.newDoneButton);
        updateDone = (Button) findViewById(R.id.updateDoneButton);
        radioGroup = (RadioGroup) findViewById(R.id.selection);
        radioGroup.setOrientation(TableLayout.VERTICAL);
        postInitialize();
    }

    private final void initServices(){
        this.playerControlServices = new PlayerControlServicesImpl();
        this.doneControlServices = new DoneControlServicesImpl(playerControlServices);
        this.applicationControlServices = new ApplicationControlServicesImpl();
        application = Application.getInstance();
        if (application.getPlayers().size() == 0)
            initApplication();
        System.out.println("Nombre de dones dans initServices : " + application.getDones().size());
    }

    private void initApplication(){
        Player sebastien = new Player();
        sebastien.setName("Sebastien");

        Player jamal = new Player();
        jamal.setName("Jamal");

        Player virgine = new Player();
        virgine.setName("Virginie");

        Player marco = new Player();
        marco.setName("Marco");

        Player pierre = new Player();
        pierre.setName("Pierre");

        Player antoine = new Player();
        antoine.setName("Antoine");

        Player alexandre = new Player();
        alexandre.setName("Alexandre");

        applicationControlServices.addPlayer(application, sebastien);
        applicationControlServices.addPlayer(application, jamal);
        applicationControlServices.addPlayer(application, virgine);
        applicationControlServices.addPlayer(application, marco);
        applicationControlServices.addPlayer(application, pierre);
        applicationControlServices.addPlayer(application, antoine);
        applicationControlServices.addPlayer(application, alexandre);
    }

    private void postInitialize(){
        initFirstLine();
        this.newDone.setOnClickListener((v)->ApplicationActivity.this.newDoneClicked(v));
        initLines();
    }

    private void newDoneClicked(View v){
        Intent intent = new Intent(this, DoneActivity.class);
        Done done = new Done();
        applicationControlServices.addDone(application, done);
        intent.putExtra(DONE, getDoneIndex(done));
        startActivity(intent);
        finish();
    }

    private int getDoneIndex(Done done){
        int doneIndex = -1;
        for (int i = 0 ; i < application.getDones().size() ; i++){
            Done d = application.getDones().get(i);
            if (done.equals(d)){
                doneIndex = i;
            }
        }
        return doneIndex;
    }

    private void initFirstLine(){
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        for(Player player : application.getPlayers()){
            TextView playerName = new TextView(this);
            playerName.setText(player.getName());
            row.addView(playerName);
        }
        scoresTable.addView(row,0);
    }

    private void initLines(){
        System.out.println("Nombre de joueurs dans initLines : " + application.getPlayers().size());
        int i = 1;
        for (Done done : application.getDones()){
            System.out.println("Nombre de joueurs dans la done dans initLines : " + application.getDones().get(0).getPlayers().size());
            TableRow line = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            line.setLayoutParams(lp);
            //RadioButton radio = new RadioButton(this);
            //radioGroup.addView(radio);
            //line.addView(radio);
            for(Player player : application.getPlayers()) {
                Player donePlayer = null;
                List<Player> allPlayers = new ArrayList<>(done.getPlayers());
                allPlayers.addAll(done.getDeads());
                for (Player donePlayer1 : allPlayers){
                    if (donePlayer1.getName().equals(player.getName())){
                        donePlayer = donePlayer1;
                    }
                }
                TextView playerPoints = new TextView(this);
                playerPoints.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                if (done.getDeads().contains(donePlayer)){
                    playerPoints.setBackgroundColor(Color.DKGRAY);
                    playerPoints.setTextColor(Color.WHITE);
                    playerPoints.setText("0");
                } else {
                    if (donePlayer.getPoints() > 0) {
                        playerPoints.setBackgroundColor(Color.argb(180, 50, 240, 50));
                    }
                    else {
                        playerPoints.setBackgroundColor(Color.RED);
                    }
                    playerPoints.setTextColor(Color.WHITE);
                    playerPoints.setText(String.valueOf(donePlayer.getPoints()));
                }
                line.addView(playerPoints);
            }
            scoresTable.addView(line,i);
            i++;
        }
    }
}
