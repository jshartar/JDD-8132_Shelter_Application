package persistence;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.example.meenal.petconnect.*;

public class ManagementFacade {
    public final static String DEFAULT_TEXT_FILE_NAME = "data.txt";
    public final static String OTHER_DEFAULT_TEXT_FILE_NAME = "data2.txt";
    public final static String USER_FILE_NAME = "user.text";

    /**
     * the facade maintains references to any required model classes
     */
    private final UserManager im;

    /**
     * Singleton pattern
     */
    private static final ManagementFacade instance = new ManagementFacade();


    /**
     * private constructor for facade pattern
     */
    private ManagementFacade() {
        im = new UserManager();
    }

    /**
     * Singleton pattern accessor for instance
     *
     *
     * @return the one and only one instance of this facade
     */
    public static ManagementFacade getInstance() { return instance; }

    /**
     *loads text

     * @param users file for users
     * @return true when completed false if not
     */
    public boolean loadText(File users) {
        try {

            BufferedReader userReader = new BufferedReader(new FileReader(users));
            im.loadFromText(userReader);

        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open text file for loading!");
            return false;
        }

        return true;
    }


    public boolean saveText(File users) {
        System.out.println("Saving as a text file");
        try {
            PrintWriter pwUsers = new PrintWriter(users);
            im.saveAsUsers(pwUsers);
            pwUsers.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ManagerFacade", "Error opening the text file for save!");
            return false;
        }

        return true;
    }




    /**
     *add user
     * @param user user to be added
     */
    public void addUser(User user) {
        im.addUser(user);
    }
    /**
     *removes user
     * @param user user to be removed
     */
    public void removeUser(User user) {
        im.removeUser(user);
    }

}
