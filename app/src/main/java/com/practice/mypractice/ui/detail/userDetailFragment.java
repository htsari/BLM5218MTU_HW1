package com.practice.mypractice.ui.detail;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.practice.mypractice.Model.userModel;
import com.practice.mypractice.R;
import com.practice.mypractice.Util.sendUserListener;
import com.practice.mypractice.ui.home.HomeViewModel;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class userDetailFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {

    private EditText name, lastName, password, weight, height;
    private TextView date;
    Spinner gender;
    private Button save;
    private int index = 0;
    userModel user;
    String[] gen = { "Male", "Female"};
    private sendUserListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_user_detail, container, false);
        initView(root);
        Type type = new TypeToken<userModel>() {}.getType();
        assert getArguments() != null;
        user = new Gson().fromJson(getArguments().getString("user"), type);
        index = Integer.parseInt(requireArguments().getString("index"));
        name.setText(user.getName());
        lastName.setText(user.getLastName());
        password.setText(user.getPassword());

        weight.setText(String.valueOf(user.getWeight()));
        height.setText(String.valueOf(user.getHeight()));
        date.setText(user.getDate());


        gender.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,gen);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(aa);

        int spinnerPosition = aa.getPosition(user.getGender());
        gender.setSelection(spinnerPosition);

        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                    date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                final Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), mDateSetListener, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        // This callback will only be called when MyFragment is at least Started.
        final OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };


        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                user.setName(name.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setPassword(password.getText().toString());
                user.setGender(gen[gender.getSelectedItemPosition()]);
                user.setWeight(Integer.parseInt(weight.getText().toString()));
                user.setHeight(Integer.parseInt(height.getText().toString()));
                user.setDate(date.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("user", new Gson().toJson(user));
                bundle.putString("index", String.valueOf(index));
                Navigation.findNavController(view).setGraph(R.navigation.mobile_navigation, bundle);
                requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
            }
        });

        return root;
    }

    private void initView(View itemView) {
        name = itemView.findViewById(R.id.Name);
        lastName = itemView.findViewById(R.id.LastName);
        password = itemView.findViewById(R.id.Password);
        gender = itemView.findViewById(R.id.Gender);
        weight = itemView.findViewById(R.id.Weight);
        height = itemView.findViewById(R.id.Height);
        date = itemView.findViewById(R.id.Date);
        save = itemView.findViewById(R.id.save);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
