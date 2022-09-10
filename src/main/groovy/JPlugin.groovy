import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.gradle.api.Plugin
import org.gradle.api.Project

class JPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def rootPath = project.getRootDir().getAbsolutePath()
        if (rootPath != null && !rootPath.isEmpty()) {
            def jExtion = getListSites(rootPath)
            println(jExtion.toString())
            if (jExtion != null) {
                def sites = jExtion.getSites()
                if (sites != null && sites.size() > 0) {
                    for (i in 0..<sites.size()) {
                        def item = sites.get(i)
                        print("=====>>item:" + item.toString())
                        project.task(item.getWhichPro()).doFirst {
                            JTaskUtil.getKeyValue(rootPath,item)
                        }.setGroup("ybSiteConfig")
                    }
                }
            }
        }
    }


    JExtion getListSites(String rootPath) {
        def file = new File(rootPath + File.separator + "bw_site_change_config.yaml")
        if (file.exists()) {
            def mapper = new ObjectMapper(new YAMLFactory())
            return mapper.readValue(file, JExtion)
        } else {
            print("=====>>" + "配置 bw_site_change_config.yaml 文件")
        }
        return null
    }


}
