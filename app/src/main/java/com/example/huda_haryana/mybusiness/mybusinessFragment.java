package com.example.huda_haryana.mybusiness;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.huda_haryana.R;
import com.example.huda_haryana.ask_details;

import org.w3c.dom.Text;

import java.util.Objects;

public class mybusinessFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.fragment_mybusiness, container, false);

        TextView notes=view.findViewById(R.id.notes);
        TextView details=view.findViewById(R.id.edit_details);
        TextView addlogo=view.findViewById(R.id.add_logo);
        requireActivity().setTitle("My Business");
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotesActivity.class);
                startActivity(intent);
            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditDetails.class);
                startActivity(intent);
            }
        });
        addlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditDetails.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
