package in.dreamnation.salesgun.dbmodels;


import java.util.Date;


public class BrandModel {

    public int BrandId, BrandStatus;
    public String BrandName, BrandTagLine, BrandDescription;
    public String BrandImage;
    public Date BrandCreatedDate, BrandModifiedDate;

    private int UserBrandCredits;
    private Date UserBrandStartDate, UserBrandEndDate;


    public BrandModel(){}

    public BrandModel(int brandId, String brandName, String brandTagLine, String brandDescription, int brandStatus, String brandImage, Date brandCreatedDate, Date brandModifiedDate)
    {
        BrandId = brandId;
        BrandName = brandName;
        BrandTagLine = brandTagLine;
        BrandDescription = brandDescription;
        BrandImage = brandImage;
        BrandStatus = brandStatus;
        BrandCreatedDate = brandCreatedDate;
        BrandModifiedDate = brandModifiedDate;
    }

    public int getBrandId()
    {
        return BrandId;
    }

    public void setBrandId(int brandId)
    {
        BrandId = brandId;
    }

    public String getBrandName()
    {
        return BrandName;
    }

    public void setBrandName(String brandName)
    {
        BrandName = brandName;
    }

    public String getBrandTagLine()
    {
        return BrandTagLine;
    }

    public void setBrandTagLine(String brandTagLine)
    {
        BrandTagLine = brandTagLine;
    }

    public String getBrandDescription()
    {
        return BrandDescription;
    }

    public void setBrandDescription(String brandDescription)
    {
        BrandDescription = brandDescription;
    }

    public int getBrandStatus()
    {
        return BrandStatus;
    }

    public void setBrandStatus(int brandStatus)
    {
        BrandStatus = brandStatus;
    }

    public String getBrandImage()
    {
        return BrandImage;
    }

    public void setBrandImage(String brandImage)
    {
        BrandImage = brandImage;
    }

    public Date getBrandCreatedOn()
    {
        return BrandCreatedDate;
    }

    public void setBrandCreatedDate(Date brandCreatedDate)
    {
        BrandCreatedDate = brandCreatedDate;
    }

    public Date getBrandModifiedDate()
    {
        return BrandModifiedDate;
    }

    public void setBrandModifiedDate(Date brandModifiedDate)
    {
        BrandModifiedDate = brandModifiedDate;
    }

    public int getUserBrandCredits()
    {
        return UserBrandCredits;
    }

    public void setUserBrandCredits(int userBrandCredits)
    {
        UserBrandCredits = userBrandCredits;
    }

    public Date getUserBrandStartDate()
    {
        return UserBrandStartDate;
    }

    public void setUserBrandStartDate(Date userBrandStartDate)
    {
        UserBrandStartDate = userBrandStartDate;
    }

    public Date getUserBrandEndDate()
    {
        return UserBrandEndDate;
    }

    public void setUserBrandEndDate(Date userBrandEndDate)
    {
        UserBrandEndDate = userBrandEndDate;
    }

}
