package lab4;

public class Subject {
    private String subj_name;
    private int hour;
    private int semester;

    public Subject(String subj_name, int hour, int semester) {
        this.subj_name = subj_name;
        this.hour = hour;
        this.semester = semester;
    }

    public String getSubj_name() {
        return subj_name;
    }

    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}