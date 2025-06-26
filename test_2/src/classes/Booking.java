package classes;


import java.util.Date;

public class Booking {
    User user;
    Room room;
    Date checkIn;
    Date checkOut;
    int totalCost;

    public Booking(User user, Room room, Date checkIn, Date checkOut, int totalCost) {
        this.user = new User(user.userId, user.balance); // snapshot
        this.room = new Room(room.roomNumber, room.roomType, room.pricePerNight); // snapshot
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
