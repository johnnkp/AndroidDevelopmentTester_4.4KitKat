package hkcc.ccn3165.songplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.development.R;

import java.io.IOException;

public class SongPlayer extends AppCompatActivity {
    private ImageView imgStop, imgPlay, imgPause;
    // Task 2a
    // Declare the variables of the three images by ImageView.
    private ImageView imgPrev, imgNext, imgEnd;
    private ListView lstMusic;
    private TextView txtMusic;
    public MediaPlayer mediaplayer;
    // Songs Name
    String[] songname = new String[]{"Demo 0", "Demo 1", "Demo 2", "Demo 3", "Demo 4", "Demo 5", "Demo 6", "Demo 7", "Demo 8"};
    // Songs Resources
    int[] songfile = new int[]{R.raw.greensleeves, R.raw.songbird, R.raw.tradewinds, R.raw.greensleeves, R.raw.songbird, R.raw.tradewinds, R.raw.greensleeves, R.raw.songbird, R.raw.tradewinds};
    private int cListItem = 0; // Current playing song
    private Boolean flagPause = false; // Flag the pause

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player);

        imgStop = (ImageView) findViewById(R.id.imgStop);
        imgPlay = (ImageView) findViewById(R.id.imgPlay);
        imgPause = (ImageView) findViewById(R.id.imgPause);

        // Task 2b
        // Assign the variables with findViewById.
        imgPrev = (ImageView) findViewById(R.id.imgPrev);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgEnd = (ImageView) findViewById(R.id.imgEnd);

        lstMusic = (ListView) findViewById(R.id.lstMusic);
        txtMusic = (TextView) findViewById(R.id.txtMusic);
        imgStop.setOnClickListener(listener);
        imgPlay.setOnClickListener(listener);
        imgPause.setOnClickListener(listener);

        // Task 2c
        // Set the OnClickListener to the variables.
        imgPrev.setOnClickListener(listener);
        imgNext.setOnClickListener(listener);
        imgEnd.setOnClickListener(listener);

        lstMusic.setOnItemClickListener(lstListener);
        mediaplayer = new MediaPlayer();
        ArrayAdapter<String> adaSong = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songname);
        lstMusic.setAdapter(adaSong);
    }

    private ImageView.OnClickListener listener = new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {
            icon();
            switch (v.getId()) {
                case R.id.imgPlay: // Play
                    // Task 2d
                    // Change the image from play.png to play_.png when an image is tapped.
                    imgPlay.setImageResource(R.drawable.play_);

                    if (flagPause) { // If pause status, resume playing
                        mediaplayer.start();
                        flagPause = false;
                    } else // Else play from beginning
                        playSong(songfile[cListItem]);
                    break;
                case R.id.imgPause: // Pause
                    // Task 2e
                    // Change the image from pause.png to pause_.png when an image is tapped.
                    imgPause.setImageResource(R.drawable.pause_);

                    mediaplayer.pause();
                    flagPause = true;
                    break;
                case R.id.imgStop: // Stop
                    // Task 2f
                    // Change the image from stop.png to stop_.png when an image is tapped.
                    imgStop.setImageResource(R.drawable.stop_);

                    if (mediaplayer.isPlaying()) { // Check whether is playing
                        mediaplayer.reset(); // Reset MediaPlayer
                    }
                    break;
                case R.id.imgPrev:
                    imgPlay.setImageResource(R.drawable.play_);
                    prevSong();
                    break;
                case R.id.imgNext:
                    imgPlay.setImageResource(R.drawable.play_);
                    nextSong();
                    break;
                case R.id.imgEnd:
                    mediaplayer.release(); // Release MediaPlayer
                    finish(); // Quit app
                    break;
            }
        }
    };

    private ListView.OnItemClickListener lstListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // Task 3
            // Change the image from play.png to play_.png when a song name is tapped.
            icon();
            imgPlay.setImageResource(R.drawable.play_);

            cListItem = position; // Get the tapped position
            playSong(songfile[cListItem]); // Play
        }
    };

    private void playSong(int song) {
        mediaplayer.reset();
        mediaplayer = MediaPlayer.create(SongPlayer.this, song); // Play the song
        try {
            mediaplayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaplayer.start(); // Start playing
        txtMusic.setText("Now Playing >>> " + songname[cListItem]); // Update the song name
        flagPause = false;
        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                nextSong();
            }
        });
    }

    private void prevSong() {
        cListItem--;
        if (cListItem < 0)
            cListItem = lstMusic.getCount() - 1;
        playSong(songfile[cListItem]);
    }

    private void nextSong() {
        // Task 4
        // Complete the nextSong(), when it is playing the last song on the list, go back to the first one.
        cListItem++;
        if (cListItem >= lstMusic.getCount())
            cListItem = 0;
        playSong(songfile[cListItem]);
    }

    private void icon() {
        // Task 5
        // Reset the images of STOP, PLAY, and PAUSE.
        imgStop.setImageResource(R.drawable.stop);
        imgPlay.setImageResource(R.drawable.play);
        imgPause.setImageResource(R.drawable.pause);
    }

    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } */
}
