package com.dictionary.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.dictionary.dictionary.RestApi.BaseUrl;
import com.dictionary.dictionary.RestApi.RestApiClient;
import com.dictionary.dictionary.adapters.adapter;
import com.dictionary.dictionary.listener.listener;
import com.dictionary.dictionary.models.Meaning;
import com.dictionary.dictionary.models.TranslateResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Meaning> list;
    Spinner spinnerDil1;
    Spinner spinnerDil2;
    Button btnDegistir;
    EditText edTxtKelime;
    Button btnAra;
    private ArrayAdapter<String> dataAdapterDil1;
    private ArrayAdapter<String> dataAdapterDil2;
    private String[] secilenDiller={"tur","eng"};
    private String[] cevrilecekDiller={"eng","tur"};
    private String secilenDil;
    private String cevrilecekDil;
    String kelime;
    RecyclerView recyclerView;
    Meaning meaning;
    adapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();

        dataAdapterDil1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, secilenDiller);
        dataAdapterDil2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cevrilecekDiller);
        dataAdapterDil1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterDil2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDil1.setAdapter(dataAdapterDil1);
        spinnerDil2.setAdapter(dataAdapterDil2);
        secilenDil= "eng";
        cevrilecekDil= "tur";

        spinnerDil1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secilenDil= spinnerDil1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                secilenDil= spinnerDil1.getItemAtPosition(0).toString();
            }
        });

        spinnerDil2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cevrilecekDil= spinnerDil2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cevrilecekDil= spinnerDil2.getItemAtPosition(0).toString();
            }
        });

        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTxtKelime=(EditText) findViewById(R.id.txtKelime);
                kelime=edTxtKelime.getText().toString();
                istek(secilenDil, cevrilecekDil, kelime);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(a);
            }
        });

        btnDegistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerDil1.getSelectedItemPosition()==1){
                    spinnerDil1.setSelection(0);
                }else{
                    spinnerDil1.setSelection(1);
                }
                if (spinnerDil2.getSelectedItemPosition()==1){
                    spinnerDil2.setSelection(0);
                }else{
                    spinnerDil2.setSelection(1);
                }
            }
        });


    }
    public void tanimla(){
        spinnerDil1= (Spinner) findViewById(R.id.spinnerDil1);
        spinnerDil2= (Spinner) findViewById(R.id.spinnerDil2);
        btnDegistir= (Button) findViewById(R.id.btnDegistir);
        btnAra=(Button) findViewById(R.id.btnAra);
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);

    }

    public void istek(String secilenDil, String cevrilecekDil, String kelime){
        list= new ArrayList<>();

        RestApiClient restApiClient = new RestApiClient(BaseUrl.bilgi_URL);

       // Call<TranslateResponse> call= restApiClient.getRestApi().bilgiGetir("eng","tur","json","go");


        Call<TranslateResponse> call= restApiClient.getRestApi().bilgiGetir(secilenDil,cevrilecekDil,"json",kelime);

                    Log.d("myTag", secilenDil);
                    Log.d("myTagg", cevrilecekDil);
                    Log.d("myTaggg", kelime);

        call.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                if(response.isSuccessful()){
                    list = response.body().getTuc();

                     a= new adapter(list, getApplicationContext(), new listener() {
                        @Override
                        public void onDetail(View view, int position) {
                            meaning = list.get(position);
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<TranslateResponse> call, Throwable t) {
                Log.i("xxxx", t.toString());
            }
        });
    }
}
