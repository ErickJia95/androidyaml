import com.google.gson.Gson
import com.google.gson.JsonParser

class JTaskUtil  {

    static void getKeyValue(String rootPath, JProModel jProModel) {
        if (jProModel != null) {
            def gson = new Gson()
            String json = gson.toJson(jProModel)
            def parser = new JsonParser()
            // 2.获得 根节点元素
            def element = parser.parse(json)
            // 3.根据 文档判断根节点属于 什么类型的 Gson节点对象
            def root = element.getAsJsonObject()
            root.entrySet().forEach {
                changeProperties(rootPath,it.key,it.value.asString)
            }
        }
    }


    static  void changeProperties(String rootPath,String key,String value) {
        def file = new File(rootPath + File.separator + "gradle.properties")
        if (file.exists()) {
            String temp = ""
            try {
                InputStream inputStream = new FileInputStream(file)
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
                StringBuffer stringBuffer = new StringBuffer()
                while ((temp = reader.readLine()) != null) {
                    def sp = temp.split("=")
                    if (sp != null && sp.size() > 0 && key == sp[0].replaceAll(" ", "")) {
                        if (key == "appKey"){
                            temp = key + "=" + "\""+ value + "\""
                        }else {
                            temp = key + "=" + value
                        }
                    }
                    stringBuffer.append(temp)
                    stringBuffer.append(System.getProperty("line.separator"))
                }
                reader.close()
                FileOutputStream fileOutputStream = new FileOutputStream(file)
                PrintWriter printWriter = new PrintWriter(fileOutputStream)
                printWriter.write(stringBuffer.toString().toCharArray())
                printWriter.flush()
                printWriter.close()
            } catch (Exception e) {
                print("=====>>" + "配置 gradle.properties 异常" + e.message)
            }
        } else {
            print("=====>>" + "配置 gradle.properties 文件不存在")
        }


    }



}
