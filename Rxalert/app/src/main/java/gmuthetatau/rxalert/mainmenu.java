package gmuthetatau.rxalert;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainmenu extends AppCompatActivity {

    public Button scanButton;
    public Button listButton;

    public void initScan() {
        scanButton = (Button) findViewById(R.id.buttonscan);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(mainmenu.this, Barcode_Scanner.class);
                startActivity(register);
            }
        });
    }

    public void initList() {
        listButton = (Button) findViewById(R.id.buttonlist);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(mainmenu.this, rx_list.class);
                startActivity(register);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        initScan();
        initList();
    }
}
