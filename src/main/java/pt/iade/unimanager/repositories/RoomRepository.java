package pt.iade.unimanager.repositories;

import java.util.ArrayList;

import pt.iade.unimanager.models.Computer;
import pt.iade.unimanager.models.Laboratory;
import pt.iade.unimanager.models.Material;
import pt.iade.unimanager.models.Room;

public class RoomRepository {
  private static ArrayList<Room> rooms = new ArrayList<>();

  public static void populate() {
    rooms.add(new Room("35", 30));
    rooms.add(new Room("25", 25));
    rooms.add(new Room("15", 20));
    Laboratory l = new Laboratory("36", 30);
    l.getMaterials().add(new Material("Chair", "New"));
    l.getMaterials().add(new Material("Table", "New"));
    l.getMaterials().add(new Computer("I7", "New"));
    l.getMaterials().add(new Computer("I5", "New"));
    rooms.add(l);
    l = new Laboratory("23", 20);
    l.getMaterials().add(new Material("Chair", "New"));
    l.getMaterials().add(new Material("Table", "New"));
    l.getMaterials().add(new Computer("I7", "Used"));
    l.getMaterials().add(new Computer("I5", "New"));
    rooms.add(l);
  }

  public static ArrayList<Room> getAllRooms() {
    return rooms;
  }

  public static Room getRoomByDesignation(String designation) {
    for (Room x : rooms) {
      if (x.getDesignation().toLowerCase().equals(designation.toLowerCase())) {
        return x;
      }
    }

    return null;
  }

  public static void addRoom(Room room) {
    rooms.add(room);
  }

  public static boolean deleteRoomByDesignation(String designation) {
    return rooms.removeIf(x -> x.getDesignation().toLowerCase().equals(designation.toLowerCase()));
  }
}
