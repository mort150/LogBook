package com.example.logbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.logbook.database.InitDatabase;
import com.example.logbook.database.PictureEntity;
import com.example.logbook.databinding.FragmentAddPictureBinding;
import com.example.logbook.Helper.InputTool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPictureFragment extends Fragment {
    private FragmentAddPictureBinding binding;
    private InitDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(requireContext(), InitDatabase.class, "logbook.db").allowMainThreadQueries().build();
        requireActivity().setTitle("Add Picture");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPictureBinding.inflate(inflater, container, false);

        binding.saveBtn.setOnClickListener(view -> {
            String url = binding.urlInput.getText().toString();
            String name = binding.nameInput.getText().toString();
            String description = binding.descriptionInput.getText().toString().isEmpty()
                    ? "There hasn't description for this picture yet!!!" : binding.descriptionInput.getText().toString();
            String date = new SimpleDateFormat("dd-MM-yy hh:mm:ss").format(new Date());

            if (InputTool.validate(binding.nameInput, binding.urlInput)) {
                PictureEntity picture = new PictureEntity();
                picture.name = name;
                picture.url = url;
                picture.description = description;
                picture.create_at = date;
                db.pictureDao().addPicture(picture);

                Toast.makeText(requireContext(), "Add picture successfully!", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }
}