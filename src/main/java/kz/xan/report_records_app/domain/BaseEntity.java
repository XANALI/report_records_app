package kz.xan.report_records_app.domain;

public class BaseEntity {
    private Long ID;

    BaseEntity() {
    }

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
