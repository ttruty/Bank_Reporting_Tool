package com.company;

public class User {
    /**
     * Create the user object to hold user data
     * id, username, password, permissions
     *
     */

    //instance fileds
    private int id;
    private String username;
    private String password;
    private int permisions;

    public User(){
        this.id = id;
        this.username = username;
        this.password = password;
        this.permisions = permisions;
    }

    /**
     * Gets id
     *
     * @return value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets ids
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  Gets username
     *
     * @return string value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     *  Sets unsername
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     *
     * @return string value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets permissions
     *
     * @return int value of permission
     */
    public int getPermisions() {
        return permisions;
    }

    /**
     *  Sets permissions
     *
     * @param permisions
     */
    public void setPermisions(int permisions) {
        this.permisions = permisions;
    }

    @Override
    public String toString() {

        return String.format("%-12s%-12s%-12s%-12s%-12s\n",
                "User",
                id,
                username,
                password,
                permisions);
    }
}
