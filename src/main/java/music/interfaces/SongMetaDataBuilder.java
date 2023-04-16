package music.interfaces;

public interface SongMetaDataBuilder {
    SongMetaDataBuilder setTitle(String title);
    SongMetaDataBuilder setArtist(String artist);
    SongMetaDataBuilder setAlbum(String album);
    SongMetaDataBuilder setDuration(int duration);
    SongMetaData build();
}
