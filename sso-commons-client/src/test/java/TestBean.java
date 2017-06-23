import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @author Stony Created Date : 2016/4/25  21:56
 */
public class TestBean implements Serializable{

    private String name;
    private String filter;

    @Value("${client.filter.chain.definitions}")
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
