package hello;

import org.springframework.http.HttpStatus;

/**
 * Created by Tom on 9/26/2016.
 */
public class OrderDivisionNotFoundException extends RuntimeException
{
    private String divisionName;
    private String companyName;
    private String siteCode;

    public OrderDivisionNotFoundException(HoldPointsRequest holdPointsRequest)
    {
        divisionName = holdPointsRequest.getOrderDivisionName();
        companyName = holdPointsRequest.getOrderCompanyName();
        siteCode = holdPointsRequest.getOrderSiteCode();
    }

    public RESTAPIError toRESTAPIError()
    {
        String errorString = "Order Division not found for " +
                (getCompanyName() != null ? ("orderCompanyName = " + getCompanyName() + ", ") : "") +
                (getDivisionName() != null ? ("orderDivisionName = " + getDivisionName() + ", ") : "") +
                (getSiteCode() != null ? ("orderSiteCode = " + getSiteCode()) : "");

        if (errorString.endsWith(", "))
        {
            errorString = errorString.substring(0, errorString.length() - 2);
        }

        RESTAPIError restAPIError =
                new RESTAPIError(HttpStatus.NOT_FOUND, getLocalizedMessage(), errorString);

        return restAPIError;
    }

    public String getDivisionName()
    {
        return divisionName;
    }
    public void setDivisionName(String divisionName)
    {
        this.divisionName = divisionName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getSiteCode()
    {
        return siteCode;
    }
    public void setSiteCode(String siteCode)
    {
        this.siteCode = siteCode;
    }
}
