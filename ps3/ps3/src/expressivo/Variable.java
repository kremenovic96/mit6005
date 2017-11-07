package expressivo;

import java.awt.List;
import java.util.ArrayList;

public class Variable implements Expression {
    /*
     * Abstraction function
     *      represents a variable with name in string var
     * rep invariant
     *      var consist of letters
     * Safety from rep exposure
     *      fields private and final          
     */
    private final String var;
    public Variable(String v){
        var = v;
        checkRep();
    }
    
    private void checkRep(){
        assert var.matches("[a-zA-Z]+");
    }
    
    public String toString(){
        return var;
    }
    
    public boolean equals(Object other){
        if(!(other instanceof Variable)) return false;
        Variable otherVar = (Variable) other;
        return otherVar.var.equals(var);
    }
    
    public List<Expression> terms(){
        List<Expression> te = new ArrayList<>();
        te.add(this);
        return te;
    }
    
    
}
