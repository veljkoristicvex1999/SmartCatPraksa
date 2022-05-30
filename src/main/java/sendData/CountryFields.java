package sendData;

public class CountryFields {
    private int id;
    private String name;
    private String countryCode;
    private Object flag = null;
    private Integer parentAreaId;
    private String parentArea;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public Integer getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(Integer parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public CountryFields() {

    }

    public CountryFields(int id, String name, String countryCode, Object flag, Integer parentAreaId, String parentArea) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.flag = flag;
        this.parentAreaId = parentAreaId;
        this.parentArea = parentArea;
    }
}
