
package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.SmeUser;
import bankingApp.SpringBoot.model.dao.SmeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SmeUserService {

    @Autowired
    SmeUserRepository smeUserDao;


    public SmeUser findByUserName (String username){
      return smeUserDao.findByUserName(username);
    }

    public SmeUser findByBsn (int bsn){
        return smeUserDao.findByBsn(bsn);
    }

    public void newSmeUser (@Valid SmeUser smeUser){
        smeUserDao.save(smeUser);
    }


}
