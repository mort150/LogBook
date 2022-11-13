package com.example.logbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.logbook.database.InitDatabase;
import com.example.logbook.databinding.FragmentMainBinding;
import com.example.logbook.tools.AlertDialogTool;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private InitDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(requireContext(), InitDatabase.class, "logbook.db").allowMainThreadQueries().build();
        requireActivity().setTitle("Pictures list");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        db.pictureDao().getAllData().observe(getViewLifecycleOwner(),pictures -> {
            PictureAdapter adapter = new PictureAdapter(pictures);
            adapter.setOnClickListener(((view, position) -> {
                Bundle bundle = new Bundle();
                bundle.putInt("pictureId",pictures.get(position).pictureId);
                NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_pictureDetailFragment,bundle);
            }));
            binding.recyclerView.setAdapter(adapter);
            binding.recyclerView.setLayoutManager((new LinearLayoutManager(requireContext())));
            binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                    RecyclerView.VERTICAL));
        });
        binding.delFab.setOnClickListener(view -> {
            AlertDialogTool.showConfirmDialog(requireContext(),"Do you want to delete all pictures here?","Confirm",
                    (dialog, which) -> {
                        db.pictureDao().deleteAllPicture();
                        Toast.makeText(requireContext(),"Delete all pictures successfully!",Toast.LENGTH_SHORT).show();
                    },
                    (dialog, which) -> {
                        dialog.dismiss();
                    });
        });

        binding.addFab.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_addPictureFragment);
        });

        return binding.getRoot();
    }
}