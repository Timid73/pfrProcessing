package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Timid on 12.12.2015.
 */
@Entity
@Table(name = "archive")
public class Archive extends BaseEntity {
    @Column(name = "file_name")
    private String fileName;
    private Timestamp date;
}
