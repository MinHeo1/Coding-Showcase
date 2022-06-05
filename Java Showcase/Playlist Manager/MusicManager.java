// Min Heo
// 4/18/22
// CSE 143B
// This program keeps track of a music playlist for the client. Using this program
// the client can check what song is next, what songs are in the playist, remove songs
// in the playlist as well as viewing all the songs that were removed. The casing of the
// songs the user wants to check is ignored.

import java.util.*;

public class MusicManager {
   
   private MusicNode musicFront;
   private MusicNode removedMusicFront;
   
   // pre: If the playlist has no songs in it the program throws an illegalArgumentException.
   // post: Creates the playlist of the user in the same order as provided. It also intializes
   //       the list of removed songs. 
   // parameters:
   //    songs: All the songs that are in the music playlist.
   public MusicManager(List<String> songs) {
      if (songs.size() == 0) {
         throw new IllegalArgumentException();
      }
      MusicNode removeMusicFront = null;
      musicFront = new MusicNode(songs.get(0));
      MusicNode currMusic = musicFront;
      for (int i = 1; i < songs.size(); i++) {
         String song = songs.get(i);
         MusicNode newSong = new MusicNode(song);
         currMusic.next = newSong;
         currMusic = currMusic.next;
      }
   }
   
   // post: Shows all of the songs that are in the playlist.
   public void printCurrent() {
      print(musicFront);
   }
   
   // post: This shows all the songs that were removed by the user in opposite order. Meaning,
   //       it prints the last removed song first all the way to the first removed song.
   public void printRemoved() {
      print(removedMusicFront);
   }
   
   // post: Tells the user whether a certain song is in the playlist ignoring casing. 
   // parameters:
   //    song: The song the client wants to see is in the playlist or not.
   // return: Returns whether the song, ignoring casing, is in the given playlist.
   public boolean currentContains(String song) {
      return checkContains(musicFront, song);
   }
   
   // post: Tells the user whether the given song, ingoring casing, is in the playlist.
   // parameters:
   //    song: The song the client wants to see is in the removed song list or not.
   // return: Returns whether the song is in the removed songs playlist or not.
   public boolean removedContains(String song) {
      return checkContains(removedMusicFront, song);
   }
   
   // post: Checks to see whether there are more than 0 songs the playlist.
   // return: Returns whether there is more than 0 song in the playlist.
   public boolean hasSongs() {
      return musicFront != null;
   }
   
   // post: Shows the user the next song in the playlist.
   // return: Returns the next song in the playlist.
   // return: If there are no songs next, the program returns null.
   public String nextSong() {
      if (hasSongs()) {
         return musicFront.song;
      }
      return null;
   }
   
   // pre: If there are no songs in the playlist then the program throws an 
   //      IllegalStateException. If there the given song is not in the playlist
   //      then the program throws an IllegalArgumentException.
   // post: Removes the song that the user wants to remove. The song is then put into
   //       the front of the removed songs playlist. The order of the song remains the same.
   //       Casing does not matter for the song name.
   // parameters:
   //    song: The name of the song that the client wants to remove (ignoring casing).
   public void remove(String song) {
      if (!hasSongs()) {
         throw new IllegalStateException();
      }
      if (!currentContains(song)) {
         throw new IllegalArgumentException();
      }
      MusicNode currSong = musicFront;
      MusicNode theSong;
      if (musicFront.song.equalsIgnoreCase(song)) {
         theSong = musicFront;
         musicFront = theSong.next;
         currSong = currSong.next;
      } else {
         while (!currSong.next.song.equalsIgnoreCase(song)) {
            currSong = currSong.next;
         }
         theSong = currSong.next;
         currSong.next = theSong.next;
      }
      theSong.next = removedMusicFront;
      removedMusicFront = theSong;
   }
   
   // post: Checks whether the song is in the playlist or the removed songs list
   //       while ignoring casing.
   // parameters:
   //    inOrOutPlaylist: The songs in the playlist or the removed songs playlist.
   //    song: The song that the user wants to see is either playlist or not.
   // return: Returns true if the song is in either playlist, false otherwise.
   private boolean checkContains(MusicNode inOrOutPlaylist, String song) {
      MusicNode currSong = inOrOutPlaylist;
      while (currSong != null) {
         if (currSong.song.equalsIgnoreCase(song)) {
            return true;
         }
         currSong = currSong.next;
      }
      return false;
   }
   
   // post: Shows the user the songs in either the playlist or the removed playlist.
   // parameters:
   //    list: The songs in either the playlist or the removed playlist.
   private void print(MusicNode list) {
      MusicNode currSong = list;
      while (currSong != null) {
         MusicNode nextSong = currSong.next;
         System.out.println("    " + currSong.song);
         currSong = nextSong;
      }
   }
}