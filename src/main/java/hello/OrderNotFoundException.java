package hello;

import org.springframework.http.HttpStatus;

/**
 * Created by Tom on 9/26/2016.
 */
public class OrderNotFoundException extends RuntimeException
{
    private Long orderId;
    private String orderCode;
    private String divisionName;
    private String siteCode;
    private String companyName;
    private String jsonRPCMessageId;
    private String jsonRPCResponseObjectErrorMessage = "";

    public OrderNotFoundException(HoldPointsRequest holdPointsRequest)
    {
        orderId = holdPointsRequest.getOrderId();
        orderCode = holdPointsRequest.getOrderCode();
        divisionName = holdPointsRequest.getOrderDivisionName();
        companyName = holdPointsRequest.getOrderCompanyName();
        siteCode = holdPointsRequest.getOrderSiteCode();
    }

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

    public String getDivisionName()
    {
        return divisionName;
    }
    public void setDivisionName(String divisionName)
    {
        this.divisionName = divisionName;
    }

    public String getSiteCode()
    {
        return siteCode;
    }
    public void setSiteCode(String siteCode)
    {
        this.siteCode = siteCode;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getJsonRPCMessageId()
    {
        return jsonRPCMessageId;
    }
    public void setJsonRPCMessageId(String jsonRPCMessageId)
    {
        this.jsonRPCMessageId = jsonRPCMessageId;
    }

    public String getJsonRPCResponseObjectErrorMessage()
    {
        return jsonRPCResponseObjectErrorMessage;
    }
    public void setJsonRPCResponseObjectErrorMessage(String jsonRPCResponseObjectErrorMessage)
    {
        this.jsonRPCResponseObjectErrorMessage = jsonRPCResponseObjectErrorMessage;
    }

    public int getJsonRPCResponseObjectErrorCode()
    {
        return -32602;
    }

    public JSONRPCResponseObject toJSONRPCResponseObject()
    {
        JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
        responseObject.setSuccess(false);
        responseObject.setId(getJsonRPCMessageId());
        responseObject.getError().setCode(getJsonRPCResponseObjectErrorCode());
        responseObject.getError().setMessage(getJsonRPCResponseObjectErrorMessage());
        return responseObject;
    }

    public RESTAPIError toRESTAPIError()
    {
        String errorString = "Order not found for " +
                (getOrderId() != null ? ("orderId = " + getOrderId() + ", ") : "") +
                (getOrderCode() != null ? ("orderCode = " + getOrderCode() + ", ") : "") +
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
}
