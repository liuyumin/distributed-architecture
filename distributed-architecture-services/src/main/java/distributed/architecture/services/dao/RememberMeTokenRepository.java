package distributed.architecture.services.dao;

import distributed.architecture.services.entity.PersistentLogins;
import distributed.architecture.services.mapper.PersistentLoginsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RememberMeTokenRepository {

    @Autowired
    private PersistentLoginsMapper persistentLoginsMapper;

    public PersistentLogins getLocalBySeries(String series){
        return persistentLoginsMapper.getBySeries(series);
    }

    public void deleteLocalByUsername(String username){
        persistentLoginsMapper.deleteByUsername(username);
    }

    public List<PersistentLogins> getLocalAllByUsernameOrderByDate(String username){
        return persistentLoginsMapper.getAllByUsernameOrderByDate(username);
    }

    public void save(PersistentLogins persistentLogins){
        persistentLoginsMapper.insert(persistentLogins);
    }

    public void delete(PersistentLogins persistentLogins){
        persistentLoginsMapper.deleteByPrimaryKey(persistentLogins.getId());
    }

}
