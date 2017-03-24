import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by DevelopAccount on 2016/11/22.
 */

public class Manager {
    ArrayList<Task> taskList = new ArrayList<>();
    PriorityQueue<Task> priorityQueue = new PriorityQueue<>(11,new Comparetor());

    public void GetTaskList(String filepath) throws IOException{
        ArrayList<Task> taskList = new ArrayList<>();
        try{
            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = null;
                while((str = bufferedReader.readLine())!=null){
                Task task = new Task();
                task.SetName(str.split(",")[0]);
                task.SetTime(Double.parseDouble(str.split(",")[1]));
                task.SetPriority(Integer.parseInt(str.split(",")[2]));
                taskList.add(task);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        this.taskList = taskList;
    }
    public void SortWorkList() throws FileNotFoundException{
        for(int i=0;i<taskList.size();i++){
            priorityQueue.add(taskList.get(i));
        }
    }
}

