package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {

    @Override
    public Human getPerson(int age) {
        Human person;

        if (age <= KidGirl.MAX_AGE) {
            person = new KidGirl();
        } else if (age <= TeenGirl.MAX_AGE) {
            person = new TeenGirl();
        } else {
            person = new Woman();
        }
        return person;
    }

}
