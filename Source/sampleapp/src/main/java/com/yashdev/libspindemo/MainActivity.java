package com.yashdev.libspindemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yashdev.libspin.SpinWheel;
import com.yashdev.libspin.model.SpinItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    List<SpinItem> data = new ArrayList<>();
    SpinWheel spinWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        spinWheel = findViewById(R.id.spinWheel);
        TextView spinTitle = findViewById(R.id.spinTitle);
        Button spinButton = findViewById(R.id.spinButton);

        spinTitle.setText("SPIN & WIN");
        spinTitle.setTextSize(32);

        final SpinItem item1 = new SpinItem();
        item1.topText = "0";
        item1.icon = R.drawable.ic_coins;
        item1.color = 0xffFFF3E0;
        data.add(item1);

        SpinItem item2 = new SpinItem();
        item2.topText = "1";
        item2.icon = R.drawable.ic_coins;
        item2.color = 0xffFFE0B2;
        data.add(item2);

        SpinItem item3 = new SpinItem();
        item3.topText = "2";
        item3.icon = R.drawable.ic_coins;
        item3.color = 0xffFFCC80;
        data.add(item3);

        SpinItem item4 = new SpinItem();
        item4.topText = "3";
        item4.icon = R.drawable.ic_coins;
        item4.color = 0xffFFF3E0;
        data.add(item4);

        SpinItem item5 = new SpinItem();
        item5.topText = "4";
        item5.icon = R.drawable.ic_coins;
        item5.color = 0xffFFE0B2;
        data.add(item5);

        SpinItem item6 = new SpinItem();
        item6.topText = "5";
        item6.icon = R.drawable.ic_coins;
        item6.color = 0xffFFCC80;
        data.add(item6);

        spinWheel.setData(data);
        spinWheel.setRound(5);
        spinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start the Spin
                int index = getRandomIndex();
                spinWheel.startSpinWheelWithTargetIndex(index);
            }
        });

        // Spin Wheel onFinishListener
        spinWheel.setSpinWheelRoundItemSelectedListener(new SpinWheel.SpinWheelRoundItemSelectedListener() {
            @Override
            public void SpinWheelRoundItemSelected(int index) {

                // reward here
                String topText = data.get(index).topText;

                Toast.makeText(getApplicationContext(), topText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getRandomIndex() {
        Random rand = new Random();
        return rand.nextInt(data.size() - 1) + 0;
    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
    }
}
