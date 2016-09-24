package hello;

/**
 * Created by Tom on 9/24/2016.
 */
public class LoyaltyNotFoundException extends RuntimeException
{
    private Long loyaltyId = null;
    private String loyaltyCode = null;
    private String divisionName = null;
    private String siteCode = null;
    private String companyName = null;
    private String jsonRPCMessageId = null;
    private String jsonRPCResponseObjectErrorMessage = "";

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

}
