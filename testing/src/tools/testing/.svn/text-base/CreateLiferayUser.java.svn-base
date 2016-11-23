package tools.testing;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateLiferayUser
{
    private static Connection con                   = null;
    private static PreparedStatement ps_create_user = null;
    private static DecimalFormat df                 = new DecimalFormat ( "0000" );


    public static void main ( String args[] ) throws Exception
    {
        Class.forName ( "com.mysql.jdbc.Driver" );
        con            = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/lportal" );
        ps_create_user = con.prepareStatement ( "INSERT INTO user_ ( userId, companyId, createDate, password_, passwordEncrypted passwordExpirationDate, passwordReset, firstName, middleName, lastName, nickName, male, birthday, emailAddress, smsId, aimId, icqId, msnId, ymId, favoriteActivity, favoriteBibleVerse, favoriteFood, favoriteMovie, favoriteMusic, languageId, timeZoneId, themeId, colorSchemeId, greeting, resolution, comments, loginDate, loginIP, lastLoginDate, lastLoginIP, failedLoginAttempts, agreedToTermsOfUse, active_ ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
            
    }

    private static void createLiferayUser ( String username, String password ) throws Exception
    {
        Date date = new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.add ( Calendar.YEAR, 2 );
        Date expire_date  = calendar.getTime();
        String company_id = "barclaysglobal.com";

        ps_create_user.setString    (  1, username    );  //  userId
        ps_create_user.setString    (  2, company_id  );  //  companyId
        ps_create_user.setTimestamp (  3, (Timestamp) date        );  //  createDate
        ps_create_user.setString    (  4, password    );  //  password_ 
        ps_create_user.setInt       (  5, 0           );  //  passwordEncrypted 
        ps_create_user.setTimestamp (  6, new Timestamp(expire_date.getTime())  );  //  passwordExpirationDate 
        ps_create_user.setInt       (  7, 0           );  //  passwordReset 
        ps_create_user.setString    (  8, "Test"      );  //  firstName 
        ps_create_user.setString    (  9, "User"      );  //  middleName 
        ps_create_user.setString    ( 10, "Clone"     );  //  lastName 
        ps_create_user.setString    ( 11, "TestBot"   );  //  nickName
        ps_create_user.setInt       ( 12, 1           );  //  male
        ps_create_user.setString    ( 13, ""  );  //  birthday
        ps_create_user.setString    ( 14, ""  );  //  emailAddress
        ps_create_user.setString    ( 15, ""          );  //  smsId
        ps_create_user.setString    ( 16, ""          );  //  aimId
        ps_create_user.setString    ( 17, ""          );  //  icqId
        ps_create_user.setString    ( 18, ""          );  //  msnId
        ps_create_user.setString    ( 19, ""          );  //  ymId
        ps_create_user.setString    ( 20, ""  );  //  favoriteActivity
        ps_create_user.setString    ( 21, ""  );  //  favoriteBibleVerse
        ps_create_user.setString    ( 22, ""  );  //  favoriteFood
        ps_create_user.setString    ( 23, ""  );  //  favoriteMovie
        ps_create_user.setString    ( 24, ""  );  //  favoriteMusic
        ps_create_user.setString    ( 25, ""  );  //  languageId
        ps_create_user.setString    ( 26, ""  );  //  timeZoneId
        ps_create_user.setString    ( 27, ""  );  //  themeId
        ps_create_user.setString    ( 28, ""  );  //  colorSchemeId
        ps_create_user.setString    ( 29, ""  );  //  greeting
        ps_create_user.setString    ( 30, ""  );  //  resolution
        ps_create_user.setString    ( 31, ""  );  //  comments
        ps_create_user.setString    ( 32, ""  );  //  loginDate
        ps_create_user.setString    ( 33, ""  );  //  loginIP
        ps_create_user.setString    ( 34, ""  );  //  lastLoginDate
        ps_create_user.setString    ( 35, ""  );  //  lastLoginIP
        ps_create_user.setString    ( 36, ""  );  //  failedLoginAttempts
        ps_create_user.setInt       ( 37, 1           );  //  agreedToTermsOfUse
        ps_create_user.setInt       ( 38, 1           );  //  active_

        ps_create_user.executeUpdate();
    }
};
