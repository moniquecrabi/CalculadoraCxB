package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EdtEtanol, edtGasolina;
    Button btnCalcular;
    TextView txtCxB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadWidgets();
    }

    private void loadWidgets() {
        this.EdtEtanol = findViewById(R.id.edtEtanol);
        this.edtGasolina = findViewById(R.id.edGasolina);
        this.btnCalcular = findViewById(R.id.btnCalcular);
        this.btnCalcular.setOnClickListener(this);
        this.txtCxB = findViewById(R.id.txtCxB);
    }

    @Override
    public void onClick(View view) {
        float gasolina = Float.parseFloat(this.edtGasolina.getText().toString());
        float etanol = Float.parseFloat(this.EdtEtanol.getText().toString());
        float cxb = this.calculaCxB(gasolina, etanol);
        String classificacao = this.calculaCxB(cxb);
        this.exibeClassificacao(classificacao);
        this.limpaCampos();
    }

    private float calculaCxB(float gasolina, float etanol) {
        return etanol / gasolina;
    }

    private String calculaCxB(float cxb) {
        String classificacao;
        if (cxb < 0) {
            classificacao = "Calculo invalido";
        } else if (cxb > 0 && cxb < 0.7) {
            classificacao = "Etanol está compensado mais";
        } else {
            classificacao = "Gasolina está compensando mais"
        }
        return classificacao;
    }

    private void exibeClassificacao(String classificacao) {
        txtCxB.setText(classificacao);
    }

    private void limpaCampos() {
        this.EdtEtanol.setText("");
        this.edtGasolina.setText("");
        this.EdtEtanol.requestFocus();
    }
}