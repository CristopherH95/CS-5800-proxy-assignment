package music.interfaces;

import music.exceptions.SongMetaDataNotFound;

import java.util.List;

public interface SongService {
    SongMetaData searchById(int id) throws SongMetaDataNotFound;
    List<SongMetaData> searchByTitle(String title);
    List<SongMetaData> searchByAlbum(String album);
}
