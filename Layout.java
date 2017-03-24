import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by DevelopAccount on 2016/11/26.
 */
public class Layout {
    private JFrame frame = new JFrame();
    private JFrame taskframe = new JFrame();
    private JFrame extraframe = new JFrame();
    private JTextField TaskNameText = new JTextField(20);
    private JTextField TaskPriorityText = new JTextField(20);
    private JTextField TaskTimeText = new JTextField(20);
    private JTextArea textArea = new JTextArea(10,20);
    public JTextArea RunArea = new JTextArea(20,20);
    public JScrollPane jScrollPane = new JScrollPane(RunArea);
    JButton button = new JButton("添加任务");
    JButton jButton = new JButton("添加新线程");
    JButton runButton = new JButton("运行任务");

    public void gui () {
        BorderLayout borderLayout = new BorderLayout();
        JPanel panel = new JPanel(borderLayout);

        button.addActionListener(new AddTask());
        jButton.addActionListener(new NewThread());
        runButton.addActionListener(new RUN());

        RunArea.setLineWrap(true);

        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setAutoscrolls(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(button);
        panel.add(jButton);
        panel.add(runButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, jScrollPane);
        frame.pack();
        frame.setVisible(true);
    } //主界面

    public class AddTask implements ActionListener{
        public void actionPerformed(ActionEvent e){
            BorderLayout layout = new BorderLayout();
            JPanel panel = new JPanel(layout);

            JButton AddButton = new JButton("Add");
            JButton RunButton = new JButton("Run");

            JLabel sName = new JLabel("线程名称：");
            JLabel sPriority = new JLabel("线程优先级：");
            JLabel sTime = new JLabel("线程运行时间");

            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            AddButton.addActionListener(new ADD());
            RunButton.addActionListener(new RUN());
            panel.add(sName);
            panel.add(TaskNameText);
            panel.add(sPriority);
            panel.add(TaskPriorityText);
            panel.add(sTime);
            panel.add(TaskTimeText);

            taskframe.getContentPane().add(BorderLayout.WEST,panel);
            taskframe.getContentPane().add(BorderLayout.EAST,textArea);
            taskframe.getContentPane().add(BorderLayout.CENTER,AddButton);
            taskframe.getContentPane().add(BorderLayout.SOUTH,RunButton);
            taskframe.pack();
            taskframe.setVisible(true);
        }
    }//添加任务

    class ADD implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String name = TaskNameText.getText();
            String priority = TaskPriorityText.getText();
            String time = TaskTimeText.getText();

            textArea.append(name+" "+priority+" "+time+"\n");

            try {
                FileWriter fileWriter =new FileWriter("task.txt",true);
                fileWriter.write(name+","+time+","+priority+"\n");
                fileWriter.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }

            TaskNameText.setText("");
            TaskPriorityText.setText("");
            TaskTimeText.setText("");
        }
    }//任务写入txt

    class RUN implements ActionListener{
        public void actionPerformed(ActionEvent e){
            taskframe.setVisible(false);
            Manager manager = new Manager();
            try{
                manager.GetTaskList("task.txt");
            }catch (IOException e1){
                e1.printStackTrace();
            }
            try {
                manager.SortWorkList();
            }catch (FileNotFoundException e1){
                e1.printStackTrace();
            }
            int size = manager.priorityQueue.size();
            for(int i = 0;i < size ; i++){
                Task task = manager.priorityQueue.poll();
                    SHOW(task.GetName(), String.valueOf(task.GetPriority()), task.GetTime());
            }
        }
    }//执行任务

    class NewThread implements ActionListener{
        public void actionPerformed(ActionEvent e){
            BorderLayout borderLayout = new BorderLayout();
            JPanel jPanel = new JPanel(borderLayout);
            JButton button = new JButton("立即执行");
            //button.addActionListener(new RUN());

            JLabel sName = new JLabel("线程名称：");
            JLabel sPriority = new JLabel("线程优先级：");
            JLabel sTime = new JLabel("线程运行时间");

            jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
            jPanel.add(sName);
            jPanel.add(TaskNameText);
            jPanel.add(sPriority);
            jPanel.add(TaskPriorityText);
            jPanel.add(sTime);
            jPanel.add(TaskTimeText);

            extraframe.getContentPane().add(BorderLayout.NORTH,jPanel);
            extraframe.getContentPane().add(BorderLayout.SOUTH,button);
            extraframe.pack();
            extraframe.setVisible(true);

        }
    }//冒出来的任务！

    private void SHOW(String n,String p,double t){
        int size = (int) t;
        RunArea.append("taskname:"+n+","+"priority"+p+"\n");
        for(int i = size;i>0;i--) {
            try {
                RunArea.append(i+"\n");
                RunArea.setCaretPosition(RunArea.getText().length());
                //frame.repaint(frame.getDefaultCloseOperation());
                //frame.repaint();
                RunArea.paintImmediately(RunArea.getX(), RunArea.getY(), RunArea.getWidth(), RunArea.getHeight());
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//打印任务执行信息

}

