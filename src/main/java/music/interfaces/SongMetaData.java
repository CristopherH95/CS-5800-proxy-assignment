package music.interfaces;

public interface SongMetaData extends Comparable<SongMetaData> {
    Integer getId();
    String getTitle();
    String getArtist();
    String getAlbum();
    int getDuration();
}
