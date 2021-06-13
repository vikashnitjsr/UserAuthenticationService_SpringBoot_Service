package com.SpringBootProject.UserApp.controller;
import com.SpringBootProject.UserApp.pojos.BaseResponse;
import com.SpringBootProject.UserApp.pojos.RequestPojo;
import com.SpringBootProject.UserApp.pojos.UpdatePojo;
import com.SpringBootProject.UserApp.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;



@RequestMapping("/v1/")

@RestController
public class UserAppController
{
    @Autowired
    private UserAppService userAppService;


    @GetMapping(value = "/getall")
    public ResponseEntity<BaseResponse> getAllUserDetails()
    {
      return userAppService.showAllUserDetails();
    }



    @GetMapping(value = "/{jwtToken}")
    public ResponseEntity<BaseResponse> detailsUsingJWTToken(@PathVariable String jwtToken) throws SQLException
    {
      return  userAppService.userDetailsUsingJwtToken(jwtToken);
    }



    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse> addNewDetails(@RequestBody RequestPojo requestPojo)
    {
        userAppService.addNewUser(requestPojo);
        return new ResponseEntity(new BaseResponse("201","User Created successfully"), HttpStatus.CREATED);
    }




    @PutMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateNewDetails(@RequestBody UpdatePojo updatePojo)
    {
        boolean ans= userAppService.updateUserDetails(updatePojo);
        if(ans)
         return new ResponseEntity(new BaseResponse("202","User updated Successfully "),HttpStatus.ACCEPTED);
        else
         return new ResponseEntity(new BaseResponse("504","Timeout"),HttpStatus.GATEWAY_TIMEOUT);
    }





    @DeleteMapping(value = "/logout/{ID}")
    public ResponseEntity<BaseResponse> deleteDetails(@PathVariable Long ID)
    {
           return userAppService.userLogOut(ID);
    }



    @GetMapping(value = "/search/{statusID}")
    public ResponseEntity<BaseResponse> searchUserByStatusID(@PathVariable Long statusID)
    {
        return userAppService.searchUserByStatusID(statusID);
    }


}

