package com.example.projet_3_oc_maru.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.R;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Objects;

public class DetailMeetingActivity extends AppCompatActivity {
    ImageView imageViewDetailMeet;
    TextView textViewDetailHourMeetBegin,textViewDetailHourMeetEnd,textViewDetailIdMeet,textViewDetailDateMeet,textViewDetailSubjectMeet,textViewDetailParticipantsMeet,textViewDetailRoomMeet;
    Meeting meeting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getMeetingParcelable();
        meetingNotNull();
    }

    public void getMeetingParcelable(){
        meeting = getIntent().getParcelableExtra("detailMeeting");
    }


    public void meetingNotNull(){
        if (meeting != null) {
            setUpViews();
            setTextAndImage();
        }
    }

    public void setUpViews(){
        imageViewDetailMeet = findViewById(R.id.imageViewDetailMeet);
        textViewDetailIdMeet =findViewById(R.id.textViewDetailIdMeet);
        textViewDetailSubjectMeet = findViewById(R.id.textViewDetailSubjectMeet);
        textViewDetailHourMeetBegin = findViewById(R.id.textViewDetailHourMeetBegin);
        textViewDetailHourMeetEnd = findViewById(R.id.textViewDetailHourMeetEnd);
        textViewDetailParticipantsMeet = findViewById(R.id.textViewDetailParticipantsMeet);
        textViewDetailDateMeet = findViewById(R.id.textViewDetailDateMeet);
        textViewDetailRoomMeet = findViewById(R.id.textViewDetailRoomMeet);
    }


    public void setTextAndImage(){

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");

        imageViewDetailMeet.setColorFilter(meeting.getMeetingRoom().getmRoomMeetingColor());

        textViewDetailIdMeet.setText("Numéro : "+meeting.getId());

        textViewDetailSubjectMeet.setText("A propos de : "+meeting.getSubject());

        textViewDetailDateMeet.setText("Date : "+meeting.getDateTimeBegin().toLocalDate().toString());

        textViewDetailRoomMeet.setText("Salle : "+ meeting.getMeetingRoom().getmNameRoomMeeting());

        textViewDetailHourMeetBegin.setText("Début :"+ meeting.getDateTimeBegin().toLocalTime().toString(fmt));

        textViewDetailHourMeetEnd.setText("Fin :"+ meeting.getDateTimeEnd().toLocalTime().toString(fmt));

        textViewDetailParticipantsMeet.setText("Participants : "+meeting.getParticipants());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}