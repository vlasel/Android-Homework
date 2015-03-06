package calc.vlas.htp.by.calculator.operations;

/**
 * Created by VlasEL on 01.03.2015 13:45
 */
public interface Operation {

    void execute() throws OperationException;

    Double getResult();

    String getError();

    String getName();

    String prettyPrint();
}
