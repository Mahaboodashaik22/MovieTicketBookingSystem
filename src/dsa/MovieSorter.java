package dsa;

import model.Movie;

import java.util.ArrayList;

public class MovieSorter {

    public void sortByRating(ArrayList<Movie> movies) {

        for (int i = 0; i < movies.size() - 1; i++) {

            for (int j = 0; j < movies.size() - i - 1; j++) {

                if (Double.parseDouble(movies.get(j).getRating())
                        < Double.parseDouble(movies.get(j + 1).getRating())) {

                    Movie temp = movies.get(j);
                    movies.set(j, movies.get(j + 1));
                    movies.set(j + 1, temp);
                }
            }
        }
    }
}