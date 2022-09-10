import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

class MyTest {
    @Test
    void test() {
        Project project = ProjectBuilder.builder().build()
        getListSites(project)

//        getListSites()
//        writeProperties()
    }

    void getListSites(Project project) {
        String rootPath="/Users/admin/bwork/ybPlugin/src/test"
        def pfile = "/Users/admin/bwork/ybPlugin/src/test/bw_site_change_config.yaml"
        def mapper = new ObjectMapper(new YAMLFactory())
        JExtion jExtion = mapper.readValue(new File(pfile), JExtion)
        println(jExtion.toString())
        if (jExtion != null) {
            def sites=jExtion.getSites()
            if (sites!=null&&sites.size()>0){
                for (i in 0..<sites.size()) {
                    def item = sites.get(i)
                    print("=====>>item:" + item.toString())
                    project.task(item.whichPro).doFirst {
                        JTaskUtil.getKeyValue(item)
                    }.setGroup("ybSiteConfig")
                }
            }
        }

    }





}
