/**
 * Created by DevelopAccount on 2016/11/22.
 */

public class Task {
    private String TaskName;
    private double TaskTime;
    private int    TaskPriority;

    public void SetName(String name){
        this.TaskName = name;
    }

    public void SetTime(double time){
        this.TaskTime = time;
    }

    public void SetPriority(int priority){
        this.TaskPriority = priority;
    }

    public String GetName(){
        return TaskName;
    }

    public double GetTime(){
        return TaskTime;
    }
    public int GetPriority(){
        return TaskPriority;
    }
}
