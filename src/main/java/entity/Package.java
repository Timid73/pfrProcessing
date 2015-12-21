package entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Timid on 12.12.2015.
 */
@Entity
@Table(name = "package")
public class Package extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "from_organization_id", nullable = false)
    private Organization from;
    @ManyToOne
    @JoinColumn(name = "to_organization_id", nullable = false)
    private Organization to;
    private Timestamp date;
    private String type;
    private boolean positive;
    private Archive file;

    public Organization getFrom() {
        return from;
    }

    public void setFrom(Organization from) {
        this.from = from;
    }

    public Organization getTo() {
        return to;
    }

    public void setTo(Organization to) {
        this.to = to;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public Archive getFile() {
        return file;
    }

    public void setFile(Archive file) {
        this.file = file;
    }
}
