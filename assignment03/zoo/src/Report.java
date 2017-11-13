import java.util.Vector;

/**
 * Created by Jeong Wonil on 2017-11-06.
 * This file should not be modified.
 */
public class Report {

    Vector<String> lines;

    public Report(){
        lines = new Vector<>();
    }
    public Report(int size, String[] lines){
        this.lines = new Vector<>();
        for(int i = 0; i < size; i++){
            this.lines.add(lines[i]);
        }
    }
    public Report(Vector<String> lines){
        this.lines = new Vector<>();
        for(int i = 0; i < lines.size(); i++){
            this.lines.add(lines.elementAt(i));
        }
    }
    public void set(int size, String[] lines){
        for(int i = 0; i < size; i++){
            this.lines.add(lines[i]);
        }
    }
    public void set(Vector<String> lines){
        for(int i = 0; i < lines.size(); i++){
            this.lines.add(lines.elementAt(i));
        }
    }
    public void printAll(){
        for(int i = 0; i < lines.size(); i++){
            System.out.println(lines.elementAt(i));
        }
    }
}
