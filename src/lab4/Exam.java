package lab4;

public class Exam {
    private int student_id;
    private int mark;
    private String exam_date;

    public Exam(int student_id, int mark, String exam_date) {
        this.student_id = student_id;
        this.mark = mark;
        this.exam_date = exam_date;
    }

    // public Exam(String student_id, String exam_date, String mark) {
    // this.student_id = Integer.parseInt(student_id);
    // this.exam_date = exam_date;
    // this.mark = Integer.parseInt(mark);
    // }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }
}
