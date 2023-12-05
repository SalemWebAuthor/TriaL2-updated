//package com.example.trial2;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class HomeFragment extends Fragment {
//
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//}
package com.example.trial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the buttons in the fragment's layout
        Button buttonStock = rootView.findViewById(R.id.buttonstock);
        Button buttonReceived = rootView.findViewById(R.id.buttonreceived);
        Button buttonReleased = rootView.findViewById(R.id.buttonrelease);
        Button buttonTotalItems = rootView.findViewById(R.id.buttontotal);

        buttonStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStonksAvailable();
            }
        });

        buttonReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStocksReceived();
            }
        });

        buttonReleased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStocksReleased();
            }
        });

        buttonTotalItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTotalItems();
            }
        });

        return rootView;
    }

    // Open the StocksAvailable activity
    private void openStonksAvailable() {
        Intent intent = new Intent(getActivity(), StocksAvailable.class);
        startActivity(intent);
    }

    // Open the StocksReceived activity
    private void openStocksReceived() {
        Intent intent = new Intent(getActivity(), StocksReceived.class);
        startActivity(intent);
    }

    // Open the StocksReleased activity
    private void openStocksReleased() {
        Intent intent = new Intent(getActivity(), StocksReleased.class);
        startActivity(intent);
    }

    // Open the TotalItems activity
    private void openTotalItems() {
        Intent intent = new Intent(getActivity(), TotalItems.class);
        startActivity(intent);
    }
}