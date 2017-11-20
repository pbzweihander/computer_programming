import java.util.Vector;

public class Manage {
    private ManageInfo info;

    public Manage() {
        info = new ManageInfo();
    }

    public Manage(ManageInfo info) {
        this.info = info;
    }

    public void set(ManageInfo info) {
        this.info = info;
    }

    public Report report() {
        Vector<String> lines = new Vector<>();

        lines.add("" + info.getLength());
        for (int i = 0; i < info.getLength(); i++) {
            Room room = info.getRoom(i);



            String line = "Room " + (i + 1) + " :";
            Vector<String> tokens = new Vector<>();

            if (room.isCareTreat())
                tokens.add(" CareTreat");
            if (room.isDangerous())
                tokens.add(" Dangerous");
            if (room.isFoodType())
                tokens.add(" FoodType");
            line += String.join(",", tokens);

            lines.add(line);
        }

        return new Report(lines);
    }
}
