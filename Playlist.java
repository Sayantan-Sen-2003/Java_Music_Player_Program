import java.io.*;
import java.util.Scanner;

class Node {
    String song;
    Node next;
    Node prev;

    Node(String song) {
        this.song = song;
        this.next = null;
        this.prev = null;
    }
}

public class Playlist {
    Node head;
    Node top;
    String playlistName;

    public Playlist(String playlistName) {
        this.head = null;
        this.top = null;
        this.playlistName = playlistName;
    }

    public void toFile(String song) {
        try (FileWriter writer = new FileWriter("playlist.txt", true)) {
            writer.write(song + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNode(String song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        toFile(song);
    }

    public void addNodeFromFile(String song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
    }

    public void deleteFile(String song) {
        File inputFile = new File("playlist.txt");
        File tempFile = new File("temp.txt");

        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(tempFile)) {

            int c;
            StringBuilder currentLine = new StringBuilder();
            boolean found = false;

            while ((c = reader.read()) != -1) {
                if (c == '\n') {
                    if (!currentLine.toString().trim().equals(song)) {
                        writer.write(currentLine.toString() + "\n");
                    } else {
                        found = true;
                    }
                    currentLine = new StringBuilder();
                } else {
                    currentLine.append((char) c);
                }
            }

            // Last line case (if no newline at the end of the file)
            if (currentLine.length() > 0) {
                if (!currentLine.toString().trim().equals(song)) {
                    writer.write(currentLine.toString() + "\n");
                } else {
                    found = true;
                }
            }

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            }

            if (found) {
                System.out.println("Song has been deleted.");
            } else {
                System.out.println("There is no song with the name you entered.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteNode() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        if (head.next == null) {
            head = null;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.prev.next = null;
        }
        System.out.println("Deleted last song.");
    }

    public void printList() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        System.out.println("\nPlaylist: " + playlistName);
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.song);
            temp = temp.next;
        }
    }

    public void countNodes() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("\nTotal songs: " + count);
    }

    public void search(String song) {
        Node temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.song.equals(song)) {
                System.out.println("\nSong Found");
                found = true;
                break;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("\nSong Not found");
        }
    }

    public void create() {
        top = null;
    }

    public void push(String song) {
        Node newNode = new Node(song);
        if (top == null) {
            top = newNode;
        } else {
            if (!top.song.equals(song)) {
                newNode.next = top;
                top = newNode;
            }
        }
    }

    public void displayRecent() {
        if (top == null) {
            System.out.println("\nNo recently played tracks.");
            return;
        }
        System.out.println("\nRecently played tracks:");
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.song);
            temp = temp.next;
        }
    }

    public void play(String song) {
        Node temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.song.equals(song)) {
                System.out.println("\nNow Playing: " + song);
                push(song);
                found = true;
                break;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("\nSong Not found");
        }
    }

    public void lastPlayed() {
        if (top == null) {
            System.out.println("\nNo last played tracks.");
            return;
        }
        System.out.println("\nLast Played Song: " + top.song);
    }

    public void sort() {
        if (head == null || head.next == null) {
            return;
        }
        Node end = null;
        while (end != head.next) {
            Node p = head;
            Node q = head.next;
            Node prev = null;
            while (q != end) {
                if (p.song.compareTo(q.song) > 0) {
                    if (p == head) {
                        head = q;
                    } else {
                        prev.next = q;
                    }
                    p.next = q.next;
                    q.next = p;
                    if (p.next != null) {
                        p.next.prev = p;
                    }
                    q.prev = prev;
                    p.prev = q;
                    prev = q;
                    q = p.next;
                } else {
                    prev = p;
                    p = p.next;
                    q = q.next;
                }
            }
            end = p;
        }
    }

    public void addPlaylistFromFile() {
        try (FileReader reader = new FileReader("playlist.txt")) {
            int c;
            StringBuilder currentLine = new StringBuilder();

            while ((c = reader.read()) != -1) {
                if (c == '\n') {
                    addNodeFromFile(currentLine.toString().trim());
                    currentLine = new StringBuilder();
                } else {
                    currentLine.append((char) c);
                }
            }

            // Last line case (if no newline at the end of the file)
            if (currentLine.length() > 0) {
                addNodeFromFile(currentLine.toString().trim());
            }

            System.out.println("Playlist added from file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPosition(int pos) {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        if (pos == 1) {
            deleteFile(head.song);
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            System.out.println("Song deleted from position: " + pos);
            return;
        }

        Node temp = head;
        int count = 1;
        while (temp != null && count < pos) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        deleteFile(temp.song);
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        System.out.println("Song deleted from position: " + pos);
    }

    public void deleteBySearch(String song) {
        Node temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.song.equals(song)) {
                deleteFile(temp.song);
                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                }
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                if (temp == head) {
                    head = temp.next;
                }
                found = true;
                System.out.println("Song deleted: " + song);
                break;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Song not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t**WELCOME**");
        System.out.println("\n**Please use '_' for space.");

        System.out.print("\nEnter your playlist name: ");
        String playlistName = scanner.nextLine();
        Playlist playlist = new Playlist(playlistName);

        playlist.create();

        int choice;
        do {
            System.out.println("\n1. Add New Song\n2. Delete Song\n3. Display Entered Playlist\n4. Total Songs\n5. Search Song\n6. Play Song\n7. Recently Played List\n8. Last Played\n9. Sorted Playlist\n10. Add From File\n11. Exit");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Song name: ");
                    String newSong = scanner.nextLine();
                    playlist.addNode(newSong);
                    break;
                case 2:
                    System.out.println("Which type of delete do you want?\n1. By Search\n2. By Position");
                    int deleteChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (deleteChoice == 1) {
                        System.out.print("Enter song you wish to delete: ");
                        String songToDelete = scanner.nextLine();
                        playlist.deleteBySearch(songToDelete);
                    } else if (deleteChoice == 2) {
                        System.out.print("Enter the position of the song: ");
                        int pos = scanner.nextInt();
                        playlist.deleteByPosition(pos);
                    }
                    break;
                case 3:
                    playlist.printList();
                    break;
                case 4:
                    playlist.countNodes();
                    break;
                case 5:
                    System.out.print("Enter song to be searched: ");
                    String searchSong = scanner.nextLine();
                    playlist.search(searchSong);
                    break;
                case 6:
                    System.out.print("Choose song you wish to play: ");
                    String playSong = scanner.nextLine();
                    playlist.play(playSong);
                    break;
                case 7:
                    playlist.displayRecent();
                    break;
                case 8:
                    playlist.lastPlayed();
                    break;
                case 9:
                    playlist.sort();
                    playlist.printList();
                    break;
                case 10:
                    playlist.addPlaylistFromFile();
                    break;
                case 11:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 11);
    }
}