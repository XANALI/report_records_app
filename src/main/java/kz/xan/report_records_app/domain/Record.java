package kz.xan.report_records_app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Record extends BaseEntity {
    private LocalDate date;
    private String carModel;
    private String vin;
    private RecordState recordState;
    private RecordStatus recordStatus;
    private Integer sum;
    private LocalDateTime recordDateTime;
    private Long userID;

    public Record() {
        super();
    }

    public Record(LocalDate date, String carModel, String vin, RecordState recordState,
                  RecordStatus recordStatus, Integer sum, LocalDateTime recordDateTime, Long userID) {
        this.date = date;
        this.carModel = carModel;
        this.vin = vin;
        this.recordState = recordState;
        this.recordStatus = recordStatus;
        this.sum = sum;
        this.recordDateTime = recordDateTime;
        this.userID = userID;
    }

    public Record(Long ID, LocalDate date, String carModel, String vin, RecordState recordState,
                  RecordStatus recordStatus, Integer sum, LocalDateTime recordDateTime, Long userID) {
        super(ID);
        this.date = date;
        this.carModel = carModel;
        this.vin = vin;
        this.recordState = recordState;
        this.recordStatus = recordStatus;
        this.sum = sum;
        this.recordDateTime = recordDateTime;
        this.userID = userID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public RecordState getRecordState() {
        return recordState;
    }

    public void setRecordState(RecordState recordState) {
        this.recordState = recordState;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public LocalDateTime getRecordDateTime() {
        return recordDateTime;
    }

    public void setRecordDateTime(LocalDateTime recordDateTime) {
        this.recordDateTime = recordDateTime;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", carModel='" + carModel + '\'' +
                ", vin='" + vin + '\'' +
                ", recordState=" + recordState +
                ", recordStatus=" + recordStatus +
                ", sum=" + sum +
                ", recordDateTime=" + recordDateTime +
                ", userID=" + userID +
                '}';
    }
}
