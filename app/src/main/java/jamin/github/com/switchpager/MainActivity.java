package jamin.github.com.switchpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import jamin.github.com.switchpager.widget.CurlPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurlPage curlPage = new CurlPage(this);

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.activity_main);
        rootLayout.addView(curlPage);
    }
}
