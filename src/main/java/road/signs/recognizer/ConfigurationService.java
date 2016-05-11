package road.signs.recognizer;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import road.signs.recognizer.config.ApplicationConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Created by Basia on 11.05.16.
 */
public class ConfigurationService {

    private static final String SETUP_CFG_FILE_PATH = "setup.cfg";
    private static final Charset SETUP_CGF_FILE_ENCODING = Charsets.UTF_8;

    public static void saveConfiguration(ApplicationConfiguration applicationConfiguration){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String config = gson.toJson(applicationConfiguration);
        try {
            FileUtils.writeStringToFile(new File(SETUP_CFG_FILE_PATH), config, SETUP_CGF_FILE_ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<ApplicationConfiguration> loadConfiguration(){
        try {
            String json = FileUtils.readFileToString(new File(SETUP_CFG_FILE_PATH), SETUP_CGF_FILE_ENCODING);
            return Optional.ofNullable(new Gson().fromJson(json, ApplicationConfiguration.class));
        } catch (IOException e) {
            System.out.println("[ERROR] No configuration found! Expected config file: " + SETUP_CFG_FILE_PATH);
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
