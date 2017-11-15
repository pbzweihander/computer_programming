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
        return null;
    }
}
