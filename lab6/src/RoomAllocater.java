import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomAllocater {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments");
            return;
        }

        RoomAllocater c = new RoomAllocater();

        Room[] rooms;
        try {
            rooms = c.readRooms(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        File f = new File(args[1]);
        try {
            if (!f.exists())
                if (!f.createNewFile())
                    return;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        PrintStream stream;
        try {
            stream = new PrintStream(f);
        } catch(FileNotFoundException e) {
            return;
        }
        stream.println(rooms.length);

        ArrayList<Room> rooms_with_problem = new ArrayList<>();
        for (Room r: rooms) {
            int last_gender = -1;
            stream.printf("Room %d: %d\n", r.getNumber(), r.getPeople().length);
            for (Person p: r.getPeople()) {
                stream.println(p.getName());
                if (last_gender != -1 && p.gender != last_gender)
                    rooms_with_problem.add(r);
                last_gender = p.gender;
            }
        }

        if (rooms_with_problem.size() == 0)
            stream.println("No problems found.");
        else {
            stream.print("Problems found in ");
            for (Room r : rooms_with_problem) {
                stream.printf("%d, ", r.getNumber());
            }
            stream.println();
        }
    }

    private Room[] readRooms(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int number_of_rooms = Integer.parseInt(scanner.nextLine().trim());
        Room[] rooms = new Room[number_of_rooms];

        for (int i = 0; i < number_of_rooms; i++) {
            int number_of_people = Integer.parseInt(scanner.nextLine().split(":")[1].trim());
            Person[] people = new Person[number_of_people];
            for (int j = 0; j < number_of_people; j++) {
                String[] line = scanner.nextLine().split(",");
                String name = line[0].trim();
                int gender;
                switch (line[1].trim().toLowerCase()) {
                    case "male":
                        gender = 1;
                        break;
                    case "female":
                        gender = 2;
                        break;
                    default:
                        gender = 0;
                        break;
                }
                people[j] = new Person(name, gender);
            }
            rooms[i] = new Room(i + 1, people);
        }

        return rooms;
    }

    private class Room {
        private int number;
        private Person[] people;

        public Room(int number, Person[] people) {
            this.number = number;
            this.people = people;
        }

        public int getNumber() {
            return number;
        }

        public Person[] getPeople() {
            return people;
        }
    }

    private class Person {
        private String name;
        private int gender; // 1: Male, 2: Female

        public Person(String name, int gender) {
            this.name = name;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public int getGender() {
            return gender;
        }
    }
}
