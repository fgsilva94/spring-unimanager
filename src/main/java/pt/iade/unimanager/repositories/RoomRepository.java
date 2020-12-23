package pt.iade.unimanager.repositories;

import java.util.ArrayList;
import java.util.List;

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
    l.getMaterials().add(new Material("Chair"));
    l.getMaterials().add(new Material("Table"));
    l.getMaterials().add(new Computer("I7"));
    l.getMaterials().add(new Computer("I5"));
    rooms.add(l);
    l = new Laboratory("23", 20);
    l.getMaterials().add(new Material("Chair"));
    l.getMaterials().add(new Material("Table"));
    l.getMaterials().add(new Computer("I7"));
    l.getMaterials().add(new Computer("I5"));
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

  public static List<Material> getMaterialsByRoomDesignation(String designation) {
    Room r = getRoomByDesignation(designation);
    if (r.getClass().equals(Laboratory.class)) {
      Laboratory l = (Laboratory) r;
      return l.getMaterials();
    } else {
      return null;
    }
  }
}
