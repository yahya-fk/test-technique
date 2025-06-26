package service;
import classes.Booking;
import classes.Room;
import classes.User;
import enums.RoomType;

import java.util.*;

public class Service {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) return;
        }
        rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
    }

    public void setUser(int userId, int balance) {
        for (User u : users) {
            if (u.getUserId() == userId) return;
        }
        users.add(new User(userId, balance));
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        if (!checkIn.before(checkOut)) {
            System.out.println("Invalid date range");
            return;
        }

        User user = users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
        Room room = rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null);
        if (user == null || room == null) {
            System.out.println("User or room not found");
            return;
        }

        // Check if room is available
        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == roomNumber &&
                    !(checkOut.compareTo(b.getCheckIn()) <= 0 || checkIn.compareTo(b.getCheckOut()) >= 0)) {
                System.out.println("Room not available");
                return;
            }
        }

        long days = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
        int cost = (int) days * room.getPricePerNight();

        if (user.getBalance() < cost) {
            System.out.println("Not enough balance");
            return;
        }

        user.setBalance(user.getBalance() - cost);
        bookings.add(new Booking(user, room, checkIn, checkOut, cost));
        System.out.println("Booking successful");
    }

    public void printAll() {
        System.out.println("--- Rooms ---");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            Room r = rooms.get(i);
            System.out.printf("Room %d | Type: %s | Price/Night: %d\n", r.getRoomNumber(), r.getRoomType(), r.getPricePerNight());
        }

        System.out.println("--- Bookings ---");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            Booking b = bookings.get(i);
            System.out.printf("User %d | Room %d | From %s to %s | Total Cost: %d\n",
                    b.getUser().getUserId(), b.getRoom().getRoomNumber(), b.getCheckIn(), b.getCheckOut(), b.getTotalCost());
        }
    }

    public void printAllUsers() {
        System.out.println("--- Users ---");
        for (int i = users.size() - 1; i >= 0; i--) {
            User u = users.get(i);
            System.out.printf("User %d | Balance: %d\n", u.getUserId(), u.getBalance());
        }
    }
}
