package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransitionImpl;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimdeano.Data.SecurityPreferences;
import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.FimDeAnoConstants;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    /* criar uma instância da classe viewHolder criada */

    private viewHolder mviewHolder = new viewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        // Mapeamento dos elementos da activity_details
        this.mviewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mviewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participate) {
            if (this.mviewHolder.checkParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    private static class viewHolder {
        CheckBox checkParticipate;
    }

    private void loadDataFromActivity() {
        //Obtendo os dados trazidos pela intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)) {
                this.mviewHolder.checkParticipate.setChecked(true);
            } else {
                this.mviewHolder.checkParticipate.setChecked(false);
            }
        }

    }

}
