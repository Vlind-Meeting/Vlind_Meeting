package com.example.vlind_meeting;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class RecordFragment extends Fragment{
    private Button next_button;
    private Button record;
    private Button listen;
    private ViewGroup viewgroup;

    FragmentListener fragmentListener;
    MediaRecorder recorder;
    String fileName;
    MediaPlayer mediaPlayer;
    int position = 0;
    private int n = 0;

    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;

    private boolean isRecording = false;
    private Uri audioUri = null;
    private Boolean isPlaying = false;

    public RecordFragment(){

    }

    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewgroup = (ViewGroup) inflater.inflate(R.layout.fragment_record, container, false);

        next_button = (Button) viewgroup.findViewById(R.id.next_button);
        record = (Button) viewgroup.findViewById(R.id.record);
        listen = (Button) viewgroup.findViewById(R.id.listen);

        File file = new File(Environment.getExternalStorageDirectory(), "recorded.mp4");
        fileName = file.getAbsolutePath();  // 파일 위치 가져옴
        Toast.makeText(getActivity().getApplicationContext(), "파일 위치:"+fileName, Toast.LENGTH_SHORT).show();

        record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(n%2==0){
                    if (recorder == null) {
                        recorder = new MediaRecorder(); // 미디어리코더 객체 생성
                    }
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 오디오 입력 지정(마이크)
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);    // 출력 형식 지정
                    //마이크로 들어오는 음성데이터는 용량이 크기 때문에 압축이 필요
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);   // 인코딩
                    recorder.setOutputFile(fileName);  // 음성 데이터를 저장할 파일 지정
                    try {
                        recorder.prepare();
                        recorder.start();
                        Toast.makeText(getActivity().getApplicationContext(), "녹음시작", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {e.printStackTrace();}
                }
                else{
                    if (recorder != null) {
                        recorder.stop();
                        recorder.release();
                        recorder = null;
                    }
                    Toast.makeText(getActivity().getApplicationContext(), "녹음중지", Toast.LENGTH_SHORT).show();
                }
                n++;
            }
        });

        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(mediaPlayer != null){    // 사용하기 전에
                        mediaPlayer.release();  // 리소스 해제
                        mediaPlayer = null;
                    }
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fileName); // 음악 파일 위치 지정
                    mediaPlayer.prepare();  // 미리 준비
                    mediaPlayer.start();    // 재생
                    Toast.makeText(getActivity().getApplicationContext(), "재생시작", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.setRecord(fileName);
                fragmentListener.nextRecord();
            }
        });

        return viewgroup;
    }


    private boolean checkAudioPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), recordPermission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }

    // 녹음한 파일 저장
    public Uri save(){
        ContentValues values = new ContentValues(10);
        values.put(MediaStore.MediaColumns.TITLE, "Recorded");
        values.put(MediaStore.Audio.Media.ALBUM, "Audio_Album");
        values.put(MediaStore.Audio.Media.ARTIST, "Ton");
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis()/1000);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4"); // 미디어 파일의 포맷
        values.put(MediaStore.Audio.Media.DATA, fileName); // 저장된 녹음 파일

        // ContentValues 객체를 추가할 때, 음성 파일에 대한 내용 제공자 URI 사용
        return getActivity().getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            fragmentListener = (FragmentListener) context; }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (fragmentListener != null) {
            fragmentListener = null; }
    }
}
