package entities;

import java.util.Objects;

public class Ticket {


    private int id;
    private long amount;
    private String description;
    private int status;
    private int userId;


    public Ticket() {
    }

    public Ticket(int id, long amount, String description, int status, int userId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && amount == ticket.amount && status == ticket.status && userId == ticket.userId && Objects.equals(description, ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
