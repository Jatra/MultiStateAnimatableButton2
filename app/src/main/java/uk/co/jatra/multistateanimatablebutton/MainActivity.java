package uk.co.jatra.multistateanimatablebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private ReadyableImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ReadyableImageButton)findViewById(R.id.button);
    }

    public void disable(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        button.setEnabled(!checked);
    }

    public void readying(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        setButtonState(checked, false, false, false);
    }

    public void operating(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        setButtonState(false, checked, false, false);
    }

    public void completing(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        setButtonState(false, false, checked, false);
    }

    public void ready(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        setButtonState(false, false, false, checked);
    }

    public void enabled(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        button.setEnabled(checked);
    }

    public void setButtonState(boolean readying, boolean operating, boolean completing, boolean ready){
        button.setReadying(readying);
        button.setOperating(operating);
        button.setCompleting(completing);
        button.setReady(ready);
    }
}
