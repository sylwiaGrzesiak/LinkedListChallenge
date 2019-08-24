package com.syanna;

import java.util.ArrayList;

public class Album {

    private String name;
    private ArrayList<Song> songs;

    public Album(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(String title, String duration) {
        if(findSong(title) == null) {
            songs.add(new Song(title, duration));
            System.out.println("Song \"" + title + "\" has been added to album " + this.name + ".");
        } else {
            System.out.println("Song already exists in the album");
        }
    }

    public Song findSong(String title) {
        for(Song checkedSong: this.songs) { // for each command
            if(checkedSong.getTitle().equals(title)){
                return checkedSong;
            }
        }
//        for(int i = 0; i< songs.size(); i++) {
//            if(songs.get(i).getTitle().equals(title)) {
//                return songs.get(i);
//            }
//        }
        return null;
    }
}
