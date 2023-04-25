package com.giftgalaxy.Model.Relationship;

public abstract class Relation {
    private long relationID;
    private String relationType;

    public long getRelationID() {
        return relationID;
    }

    public void setRelationID(long relationID) {
        this.relationID = relationID;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public Relation(long relationID, String relationType){
        this.relationID = relationID;
        this.relationType = relationType;
    }


}
