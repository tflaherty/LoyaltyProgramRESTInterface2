package hello;

import org.springframework.http.HttpStatus;

/**
 * Created by Tom on 9/24/2016.
 */
public class LoyaltyNotFoundException extends RuntimeException
{
    private Long loyaltyId;
    private String loyaltyCode;
    private String divisionName;
    private String siteCode;
    private String companyName;
    private String jsonRPCMessageId;
    private String jsonRPCResponseObjectErrorMessage = "";

    public LoyaltyNotFoundException(PointsRequest pointsRequest)
    {
        loyaltyId = pointsRequest.getLoyaltyId();
        loyaltyCode = pointsRequest.getLoyaltyCode();
        divisionName = pointsRequest.getLoyaltyDivisionName();
        companyName = pointsRequest.getLoyaltyCompanyName();
        siteCode = pointsRequest.getLoyaltySiteCode();
    }

    public LoyaltyNotFoundException(long loyaltyId, String jsonRPCMessageId)
    {
        this.loyaltyId = loyaltyId;
        this.jsonRPCMessageId = jsonRPCMessageId;
        // if I use \" to embed a double quote inside the string it outputs as \" and not "!
        // this seems to be because somewhere in the JSON encoding to a string this gets added in.  Sigh
        jsonRPCResponseObjectErrorMessage = "Could not find loyalty account for loyaltyId = " +
                "'" + loyaltyId + "'";
    }

    public LoyaltyNotFoundException(String loyaltyCode, String divisionName, String companyName, String jsonRPCMessageId)
    {
        this.loyaltyCode = loyaltyCode;
        this.divisionName = divisionName;
        this.companyName = companyName;
        this.jsonRPCMessageId = jsonRPCMessageId;
        // if I use \" to embed a double quote inside the string it outputs as \" and not "!
        // this seems to be because somewhere in the JSON encoding to a string this gets added in.  Sigh
        jsonRPCResponseObjectErrorMessage = "Could not find loyalty account for loyaltyCode = " +
                "'" + loyaltyCode + "'" +
                ", divisionName = " +
                "'" + divisionName + "'" +
                ", companyName = " +
                "'" + companyName + "'";
    }

    public LoyaltyNotFoundException(String loyaltyCode, String siteCode, String jsonRPCMessageId)
    {
        this.loyaltyCode = loyaltyCode;
        this.siteCode = siteCode;
        this.jsonRPCMessageId = jsonRPCMessageId;
        // if I use \" to embed a double quote inside the string it outputs as \" and not "!
        // this seems to be because somewhere in the JSON encoding to a string this gets added in.  Sigh
        jsonRPCResponseObjectErrorMessage = "Could not find loyalty account for loyaltyCode = " +
                "'" + loyaltyCode + "'" +
                ", siteCode = " +
                "'" + siteCode;
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
        String errorString = "Loyalty account not found for " +
                (getLoyaltyId() != null ? ("loyaltyId = " + getLoyaltyId() + ", ") : "") +
                (getLoyaltyCode() != null ? ("loyaltyCode = " + getLoyaltyCode() + ", ") : "") +
                (getCompanyName() != null ? ("loyaltyCompanyName = " + getCompanyName() + ", ") : "") +
                (getDivisionName() != null ? ("loyaltyDivisionName = " + getDivisionName() + ", ") : "") +
                (getSiteCode() != null ? ("loyaltySiteCode = " + getSiteCode()) : "");

        if (errorString.endsWith(", "))
        {
            errorString = errorString.substring(0, errorString.length() - 2);
        }

        RESTAPIError restAPIError =
                new RESTAPIError(HttpStatus.NOT_FOUND, getLocalizedMessage(), errorString);

        return restAPIError;
    }

}
