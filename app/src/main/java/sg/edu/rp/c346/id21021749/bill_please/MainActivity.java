package sg.edu.rp.c346.id21021749.bill_please;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

EditText etAmt;
EditText etPax;
EditText etDisc;
ToggleButton tbNotSvs;
ToggleButton tbGst;
RadioGroup rgPayment;
Button btnSplit;
Button btnReset;
TextView tvTotal;
TextView tvSplit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.amtEnter);
        etPax = findViewById(R.id.paxEnter);
        etDisc = findViewById(R.id.discAmt);
        tbNotSvs = findViewById(R.id.notcheckedsvs);
        tbGst = findViewById(R.id.gst);
        rgPayment = findViewById(R.id.radioGroupPayment);
        btnSplit = findViewById(R.id.split);
        btnReset = findViewById(R.id.reset);
        tvTotal = findViewById(R.id.totalBill);
        tvSplit = findViewById(R.id.splitBill);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPax.getText().toString().length() != 0 && etAmt.getText().toString().length() != 0) {
                    double newTotal = 0.0;
                    if (tbNotSvs.isChecked() && !tbGst.isChecked()){
                        newTotal = Double.parseDouble((etAmt.getText().toString())) * 1.07;
                    } else if (!tbNotSvs.isChecked() && !tbGst.isChecked()) {
                        newTotal = Double.parseDouble((etAmt.getText().toString()));
                    } else if (!tbNotSvs.isChecked() && tbGst.isChecked()){
                        newTotal = Double.parseDouble((etAmt.getText().toString())) * 1.1;
                    } else {
                        newTotal = Double.parseDouble((etAmt.getText().toString())) * 1.7;
                    }

                    //discount
                    if(etDisc.getText().toString().length() != 0) {
                        newTotal *= 1 - Double.parseDouble(etAmt.getText().toString()) / 100;
                    }

                    tvTotal.setText("Total Bill: $" + String.format("%.2f", newTotal));
                    int numOfPeople = Integer.parseInt(etPax.getText().toString());
                    double newAmt = newTotal / numOfPeople;
                    int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                    if (checkedRadioId == R.id.radioButtonCash && numOfPeople != 1 ) {
                        tvSplit.setText("Each Pays: $" + String.format("%.2f",newAmt + " via cash"));
                    } else if (checkedRadioId == R.id.radioButtonPaynow && numOfPeople != 1 ){
                        tvSplit.setText("Each Pays: $" + String.format("%.2f",newAmt + " via paynow"));
                    } else if (checkedRadioId == R.id.radioButtonPaynow ) {
                        tvSplit.setText("Each Pays: $" + String.format("%.2f" ,newTotal + " via paynow"));
                    } else {
                        tvSplit.setText("Each Pays: $" + String.format("%.2f",newTotal + " via cash"));
                    }

                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAmt.setText("");
                etPax.setText("");
                tbNotSvs.setChecked(false);
                tbGst.setChecked(false);
                etDisc.setText("");
            }
        });








    }
}