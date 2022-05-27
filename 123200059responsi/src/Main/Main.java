/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import controller.MovieController;
import model.MovieModel;
import view.MovieView;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MovieView view = new MovieView();
        MovieModel mm = new MovieModel();
        MovieController mc = new MovieController(mm, view);
    }
    
}
