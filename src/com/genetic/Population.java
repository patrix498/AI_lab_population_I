/*
program nie posiada zabezpieczenia, przed brakiem możliwosci dalszego rozmnażania, by powstał idealnyn osobnik,
jedyna mozliwosc wtedy, by powstal jest poprzez dodawanie swiezych osobnikow do populacji, co jest tutaj robione w "createNewPopulation" tuz przed returnem
*/

package com.genetic;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    public static int populationSize = 10000;
    public static int dnaSize = 20;      //żeby ustawienie dna dzialalo w Osobniku, trzeba przebudoiwac jego konstruktor w dwoch miejscach
    public static int generations = 1;

    public static void main(String[] args) {
        ArrayList<Osobnik> population = new ArrayList<>();

        int sumaPrzystosowan = 0;
        for (int i = 0; i < populationSize; ++i) {
            population.add(new Osobnik());
            population.get(i).fittness();
            sumaPrzystosowan += population.get(i).przystosowanie;
        }
        while (true) {
            System.out.println("Generation: " + generations + "\nSuma przystosowań: " + sumaPrzystosowan);

            Osobnik isPerfect = findPerfect(population);
            if (isPerfect != null) {
                System.out.println("Perfect individuum: " + isPerfect + " in generation number: " + generations);
                return;
            }
            ArrayList<Osobnik> tempPopulation = population;
            population.clear();
            population = createNewPopulation(tempPopulation);
            sumaPrzystosowan = 0;
            for (int i = 0; i < population.size(); ++i){
                sumaPrzystosowan += population.get(i).przystosowanie;
            }
            ++generations;
        }

    }

    public static Osobnik findPerfect(ArrayList<Osobnik> population) {
        for (int i = 0; i < populationSize; ++i) {
            if (population.get(i).przystosowanie == dnaSize)
                return population.get(i);
        }
        return null;
    }

    public static ArrayList<Osobnik> createNewPopulation(ArrayList<Osobnik> oldPopulation) {
        ArrayList<Osobnik> newPopulation = new ArrayList<>();
        Random generator = new Random();
        while (!oldPopulation.isEmpty()) {
            int osobnikA = generator.nextInt(populationSize);
            int osobnikB = generator.nextInt(populationSize);
            while (osobnikA == osobnikB)
                osobnikB = generator.nextInt(populationSize);
            Osobnik a = oldPopulation.get(osobnikA);
            Osobnik b = oldPopulation.get(osobnikB);
            newPopulation.add(Krzyzowanie(a, b));
            oldPopulation.remove(a);
            oldPopulation.remove(b);
        }
        for (int i = newPopulation.size(); i < populationSize; ++i) {
            newPopulation.add(new Osobnik());
            newPopulation.get((i)).fittness();
        }
        return newPopulation;
    }

    public static Osobnik Krzyzowanie(Osobnik a, Osobnik b) {
        Osobnik child = new Osobnik();
        String[] dnaA = {a.dna.substring(0, dnaSize / 2), a.dna.substring(dnaSize / 2)};
        String[] dnaB = {b.dna.substring(0, dnaSize / 2), b.dna.substring(dnaSize / 2)};
        child.dna = dnaA[0] + dnaB[1];
        child.fittness();
        return child;
    }
}
