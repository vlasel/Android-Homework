package calc.vlas.htp.by.calculator.operations;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import calc.vlas.htp.by.calculator.R;

/**
 * Created by VlasEL on 01.03.2015 13:55
 */
public class OperationDiv implements Operation {

    Context mContext;
    double mOperand1;
    double mOperand2;

    public OperationDiv(Context pContext, double pOperand1, double pOperand2) {
        this.mContext = pContext;
        this.mOperand1 = pOperand1;
        this.mOperand2 = pOperand2;
    }

    @Override
    public String getName() {
        return mContext.getString(R.string.operation_div);
    }

    public double getOperand1() {
        return mOperand1;
    }

    public double getOperand2() {
        return mOperand2;
    }

    @Override
    public double execute() throws OperationException {
        if (mOperand2 == 0) {
            throw new OperationException(mContext.getString(R.string.error_divide_to_zero));
        } else {
            return mOperand1 / mOperand2;
        }
    }

    @Override
    public String prettyPrint() {
        return mOperand1 + " / " + mOperand2;
    }

    @Override
    public String toString() {
        return prettyPrint();
    }
}
