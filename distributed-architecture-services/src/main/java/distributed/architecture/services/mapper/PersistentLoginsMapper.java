package distributed.architecture.services.mapper;

import distributed.architecture.services.entity.PersistentLogins;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersistentLoginsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersistentLogins record);

    int insertSelective(PersistentLogins record);

    PersistentLogins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersistentLogins record);

    int updateByPrimaryKey(PersistentLogins record);

    void deleteByUsername(@Param("username") String username);

    PersistentLogins getBySeries(@Param("series") String series);

    List<PersistentLogins> getAllByUsernameOrderByDate(@Param("username") String username);

    void deleteByToken(@Param("token") String token);
}