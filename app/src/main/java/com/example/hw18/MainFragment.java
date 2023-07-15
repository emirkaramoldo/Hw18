package com.example.hw18;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private TextView tvValue;

    private int value = 0;
    private final Handler handler = new Handler();
    private boolean isMinusButtonPressed = false;
    private boolean isPlusButtonPressed = false;
    private Runnable minusButtonRunnable;
    private Runnable plusButtonRunnable;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tvValue = view.findViewById(R.id.tvValue);
        Button btnMinus = view.findViewById(R.id.btnMinus);
        Button btnPlus = view.findViewById(R.id.btnPlus);

        updateValueText();

        btnMinus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isMinusButtonPressed = true;
                    decreaseValue();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    isMinusButtonPressed = false;
                    stopDecrementing();
                }
                return true;
            }
        });

        btnPlus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isPlusButtonPressed = true;
                    increaseValue();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    isPlusButtonPressed = false;
                    stopIncrementing();
                }
                return true;
            }
        });

        return view;
    }

    private void decreaseValue() {
        minusButtonRunnable = new Runnable() {
            @Override
            public void run() {
                if (isMinusButtonPressed) {
                    value--;
                    updateValueText();
                    handler.postDelayed(this, 100);
                }
            }
        };
        handler.postDelayed(minusButtonRunnable, 400);
    }

    private void stopDecrementing() {
        handler.removeCallbacks(minusButtonRunnable);
    }

    private void increaseValue() {
        plusButtonRunnable = new Runnable() {
            @Override
            public void run() {
                if (isPlusButtonPressed) {
                    value++;
                    updateValueText();
                    handler.postDelayed(this, 100);
                }
            }
        };
        handler.postDelayed(plusButtonRunnable, 400);
    }

    private void stopIncrementing() {
        handler.removeCallbacks(plusButtonRunnable);
    }

    private void updateValueText() {
        tvValue.setText(String.valueOf(value));
    }
}