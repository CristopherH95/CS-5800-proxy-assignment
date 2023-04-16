package music.services;

import music.abstractions.MemorySongService;
import music.exceptions.SongMetaDataNotFound;
import music.interfaces.SongMetaData;

import java.util.ArrayList;
import java.util.List;

public class SongServerService extends MemorySongService {
    public SongServerService(ArrayList<SongMetaData> songs) {
        super(songs);
    }

    @Override
    public SongMetaData searchById(int id) throws SongMetaDataNotFound {
        simulateServerDelay();
        return super.searchById(id);
    }

    @Override
    public List<SongMetaData> searchByTitle(String title) {
        simulateServerDelay();
        return super.searchByTitle(title);
    }

    @Override
    public List<SongMetaData> searchByAlbum(String album) {
        simulateServerDelay();
        return super.searchByAlbum(album);
    }

    private void simulateServerDelay() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }
}
