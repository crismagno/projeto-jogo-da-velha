package com.example.cristhofer.variasactivities;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.content.Intent;
import android.text.TextUtils;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    public boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }
    public void msg(String msg){
        AlertDialog.Builder cxMsg = new AlertDialog.Builder(MainActivity.this);
        cxMsg.setTitle("AVISO");
        cxMsg.setMessage(msg);
        cxMsg.setNeutralButton("OK", null);
        cxMsg.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("                      DEU VELHA");
        //toolbar.setBackgroundColor(Color.rgb(0, 153, 204));
        setSupportActionBar(toolbar);

        Button btnJogar = (Button) findViewById(R.id.btnJogar);
        btnJogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 Intent it = new Intent(MainActivity.this, Tela2.class);
                    EditText etxt_x = (EditText) findViewById(R.id.etxt_x);
                    EditText etxt_o = (EditText) findViewById(R.id.etxt_o);

                  String nomeX = "";
                  nomeX = etxt_x.getText().toString();
                  String nomeO = "";
                  nomeO = etxt_o.getText().toString();

                  Bundle parans = new Bundle();
                  parans.putString("nomeX", nomeX);
                  parans.putString("nomeO", nomeO);
                  it.putExtras(parans);

                if (isCampoVazio(etxt_x.getText().toString())){
                     Toast.makeText(MainActivity.this," Digite o nome do jogador X!", Toast.LENGTH_SHORT).show();
                        etxt_x.requestFocus();
                    }else if (isCampoVazio(etxt_o.getText().toString())){
                     Toast.makeText(MainActivity.this," Digite o nome do jogador O!", Toast.LENGTH_SHORT).show();
                        etxt_o.requestFocus();
                    } else{
                         if(etxt_x.getText().toString().equals(etxt_o.getText().toString())){
                             Toast.makeText(MainActivity.this, "Erro: nomes iguais! ", Toast.LENGTH_SHORT).show();
                             etxt_o.requestFocus();
                    }else {
                             startActivityForResult(it, 0);
                             finish();
                         }
               }
            }

        });

        Button btnFechar = (Button) findViewById(R.id.btnFechar);
        btnFechar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ConstraintLayout telaInicial = (ConstraintLayout) findViewById(R.id.telaInicial);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sobre) {
            Snackbar.make(telaInicial, "Você está jogando o jogo da velha", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
