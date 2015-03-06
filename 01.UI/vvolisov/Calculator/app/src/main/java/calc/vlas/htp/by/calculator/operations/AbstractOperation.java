package calc.vlas.htp.by.calculator.operations;

import android.content.Context;

/**
 * Created by VlasEL on 06.03.2015 13:14
 */
public abstract class AbstractOperation implements Operation {

    Context mContext;
    double mOperand1;
    double mOperand2;
    Double mResult = null;
    String mError = null;

    public double getOperand1() {
        return mOperand1;
    }

    public double getOperand2() {
        return mOperand2;
    }

    @Override
    public Double getResult() {
        return mResult;
    }

    public void setResult(Double result) {
        mResult = result;
    }

    @Override
    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    @Override
    public String toString() {
        return prettyPrint();
    }

}
