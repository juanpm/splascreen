package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.splashscreen.adapters.EquipoAdaptador;
import com.example.splashscreen.helpers.QueueUtils;
import com.example.splashscreen.models.Equipo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView equiposList;
    EquipoAdaptador equipoAdaptador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Equipo> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        equiposList = findViewById(R.id.equiposList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Equipo.injectEquiposFromCloud(queue, items, this);
        equipoAdaptador = new EquipoAdaptador(this, items);
        equiposList.setAdapter(equipoAdaptador);
    }

    public void refreshList() {
        if (equipoAdaptador != null) {
            equipoAdaptador.notifyDataSetChanged();
        }
    }
}