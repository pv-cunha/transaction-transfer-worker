package com.transactiontransferworker.api.controllers;

import com.transactiontransferworker.api.dtos.ResponseDTO;
import com.transactiontransferworker.api.dtos.UserCreatedDTO;
import com.transactiontransferworker.api.dtos.UserDTO;
import com.transactiontransferworker.api.messages.APIMessages;
import com.transactiontransferworker.business.object.UserBO;
import com.transactiontransferworker.utils.Constants;
import com.transactiontransferworker.utils.PathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = PathConstants.PATH_TRANSACTION_TRANSFER_WORKER_USER)
public class UserController {
    @Autowired
    private APIMessages apiMessages;
    @Autowired
    private UserBO userBO;

    @PostMapping(PathConstants.PATH_POST_CREATE_USER)
    public @ResponseBody ResponseDTO<UserCreatedDTO> createNewUser(@RequestBody @Valid UserDTO userDTO) {

        UserCreatedDTO userCreatedDTO = userBO.create(userDTO);

        return ResponseDTO.success(apiMessages.getSuccessFullMessage(), Constants.SUCCESS_CODE, userCreatedDTO);
    }

}
