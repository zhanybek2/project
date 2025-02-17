package api.asserts;

import api.entity.BaseEntity;
import api.entity.create_user.CreateUsersAndUpdate;

public class UserAssert extends EntityAssert {

    public UserAssert(BaseEntity actualBaseEntity) {
        super(actualBaseEntity);
    }

    public static UserAssert assertThat(CreateUsersAndUpdate user) {
        return new UserAssert(user);
    }
}
