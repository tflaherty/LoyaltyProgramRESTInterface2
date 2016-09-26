package hello;

import java.math.BigDecimal;

/**
 * Created by Tom on 9/26/2016.
 */
public class GiftPointsRequest extends PointsRequest
{
    private BigDecimal dollarValue;

    public BigDecimal getDollarValue()
    {
        return dollarValue;
    }
    public void setDollarValue(BigDecimal dollarValue)
    {
        this.dollarValue = dollarValue;
    }
}
