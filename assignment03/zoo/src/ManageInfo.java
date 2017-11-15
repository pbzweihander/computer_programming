import java.util.Vector;

/**
 * Created by Jeong Wonil on 2017-11-06.
 * This file should not be modified.
 */
public class ManageInfo {

    Vector<Room> rooms;

    public ManageInfo() {
        rooms = new Vector<>();
    }

    public ManageInfo(Vector<Room> roomVec) {
        rooms = new Vector<>();
        for (int i = 0; i < roomVec.size(); i++) {
            rooms.add(roomVec.elementAt(i));
        }
    }

    public Animal getAnimal(int i, int j) {
        return rooms.elementAt(i).getAnimal(j);
    }

    public Room getRoom(int i) {
        return rooms.elementAt(i);
    }

    public boolean getFood(int i, int j) {
        return rooms.elementAt(i).getFood(j);
    }

    public int getLength() {
        return rooms.size();
    }
}
