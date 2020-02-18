package com.example.geradorsenhas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder  _viewHowder = new ViewHolder();
    private boolean     _controllerMinusculoClick = false;
    private boolean     _controllerMaiusculoClick = false;
    private boolean     _controllerNumerosClick = false;
    private boolean     _controllerCaracteresEspeciaisClick = false;
    private String      _password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        } catch (Exception e) {

            Toast.makeText(this, "Erro fatal de Interface : " + e, Toast.LENGTH_LONG).show();

        }

        try{

            this._viewHowder.qtPassword = findViewById(R.id.edit_real);
            this._viewHowder.password = findViewById(R.id.text_password);
            this._viewHowder.chkMinuscula = findViewById(R.id.chk_minusculas);
            this._viewHowder.chkMaiuscula = findViewById(R.id.chk_maiusculas);
            this._viewHowder.chkNumeros = findViewById(R.id.chk_numeros);
            this._viewHowder.chkCaracteresEspeciais = findViewById(R.id.chk_caracteresEspeciais);
            this._viewHowder.generationButton = findViewById(R.id.button_calculate);


            this._viewHowder.chkMinuscula.setOnClickListener(this);
            this._viewHowder.chkMaiuscula.setOnClickListener(this);
            this._viewHowder.chkNumeros.setOnClickListener(this);
            this._viewHowder.chkCaracteresEspeciais.setOnClickListener(this);


            this._viewHowder.generationButton.setOnClickListener(this);
            this._viewHowder.password.setOnClickListener(this);

        } catch (Exception e) {

            Toast.makeText(this, "Erro ao alocar as variaveis corretamente : " + e, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onClick(View view) {
        try{
            //Controle do Chk definindo as variaveis correspondentes para verdadeira ou falsa
            ControllerCheckBox(view);

            if (view.getId() == R.id.button_calculate) {

                String value = this._viewHowder.qtPassword.getText().toString();

                if ("".equals(value)) {

                    Toast.makeText(this, "Informe um Valor de (1 a 25) para gerar a Senha", Toast.LENGTH_LONG).show();

                } else {

                    ControllerPassword(value);
                }

            } else if (view.getId() == R.id.text_password) {

                //Copiando a senha gerada para ser salva em outros lugares
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Senha Copiada", this._viewHowder.password.getText());
                clipboard.setPrimaryClip(clip);


                Toast.makeText(this, "Senha Copiada", Toast.LENGTH_LONG).show();

            }
        } catch (Exception e){

            Toast.makeText(this, "Erro ao gerar a senha : " + e, Toast.LENGTH_LONG).show();

        }
    }

    private void ControllerCheckBox(View view) {
        try {

            if (view.getId() == R.id.chk_minusculas) {

                if (_controllerMinusculoClick == false) {

                    _controllerMinusculoClick = true;

                } else if (_controllerMinusculoClick) {

                    _controllerMinusculoClick = false;

                }

            }

            if (view.getId() == R.id.chk_maiusculas) {

                if (_controllerMaiusculoClick == false) {

                    _controllerMaiusculoClick = true;

                } else if (_controllerMaiusculoClick) {

                    _controllerMaiusculoClick = false;

                }

            }

            if (view.getId() == R.id.chk_numeros) {

                if (_controllerNumerosClick == false) {

                    _controllerNumerosClick = true;

                } else if (_controllerNumerosClick) {

                    _controllerNumerosClick = false;

                }

            }

            if (view.getId() == R.id.chk_caracteresEspeciais) {

                if (_controllerCaracteresEspeciaisClick == false) {

                    _controllerCaracteresEspeciaisClick = true;

                } else if (_controllerCaracteresEspeciaisClick) {

                    _controllerCaracteresEspeciaisClick = false;

                }

            }

        } catch (Exception e) {

            Toast.makeText(this, "Erro ao gerenciar o CheckBox corretamente : " + e, Toast.LENGTH_LONG).show();

        }

    }

    private void ControllerPassword(String value) {

        try {

            _password = "";

            if (Integer.parseInt(value) > 0 && Integer.parseInt(value) <= 25) {

                Character[] minusculos = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                Character[] maiusculos = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                Character[] numeros = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                Character[] caracteresEspeciais = {'@', '!', '#', '%', '&', 'ç', '~'};

                //Efetuando os calculos para gerar a senha de acordo com os itens selecionados

                if (_controllerMinusculoClick) {

                    for (int x = 0; x < Integer.parseInt(value); x++) {
                        int j = (int) (Math.random() * minusculos.length);
                        _password += minusculos[j];
                    }

                    this._viewHowder.password.setText(_password);

                }

                if (_controllerMaiusculoClick) {

                    for (int x = 0; x < Integer.parseInt(value); x++) {
                        int j = (int) (Math.random() * maiusculos.length);
                        _password += maiusculos[j];
                    }

                    this._viewHowder.password.setText(_password);

                }

                if (_controllerNumerosClick) {

                    for (int x = 0; x < Integer.parseInt(value); x++) {
                        int j = (int) (Math.random() * numeros.length);
                        _password += numeros[j];
                    }

                    this._viewHowder.password.setText(_password);

                }

                if (_controllerCaracteresEspeciaisClick) {

                    for (int x = 0; x < Integer.parseInt(value); x++) {
                        int j = (int) (Math.random() * caracteresEspeciais.length);
                        _password += caracteresEspeciais[j];
                    }

                    this._viewHowder.password.setText(_password);

                }

                if (_controllerMinusculoClick && _controllerMaiusculoClick) {

                    char[] stringMax = new char[minusculos.length + maiusculos.length];


                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i] = minusculos[i];
                    }

                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i + minusculos.length] = maiusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);
                }

                if (_controllerMinusculoClick && _controllerNumerosClick) {

                    char[] stringMax = new char[minusculos.length + numeros.length];


                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i] = minusculos[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + minusculos.length] = numeros[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMinusculoClick && _controllerCaracteresEspeciaisClick) {

                    char[] stringMax = new char[minusculos.length + caracteresEspeciais.length];


                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i] = minusculos[i];
                    }

                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i + minusculos.length] = caracteresEspeciais[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMaiusculoClick && _controllerMinusculoClick) {

                    char[] stringMax = new char[maiusculos.length + minusculos.length];


                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i] = maiusculos[i];
                    }

                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i + maiusculos.length] = minusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMaiusculoClick && _controllerNumerosClick) {

                    char[] stringMax = new char[maiusculos.length + numeros.length];


                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i] = maiusculos[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + maiusculos.length] = numeros[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMaiusculoClick && _controllerCaracteresEspeciaisClick) {

                    char[] stringMax = new char[maiusculos.length + caracteresEspeciais.length];


                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i] = maiusculos[i];
                    }

                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i + maiusculos.length] = caracteresEspeciais[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerNumerosClick && _controllerMaiusculoClick) {

                    char[] stringMax = new char[numeros.length + maiusculos.length];


                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i] = numeros[i];
                    }

                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i + numeros.length] = maiusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerNumerosClick && _controllerMinusculoClick) {

                    char[] stringMax = new char[numeros.length + minusculos.length];


                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i] = numeros[i];
                    }

                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i + numeros.length] = minusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerNumerosClick && _controllerCaracteresEspeciaisClick) {

                    char[] stringMax = new char[numeros.length + caracteresEspeciais.length];


                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i] = numeros[i];
                    }

                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i + numeros.length] = caracteresEspeciais[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerCaracteresEspeciaisClick && _controllerMinusculoClick) {

                    char[] stringMax = new char[caracteresEspeciais.length + minusculos.length];


                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i] = caracteresEspeciais[i];
                    }

                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i + caracteresEspeciais.length] = minusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerCaracteresEspeciaisClick && _controllerMaiusculoClick) {

                    char[] stringMax = new char[caracteresEspeciais.length + maiusculos.length];


                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i] = caracteresEspeciais[i];
                    }

                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i + caracteresEspeciais.length] = maiusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerCaracteresEspeciaisClick && _controllerNumerosClick) {


                    char[] stringMax = new char[caracteresEspeciais.length + numeros.length];


                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i] = caracteresEspeciais[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + caracteresEspeciais.length] = numeros[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerCaracteresEspeciaisClick && _controllerMinusculoClick && _controllerMaiusculoClick) {

                    char[] stringMax = new char[caracteresEspeciais.length + minusculos.length + maiusculos.length];


                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i] = caracteresEspeciais[i];
                    }

                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i + caracteresEspeciais.length] = minusculos[i];
                    }

                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i + caracteresEspeciais.length + minusculos.length] = maiusculos[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMaiusculoClick && _controllerNumerosClick && _controllerCaracteresEspeciaisClick) {

                    char[] stringMax = new char[maiusculos.length + numeros.length + caracteresEspeciais.length];


                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i] = maiusculos[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + maiusculos.length] = numeros[i];
                    }

                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i + maiusculos.length + numeros.length] = caracteresEspeciais[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMinusculoClick && _controllerNumerosClick && _controllerCaracteresEspeciaisClick) {

                    char[] stringMax = new char[minusculos.length + numeros.length + caracteresEspeciais.length];


                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i] = minusculos[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + minusculos.length] = numeros[i];
                    }

                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i + minusculos.length + numeros.length] = caracteresEspeciais[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMinusculoClick && _controllerMaiusculoClick && _controllerNumerosClick) {

                    char[] stringMax = new char[minusculos.length + maiusculos.length + numeros.length];


                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i] = minusculos[i];
                    }

                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i + minusculos.length] = maiusculos[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + minusculos.length + maiusculos.length] = numeros[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMinusculoClick && _controllerMaiusculoClick && _controllerNumerosClick && _controllerCaracteresEspeciaisClick) {

                    char[] stringMax = new char[minusculos.length + maiusculos.length + numeros.length + caracteresEspeciais.length];


                    for (int i = 0; i < minusculos.length; i++) {
                        stringMax[i] = minusculos[i];
                    }

                    for (int i = 0; i < maiusculos.length; i++) {
                        stringMax[i + minusculos.length] = maiusculos[i];
                    }

                    for (int i = 0; i < numeros.length; i++) {
                        stringMax[i + minusculos.length + maiusculos.length] = numeros[i];
                    }

                    for (int i = 0; i < caracteresEspeciais.length; i++) {
                        stringMax[i + minusculos.length + maiusculos.length + numeros.length] = caracteresEspeciais[i];
                    }

                    Arrays.toString(stringMax);

                    String _passwordNew = "";

                    for (int x = 0; x < Integer.parseInt(value); x++) {

                        int j = (int) (Math.random() * (stringMax.length));

                        _passwordNew += stringMax[j];
                    }

                    this._viewHowder.password.setText(_passwordNew);

                }

                if (_controllerMinusculoClick == false && _controllerMaiusculoClick == false && _controllerNumerosClick == false && _controllerCaracteresEspeciaisClick == false) {

                    Toast.makeText(this, "Selecione pelo menos uma das opções de definições para a senha", Toast.LENGTH_LONG).show();

                }


            } else {

                Toast.makeText(this, "É permitido apenas valores entre (1 a 25)", Toast.LENGTH_LONG).show();

            }

        } catch (Exception e) {

            Toast.makeText(this, "Erro ao gerar a Senha : " + e, Toast.LENGTH_LONG).show();

        }

    }

    private static class ViewHolder {
        EditText qtPassword;
        TextView password;
        Button generationButton;
        CheckBox chkMinuscula;
        CheckBox chkMaiuscula;
        CheckBox chkNumeros;
        CheckBox chkCaracteresEspeciais;
    }

}