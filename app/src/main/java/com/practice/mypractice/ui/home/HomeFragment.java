package com.practice.mypractice.ui.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.practice.mypractice.Adapter.userListAdapter;
import com.practice.mypractice.Model.userModel;
import com.practice.mypractice.R;
import com.practice.mypractice.Util.RecyclerTouchListener;
import com.practice.mypractice.Util.sendUserListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView rec;
    public static final String KEY_CONNECTIONS = "userList";
    private List < userModel > userlList = new ArrayList<>();
    private userListAdapter userAdapter;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
       // if (root == null) {
            root = inflater.inflate(R.layout.fragment_home, container, false);
        //}

        init(root);
        OnClick();
        if (fetchData() == null || fetchData().size() == 0) {
            initData();
        }
        userlList = fetchData();
        if (getArguments() != null) {
            Type type = new TypeToken<userModel>() {}.getType();
            userlList.set(Integer.parseInt(requireArguments().getString("index")), (userModel) new Gson().fromJson(getArguments().getString("user"), type));
            storeData(userlList);
            userlList = fetchData();
        }
        userAdapter = new userListAdapter(userlList, getActivity());
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();

        return root;
    }

    private void init(View itemView){
        rec = itemView.findViewById(R.id.rec);
    }

    private List<userModel> fetchData() {
        String connectionsJSONString = requireActivity().getPreferences(MODE_PRIVATE).getString(KEY_CONNECTIONS, null);
        Type type = new TypeToken< List < userModel >>() {}.getType();
        return new Gson().fromJson(connectionsJSONString, type);
    }

    private void storeData(List<userModel> userModelList) {
        SharedPreferences.Editor editor = requireActivity().getPreferences(MODE_PRIVATE).edit();
        String connectionsJSONString = new Gson().toJson(userModelList);
        editor.putString(KEY_CONNECTIONS, connectionsJSONString);
        editor.apply();
    }

    private void OnClick(){
        rec.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rec, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("user", new Gson().toJson(userlList.get(position)));
                bundle.putString("index", String.valueOf(position));
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_userDetailFragment, bundle);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    private void initData() {
        List < userModel > userModelList = new ArrayList<>();
        userModelList.add(new userModel(1, "Tamer", "sarı1", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(2, "Tamer", "sarı2", "1234", "Female", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(3, "Tamer", "sarı3", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(4, "Tamer", "sarı4", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(5, "Tamer", "sarı5", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(6, "Tamer", "sarı6", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(7, "Tamer", "sarı7", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(8, "Tamer", "sarı8", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(9, "Tamer", "sarı9", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(10, "Tamer", "sarı10", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(11, "Tamer", "sarı11", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(12, "Tamer", "sarı12", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(13, "Tamer", "sarı13", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(14, "Tamer", "sarı14", "1234", "Male", 80, 170, "01/01/2020"));
        userModelList.add(new userModel(15, "Tamer", "sarı15", "1234", "Male", 80, 160, "01/01/2020"));
        userModelList.add(new userModel(16, "Tamer", "sarı16", "1234", "Male", 80, 160, "01/01/2020"));
        userModelList.add(new userModel(17, "Tamer", "sarı17", "1234", "Male", 80, 160, "01/01/2020"));
        userModelList.add(new userModel(18, "Tamer", "sarı18", "1234", "Male", 80, 160, "01/01/2020"));
        userModelList.add(new userModel(19, "Tamer", "sarı19", "1234", "Male", 80, 160, "01/01/2020"));
        userModelList.add(new userModel(20, "Tamer", "sarı20", "1234", "Male", 80, 160, "01/01/2020"));
        storeData(userModelList);
    }
}
