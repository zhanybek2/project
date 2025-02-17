package api.asserts;

import api.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
@Slf4j
public abstract class EntityAssert {
    BaseEntity actualBaseEntity;

    public EntityAssert(BaseEntity actualBaseEntity) {
        this.actualBaseEntity = actualBaseEntity;
    }

    public void isEqualTo(BaseEntity expectedEntity) {
        Assertions.assertThat(actualBaseEntity)
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedEntity);
        log.info("Actual entity is equal to expected, \nActual: {},\nExpected: {}"
                , actualBaseEntity, expectedEntity);
    }
}