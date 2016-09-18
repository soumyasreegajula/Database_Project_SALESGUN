package in.dreamnation.salesgun.dbmodels;


public class UserModel {
    public int UserId;
    public String UserName, UniqueId, Phone, Email;
    public byte[] Image;

    public UserModel(){}

    public UserModel(int userId, String userName, String uniqueId, String phone, String email, byte[] image)
    {
        UserId = userId;
        UserName = userName;
        UniqueId = uniqueId;
        Phone = phone;
        Email = email;
        Image = image;
    }

    public int getUserId()
    {
        return UserId;
    }
    public void setUserId(int userId)
    {
        UserId = userId;
    }

    public String getUserName()
    {
        return UserName;
    }
    public void setUserName(String userName)
    {
        UserName = userName;
    }

    public String getUniqueId()
    {
        return UniqueId;
    }
    public void setUniqueId(String uniqueId)
    {
        UniqueId = uniqueId;
    }

    public String getPhone()
    {
        return Phone;
    }
    public void setPhone(String phone)
    {
        Phone = phone;
    }

    public String getEmail()
    {
        return Email;
    }
    public void setEmail(String email)
    {
        Email = email;
    }

    public byte[] getImage()
    {
        return Image;
    }
    public void setImage(byte[] image)
    {
        Image = image;
    }
}
