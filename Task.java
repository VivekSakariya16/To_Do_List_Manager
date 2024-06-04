package Educational_Initiatives.To_Do_List_Manager;

import java.util.Date;

public class Task {
    private String description;
    private boolean completed;
    private Date dueDate;

    private String note;

    private Task() {}

    public static class Builder {
        private String description;
        private Date dueDate;
        private String note;

        public Builder(String description) {
            this.description = description;
        }

        public Builder dueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Task build() {
            Task task = new Task();
            task.description = this.description;
            task.dueDate = this.dueDate;
            task.note = this.note;
            task.completed = false;
            return task;
        }
    }


    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getNote() {
        return note;
    }
}
