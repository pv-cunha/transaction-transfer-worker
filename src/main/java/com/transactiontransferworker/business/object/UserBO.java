package com.transactiontransferworker.business.object;

import com.transactiontransferworker.api.dtos.UserCreateDTO;
import com.transactiontransferworker.api.dtos.UserDTO;
import com.transactiontransferworker.business.service.UserBS;
import com.transactiontransferworker.business.service.UserPermissionBS;
import com.transactiontransferworker.converters.UsersConverter;
import com.transactiontransferworker.exceptions.UserBalanceException;
import com.transactiontransferworker.exceptions.UserMerchantException;
import com.transactiontransferworker.repository.enuns.UserType;
import com.transactiontransferworker.repository.models.Group;
import com.transactiontransferworker.repository.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserBO {

    @Autowired
    private UserBS userBS;

    @Autowired
    private UserPermissionBS userPermissionBS;

    @Autowired
    private UsersConverter usersConverter;

    public UserCreateDTO create(UserDTO userDTO) {
        User user = usersConverter.convertToUser(userDTO);

        List<Group> groupList = userPermissionBS.getGroupByUserType(user.getUserType());

        user.setPermissionsGroup(groupList);

        userBS.create(user);

        return usersConverter.convertToUserCreateDTO(userDTO);
    }

    public void validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new UserMerchantException();
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new UserBalanceException();
        }
    }

    public void updateSenderBalance(User user, BigDecimal amount) {

        user.setBalance(user.getBalance().subtract(amount));

        userBS.update(user);
    }

    public void updateReceiverBalance(User user, BigDecimal amount) {

        user.setBalance(user.getBalance().add(amount));

        userBS.update(user);
    }

}
