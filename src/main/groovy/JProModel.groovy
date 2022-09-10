

class JProModel{
    String whichPro
    String appName
    String appKey
    String appType

    JProModel() {
    }

    JProModel(String whichPro, String appName, String appKey, String appType) {
        this.whichPro = whichPro
        this.appName = appName
        this.appKey = appKey
        this.appType = appType
    }

    String getWhichPro() {
        return whichPro
    }

    void setWhichPro(String whichPro) {
        this.whichPro = whichPro
    }

    String getAppName() {
        return appName
    }

    void setAppName(String appName) {
        this.appName = appName;
    }

    String getAppKey() {
        return appKey
    }

    void setAppKey(String appKey) {
        this.appKey = appKey
    }

    String getAppType() {
        return appType
    }

    void setAppType(String appType) {
        this.appType = appType
    }

    @Override
    String toString() {
        return "JProModel{" +
                "whichPro='" + whichPro + '\'' +
                ", appName='" + appName + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appType='" + appType + '\'' +
                '}';
    }
}
