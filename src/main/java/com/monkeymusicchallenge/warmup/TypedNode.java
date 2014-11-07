package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

/**
 * Graph node of specific type with 2D coordinates
 */
public class TypedNode {
    private int x, y;
    private boolean visited; 
    private int type;

    public TypedNode (int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.type = type;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getType() { return type; }   
    public void visit(boolean mark) { this.visited = mark; }    
    public boolean visited() { return this.visited; }
    
    public TypedNode clone() {
    	return new TypedNode(this.x, this.y, this.type);
    }

}