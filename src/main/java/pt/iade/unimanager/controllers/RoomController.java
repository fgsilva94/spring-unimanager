package pt.iade.unimanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Response;
import pt.iade.unimanager.models.Room;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.repositories.RoomRepository;

@RestController
@RequestMapping(path = "api/rooms")
public class RoomController {
  private Logger logger = LoggerFactory.getLogger(RoomController.class);

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Room> getAllRooms() {
    logger.info("Sending all Rooms");
    return RoomRepository.getAllRooms();
  }

  @GetMapping(path = "/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Room getRoomByDesignation(@PathVariable("designation") String designation) throws NotFoundException {
    logger.info("Sending Room with designation " + designation);
    Room room = RoomRepository.getRoomByDesignation(designation);

    if (room != null) {
      return room;
    } else {
      throw new NotFoundException(designation, "Room", "designation");
    }
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Room addRoom(@RequestBody Room room) {
    logger.info("Adding room " + room.getDesignation());
    RoomRepository.addRoom(room);
    return room;
  }

  @DeleteMapping(path = "/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response deleteRoomByDesignation(@PathVariable("designation") String designation) {
    logger.info("Deleting room with designation " + designation);

    if (RoomRepository.deleteRoomByDesignation(designation)) {
      return new Response(designation + " was deleted.", null);
    } else {
      return new Response(designation + " not found.", null);
    }
  }
}
