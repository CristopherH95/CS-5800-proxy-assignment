package driver;

import music.interfaces.SongMetaData;
import music.interfaces.SongService;
import music.services.CachedSongServerService;
import music.services.SongServerService;
import music.songs.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String[] artists = new String[]{
        "The Beatles",
        "Taylor Swift",
        "Elvis Presley",
        "Slipknot",
        "Bad Bunny",
        "Hans Zimmer"
    };
    private static final int songsPerArtist = 2;
    private static final String[] albums = new String[]{
        "Album A",
        "Album B"
    };
    private static final String[] titles = new String[]{
        "Title A",
        "Title B"
    };

    public static void main(String[] args) {
        int testRounds = 2;
        SongService songService = new SongServerService(generateMusic());
        System.out.println("**** TESTING SERVER SERVICE ****");
        testSongService(songService, testRounds);
        SongService cachedSongService = new CachedSongServerService(songService);
        System.out.println("**** TESTING PROXY SERVICE **** ");
        testSongService(cachedSongService, testRounds);
    }

    private static ArrayList<SongMetaData> generateMusic() {
        ArrayList<SongMetaData> songs = new ArrayList<>();

        for (String artistName : artists) {
            for (int i = 0; i < songsPerArtist; i++) {
                Song.SongBuilder builder = new Song.SongBuilder();
                builder.setArtist(artistName)
                        .setAlbum(albums[i])
                        .setTitle(titles[i])
                        .setDuration(getRandomDuration());
                songs.add(builder.build());
            }
        }

        return songs;
    }

    private static int getRandomDuration() {
        return ThreadLocalRandom.current().nextInt(1, 5);
    }

    private static void testSongService(SongService songService, int numberOfRounds) {
        System.out.printf("Running song service test for %d round(s)%n", numberOfRounds);

        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfRounds; i++) {
            System.out.printf("** Round %d **%n", i + 1);
            for (int j = 0; j < songsPerArtist; j++) {
                testTitleSearch(songService, titles[j]);
                testAlbumSearch(songService, albums[j]);
            }
        }
        long endTime = System.nanoTime();
        long runTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("Total runtime for song service test: %d milliseconds%n", runTime);
    }

    private static void testTitleSearch(SongService songService, String title) {
        System.out.printf("Searching for title: %s%n", title);
        long startTime = System.nanoTime();
        List<SongMetaData> foundSongs = songService.searchByTitle(title);
        long endTime = System.nanoTime();
        long runTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("Found %d results in %d milliseconds%n", foundSongs.size(), runTime);
    }

    private static void testAlbumSearch(SongService songService, String album) {
        System.out.printf("Searching for album: %s%n", album);
        long startTime = System.nanoTime();
        List<SongMetaData> foundSongs = songService.searchByAlbum(album);
        long endTime = System.nanoTime();
        long runTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("Found %d results in %d milliseconds%n", foundSongs.size(), runTime);
    }
}