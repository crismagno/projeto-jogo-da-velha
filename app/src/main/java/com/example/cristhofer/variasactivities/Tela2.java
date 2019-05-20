package com.example.cristhofer.variasactivities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.graphics.Color;
import android.app.AlertDialog;

public class Tela2 extends AppCompatActivity {

    //variaveis da tela 2(jogo)
    TextView txt_aparece_x, txt_aparece_o, txt;
    Button b[] = new Button[9],btn_iniciar, btn_continuar;
    byte rodada = 0, venceu_X = 0, venceu_O = 0, guarda_rodada;
    ConstraintLayout telaJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("                 JOGO DA VELHA");
        setSupportActionBar(toolbar);
        telaJogo = (ConstraintLayout) findViewById(R.id.telaJogo);


        txt_aparece_x  = (TextView)findViewById(R.id.txt_aparece_x);
        txt_aparece_o  = (TextView)findViewById(R.id.txt_aparece_o);
        txt_aparece_x.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(Tela2.this, txt_aparece_x.getText().toString() + " marca = X", Toast.LENGTH_SHORT).show();
            }
        });
        txt_aparece_o.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(Tela2.this, txt_aparece_o.getText().toString() + " marca = O", Toast.LENGTH_SHORT).show();
            }
        });

        settings_initial();

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Tela2.this, MainActivity.class));
                finish();
            }
        });

        Button btnSair = (Button) findViewById(R.id.btn_sair);
        btnSair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    //métodos do jogo da tela 2
    public void settings_initial(){
        Intent it = getIntent();
        Bundle args = it.getExtras();
        String etxt_x = args.getString("nomeX");
        String etxt_o = args.getString("nomeO");

        //atribuindo id aos botoes
        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_continuar = (Button) findViewById(R.id.btn_continuar);

        //botoes do jogo
        b[0] = (Button) findViewById(R.id.b0);
        b[1] = (Button) findViewById(R.id.b1);
        b[2] = (Button) findViewById(R.id.b2);
        b[3] = (Button) findViewById(R.id.b3);
        b[4] = (Button) findViewById(R.id.b4);
        b[5] = (Button) findViewById(R.id.b5);
        b[6] = (Button) findViewById(R.id.b6);
        b[7] = (Button) findViewById(R.id.b7);
        b[8] = (Button) findViewById(R.id.b8);

        // atribuindo id as parte de texto
        txt  = (TextView)findViewById(R.id.txt);
        txt_aparece_x  = (TextView)findViewById(R.id.txt_aparece_x);
        txt_aparece_o  = (TextView)findViewById(R.id.txt_aparece_o);

        for (byte i = 0; i < 9; i++){
            b[i].setEnabled(false);
            b[i].setBackgroundColor(Color.DKGRAY);

        }

        //configurando o bottão continuar e iniciar
        btn_continuar.setEnabled(false);
        btn_continuar.setBackgroundColor(Color.GRAY);
        btn_continuar.setText("desabilitado");
        btn_iniciar.setText("iniciar");

        //inserindo aos componentes textview uma inicialiazção

        txt_aparece_x.setText(etxt_x);
        txt_aparece_o.setText(etxt_o);

        //esse método é onde o jogo começa
        iniciar();
    }

    public void iniciar(){
        Intent it = getIntent();
        Bundle args = it.getExtras();
        final String etxt_x = args.getString("nomeX");
        final String etxt_o = args.getString("nomeO");

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //um aleatório pra ver quem começa
                int ale = (int) (Math.round(Math.random() * 1));
                if (ale == 0){
                    rodada = 0;

                    Toast.makeText(Tela2.this, etxt_x+ " começa e marca com 'X'", Toast.LENGTH_SHORT).show();

                    txt.setText( etxt_x + " começa!");
                }else{
                    rodada = 1;
                    Toast.makeText(Tela2.this, etxt_o+ " começa e marca com 'O'", Toast.LENGTH_SHORT).show();

                    txt.setText(etxt_o + " começa!");
                }

                //usando a variavel guarda_rodada
                if (rodada == 0){
                    guarda_rodada = 0;
                }else{
                    guarda_rodada = 1;
                }

                //trocando o texto ddo botão iniciar
                if (btn_iniciar.getText() == "iniciar"){
                    btn_iniciar.setText("reiniciar");
                }
                btn_iniciar.setBackgroundColor(Color.RED);

                //habilitando os botões do jogo
                for (byte i = 0; i < 9; i++){
                    b[i].setText("");
                    b[i].setEnabled(true);
                    b[i].setBackgroundColor(Color.rgb(0,180,225));
                }

                //partes do botao continuar (desabilitando, mudando o texo e a cor)
                btn_continuar.setEnabled(false);
                btn_continuar.setText("desabilitado");
                btn_continuar.setBackgroundColor(Color.DKGRAY);

                //zerando as variaveis que no caso é a pontuação dos jogadores
                venceu_X = 0;
                venceu_O = 0;

                txt_aparece_x.setText(etxt_x);
                txt_aparece_o.setText(etxt_o);

                if(venceu_X == 0) {
                    txt_aparece_x.setTextColor(Color.GRAY);
                }
                if(venceu_O == 0) {
                    txt_aparece_o.setTextColor(Color.GRAY);
                }
                //métodos dos botoes e o q chama o botão continuar
                botoes();
                continuar();
            }
        });
    }

    public void continuar(){
        Intent it = getIntent();
        Bundle args = it.getExtras();
        final String etxt_x = args.getString("nomeX");
        final String etxt_o = args.getString("nomeO");
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rodada%2 == 1){
                    rodada = 1;
                    txt.setText(etxt_o + " continua!");
                }else{
                    rodada = 0;
                    txt.setText(etxt_x + " continua!");
                }

                //usando a variavel guarda_rodada
                if (rodada == 0){
                    guarda_rodada = 0;
                }else{
                    guarda_rodada = 1;
                }

                //habilitando os botoes do jogo
                for (byte i = 0; i < 9; i++){
                    b[i].setText("");
                    b[i].setEnabled(true);
                }

                //partes do botao continuar (desabilitando, mudando o texo e a cor)
                btn_continuar.setEnabled(false);
                btn_continuar.setText("desabilitado");
                btn_continuar.setBackgroundColor(Color.DKGRAY);
            }
        });
    }

    public void botoes(){

        //evento de click dos botões
        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(0);
                b[0].setEnabled(false);
            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(1);
                b[1].setEnabled(false);
            }
        });

        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(2);
                b[2].setEnabled(false);
            }
        });

        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(3);
                b[3].setEnabled(false);
            }
        });

        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(4);
                b[4].setEnabled(false);
            }
        });

        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(5);
                b[5].setEnabled(false);
            }
        });

        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(6);
                b[6].setEnabled(false);
            }
        });

        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(7);
                b[7].setEnabled(false);
            }
        });

        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("");
                jogada(8);
                b[8].setEnabled(false);
            }
        });

    }

    public void jogada(int n){
        String icon;
        if (rodada%2 == 1){
            icon = "O";
        }else{
            icon = "X";
        }

        b[n].setText(icon);
        verifica_jogo();
        rodada++;
    }

    public void verifica_jogo(){
        Intent it = getIntent();
        Bundle args = it.getExtras();
         String etxt_x = args.getString("nomeX");
         String etxt_o = args.getString("nomeO");
        //jogador 1 = X
        if (//horizontal
                b[0].getText() == "X" && b[1].getText() == "X" && b[2].getText() == "X"
                        || b[3].getText() == "X" && b[4].getText() == "X" && b[5].getText() == "X"
                        || b[6].getText() == "X" && b[7].getText() == "X" && b[8].getText() == "X"
                        //vertical
                        || b[0].getText() == "X" && b[3].getText() == "X" && b[6].getText() == "X"
                        || b[1].getText() == "X" && b[4].getText() == "X" && b[7].getText() == "X"
                        || b[2].getText() == "X" && b[5].getText() == "X" && b[8].getText() == "X"
                        //diagonal
                        || b[0].getText() == "X" && b[4].getText() == "X" && b[8].getText() == "X"
                        || b[2].getText() == "X" && b[4].getText() == "X" && b[6].getText() == "X")
        {
            btn_continuar.setEnabled(true);
            btn_continuar.setText("continuar");
            btn_continuar.setBackgroundColor(Color.rgb(153, 204, 0));

            rodada = 1;
            venceu_X++;

            jogar_novamente(etxt_x, "X");

            for(byte i = 0; i < 9; i++){
                b[i].setEnabled(false);
            }

            if(venceu_X > 0) {
                txt_aparece_x.setTextColor(Color.rgb(0, 180, 255));
            }

            if (venceu_X%5 == 0 && venceu_X > venceu_O){
                Snackbar.make(telaJogo,etxt_x + " está ganhando!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }

        }

        //jogador 2 = O
        if (//horizontal
                b[0].getText() == "O" && b[1].getText() == "O" && b[2].getText() == "O"
                        || b[3].getText() == "O" && b[4].getText() == "O" && b[5].getText() == "O"
                        || b[6].getText() == "O" && b[7].getText() == "O" && b[8].getText() == "O"
                        //vertical
                        || b[0].getText() == "O" && b[3].getText() == "O" && b[6].getText() == "O"
                        || b[1].getText() == "O" && b[4].getText() == "O" && b[7].getText() == "O"
                        || b[2].getText() == "O" && b[5].getText() == "O" && b[8].getText() == "O"
                        //diagonal
                        || b[0].getText() == "O" && b[4].getText() == "O" && b[8].getText() == "O"
                        || b[2].getText() == "O" && b[4].getText() == "O" && b[6].getText() == "O")
        {
            btn_continuar.setEnabled(true);
            btn_continuar.setText("continuar");
            btn_continuar.setBackgroundColor(Color.rgb(153, 204, 0));
            rodada = 0;
            venceu_O++;

            jogar_novamente(etxt_o, "O");

            for(byte i = 0; i < 9; i++){
                b[i].setEnabled(false);
            }

            if(venceu_O > 0) {
                txt_aparece_o.setTextColor(Color.rgb(153, 204, 0));
            }

            if (venceu_O%5 == 0 && venceu_O > venceu_X){
                Snackbar.make(telaJogo,etxt_o + " está ganhando!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();            }

        }


        //verifica se todos os botoes foram usados
        if (b[0].getText().toString() != "" && b[1].getText().toString() != "" && b[2].getText().toString() != "" &&
                b[3].getText().toString() != "" && b[4].getText().toString() != "" && b[5].getText().toString() != "" &&
                b[6].getText().toString() != "" && b[7].getText().toString() != "" && b[8].getText().toString() != "" ){

            btn_continuar.setEnabled(true);
            btn_continuar.setText("continuar");
            btn_continuar.setBackgroundColor(Color.rgb(153, 204, 0));
        }

        if (guarda_rodada == 0 && rodada == 8 || guarda_rodada == 1 && rodada == 9){
            txt.setText("DEU VELHA!!!");
            Toast.makeText(Tela2.this, "DEU VELHA!\nCLICK EM CONTINUAR!", Toast.LENGTH_SHORT).show();

        }


    }

    public void jogar_novamente(String jogador, String valor){
        Intent it = getIntent();
        Bundle args = it.getExtras();
        final String etxt_x = args.getString("nomeX");
        final String etxt_o = args.getString("nomeO");

        if (venceu_X == 0 ){

        }else {
            txt_aparece_x.setText(etxt_x+": "+venceu_X);
        }

        if (venceu_O == 0 ){

        }else {
            txt_aparece_o.setText(etxt_o+": "+venceu_O);
        }

        txt.setText('"'+jogador+'"' + " VENCEU!!! \n Click em continuar.");

    }

}
