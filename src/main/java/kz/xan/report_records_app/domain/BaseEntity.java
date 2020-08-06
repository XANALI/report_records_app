package kz.xan.report_records_app.domain;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private Long ID;

    BaseEntity(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
