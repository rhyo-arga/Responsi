/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.MovieModel;
import controller.MovieController;
import view.MovieView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ASUS
 */
public class MovieController {
    MovieModel model;
    MovieView view;
    String data;
    
    public MovieController(MovieModel model,MovieView view) {
        this.model = model;
        this.view = view;
        
        if (model.getData()!=0) {
            String dataMovie[][] = model.readMovie();
            view.table.setModel((new JTable(dataMovie, view.columnName)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "No Data");
        }
        
        view.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String title = view.getJudul();
                double plot = Double.parseDouble(view.getAlur());
                double character = Double.parseDouble(view.getPenokohan());
                double acting = Double.parseDouble(view.getAkting());
                model.createMovie(title, plot, character, acting);
         
                String dataMovie[][] = model.readMovie();
                view.table.setModel((new JTable(dataMovie, view.columnName)).getModel());
            }
        });
        
        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String title = view.getJudul();
                double plot = Double.parseDouble(view.getAlur());
                double character = Double.parseDouble(view.getPenokohan());
                double acting = Double.parseDouble(view.getAkting());
                model.updateMovie(title, plot, character, acting);
                
                String dataMovie[][] = model.readMovie();
                view.table.setModel((new JTable(dataMovie, view.columnName)).getModel());
            }
        });
        
  
        
        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               int input = JOptionPane.showConfirmDialog(null,
                "Doyou want to delete the film ?", "Select Option...", JOptionPane.YES_NO_OPTION);

            if (input == 0) {
                model.deleteMovie(data);
                String dataMovie[][] = model.readMovie();
                view.table.setModel((new JTable(dataMovie, view.columnName)).getModel());
            } else {
                JOptionPane.showMessageDialog(null, "Cancel Deleting");
            }
                }
        });
        
        view.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                view.tfTitle.setText("");
                view.tfPlot.setText("");
                view.tfChararter.setText("");
                view.tfActing.setText("");
            }
        });
        
    
          view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                
                int row = view.table.getSelectedRow();
                data = view.table.getValueAt(row, 0).toString();
                String dataUpdate[] = new String[4];
                dataUpdate[0] = view.table.getValueAt(row, 0).toString();
                dataUpdate[1] = view.table.getValueAt(row, 1).toString();
                
                
                System.out.println(data);
                 
                
                
            }
           });
    }
}
