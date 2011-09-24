package hr.brbulic.android_tools;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import hr.brbulic.concurrency.BackgroundWorker;
import hr.brbulic.concurrency.interfaces.IBackgroundDelegate;
import hr.brbulic.factory.ActivityIntentFactory;
import hr.brbulic.factory.intent.IntentSelector;

public class HelloAndroidActivity extends Activity implements
        IBackgroundDelegate, View.OnClickListener {

    private TextView _myTextview;
    private Button _loadButton;
    private static String TAG = "android-tools";

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down then this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
     *                           is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.main);

        _myTextview = (TextView) this.findViewById(R.id.mainTextview);
        _loadButton = (Button) this.findViewById(R.id.LoadOptionsButton);
        _loadButton.setOnClickListener(this);

        BackgroundWorker.getInstance().EnqueueSimple(this, "THIS IS MY STRING");
        BackgroundWorker.getInstance().EnqueueRunnable(new MyHandler());
        Toast.makeText(getApplicationContext(), TAG, 2);
    }

    @Override
    public void onClick(View view) {
        /*
        Intent intent = new Intent(this.getApplicationContext(),OptionsActivity.class);
        startActivity(intent);*/

        Parcelable myObj = new Parcelable() {

            @Override
            public int describeContents() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString("Ovo je neka moja baza");
            }
        };

        try {
            ActivityIntentFactory.LoadActivity(this, IntentSelector.OPTIONS_SCREEN, myObj);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Cannot load activity! Error:" + e.getLocalizedMessage());
        }

    }

    private class MyHandler implements Runnable {

        @Override
        public void run() {
            int count = 5;

            StringBuilder mainString = new StringBuilder();

            while (count < 10) {
                String string = String.format("Ovo je prekršilo sve... %1$d",
                        count);

                mainString.append(string);

                Log.i(TAG, string);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                }

                count++;
            }

            final String resultString = mainString.toString();

            _myTextview.post(new Runnable() {

                @Override
                public void run() {
                    _myTextview.setText(resultString);

                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BackgroundWorker.getInstance().stop();
    }

    @SuppressWarnings("hiding")
    @Override
    public <String> void backgroundRequest(String internalState) {
        int count = 0;

        System.out.println(internalState);

        while (count < 10) {
            System.out.println("Just a message with number " + count);
            count++;
        }
    }

}
