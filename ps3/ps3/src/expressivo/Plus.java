package expressivo;

import java.awt.List;
import java.util.ArrayList;

public class Plus implements Expression{

    private final Expression left;
    private final Expression right;
    public Plus(Expression lft, Expression rgh){
        left = lft;
        right= rgh;       
    }
    
   public List<Expression> factors(){
       List<Expression> fa = new ArrayList<>();
       fa.add(this);
       return fa;
   }
   
   public List<Expression> terms(){
       List<Expression> te = new ArrayList<>();
       te.addAll(this.left.terms());
       te.addAll(this.right.terms());
       return te;
   }
        
    
}
