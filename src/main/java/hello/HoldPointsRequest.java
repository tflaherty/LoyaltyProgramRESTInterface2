package hello;

import java.math.BigDecimal;

/**
 * Created by Tom on 9/15/2016.
 */
public class HoldPointsRequest extends PointsRequest
{
    private Long orderId;

    private String orderCode;
    private String orderDivisionName;
    private String orderCompanyName;

    private BigDecimal dollarValue;

    public Long getOrderId()
    {
        return orderId;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderCode()
    {
        return orderCode;
    }
    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderDivisionName()
    {
        return orderDivisionName;
    }
    public void setOrderDivisionName(String orderDivisionName)
    {
        this.orderDivisionName = orderDivisionName;
    }

    public String getOrderCompanyName()
    {
        return orderCompanyName;
    }
    public void setOrderCompanyName(String orderCompanyName)
    {
        this.orderCompanyName = orderCompanyName;
    }

    public BigDecimal getDollarValue()
    {
        return dollarValue;
    }
    public void setDollarValue(BigDecimal dollarValue)
    {
        this.dollarValue = dollarValue;
    }

}
