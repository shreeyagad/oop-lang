import java.util.Iterator;
import java.util.List;

public class Attribute extends Expression {
    List<String> identifiers;


    public Attribute(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public Object eval(Environment env) {
        Iterator<String> idIterator = identifiers.iterator();
        Object nextObj = null;
        while (idIterator.hasNext()) {
            nextObj = env.getValue(idIterator.next());
            if (nextObj instanceof MyObject) {
                env = ((MyObject) nextObj).env;
            }
        }
        return nextObj;
    }
    
    public String toString() {
    	return identifiers.toString();
    }
}