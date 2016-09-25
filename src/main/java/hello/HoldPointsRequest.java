package hello;

import java.math.BigDecimal;

/**
 * Created by Tom on 9/15/2016.
 */
public class HoldPointsRequest
{
    private Long orderId;

    private String orderCode;
    private String orderDivisionName;
    private String orderCompanyName;

    private String customerCode;
    private String customerDivisionName;
    private String customerCompanyName;

    private Long loyaltyId;

    private String loyaltyCode;
    private String loyaltyDivisionName;
    private String loyaltyCompanyName;

    private Integer points;

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

    public Long getLoyaltyId()
    {
        return loyaltyId;
    }
    public void setLoyaltyId(Long loyaltyId)
    {
        this.loyaltyId = loyaltyId;
    }

    public String getLoyaltyCode()
    {
        return loyaltyCode;
    }
    public void setLoyaltyCode(String loyaltyCode)
    {
        this.loyaltyCode = loyaltyCode;
    }

    public String getLoyaltyDivisionName()
    {
        return loyaltyDivisionName;
    }
    public void setLoyaltyDivisionName(String loyaltyDivisionName)
    {
        this.loyaltyDivisionName = loyaltyDivisionName;
    }

    public String getLoyaltyCompanyName()
    {
        return loyaltyCompanyName;
    }
    public void setLoyaltyCompanyName(String loyaltyCompanyName)
    {
        this.loyaltyCompanyName = loyaltyCompanyName;
    }

    public Integer getPoints()
    {
        return points;
    }
    public void setPoints(Integer points)
    {
        this.points = points;
    }

    public BigDecimal getDollarValue()
    {
        return dollarValue;
    }
    public void setDollarValue(BigDecimal dollarValue)
    {
        this.dollarValue = dollarValue;
    }

    public String getCustomerCode()
    {
        return customerCode;
    }
    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getCustomerDivisionName()
    {
        return customerDivisionName;
    }
    public void setCustomerDivisionName(String customerDivisionName)
    {
        this.customerDivisionName = customerDivisionName;
    }

    public String getCustomerCompanyName()
    {
        return customerCompanyName;
    }
    public void setCustomerCompanyName(String customerCompanyName)
    {
        this.customerCompanyName = customerCompanyName;
    }
}
