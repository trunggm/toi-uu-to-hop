package com.toiuutohop;

public class Population {
    Tour[] tours;

    // xây dựng population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        if (initialise)   {
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // lưu lại 1 tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // lấy 1 tour từ population
    public Tour getTour(int index) {
        return tours[index];
    }

    // lấy tour tốt nhất trong population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // vòng qua các cá thể và tìm fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // kích thước population
    public int populationSize() {
        return tours.length;
    }
}