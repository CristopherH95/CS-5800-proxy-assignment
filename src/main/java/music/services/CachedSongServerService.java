package music.services;

import music.abstractions.MemorySongService;
import music.exceptions.SongMetaDataNotFound;
import music.interfaces.SongMetaData;
import music.interfaces.SongService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CachedSongServerService extends MemorySongService {
    private final SongService wrappedService;
    // Tracking the cached search terms helps promote more consistent results
    private final HashSet<String> cachedTitleSearches;
    private final HashSet<String> cachedAlbumSearches;

    public CachedSongServerService(SongService serverService) {
        super(new ArrayList<>());
        this.wrappedService = serverService;
        this.cachedTitleSearches = new HashSet<>();
        this.cachedAlbumSearches = new HashSet<>();
    }

    @Override
    public SongMetaData searchById(int id) throws SongMetaDataNotFound {
        try {
            return super.searchById(id);
        } catch(SongMetaDataNotFound e) {
            SongMetaData songData = wrappedService.searchById(id);
            cacheSongData(songData);
            return songData;
        }
    }

    @Override
    public List<SongMetaData> searchByTitle(String title) {
        if (cachedTitleSearches.contains(title)) {
            return super.searchByTitle(title);
        }
        List<SongMetaData> foundSongData = wrappedService.searchByTitle(title);
        cacheSongData(foundSongData.toArray(new SongMetaData[0]));
        cachedTitleSearches.add(title);
        return foundSongData;
    }

    @Override
    public List<SongMetaData> searchByAlbum(String album) {
        if (cachedAlbumSearches.contains(album)) {
            return super.searchByAlbum(album);
        }
        List<SongMetaData> foundSongData = wrappedService.searchByAlbum(album);
        cacheSongData(foundSongData.toArray(new SongMetaData[0]));
        cachedAlbumSearches.add(album);
        return foundSongData;
    }

    private void cacheSongData(SongMetaData... songData) {
        this.songs.addAll(List.of(songData));
    }
}
