package persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.meenal.petconnect.*;

public class UserManager implements Serializable{
    private final List<User> userList = new ArrayList<>();

    private transient Map<String, User> userMap = new HashMap<>();


    /**
     *add user
     * @param username username of user
     * @param firstName user's first name
     * @param lastName user's last name
     * @param password password of user
     * @param email user's email
     * @param phoneNumber user's phone number
     * @param type user type
     *
     */
    public void addUser(String username, String firstName, String lastName, String password, String email,
                        String phoneNumber, String type) {
        User user = new User(username, firstName, lastName, password, email, phoneNumber, type);

        userList.add(user);
    }

    /**
     *save user
     * @param writer print writer
     *
     */
    public void saveAsUsers(PrintWriter writer) {
        System.out.println("Item Manager saving: " + (userList.size()) + " users" );
        writer.println(userList.size());

        for(User u : userList) {
            u.saveAsText(writer);
        }
    }



    /**
     *loads data from text

     * @param users users
     */
    public void loadFromText(BufferedReader users) {
        System.out.println("Loading Text File");
        Model model = Model.getInstance();

        userList.clear();
        try {
            String countStrUsers = users.readLine();
            assert countStrUsers != null;
            int userCount = Integer.parseInt(countStrUsers);


            for (int i = 0; i < userCount; ++i) {
                String line = users.readLine();
                User l = User.parseEntry(line);
                System.out.println("Loading User: " + l.getFullName());
                userList.add(l);
                model.addUser(l);
            }

            //be sure and close the file
            users.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("- Done loading text file - \n" + userList.size() + " Users.");

    }


    /**
     * used by command pattern, should be not called otherwise
     *
     * @param user the student to add
     */
    void addUser(User user) {
        userList.add(user);
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void removeUser(User item) {
        userList.remove(item);
    }
}
