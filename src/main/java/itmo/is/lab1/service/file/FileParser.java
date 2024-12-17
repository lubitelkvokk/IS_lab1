package itmo.is.lab1.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.aspectj.bridge.ICommand;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@ApplicationScope
public class FileParser {
    private final List<Class<?>> objClasses;
    private final ObjectMapper mapper;

    public FileParser() throws ClassNotFoundException {
        objClasses = new ArrayList<>();
        mapper = JsonMapper.builder().findAndAddModules().build();
        String packageName = "itmo.is.lab1.DTO.model.data";
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));

        if (root == null) throw new ClassNotFoundException("Package " + packageName + " was not found");
        File[] files = new File(root.getFile()).listFiles((dir, name) -> name.endsWith(".class"));

        if (files == null) throw new ClassNotFoundException("Files wasn't found");
        for (File file : files) {
            String className = file.getName().replaceAll(".class$", "");
//            System.out.println(className);
            Class<?> cls = Class.forName(packageName + "." + className);
            objClasses.add(cls);
        }
    }

    public List<Object> parseObjects(MultipartFile multipartFile) throws IOException, ClassNotFoundException {
        List<Object> objects = new ArrayList<>();
        for (String str : new String(multipartFile.getBytes()).trim().split(";")) {
            Class<?> cls = objClasses.stream()
                    .filter(cl -> (str.trim().split("\\{")[0].trim() + "DTO").startsWith(cl.getSimpleName()))
                    .findAny().orElseThrow(() -> new NoSuchElementException("There aren't such name of model data"));
//            System.out.println("{" + str.split("\\{")[1]);
            objects.add(mapper.readValue("{" + str.split("\\{")[1], cls));
        }
        return objects;
    }
}
