package day05javaswingrestclient;

import java.util.Date;


public class Todo {
    
    public long id;
    public String task;
    public Date dueDate;
    public Status isDone;

    public Status getIsDone() {
        return isDone;
    }
    
   
            
    enum Status { Pending, Done};

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public Date getDueDate() {
        return dueDate;
    }

    
        
    @Override
    public String toString(){
        return String.format(
                "%d: %s due by %s (%s)", 
                id, 
                task, 
                dueDate.toString(), 
                                isDone
        );
    }
    
}

