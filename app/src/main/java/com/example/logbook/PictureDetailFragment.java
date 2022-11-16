package com.example.logbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logbook.database.InitDatabase;
import com.example.logbook.database.PictureEntity;
import com.example.logbook.databinding.FragmentPictureDetailBinding;

import java.util.List;

public class PictureDetailFragment extends Fragment {
    private FragmentPictureDetailBinding binding;
    private int pictureId;
    private InitDatabase db;
    private PictureEntity pictureEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(requireContext(), InitDatabase.class, "logbook.db").allowMainThreadQueries().build();
        pictureId = requireArguments().getInt("pictureId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPictureDetailBinding.inflate(inflater, container, false);
        db.pictureDao().getAllData().observe(getViewLifecycleOwner(), pictureEntities -> {

            for (PictureEntity picture : pictureEntities
            ) {
                if (picture.pictureId == pictureId) {
                    loadImage(picture);
                    break;
                }
            }
            binding.previousBtn.setOnClickListener(view -> previousPic(pictureEntities, pictureId));

            binding.nextBtn.setOnClickListener(view -> nextPic(pictureEntities, pictureId));
        });

        return binding.getRoot();
    }

    public void previousPic(List<PictureEntity> pictures, int pictureId) {
        int position = 0;
        for (int i = 0; i < pictures.size(); i++) {
            if (pictures.get(i).pictureId == pictureId) {
                position = i;
                break;
            }
        }
        position--;
        if (position < 0) {
            position = pictures.size() - 1;
        }
        loadImage(pictures.get(position));
    }

    public void nextPic(List<PictureEntity> pictures, int pictureId) {
        int position = 0;
        for (int i = 0; i < pictures.size(); i++) {
            if (pictures.get(i).pictureId == pictureId) {
                position = i;
                break;
            }
        }
        position++;
        if (position > pictures.size() - 1) {
            position = 0;
        }
        loadImage(pictures.get(position));
    }

    public void loadImage(PictureEntity pictureEntity) {
        this.pictureId = pictureEntity.pictureId;
        ImageDownloaderTask imageDownloaderTask = new ImageDownloaderTask();
        imageDownloaderTask.setOnPostExecute(bitmap -> {
            binding.iamgeDetail.setImageBitmap(bitmap);
        });
        imageDownloaderTask.execute(pictureEntity.url);
    }
}