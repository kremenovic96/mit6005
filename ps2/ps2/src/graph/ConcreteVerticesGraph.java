/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   represents vertices of a graph
    // Representation invariant:
    //   no same vertices, 
    // Safety from rep exposure:
    //   internal field is private, String type is immutable,mutables are
    //returned as copies
    
    // TODO constructor
    public ConcreteVerticesGraph(List<Vertex<L>> vr){
        this.vertices.addAll(vr);
        checkRep();
    }
    // TODO checkRep
    private void checkRep(){
        for(Vertex v : vertices){
            assert(Collections.frequency(vertices, v) < 2);
        }
    }
    @Override public boolean add(L vertex) {
       // throw new RuntimeException("not implemented");
        Vertex<L> v = new Vertex<>(vertex);
        if(this.vertices.contains(v))
            return false;
        this.vertices.add(v);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
        int val = 0;
        boolean haveSrc = false;
        boolean haveTr = false;
        for(Vertex<L> v : this.vertices){
            if (v.getLabel().equals(source))
                haveSrc = true;
            if (v.getLabel().equals(target))
                haveTr = true;
        }
        if (haveSrc && haveTr){
            for (Vertex<L> v : this.vertices){
                if (v.getLabel().equals(source)){
                    if(v.getTargets().containsKey(target))
                        val = v.getTargets().get(target);                   
                    
                    v.addTarget(target, weight);                    
                }
                if (v.getLabel().equals(target)){
                    v.addSource(source, weight);
                    
                    
                }
            }
        }
        else if (haveSrc && !haveTr){
            for (Vertex<L> v : this.vertices)
                if (v.getLabel().equals(source)){
                    Vertex<L> newVertex = new Vertex<>(target);
                    newVertex.addSource(source, weight);
                    this.vertices.add(newVertex);
                    v.addTarget(target, weight);                   
                    val = 0;
                    break;
                }
        }
        else if (!haveSrc && haveTr){
            for (Vertex<L> v : this.vertices)
                if (v.getLabel().equals(target)){
                    Vertex<L> newVertex = new Vertex<>(source);
                    newVertex.addTarget(target, weight);//this is added
                    this.vertices.add(newVertex);
                    v.addSource(source, weight);
                    val = 0;
                    break;
                }
        }
        else{
            Vertex<L> newSource = new Vertex<>(source);
            Vertex<L> newTarget = new Vertex<>(target);
            this.vertices.add(newSource);
            this.vertices.add(newTarget);
            newSource.addTarget(target, weight);
            newTarget.addSource(source, weight);
            val = 0;
        }
        checkRep();
        return val;
    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
        //ovo srediti, nije vertex nego vertex.label zapravo.. vidjeti kako        
        boolean hadIt = false;
        Iterator<Vertex<L>> iter = this.vertices.iterator();
        while(iter.hasNext()){
            Vertex<L> v = iter.next();
            if (v.getLabel().equals(vertex)){
                iter.remove();
                hadIt = true;
                break;
            }
        }
       
        return hadIt;
    }
    
    @Override public Set<L> vertices() {
       // throw new RuntimeException("not implemented");
        Set<L> vcCopies = new HashSet<>();
        for (Vertex<L> v : this.vertices)
            vcCopies.add(v.getLabel());
        return vcCopies;
    }
        
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
        Map<L, Integer> src = new HashMap<>();
        for (Vertex<L> v : this.vertices){
            if (v.getLabel() == target)
                for(L s: v.getSources().keySet())
                    src.put(s, v.getSources().get(s));
        }
        return src;
    }
    
    @Override public Map<L, Integer> targets(L source) {
       // throw new RuntimeException("not implemented");
        Map<L, Integer> targ = new HashMap<>();
        for (Vertex<L> v : this.vertices){
            if (v.getLabel() == source)
                for(L s: v.getTargets().keySet())
                    targ.put(s, v.getTargets().get(s));
        }
        return targ;
    }
    
    // TODO toString()
    @Override
    public String toString(){
        String s = this.vertices.size()+" vertices" + "\n";
        //s += this.vertices.toString();
        for (Vertex<L> x : this.vertices){
            if(x.getSources().isEmpty() && x.getTargets().isEmpty())
                s += x.getLabel()+ " ";            
        }
        if (s.length() != 0)
            s +="\n";
        for (Vertex<L> x : this.vertices){
            if(!x.getSources().isEmpty())
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
class Vertex<L> {
    
    // TODO fields
    private L label;
    private Map<L, Integer> in = new HashMap<>();
    private Map<L, Integer> out = new HashMap<>();
    
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
    public Vertex(L lbl){
        this.label = lbl;
        checkRep();
    }
    // TODO checkRep
    private void checkRep(){
        for (L inn : this.in.keySet())
            assert(this.in.get(inn) > 0);
        for (L outt : this.out.keySet())
            assert(this.out.get(outt)>0);
    }
    // TODO methods
    public L getLabel(){
        return this.label;
    }
    public Map<L, Integer> getSources(){
        HashMap<L, Integer> srcCopy = new HashMap<>();
        for(L key : this.in.keySet())
            srcCopy.put(key, this.in.get(key));
        return srcCopy;
    }
    public Map<L, Integer> getTargets(){
        HashMap<L, Integer> outCopy = new HashMap<>();
        for(L key : this.out.keySet())
            outCopy.put(key, this.out.get(key));
        return outCopy;
    }
    public void changeLabel(L lbl){
        this.label = lbl;
        checkRep();
    }
    public void addSource(L vertex, int weight){
        this.in.put(vertex, weight);
    }
    public void addTarget(L vertex, int weight){
        this.out.put(vertex, weight);
    }
    public void removeTarget(L tr){
        this.out.remove(tr);
    }
    
    public void removeSource(L src){
        this.in.remove(src);
    }
    
 
    // TODO toString()
    @Override
    public String toString(){
        String a = "";
        if(this.in.isEmpty() && this.out.isEmpty())
            return this.getLabel().toString();
        for(L s : this.in.keySet()){
            a+= s + "-"+ in.get(s)+"-"+ this.label+"\n";
        }
        /*for(String s : this.out.keySet()){
            a += this.label+" -"+out.get(s)+"- "+s;
        }*/
        return a;
    }
    
}
