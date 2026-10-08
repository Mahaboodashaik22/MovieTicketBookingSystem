package controller;

import database.ShowDAO;
import model.Show;

import java.util.List;

public class ShowController {

    private final ShowDAO showDAO;

    public ShowController() {
        showDAO = new ShowDAO();
    }

    // Get all shows for a movie
    public List<Show> getShowsByMovieId(int movieId) {

        return showDAO.getShowsByMovieId(movieId);
    }

    // Add a new show
    public void addShow(Show show) {

        showDAO.addShow(show);
    }
}