package com.giftgalaxy.Controller;

import com.giftgalaxy.Model.Relationship.Dad;
import com.giftgalaxy.Model.Relationship.Girlfriend;
import com.giftgalaxy.Model.Relationship.Mom;
import com.giftgalaxy.Model.Relationship.Relation;

import java.util.UUID;

public class SetUpRelation {

    public Relation setUpRelation(String type){
        Relation relation = null;
        long ID = generateLongID();

        switch (type) {
            case "Mom" -> relation = new Mom(ID);
            case "Girlfriend" -> relation = new Girlfriend(ID);
            case "Dad" -> relation = new Dad(ID);
        }

        return relation;
    }

    public static long generateLongID() {
        UUID uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits();
    }

}
