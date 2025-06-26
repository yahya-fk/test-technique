import enums.RoomType;
import service.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Service s = new Service();

        // Rooms
        s.setRoom(1, RoomType.STANDARD, 1000);
        s.setRoom(2, RoomType.JUNIOR, 2000);
        s.setRoom(3, RoomType.SUITE, 3000);

        // Users
        s.setUser(1, 5000);
        s.setUser(2, 10000);

        // Bookings
        s.bookRoom(1, 2, parseDate("30/06/2026"), parseDate("07/07/2026")); // 7 nights
        s.bookRoom(1, 2, parseDate("07/07/2026"), parseDate("30/06/2026")); // invalid
        s.bookRoom(1, 1, parseDate("07/07/2026"), parseDate("08/07/2026")); // 1 night
        s.bookRoom(2, 1, parseDate("07/07/2026"), parseDate("09/07/2026")); // clash
        s.bookRoom(2, 3, parseDate("07/07/2026"), parseDate("08/07/2026")); // ok

        // Update room type (should not affect bookings)
        s.setRoom(1, RoomType.SUITE, 10000);

        // Output
        s.printAll();
        s.printAllUsers();
    }

    public static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format: " + dateStr);
            return null;
        }
    }
}