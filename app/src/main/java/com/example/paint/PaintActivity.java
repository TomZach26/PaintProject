package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.graphics.Color;
import top.defaults.colorpicker.ColorPickerPopup;


public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;

    private Button btnfilled;
    private Button btnUnfilled;
    private boolean FilledOrNot;
    private Button btnWidth;
    private boolean thickorthin = false;
    private Button btnAreaRemove;




    // text view variable to set the color for GFG text
    private TextView gfgTextView;

    // two buttons to open color picker dialog and one to
    // set the color for GFG text
    private Button mSetColorButton, mPickColorButton;

    // view box to preview the selected color
    private View mColorPreview;

    // this is the default color of the preview box
    private int mDefaultColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        frame.addView(paintView);
        btnfilled = findViewById(R.id.btnFill);
        btnUnfilled = findViewById(R.id.btnNotFilled);
        btnWidth = findViewById(R.id.thickorthin);
        btnAreaRemove = findViewById(R.id.AreaRemove);

        btnfilled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilledOrNot = false;
                paintView.setstyle(FilledOrNot);

            }
        });

        btnUnfilled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilledOrNot = true;
                paintView.setstyle(FilledOrNot);
            }
        });

        btnWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.toggleThickness();
            }
        });


        btnAreaRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.RemoveAllButTheBiggest();
            }
        });


        // register two of the buttons with their
        // appropriate IDs
        mPickColorButton = findViewById(R.id.pick_color_button);


        // set the default color to 0 as it is black
        mDefaultColor = 0;

        // handling the Pick Color Button to open color
        // picker dialog
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new ColorPickerPopup.Builder(PaintActivity.this).initialColor(
                                        Color.RED) // set initial color
                                // of the color
                                // picker dialog
                                .enableBrightness(
                                        true) // enable color brightness
                                // slider or not
                                .enableAlpha(
                                        true) // enable color alpha
                                // changer on slider or
                                // not
                                .okTitle(
                                        "Choose") // this is top right
                                // Choose button
                                .cancelTitle(
                                        "Cancel") // this is top left
                                // Cancel button which
                                // closes the
                                .showIndicator(
                                        true) // this is the small box
                                // which shows the chosen
                                // color by user at the
                                // bottom of the cancel
                                // button
                                .showValue(
                                        true) // this is the value which
                                // shows the selected
                                // color hex code
                                // the above all values can be made
                                // false to disable them on the
                                // color picker dialog.
                                .build()
                                .show(
                                        v,
                                        new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void
                                            onColorPicked(int color) {
                                                paintView.setColor("#" + Integer.toHexString(color));
                                            }
                                        });
                    }
                });

    }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }

    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    
    public void clear(View view) {
        paintView.undo();
    }
}
