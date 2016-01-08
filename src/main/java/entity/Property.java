package entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Timid on 08.01.2016.
 */
@Entity
@Table(name = "settings")
@NamedQueries({
        @NamedQuery(
                name = Property.FIND_ALL,
                query = "SELECT p FROM Property p"
        )
})
public class Property extends BaseEntity {
    public static final String FIND_ALL = "Property.findAll";

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
