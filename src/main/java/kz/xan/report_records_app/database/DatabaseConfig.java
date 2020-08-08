package kz.xan.report_records_app.database;

class DatabaseConfig {
    String dbHost = "localhost";
    String dbPort = "5432";
    String dbDatabase = "ReportRecords";
    String dbUsername = "postgres";
    String dbPassword = "alikhan01";

    private String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS";
    private String INSERT = "INSERT INTO";
    private String SELECT = "SELECT * FROM";
    private String DELETE = "DELETE FROM";

    protected String USER_TABLE = " users ";

    protected String USER_ID = " user_id ";
    protected String USER_ID_TYPE = "serial ";
    protected String USER_ID_CONSTRAINTS = "PRIMARY KEY " ;

    protected String USER_USERNAME = " username ";
    protected String USER_USERNAME_TYPE = "varchar(30) ";
    protected String USER_USERNAME_CONSTRAINTS = "NOT NULL ";

    protected String USER_PASSWORD = " password ";
    protected String USER_PASSWORD_TYPE = "varchar(30) ";
    protected String USER_PASSWORD_CONSTRAINTS = "NOT NULL ";

    protected String USER_ROLE = " role ";
    protected String USER_ROLE_TYPE = "varchar(20) ";
    protected String USER_ROLE_CONSTRAINTS = "NOT NULL ";

    protected String CREATE_USER_TABLE =
            CREATE_TABLE + USER_TABLE + "("
                    + USER_ID + USER_ID_TYPE + USER_ID_CONSTRAINTS + ","
                    + USER_USERNAME + USER_USERNAME_TYPE + USER_USERNAME_CONSTRAINTS + ","
                    + USER_PASSWORD + USER_PASSWORD_TYPE + USER_PASSWORD_CONSTRAINTS + ","
                    + USER_ROLE + USER_ROLE_TYPE + USER_ROLE_CONSTRAINTS + ");";

    protected String ADD_USER =
            INSERT + USER_TABLE + "(" + USER_ID + "," + USER_USERNAME + "," + USER_PASSWORD + "," + USER_ROLE + ") VALUES (?,?,?,?)";

    protected String GET_USER =
            SELECT + USER_TABLE + "WHERE" + USER_ID + "=?";

    protected String GET_ALL_USERS =
            SELECT + USER_TABLE + ";";

    protected String GET_USER_BY_USERNAME_PASSWORD =
            SELECT + USER_TABLE + "WHERE" + USER_USERNAME + "=? AND " + USER_PASSWORD + "=?;";

    protected String REMOVE_USER =
            DELETE + USER_TABLE + "WHERE" + USER_ID + "=?";

    //-----------------------------------------------------------------------------------------//
    protected String RECORD_TABLE = " records ";

    protected String RECORD_ID = " record_id ";
    protected String RECORD_ID_TYPE = "serial ";
    protected String RECORD_ID_CONSTRAINTS = "PRIMARY KEY ";

    protected String RECORD_DATE = " date ";
    protected String RECORD_DATE_TYPE = "varchar(10) ";
    protected String RECORD_DATE_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_CAR_MODEL = " car_model ";
    protected String RECORD_CAR_MODEL_TYPE = "varchar(50) ";
    protected String RECORD_CAR_MODEL_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_VIN_CODE = " vin ";
    protected String RECORD_VIN_CODE_TYPE = "varchar(30) ";
    protected String RECORD_VIN_CODE_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_STATE = " state ";
    protected String RECORD_STATE_TYPE = "varchar(20) ";
    protected String RECORD_STATE_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_STATUS = " status ";
    protected String RECORD_STATUS_TYPE = "varchar(20) ";
    protected String RECORD_STATUS_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_SUM = " sum ";
    protected String RECORD_SUM_TYPE = "int ";
    protected String RECORD_SUM_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_DATETIME = " date_time ";
    protected String RECORD_DATETIME_TYPE = "varchar(30) ";
    protected String RECORD_DATETIME_CONSTRAINTS = "NOT NULL ";

    protected String RECORD_USER_ID = " user_id ";
    protected String RECORD_USER_ID_TYPE = "int ";
    protected String RECORD_USER_ID_CONSTRAINTS = "REFERENCES" + USER_TABLE + "(" + USER_ID + ")" + " ON DELETE CASCADE";

    protected String CREATE_RECORD_TABLE =
            CREATE_TABLE + RECORD_TABLE + "("
                    + RECORD_ID + RECORD_ID_TYPE + RECORD_ID_CONSTRAINTS + ","
                    + RECORD_DATE + RECORD_DATE_TYPE + RECORD_DATE_CONSTRAINTS + ","
                    + RECORD_CAR_MODEL + RECORD_CAR_MODEL_TYPE + RECORD_CAR_MODEL_CONSTRAINTS + ","
                    + RECORD_VIN_CODE + RECORD_VIN_CODE_TYPE + RECORD_VIN_CODE_CONSTRAINTS + ","
                    + RECORD_STATE + RECORD_STATE_TYPE + RECORD_STATE_CONSTRAINTS + ","
                    + RECORD_STATUS + RECORD_STATUS_TYPE + RECORD_STATUS_CONSTRAINTS + ","
                    + RECORD_SUM + RECORD_SUM_TYPE + RECORD_SUM_CONSTRAINTS + ","
                    + RECORD_DATETIME + RECORD_DATETIME_TYPE + RECORD_DATETIME_CONSTRAINTS + ","
                    + RECORD_USER_ID + RECORD_USER_ID_TYPE + RECORD_USER_ID_CONSTRAINTS + ");";

    protected String ADD_RECORD =
            INSERT + RECORD_TABLE + "("
                    + RECORD_ID + ","
                    + RECORD_DATE + ","
                    + RECORD_CAR_MODEL + ","
                    + RECORD_VIN_CODE + ","
                    + RECORD_STATE + ","
                    + RECORD_STATUS + ","
                    + RECORD_SUM + ","
                    + RECORD_DATETIME + ","
                    + RECORD_USER_ID + ") VALUES (?,?,?,?,?,?,?,?,?)";

    protected String GET_RECORDS_BY_USER_ID =
            SELECT + RECORD_TABLE + "WHERE" + RECORD_USER_ID + "=?;";

    protected String REMOVE_RECORD =
            DELETE + RECORD_TABLE + "WHERE" + RECORD_ID + "=?";
}
