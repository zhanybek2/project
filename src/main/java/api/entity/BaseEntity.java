package api.entity;

    import api.utils.JsonUtils;

    public abstract class BaseEntity {

        public String toJson(){
            return JsonUtils.toJson(this);
        }
    }
