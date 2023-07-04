package tn.camelstudio.jhipster.service.extended;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tn.camelstudio.jhipster.domain.User;
import tn.camelstudio.jhipster.domain.UserExtra;
import tn.camelstudio.jhipster.repository.extended.UserExtraRepositoryExtended;
import tn.camelstudio.jhipster.service.UserExtraService;
import tn.camelstudio.jhipster.service.UserService;
import tn.camelstudio.jhipster.service.dto.AdminUserDTO;

@Service
@Transactional
public class UserExtraServiceExtended extends UserExtraService {

    private final Logger log = LoggerFactory.getLogger(UserExtraServiceExtended.class);

    private final UserExtraRepositoryExtended userExtraRepositoryExtended;
    private final UserService userService;

    public UserExtraServiceExtended(UserExtraRepositoryExtended userExtraRepositoryExtended, UserService userService) {
        super(userExtraRepositoryExtended);
        this.userExtraRepositoryExtended = userExtraRepositoryExtended;
        this.userService = userService;
    }

    // But : creation d'un user extra
    // creation d'un user
    // association entre user et userExtra
    //
    public UserExtra createUserExtraWithUser(UserExtra userExtra) {
        // validation :
        // est ce que un user existe avec le même login or email
        // convertion User to AdminUserDTO
        AdminUserDTO userDTO = new AdminUserDTO(userExtra.getUser());
        String password = userExtra.getUser().getPassword();
        // creation d'un user
        User user = userService.registerUser(userDTO, password);
        // activation d'un user , ça dépend de contexte de l'application
        user.setActivated(true);
        // association entre user et user extra
        userExtra.setUser(user);
        // save user extra + association
        return userExtraRepositoryExtended.save(userExtra);
    }
}
