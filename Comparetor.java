import java.util.Comparator;

/**
 * Created by DevelopAccount on 2016/11/23.
 */
public class Comparetor implements Comparator{
    public int compare(Object o1,Object o2){
        Task task1 = (Task)o1;
        Task task2 = (Task)o2;

        int result = task1.GetPriority()>task2.GetPriority() ? 1 :
        (task1.GetPriority() == task2.GetPriority() ? 0:-1);
        if(result == 0){
            if(task1.GetTime()>task2.GetTime())result  = 1;
            if(task1.GetTime()<task2.GetTime())result  = -1;
            if(task1.GetTime()==task2.GetTime())result = 0;
        }
        return result;
    }
}
