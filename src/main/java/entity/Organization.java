package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.security.cert.Certificate;
import java.sql.Timestamp;

/**
 * Created by Timid on 12.12.2015.
 */
@Entity
@Table(name = "organization")
public class Organization extends BaseEntity {
    private String name;
    private String inn;
    @Column(name = "pfr_reg_num")
    private String pfrRegNum;
    private Certificate certificate;
    @Column(name = "cert_reg_date")
    private Timestamp certRegDate;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPfrRegNum() {
        return pfrRegNum;
    }

    public void setPfrRegNum(String pfrRegNum) {
        this.pfrRegNum = pfrRegNum;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Timestamp getCertRegDate() {
        return certRegDate;
    }

    public void setCertRegDate(Timestamp certRegDate) {
        this.certRegDate = certRegDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
