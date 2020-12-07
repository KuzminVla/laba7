package Utility;

import Stuff.LabWork;
import Stuff.LabWorkCollection;

import java.util.HashSet;

public class Collection {
    HashSet<LabWork> collection;
    public Collection() {
        collection = new HashSet<LabWork>();
        LabWorkCollection.setCollection(collection);
        System.out.println("Коллекция создана. ");
    }

}