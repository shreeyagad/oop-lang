/**
 * Represents an Attribute
 * @param identifiers The list of identifiers that ultimately point to the attribute of interest
 */
import java.util.Iterator;
import java.util.LinkedList;

public class Attribute extends Expression {
    LinkedList<String> identifiers;

    public Attribute(LinkedList<String> identifiers) {
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