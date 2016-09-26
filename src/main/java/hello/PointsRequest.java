package hello;

import java.math.BigDecimal;

/**
 * Created by Tom on 9/26/2016.
 */
public class PointsRequest
{
    private Long customerId;

    private String customerCode;
    private String customerDivisionName;
    private String customerCompanyName;
    private String customerSiteCode;

    private Long loyaltyId;

    private String loyaltyCode;
    private String loyaltyDivisionName;
    private String loyaltyCompanyName;
    private String loyaltySiteCode;

    private Integer points;

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

    public String getLoyaltySiteCode()
    {
        return loyaltySiteCode;
    }
    public void setLoyaltySiteCode(String loyaltySiteCode)
    {
        this.loyaltySiteCode = loyaltySiteCode;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerSiteCode()
    {
        return customerSiteCode;
    }
    public void setCustomerSiteCode(String customerSiteCode)
    {
        this.customerSiteCode = customerSiteCode;
    }
}
