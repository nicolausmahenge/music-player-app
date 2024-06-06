import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnItemClickListener {

    private RecyclerView recyclerViewSongs;
    private SongAdapter songAdapter;
    private MusicDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSongs = findViewById(R.id.recyclerViewSongs);
        recyclerViewSongs.setLayoutManager(new LinearLayoutManager(this));

        dataSource = new MusicDataSource(this);
        dataSource.open();

        List<Song> songs = dataSource.getAllSongs();

        songAdapter = new SongAdapter(this, songs);
        songAdapter.setOnItemClickListener(this);
        recyclerViewSongs.setAdapter(songAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }

    @Override
    public void onItemClicked(Song song) {
        // Handle item click (e.g., start playback)
        Toast.makeText(this, "Clicked: " + song.getTitle(), Toast.LENGTH_SHORT).show();
    }

    // Method to add a new song to the database
    private void addSong(String title, String artist, String album, String duration) {
        Song newSong = new Song(title, artist, album, duration);
        try {
            dataSource.addSong(newSong);
            songAdapter.refreshData(dataSource.getAllSongs());
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error adding song: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Method to control playback (e.g., play/pause)
    private void controlPlayback() {
        // Implement playback control logic here
    }

    // Implement other methods for controlling playback, updating UI, etc.
}
