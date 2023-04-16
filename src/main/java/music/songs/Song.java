package music.songs;

import music.interfaces.SongMetaData;
import music.interfaces.SongMetaDataBuilder;

public class Song implements SongMetaData {
    private final int id;
    private final String title;
    private final String artist;
    private final String album;
    private final int duration;
    private static int songCounter = 0;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getAlbum() {
        return album;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(SongMetaData o) {
        return getId().compareTo(o.getId());
    }

    private Song(SongBuilder builder) {
        songCounter++;
        id = songCounter;
        this.title = builder.title;
        this.artist = builder.artist;
        this.album = builder.album;
        this.duration = builder.duration;
    }

    public static class SongBuilder implements SongMetaDataBuilder {
        private String title;
        private String artist;
        private String album;
        private int duration;

        public SongBuilder() {
            title = "";
            artist = "";
            album = "";
            duration = 0;
        }

        @Override
        public SongBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        @Override
        public SongBuilder setArtist(String artist) {
            this.artist = artist;
            return this;
        }

        @Override
        public SongBuilder setAlbum(String album) {
            this.album = album;
            return this;
        }

        @Override
        public SongBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        @Override
        public SongMetaData build() {
            return new Song(this);
        }
    }
}
