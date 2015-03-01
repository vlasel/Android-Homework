package calc.vlas.htp.by.calculator.operations;

/**
 * Created by VlasEL on 01.03.2015 13:45
 */
public interface Operation {

    double execute() throws OperationException;

    String getName();

    String prettyPrint();
}
