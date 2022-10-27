package dto;

public class StatusUpdate {

    private String userName;

    private String password;

    private int userId;

    private String status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusUpdate{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                '}';
    }
}
