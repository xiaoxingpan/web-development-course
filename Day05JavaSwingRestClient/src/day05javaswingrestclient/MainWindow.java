/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package day05javaswingrestclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
// import javax.swing.ListModel;

/**
 *
 * @author xiaoxingpan
 */
public class MainWindow extends javax.swing.JFrame {
    
    DefaultListModel<String> modelTodoList;
    
    ApiTodos apiTodos = new ApiTodos();
    // used to defind add or save
    private int currId = 0;
    

    public int getCurrId() {
        return currId;
    }

    public void setCurrId(int currId) {
        this.currId = currId;
    }
    
    
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        modelTodoList = new DefaultListModel<>();
        
        try {
            ArrayList<Todo> list = apiTodos.getAllTodos();
            for (Todo todo : list) {
                modelTodoList.addElement(todo.toString());
            }
            initComponents();
            
        } catch (ApiErrorException ex) {
            JOptionPane.showMessageDialog(null, "Fatal error: " + ex.getMessage());
            System.exit(1); // terminate abruptly
        }
    }
        
    public  void refreshList(){
        try {
                modelTodoList.clear();
            ArrayList<Todo> list = apiTodos.getAllTodos();
            for (Todo todo : list) {
                modelTodoList.addElement(todo.toString());
            }
            
            lstTodos.repaint();
            
        } catch (ApiErrorException ex) {
            JOptionPane.showMessageDialog(null, "Fatal error: " + ex.getMessage());
            System.exit(1); // terminate abruptly
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstTodos = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        tfTask = new javax.swing.JTextField();
        tfDueDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxDone = new javax.swing.JCheckBox();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lstTodos.setModel(modelTodoList);
        lstTodos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstTodosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstTodos);

        jLabel1.setText("Id:");

        jLabel2.setText("Task:");

        jLabel3.setText("Due date:");

        lblId.setText("-");

        tfTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTaskActionPerformed(evt);
            }
        });

        jLabel5.setText("YYYY-MM-DD");

        cbxDone.setText("Done");
        cbxDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDoneActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        jButton1.setText("Sort by Id");
        jButton1.setToolTipText("");

        jButton2.setText("Sort by Task");
        jButton2.setToolTipText("");

        jButton3.setText("Sort by DueDate");
        jButton3.setToolTipText("");

        jButton4.setText("Sort by Status");
        jButton4.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxDone)
                                    .addComponent(tfTask, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnUpdate)
                                            .addComponent(tfDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(71, 71, 71)
                                                .addComponent(btnDelete))))))
                            .addComponent(btnAdd)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblId))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(cbxDone)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnDelete)
                            .addComponent(btnUpdate))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDoneActionPerformed

    private void tfTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTaskActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_tfTaskActionPerformed

    private void lstTodosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstTodosValueChanged
        // TODO add your handling code here:
        
        //extract item id
        String input = lstTodos.getSelectedValue();
        String[] parts;
        if(input !=null){
            parts = input.split(":");
            
            if (parts.length > 0) {
             String numberString = parts[0].trim();
            try {
                currId = Integer.parseInt(numberString);
                // System.out.println("Extracted number: " + currId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
            } else {
                System.out.println("No colon found in the input");
            }       

            try {
              selectItem(currId);
            } catch (ApiErrorException e) {
                    System.out.println("Selected list item failed");
                   // return;
            }
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
        } else {
            lblId.setText("-");
            tfTask.setText("");
            tfDueDate.setText("");
            cbxDone.setSelected(false);
            btnAdd.setEnabled(true);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
        }      
   
    }//GEN-LAST:event_lstTodosValueChanged
    
    public void selectItem(int id) throws ApiErrorException {
        try {
            ArrayList<Todo> list = apiTodos.getAllTodos();
                        
            Optional<Todo> res = list.stream().filter(todo -> todo.id == id ).findFirst();                            
            
            if (res.isPresent()) {
                System.out.println(res);
                Todo todo = res.get();
            
                lblId.setText(String.valueOf(todo.getId()));
                tfTask.setText(todo.getTask());
                //tfDueDate.setText(todo.getDueDate().toString());
                
                String dateString = todo.getDueDate().toString(); // Get the date string
                SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); // Input date format
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Desired output date format

        try {
            Date date = inputFormat.parse(dateString); // Parse the input date string
            String formattedDate = outputFormat.format(date); // Format the date to desired format
            tfDueDate.setText(formattedDate);
            System.out.println(formattedDate); // Output: "YYYY-MM-DD"
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
                
                
                cbxDone.setSelected(todo.getIsDone() == Todo.Status.Done);
            } 
        } catch (ApiErrorException e) {
            throw e;
        }  
    }
    
    
    
    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
       
        String task = tfTask.getText();
        Date dueDate = null;
                
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); //lenient tries to interpret and adjust the given date string if it is invalid. 
        
        try {
            dueDate = dateFormat.parse(tfDueDate.getText());
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            return;
        }
        
        Todo.Status isDone = cbxDone.isSelected() ? Todo.Status.Done : Todo.Status.Pending;
        

        
        // validate the client's input         
        if (task == null || task.isEmpty() || task.length() < 1 || task.length() > 100) {
            System.out.println("MW: Task must be entered and between 1 and 100 characters long");
            return;
        }
        
        // check if task exist already in db
        try {
            ArrayList<Todo> list = apiTodos.getAllTodos();
            
            Optional<Todo> res = list.stream().filter(todo -> todo.task.equalsIgnoreCase(task)).findFirst();
            
            
                if(!res.isEmpty() ){
                    System.out.println("MW: Task exists!Please update it!");
                    return;
                }
                        
        } catch (ApiErrorException ex) {
            System.out.println("MW: Error retrieving todos:  " + ex.getMessage());
        }       
        
        try {
            dateFormat.parse(dateFormat.format(dueDate));
        } catch (Exception ex) {
            System.out.println("MW: Due date must be a valid date in the format yyyy-MM-dd");
            return;
        }
        
        int year = Integer.parseInt(dateFormat.format(dueDate).substring(0, 4));
        if (year < 1900 || year > 2099) {
            System.out.println("MW: Due date year must be between 1900 and 2099");
            return;
        }

        if (isDone != Todo.Status.Pending && isDone!= Todo.Status.Done) {
            System.out.println("MW: isDone must be either \"Pending\" or \"Done\"");
            return;
        }
        
        Todo todo = new Todo();
        todo.task = task;
        todo.dueDate = dueDate;
        todo.isDone = isDone;
        
            try {
                long id = apiTodos.addNew(todo); // Send the Todo object to the API
                // currId = Integer.parseInt( lblId.getText() );
                System.out.println("MW: New Todo added with ID: " + id);
            } catch (ApiErrorException ex) {
                System.out.println("MW: error while adding a new Todo: " + ex.getMessage());
            }       
            refreshList();
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        
        String task = tfTask.getText();
        Date dueDate = null;
                
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dueDate = dateFormat.parse(tfDueDate.getText());
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            return;
        }
        
        Todo.Status isDone = cbxDone.isSelected() ? Todo.Status.Done : Todo.Status.Pending;
        

        
        // validate the client's input         
        if (task == null || task.isEmpty() || task.length() < 1 || task.length() > 100) {
            System.out.println("MW: Task must be entered and between 1 and 100 characters long");
            return;
        }
                
        try {
            dateFormat.parse(dateFormat.format(dueDate));
        } catch (Exception ex) {
            System.out.println("MW: Due date must be a valid date in the format yyyy-MM-dd");
            return;
        }
        
        int year = Integer.parseInt(dateFormat.format(dueDate).substring(0, 4));
        if (year < 1900 || year > 2099) {
            System.out.println("MW: Due date year must be between 1900 and 2099");
            return;
        }

        if (isDone != Todo.Status.Pending && isDone!= Todo.Status.Done) {
            System.out.println("MW: isDone must be either \"Pending\" or \"Done\"");
            return;
        }
        
        Todo todo = new Todo();
        todo.id = getCurrId();
        todo.task = task;
        todo.dueDate = dueDate;
        todo.isDone = isDone;
        
        try {
            boolean res = apiTodos.update(todo); // Send the Todo object to the API
            // currId = Integer.parseInt( lblId.getText() );
            if(res){
                    System.out.println("MW: Current record is updated ");
            }else{
                    System.out.println("MW: Update Current record failed");
            }
                
            } catch (ApiErrorException ex) {
                System.out.println("MW: error while update a new Todo: " + ex.getMessage());
            }

            refreshList();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        String input = lstTodos.getSelectedValue();
        int flag = -1;
        if(input !=null){
            flag = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Warning", 2);
            
            if (flag != 0) {
                return;
            } 
            
            try {
            boolean res = apiTodos.delete(getCurrId()); 
            if(res){
                    System.out.println("MW: Current record is deleted ");
            }else{
                    System.out.println("MW: deleted Current record failed");
            }
                
            } catch (ApiErrorException ex) {
                System.out.println("MW: error while delete a new Todo: " + ex.getMessage());
            }
            
            
        } else { 
             JOptionPane.showMessageDialog(null,"Please select a item.");            
        }

               refreshList();
    }//GEN-LAST:event_btnDeleteMouseClicked
    
     
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox cbxDone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblId;
    private javax.swing.JList<String> lstTodos;
    private javax.swing.JTextField tfDueDate;
    private javax.swing.JTextField tfTask;
    // End of variables declaration//GEN-END:variables
}
