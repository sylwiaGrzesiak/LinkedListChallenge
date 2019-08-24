package com.syanna;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {



	    albums.add(new Album("Korn"));
	    albums.add(new Album("Rammstein"));

	    // should add a method to do this
	    albums.get(0).addSong("Trash", "3:45");
        albums.get(0).addSong("Blind", "4:12");
        albums.get(0).addSong("Hey Daddy", "2:36");
        albums.get(0).addSong("Hating", "4:55");

	    albums.get(1).addSong("Nebel", "2:15");
        albums.get(1).addSong("Herzeleid", "3:46");
        albums.get(1).addSong("Mein Herz Brennt", "4:24");
        albums.get(1).addSong("Dalai Lama", "5:12");

        LinkedList<Song> playlist = new LinkedList<>();
        addSongToPlaylist(playlist, "Herzeleid", albums);
        addSongToPlaylist(playlist, "Trash", albums);
        addSongToPlaylist(playlist, "Blind", albums);
        addSongToPlaylist(playlist, "Dalai Lama", albums);

        playMusic(playlist);

    }

    //should be in album or song class
    private static void addSongToPlaylist(LinkedList<Song> playlist, String title, ArrayList<Album> albums) {
        boolean wasAdded = false;

//        for(int i=0; i<albums.size(); i++) {
        for(Album album: albums) {
            Song song = album.findSong(title);
            if(song != null) {
                playlist.add(song);
                System.out.println("Added " + song.getTitle() + " to the playlist");
                wasAdded = true;
            }
        }

        if(!wasAdded) {
            System.out.println(title + " was not found in your albums");
        }
    }

    private static void playMusic(LinkedList<Song> playlist) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playlist.listIterator();

        if(playlist.isEmpty()) {
            System.out.println("No songs in the playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    quit = true;
                    break;

                case 1:
                    if(forward) {
                        System.out.println("Replaying " + listIterator.previous().toString());
                        forward = false;
                    } else {
                        System.out.println("Replaying " + listIterator.next().toString());
                        forward = true;
                    }
                    break;

                case 2:
                    if(!forward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                            forward = true;
                        }
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("Reached last song on the playlist");
                    }
                    break;

                case 3:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                            forward = false;
                        }
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("Reached first song on the playlist");
                    }
                    break;

                case 4:
                    printMenu();
                    break;

                case 5:
                    printPlaylist(playlist);
                    break;

                case 6:
                    if(playlist.size()>0) {
                        if(forward) {
                            System.out.println("Removing " + listIterator.previous().toString());
                            listIterator.next();
                            listIterator.remove();
                        } else {
                            System.out.println("Removing " + listIterator.next().toString());
                            listIterator.previous();
                            listIterator.remove();
                        }

                        if(listIterator.hasNext()){
                            System.out.println("Now playing " + listIterator.next().toString());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous().toString());
                        }
                    }
                    break;
            }

        }

    }

    private static void printPlaylist(LinkedList<Song> playlist) {
        Iterator<Song> i = playlist.iterator(); //no need for list iterator since we don't go forwards AND backwards
        System.out.println("Songs in your playlist: ");
        while(i.hasNext()) {
            System.out.println(i.next().toString());
        }
    }

    private static void printMenu() {
        System.out.println("Options menu: \n" +
                "0 - quit\n" +
                "1 - replay current song\n" +
                "2 - play next song\n" +
                "3 - play previous song\n" +
                "4 - print options\n" +
                "5 - print playlist\n" +
                "6 - remove current song");
    }


}
