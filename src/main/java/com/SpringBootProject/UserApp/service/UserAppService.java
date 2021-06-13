package com.SpringBootProject.UserApp.service;
import com.SpringBootProject.UserApp.enumPkg.Status;
import com.SpringBootProject.UserApp.model.UserAppDTO;
import com.SpringBootProject.UserApp.pojos.BaseResponse;
import com.SpringBootProject.UserApp.pojos.RequestPojo;
import com.SpringBootProject.UserApp.pojos.UpdatePojo;
import com.SpringBootProject.UserApp.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;


@Service
public class UserAppService
{

    @Autowired
    private UserAppRepository userAppRepository;
    private long loginTimeInMilliSec;



    public ResponseEntity<BaseResponse> showAllUserDetails()
    {

            List<UserAppDTO> listOfUserAppDTO = userAppRepository.findAll();
            if (listOfUserAppDTO.isEmpty())
                return new ResponseEntity(new BaseResponse("403","User List is empty !!"),HttpStatus.FORBIDDEN);
            return new ResponseEntity(new BaseResponse("200",listOfUserAppDTO),HttpStatus.OK);
    }





    public void addNewUser(RequestPojo requestPojo)
    {
        UserAppDTO userAppDTO = new UserAppDTO();
        userAppDTO.setJwt_token(UUID.randomUUID().toString());

        userAppDTO.setDeviceID(requestPojo.getDeviceID());
        userAppDTO.setUserID(requestPojo.getUserID());
        userAppDTO.setStatus(Status.VALID.toString());


        loginTimeInMilliSec = System.currentTimeMillis();

        userAppDTO.setLoginTimeInMilliSec(loginTimeInMilliSec);
        long expiryTimeInMilliSec = loginTimeInMilliSec + 900000;

        userAppDTO.setLoginTime(new Timestamp(loginTimeInMilliSec));
        userAppDTO.setExpiryTime(new Timestamp((expiryTimeInMilliSec)));

        userAppRepository.save(userAppDTO);
    }



    public boolean updateUserDetails(UpdatePojo updatePojo)
    {
         boolean flag;
         UserAppDTO userAppDTO =  userAppRepository.findByUserID(updatePojo.getOldUserID());

               if((System.currentTimeMillis() - userAppDTO.getLoginTimeInMilliSec()) <= 900000)
                {
                   userAppDTO.setDeviceID(updatePojo.getDeviceID());
                   userAppDTO.setUserID(updatePojo.getNewUserID());
                   userAppRepository.save(userAppDTO);
                   flag=true;
                }
                else
                {
                 flag=false;
                }
                return flag;
    }



  // we have to use a Datastructureslike list .. and send data fro this method to respective controller !!
    // In controller , check and apply constraints on list and return HTTPS Status

    public ResponseEntity<BaseResponse> userDetailsUsingJwtToken(String jwtToken) throws SQLException
    {

        if(userAppRepository.existJwtToken(jwtToken)==1)
        {
              if((System.currentTimeMillis() - userAppRepository.userInfo(jwtToken).get(0).getLoginTimeInMilliSec()) <= 900000)
              {
                List<UserAppDTO> result = userAppRepository.userInfo(jwtToken);
                return new ResponseEntity(new BaseResponse("200",result),HttpStatus.OK);
              }
             else
             {
                return new ResponseEntity(new BaseResponse("504", "JwtToken Expired ,Please Login Again"),HttpStatus.GATEWAY_TIMEOUT);
             }
        }
        else
        {
                return new ResponseEntity(new BaseResponse("400","InValid JWT Token"),HttpStatus.BAD_REQUEST);
        }
    }



    public ResponseEntity<BaseResponse> userLogOut(Long statusID)
    {
        if(userAppRepository.existsById(statusID))
        {
            userAppRepository.deleteById(statusID);
            return new ResponseEntity(new BaseResponse("200","UserLogOut SuccessFully !!" ),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(new BaseResponse("400", "Please enter a valid statusID"),HttpStatus.BAD_REQUEST);
        }
    }



    public  ResponseEntity<BaseResponse> searchUserByStatusID(Long statusID)
    {
         if(userAppRepository.existsById(statusID))
          return new ResponseEntity( new BaseResponse("200",userAppRepository.findById(statusID)),HttpStatus.OK);
         else
           return new ResponseEntity( new BaseResponse("400","Please enter a valid statusID"),HttpStatus.BAD_REQUEST);
    }

}

