package com.nordman.big.smsparking2;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    static final int PAGE_COUNT = 3;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    SmsManager smsMgr = new SmsManager(this);
    SparseArray<View> views = new SparseArray<>();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        ((RadioButton) findViewById(R.id.radioButton1)).setChecked(true);
                        ((RadioButton) findViewById(R.id.radioButton2)).setChecked(false);
                        ((RadioButton) findViewById(R.id.radioButton3)).setChecked(false);
                        break;
                    case 1:
                        ((RadioButton) findViewById(R.id.radioButton1)).setChecked(false);
                        ((RadioButton) findViewById(R.id.radioButton2)).setChecked(true);
                        ((RadioButton) findViewById(R.id.radioButton3)).setChecked(false);
                        break;
                    case 2:
                        ((RadioButton) findViewById(R.id.radioButton1)).setChecked(false);
                        ((RadioButton) findViewById(R.id.radioButton2)).setChecked(false);
                        ((RadioButton) findViewById(R.id.radioButton3)).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    protected void onResume() {

        /*
        if (smsMgr.parkingActive()){
            Log.d("LOG", "smsMgr.parkingActive...");
            smsMgr.showParkingScreen();
        }
        */

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private View.OnClickListener keyboardListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SmsManager smsMgr = ((MainActivity)getActivity()).smsMgr;

                Log.d("LOG", (String) ((Button) v).getText());
                String keyPressed = (String) ((Button) v).getText();
                if (keyPressed.equals("<-")) {
                    if (smsMgr.regNum.length()>0) {
                        smsMgr.regNum = smsMgr.regNum.substring(0,smsMgr.regNum.length()-1);
                    }
                } else {
                    smsMgr.regNum = smsMgr.regNum + keyPressed;
                }
                smsMgr.saveState();
                ((MainActivity)getActivity()).updateView();
            }
        };

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onResume() {
            Log.d("LOG", "Fragment onResume...");
            ((MainActivity)getActivity()).updateView();
            super.onResume();
        }

        @Override
        public void onDestroyView() {
            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            Log.d("LOG", "View " + position + " destroyed");
            ((MainActivity)getActivity()).views.delete(position);

            super.onDestroyView();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (position){
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_1, container, false);
                    (rootView.findViewById(R.id.button0)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button1)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button2)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button3)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button4)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button5)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button6)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button7)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button8)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.button9)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonA)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonB)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonE)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonK)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonM)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonH)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonO)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonP)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonC)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonT)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonY)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonX)).setOnClickListener(keyboardListener);
                    (rootView.findViewById(R.id.buttonBack)).setOnClickListener(keyboardListener);

                    ((MainActivity)getActivity()).views.append(position,rootView);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_2, container, false);
                    ((MainActivity)getActivity()).views.append(position, rootView);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_3, container, false);
                    ((MainActivity)getActivity()).views.append(position, rootView);
                    break;
            }
            Log.d("LOG", "View " + position + " created");

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }


    private void updateView(){
        smsMgr.restoreState();
        Log.d("LOG", "RegNum = " + smsMgr.regNum);
        for(int i = 0; i < views.size(); i++) {
            int key = views.keyAt(i);
            // get the object by the key.
            View view = views.get(key);
            switch (key){
                case 1:
                    // Рег. номер
                    ((TextView) view.findViewById(R.id.regNumText)).setText(smsMgr.regNum);
                    // Клавиатура
                    switch(smsMgr.regNum.length()) {
                        case 0:
                        case 4:
                        case 5:
                            setLettersEnabled(view,true);
                            setDigitsEnabled(view,false);
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 6:
                        case 7:
                        case 8:
                            setLettersEnabled(view,false);
                            setDigitsEnabled(view,true);
                            break;
                        default:
                            setLettersEnabled(view,false);
                            setDigitsEnabled(view,false);
                            break;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }

        //((TextView) this.findViewById(R.id.regNumText)).setText(smsMgr.regNum);

        /*
        smsMgr.updateSms();
        TextView zoneDesc = (TextView) this.findViewById(R.id.zoneDesc);

        if (smsMgr.currentZone==null) {
            zoneDesc.setText("Паркинг не определен");
            zoneDesc.setTextColor(Color.RED);
        } else {
            zoneDesc.setText(smsMgr.currentZone.getZoneDesc());
            zoneDesc.setTextColor(Color.BLACK);
        }
        // выводим sms на экран
        ((TextView) this.findViewById(R.id.smsText)).setText(smsMgr.sms);
        // выводим часы
        ((TextView) this.findViewById(R.id.hourDesc)).setText(smsMgr.hourDesc());

        // формируем строку sms
        // энаблим/дизаблим кнопку "оплатить"
        if (smsMgr.smsComplete()) this.findViewById(R.id.payButton).setEnabled(true);
        else this.findViewById(R.id.payButton).setEnabled(false);

        switch (smsMgr.appStatus) {
            case SmsManager.STATUS_INITIAL:
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                ((TextView) this.findViewById(R.id.sendMessage)).setText(sendMessage);
                ((TextView) this.findViewById(R.id.sendMessage)).setTextColor(Color.RED);
                break;
            case SmsManager.STATUS_WAITING_OUT:
                Log.d("LOG", "waiting outgoing sms...");
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                ((TextView) this.findViewById(R.id.sendMessage)).setText(getResources().getString(R.string.outgoingSmsWaiting));
                ((TextView) this.findViewById(R.id.sendMessage)).setTextColor(Color.BLACK);
                break;
            case SmsManager.STATUS_WAITING_IN:
                Log.d("LOG", "waiting incoming sms...");
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                ((TextView) this.findViewById(R.id.sendMessage)).setText(getResources().getString(R.string.incomingSmsWaiting));
                ((TextView) this.findViewById(R.id.sendMessage)).setTextColor(Color.BLACK);
                break;
            case SmsManager.STATUS_SMS_SENT:
                Log.d("LOG", "sms was sent...");
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                ((TextView) this.findViewById(R.id.sendMessage)).setText(getResources().getString(R.string.sendSmsWaiting));
                ((TextView) this.findViewById(R.id.sendMessage)).setTextColor(Color.BLACK);
                break;
            case SmsManager.STATUS_SMS_NOT_SENT:
                Log.d("LOG", "sms wasn't sent...");
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                ((TextView) this.findViewById(R.id.sendMessage)).setText(getResources().getString(R.string.sendSmsFailed));
                ((TextView) this.findViewById(R.id.sendMessage)).setTextColor(Color.RED);
                break;
            case SmsManager.STATUS_SMS_NOT_RECEIVED:
                Log.d("LOG", "sms wasn't received...");
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                ((TextView) this.findViewById(R.id.sendMessage)).setText(getResources().getString(R.string.incomingSmsFailed));
                ((TextView) this.findViewById(R.id.sendMessage)).setTextColor(Color.RED);
                break;
        }
        */
    }

    private void setLettersEnabled(View view, boolean flag) {
        (view.findViewById(R.id.buttonA)).setEnabled(flag);
        (view.findViewById(R.id.buttonB)).setEnabled(flag);
        (view.findViewById(R.id.buttonE)).setEnabled(flag);
        (view.findViewById(R.id.buttonK)).setEnabled(flag);
        (view.findViewById(R.id.buttonM)).setEnabled(flag);
        (view.findViewById(R.id.buttonH)).setEnabled(flag);
        (view.findViewById(R.id.buttonO)).setEnabled(flag);
        (view.findViewById(R.id.buttonP)).setEnabled(flag);
        (view.findViewById(R.id.buttonC)).setEnabled(flag);
        (view.findViewById(R.id.buttonT)).setEnabled(flag);
        (view.findViewById(R.id.buttonY)).setEnabled(flag);
        (view.findViewById(R.id.buttonX)).setEnabled(flag);
    }

    private void setDigitsEnabled(View view, boolean flag) {
        (view.findViewById(R.id.button0)).setEnabled(flag);
        (view.findViewById(R.id.button1)).setEnabled(flag);
        (view.findViewById(R.id.button2)).setEnabled(flag);
        (view.findViewById(R.id.button3)).setEnabled(flag);
        (view.findViewById(R.id.button4)).setEnabled(flag);
        (view.findViewById(R.id.button5)).setEnabled(flag);
        (view.findViewById(R.id.button6)).setEnabled(flag);
        (view.findViewById(R.id.button7)).setEnabled(flag);
        (view.findViewById(R.id.button8)).setEnabled(flag);
        (view.findViewById(R.id.button9)).setEnabled(flag);
    }
}
