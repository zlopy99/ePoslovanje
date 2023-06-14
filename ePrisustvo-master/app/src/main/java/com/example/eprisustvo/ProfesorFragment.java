package com.example.eprisustvo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

public class ProfesorFragment extends Fragment {

    private CodeScanner mCodeScanner;

    private TextView codeData;

    List<String> rezerva = new ArrayList<>();

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_profesor, container, false);

        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        codeData = view.findViewById(R.id.list);

        CodeScannerView scannerView =view.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity,scannerView);



        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        String data = result.getText();

                        rezerva.add(data);

                        sortiraj();

                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                        mCodeScanner.startPreview();


                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        return view;
    }
    List<String> ispis = new ArrayList<>();
    Integer k = 0;
    private void sortiraj() {
        for(String j : rezerva){
            if(!ispis.contains(j)){
                ispis.add(j); //Dodaje u listu ispis sortirane elemente tj. bez duplikata
                k++;
                codeData.append("\n");
                codeData.append(k.toString()+'.');
                codeData.append(j);

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }


}
