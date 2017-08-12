/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   graph that contains vertices and edges that connect these vertices
    // Representation invariant:
    //   Edges weight >0
    //     no edges with non existing vertices
    //     no multiple edges
    // Safety from rep exposure:
    //   private fields, elements L are immutable
    
    // TODO constructor
    /*
     * makes a new ConcreteEdgesGraph with edges weight >0 and no multiple edges
     */
    public ConcreteEdgesGraph(Set<L> vert, List<Edge<L>> edgs){
        this.vertices.addAll(vert);
        this.edges.addAll(edgs);
        checkRep();
    }
    // TODO checkRep
    /*
     * check if rep invariant is true
     */
    private void checkRep(){
       for (Edge<L> edge: edges){
           assert edge.getWeight() > 0;
           assert vertices.contains(edge.getFrom());
           assert vertices.contains(edge.getTo());
           for(Edge<L> edge1 : edges){
               if(edge != edge1){
                   boolean fromDiff = !edge.getFrom().equals(edge1.getFrom());
                   boolean toDiff = !edge.getTo().equals(edge1.getTo());
                   assert(fromDiff || toDiff);
               }
           }
       }
        
    }
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
        if(vertices.contains(vertex))
            return false;
        else{
            vertices.add(vertex);
            return true;
        }
    }
    
    @Override public int set(L source, L target, int weight) {
       /* if(weight <1)
            throw new RuntimeException("weight is less than 1");*/
        Edge<L> edge = null;
        int oldWeight = 0;
        for (Edge<L> dd : edges){
            if(dd.getFrom().equals(source) && dd.getTo().equals(target)){
               // System.out.println(edges.size());
                edge = dd;
                oldWeight = edge.getWeight();
                edges.remove(dd);
                break;
            }
        }
        if (weight > 0){
            vertices.add(source);
            vertices.add(target);
            Edge<L> newEdge = new Edge<>(source, target, weight);            
            edges.add(newEdge);
        }
        checkRep();
        return oldWeight;
    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
        boolean hadIt = false;
        if(vertices.contains(vertex)){
            vertices.remove(vertex);
            hadIt = true;
        }
        return hadIt;
    }
    
    @Override public Set<L> vertices() {
       // throw new RuntimeException("not implemented");
       // Set<String> ver = new HashSet<>(this.vertices());
        //return ver;
        return new HashSet<L>(this.vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
        Map<L, Integer> sources = new HashMap<>();
        for(Edge<L> edge : this.edges){

            if (edge.getTo().equals(target)){
                sources.put(edge.getFrom(), edge.getWeight());   
            }
        }
        return sources;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        ///throw new RuntimeException("not implemented");
        Map<L, Integer> targets = new HashMap<>();
        for(Edge<L> edge : this.edges){
            if(edge.getFrom().equals(source)){
                targets.put(edge.getTo(), edge.getWeight());
            }
        }
        return targets;
    }
    
    // TODO toString()
    @Override
    public String toString(){
        
        String s = edges.size() +" edges" + "\n";
        for(Edge<L> edge : edges){
            s+= edge.toString()+"\n";
        }
        String vert = vertices.size() + " vertices"+"\n";
        if(this.vertices.size()>0)
             vert += this.vertices.toString();
        
        return s + vert;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    //Fields:
    // source: label of the source vertex
    // dest : label of the destination vertex
    // weiht of the edge
    
    // Abstraction function:
    //   represents edge, visually ends are source and dest with weight
    // Representation invariant:
    //   src always points to dest with weight  >0
    // Safety from rep exposure:
    //   types that edge is made of are private immutable
    //   no mutable types are returned nor used as parameters
    private L source;
    private L dest;
    private int weight;
    // TODO constructor
    /*
     * return a new instance of edge with given source and destination vertex, and weight
     */
    public Edge(L src, L dst, int wgt){
        this.source = src;
        this.dest = dst;
        this.weight = wgt;
    }
    
    // TODO checkRep
    
    // TODO methods
    /*
     * returns source vertex of this edge
     */
    public L getFrom(){
        return this.source;
    }
    /*
     * returns destination vertex of this edge
     */
    public L getTo(){
        return this.dest;
    }
    /*
     * returns weight of this edge
     */
    public int getWeight(){
        return this.weight;
    }
    // TODO toString()
    @Override
    public String toString(){
        return this.getFrom()+"-"+this.getWeight()+"-"+this.getTo();
    }
    
    
}

