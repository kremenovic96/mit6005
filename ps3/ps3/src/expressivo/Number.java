package expressivo;

import java.awt.List;
import java.util.ArrayList;

public class Number implements Expression {
    /*
     * Abstraction function
     *      represents positive number
     * rep invariant
     *      num>=0
     * Safety from rep exposure
     *      fields private and final          
     */
    private final double num;
    public Number(double n){
        this.num = n;
        checkRep();
    }
    
    
    @Override
    public String toString(){
        return this.num + "";
    }
    
    
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Number)) return false;
        Number otherNum = (Number) other;
        return this.num == otherNum.num;
    }
    
    private void checkRep(){
        assert this.num>=0;
    }
    
    public List<Expression> factors(){
        List<Expression> fa = new ArrayList<>();
       fa.add(this);
       return fa;
    }
    
    public List<Expression> terms(){
        List<Expression> te = new ArrayList<>();
        te.add(this);
        return te;
    }

}
