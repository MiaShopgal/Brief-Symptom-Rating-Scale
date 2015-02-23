package tw.org.tsos.bsrs.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

import tw.org.tsos.bsrs.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();
    private SharedPreferences sharedPref;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final View welcomeView = view.findViewById(R.id.profile_next);
        welcomeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.welcome_view).setVisibility(View.GONE);
            }
        });
        final RadioGroup genderRadio = (RadioGroup) view.findViewById(R.id.profile_gender);
        final Spinner yearSpinner = (Spinner) view.findViewById(R.id.profile_birthday_year);
        final Spinner monthSpinner = (Spinner) view.findViewById(R.id.profile_birthday_month);
        List<String> yearList = new ArrayList<>();
        for (int i = 1911; i < Calendar.getInstance().get(Calendar.YEAR) + 1; i++) {
            yearList.add(String.valueOf(i));
        }
        ArrayList<String> yearArrayList = new ArrayList<>(new LinkedHashSet<>(yearList));

        //noinspection unchecked
        final SpinnerAdapter yearAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, yearArrayList);
        yearSpinner.setAdapter(yearAdapter);

        List<String> monthList = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            monthList.add(String.valueOf(i));

        }
        ArrayList<String> monthArrayList = new ArrayList<>(new LinkedHashSet<>(monthList));

        //noinspection unchecked
        final SpinnerAdapter monthAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, monthArrayList);
        monthSpinner.setAdapter(monthAdapter);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        int savedGender = sharedPref.getInt(getString(R.string.gender), 0);
        Log.d(TAG, "savedGender=" + savedGender);
        if (savedGender > 0) {
            switch (savedGender) {
                case 1:
                    genderRadio.check(R.id.gender_female);
                    break;
                case 2:
                    genderRadio.check(R.id.gender_male);
                    break;
            }
        }

        int savedYear = sharedPref.getInt(getString(R.string.year), 0);
        if (savedYear > 0) {
            Log.d(TAG, "savedYear=" + savedYear);
            yearSpinner.setSelection(savedYear - 1911);
        }
        int savedMonth = sharedPref.getInt(getString(R.string.month), 0);
        if (savedMonth > 0) {
            Log.d(TAG, "savedMonth=" + savedMonth);
            monthSpinner.setSelection(savedMonth - 1);
        }

        genderRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor editor = sharedPref.edit();
                int gender = 0;
                switch (checkedId) {
                    case R.id.gender_male:
                        Log.d(TAG, "checking male");
                        gender = 2;
                        break;
                    case R.id.gender_female:
                        Log.d(TAG, "checking female");
                        gender = 1;
                        break;
                }
                editor.putInt(getString(R.string.gender), gender);
                editor.apply();
            }
        });
        /*view.findViewById(R.id.profile_birthday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBirthdayPicker().show();
            }
        });*/
        view.findViewById(R.id.profile_begin_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonId = genderRadio.getCheckedRadioButtonId();
                int yearSelection = Integer.valueOf((String) yearSpinner.getSelectedItem());
                int monthSelection = monthSpinner.getSelectedItemPosition();
                Log.d(TAG, "checkedRadioButtonId=" + checkedRadioButtonId + ", year=" + yearSelection + ", month=" + monthSelection + 1);
                if (checkedRadioButtonId > -1 &&
                        monthSelection > -1 &&
                        yearSelection > 0) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(getString(R.string.year), yearSelection);
                    editor.putInt(getString(R.string.month), monthSelection + 1);
                    editor.apply();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_blank, new QuizFragment());
                    fragmentTransaction.commitAllowingStateLoss();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.profile_filling_tips), Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    /*@SuppressWarnings("UnusedDeclaration")
    private DatePickerDialog getBirthdayPicker() {
        Calendar now = Calendar.getInstance();
        return new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "onDateSet, " + year + ", " + monthOfYear);
                ((EditText) view.findViewById(R.id.profile_birthday_year)).setText(String.valueOf(year));
                ((EditText) view.findViewById(R.id.profile_birthday_month)).setText(String.valueOf(monthOfYear));
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.year), year);
                editor.putInt(getString(R.string.month), monthOfYear);
                editor.apply();
            }
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                int year = getContext().getResources().getIdentifier("android:id/year", null, null);
                if (year != 0) {
                    View yearPicker = findViewById(year);
                    if (yearPicker != null) {
                        yearPicker.setVisibility(View.GONE);
                    }
                }
            }
        };
    }*/
}
