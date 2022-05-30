package sendData;

import java.util.ArrayList;

public class FootballData {
    private int count;
    private Object filters;
    private ArrayList<CountryFields> areas;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getFilters() {
        return filters;
    }

    public void setFilters(Object filters) {
        this.filters = filters;
    }

    public ArrayList<CountryFields> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<CountryFields> areas) {
        this.areas = areas;
    }

    public FootballData(int count, Object filters, ArrayList<CountryFields> areas) {
        this.count = count;
        this.filters = filters;
        this.areas = areas;
    }

    public FootballData() {
    }
}
