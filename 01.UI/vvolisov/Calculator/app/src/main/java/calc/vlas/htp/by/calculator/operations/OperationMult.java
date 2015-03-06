package calc.vlas.htp.by.calculator.operations;

import android.content.Context;

import calc.vlas.htp.by.calculator.R;

/**
 * Created by VlasEL on 01.03.2015 13:55
 */
public class OperationMult extends AbstractOperation {

    public OperationMult(Context pContext, double pOperand1, double pOperand2) {
        this.mContext = pContext;
        this.mOperand1 = pOperand1;
        this.mOperand2 = pOperand2;
    }

    @Override
    public String getName() {
        return mContext.getString(R.string.operation_mult);
    }

    @Override
    public void execute() throws OperationException {
        mResult = mOperand1 * mOperand2;
    }

    @Override
    public String prettyPrint() {
        return mOperand1 + " * " + mOperand2;
    }

}
