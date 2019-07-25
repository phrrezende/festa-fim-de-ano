package com.example.festafimdeano.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimdeano.Data.SecurityPreferences;
import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.FimDeAnoConstants;
import com.example.festafimdeano.view.DetailsActivity;

import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT =  new android.icu.text.SimpleDateFormat("dd/MM/YYYY");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysToLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDaysToLeft.setText(daysLeft);

        //Instanciando o objeto SecuityPreferences
        this.mSecurityPreferences = new SecurityPreferences(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm){
            /* Aqui é feito o mapeamento para a outra activity */
            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }

    /* Mapeamento dos elementos da activity */
    private static class ViewHolder{
        TextView textToday;
        TextView textDaysToLeft;
        Button buttonConfirm;
    }

    private int getDaysLeft(){
        //Dia de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        //Dia máximo do ano
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax-today;
    }

    private void verifyPresence(){
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        if (presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao_confirmado));
        }else if (presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));
        }else{
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }


}
