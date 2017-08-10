/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   represents vertices of a graph
    // Representation invariant:
    //   no same vertices, 
    // Safety from rep exposure:
    //   internal field is private, String type is immutable,mutables are
    //returned as copies
    
    // TODO constructor
    public ConcreteVerticesGraph(List<Vertex> vr){
        this.vertices.addAll(vr);
        checkRep();
    }
    // TODO checkRep
    private void checkRep(){
        for(Vertex v : vertices){
            assert(Collections.frequency(vertices, v) < 2);
        }
    }
    @Override public boolean add(String vertex) {
       // throw new RuntimeException("not implemented");
        Vertex v = new Vertex(vertex);
        if(this.vertices.contains(v))
            return false;
        this.vertices.add(v);
        checkRep();
        return true;
    }
    
    @Override public int set(String source, String target, int weight) {
        throw new RuntimeException("not implemented");
        
    }
    
    @Override public boolean remove(String vertex) {
        //throw new RuntimeException("not implemented");
        if (vertices.contains(vertex)){
            vertices.remove(vertex);
            return true;
        }
        return false;    
    }
    
    @Override public Set<String> vertices() {
       // throw new RuntimeException("not implemented");
        Set<String> vcCopies = new HashSet<>();
        for (Vertex v : this.vertices)
            vcCopies.add(v.getLabel());
        return vcCopies;
    }
        
    @Override public Map<String, Integer> sources(String target) {
        //throw new RuntimeException("not implemented");
        Map<String, Integer> src = new HashMap<>();
        for (Vertex v : this.vertices){
            if (v.getLabel() == target)
                for(String s: v.getSources().keySet())
                    src.put(s, v.getSources().get(s));
        }
        return src;
    }
    
    @Override public Map<String, Integer> targets(String source) {
       // throw new RuntimeException("not implemented");
        Map<String, Integer> targ = new HashMap<>();
        for (Vertex v : this.vertices){
            if (v.getLabel() == source)
                for(String s: v.getTargets().keySet())
                    targ.put(s, v.getTargets().get(s));
        }
        return targ;
    }
    
    // TODO toString()
    @Override
    public String toString(){
        String s = this.vertices.size()+" vertices" + "\n";
        for (Vertex x : this.vertices){
            s += x.toString()+"\n";
        }
        return s;
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // TODO fields
    private String label;
    private Map<String, Integer> in = new HashMap<>();
    private Map<String, Integer> out = new HashMap<>();
    
    // Abstraction function:
    //   represents a vertex of a graph
    // Representation invariant:
    //   TODO
   // label is a string that is the name of a vertex and len>0
    //no multiple edges with same src and targer
    // Safety from rep exposure:
    //   TODO
    //returns string that is imutable, field is private,and returns copies
    //of mutable objects
    
    // TODO constructor
    public Vertex(String lbl){
        this.label = lbl;
        checkRep();
    }
    // TODO checkRep
    private void checkRep(){
        assert this.label.length() > 0;
        for (String inn : this.in.keySet())
            assert(this.in.get(inn) > 0);
        for (String outt : this.out.keySet())
            assert(this.out.get(outt)>0);
    }
    // TODO methods
    public String getLabel(){
        return this.label;
    }
    public Map<String, Integer> getSources(){
        HashMap<String, Integer> srcCopy = new HashMap<>();
        for(String key : this.in.keySet())
            srcCopy.put(key, this.in.get(key));
        return srcCopy;
    }
    public Map<String, Integer> getTargets(){
        HashMap<String, Integer> outCopy = new HashMap<>();
        for(String key : this.out.keySet())
            outCopy.put(key, this.out.get(key));
        return outCopy;
    }
    public void changeLabel(String lbl){
        this.label = lbl;
        checkRep();
    }
    public void addSource(String vertex, int weight){
        this.in.put(vertex, weight);
    }
    public void addTarget(String vertex, int weight){
        this.out.put(vertex, weight);
    }
    // TODO toString()
    @Override
    public String toString(){
        String a = "";
        for(String s : this.in.keySet()){
            a+= s + " -"+ in.get(s)+" -"+ this.label + "\n";
        }
        for(String s : this.out.keySet()){
            a += this.label+" -"+out.get(s)+"- "+s+"\n";
        }
        return a;
    }
    
}
