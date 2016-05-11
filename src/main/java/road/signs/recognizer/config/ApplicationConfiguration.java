package road.signs.recognizer.config;

import road.signs.recognizer.config.network.architecture.NetworkArchitecture;

/**
 * Created by Basia on 11.05.16.
 */
public class ApplicationConfiguration {

    private NetworkArchitecture networkArchitecture = new NetworkArchitecture();
    private String loadNetworkPath = "encogexample.ser";

    public NetworkArchitecture getNetworkArchitecture() {
        return networkArchitecture;
    }

    public void setNetworkArchitecture(NetworkArchitecture networkArchitecture) {
        this.networkArchitecture = networkArchitecture;
    }

    public String getLoadNetworkPath() {
        return loadNetworkPath;
    }

    public void setLoadNetworkPath(String loadNetworkPath) {
        this.loadNetworkPath = loadNetworkPath;
    }
}
