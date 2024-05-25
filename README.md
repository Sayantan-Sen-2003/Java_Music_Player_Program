# Java_Music_Player_Program
A java program(NOT APP) to implement and replicate the functionalities of an actual music player using data structues like linkedlist,stack and queue. 

## Aim:-
 to implement an working music player by using playlist and replicating its functionalities .

### Functions used:-

-  public void toFile(String song)- merges the final playlist to a .txt file
-  void addNode(String song)- adds the song to the playlist
- void deleteFile(String song)- deletes that particular song from playlist
-  public void deleteNode()- deletes that particular song from playlist
-  public void printList() - prints the whole playlist
-  public void countNodes() - counts the number of song in the playlist
- public void search(String song) - searches the song in the playlist
- public void create()- initialises the playlist upon running the program
- public void displayRecent()- shows the last played song in the list
- public void play(String song)- plays a particular song
- public void lastPlayed()- shows the last played song
- public void sort() - sorts the whole playlist in-place
- public void addPlaylistFromFile() - adds the playlist from file
- public void deleteByPosition(int pos)- deletes a particular song based on position
- public void deleteBySearch(String song)- detes based on name of song

 
## Running the project:-
- use any IDE or through direct command prompt with JVM/Java compiler installed
- can be run on any online compiler as well


## Problem encountered:- 
- null pointer expections and file handling exceptions were needed to be taken into consideration
- at this moment, a single playlist can only be created as file handling cant replicate/run on multiple reruns


## Future Goals:-
- updating the current playlist functionalities to make it even faster
- finding a way  to store past and present entries on rerun
- adding support for some featues/functions which are implemented but are not not added to reduce complexity.
