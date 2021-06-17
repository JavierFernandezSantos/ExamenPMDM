package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LoteriaActivity extends AppCompatActivity {
    LoteriaAdapter adapter;
    LoteriaViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loteria);

        RecyclerView recyclerView = findViewById(R.id.lista_loteria);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LoteriaAdapter(this);
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(LoteriaViewModel.class);


        vm.obtenerDatos().observe(this, new Observer<List<Loteria>>() {
            @Override
            public void onChanged(List<Loteria> pruebas) {
                // Actualizar el UI
                adapter.addData(new ArrayList(pruebas));
            }
        });
    }
}