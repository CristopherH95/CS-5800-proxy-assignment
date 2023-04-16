package music.abstractions;

import music.exceptions.SongMetaDataNotFound;
import music.interfaces.SongMetaData;
import music.interfaces.SongService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public abstract class MemorySongService implements SongService {
    protected final HashSet<SongMetaData> songs;

    public MemorySongService(ArrayList<SongMetaData> songs) {
        this.songs = new HashSet<>(songs);
    }

    @Override
    public SongMetaData searchById(int id) throws SongMetaDataNotFound {
        SongMetaData songFound = null;

        for (SongMetaData songData : songs) {
            if (songData.getId() == id) {
                songFound = songData;
                break;
            }
        }

        if (Objects.isNull(songFound)) {
            throw new SongMetaDataNotFound(String.format("Could not find data for id '%d'", id));
        }

        return songFound;
    }

    @Override
    public List<SongMetaData> searchByTitle(String title) {
        List<SongMetaData> songsFound = new ArrayList<>();

        for (SongMetaData songData : songs) {
            if (songData.getTitle().equals(title)) {
                songsFound.add(songData);
            }
        }

        return songsFound;
    }

    @Override
    public List<SongMetaData> searchByAlbum(String album) {
        List<SongMetaData> songsFound = new ArrayList<>();

        for (SongMetaData songData : songs) {
            if (songData.getAlbum().equals(album)) {
                songsFound.add(songData);
            }
        }

        return songsFound;
    }
}
