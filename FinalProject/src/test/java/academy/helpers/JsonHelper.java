package academy.helpers;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class JsonHelper {
    @JsonIgnore
    public boolean hasMore;

    public List<ItemInJsonList> items;

    public static class ItemInJsonList {
        public String orderType;
        public Integer all;
        public Integer negative;
        public Integer positive;
    }
}

