package entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Timid on 21.12.2015.
 */
public class Transaction extends BaseEntity {
    private String uid;
    private String type;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pension_id", nullable = true)
    private Package pension;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "resign_id", nullable = true)
    private Package reSign;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_id", nullable = true)
    private Package protocol;
}
