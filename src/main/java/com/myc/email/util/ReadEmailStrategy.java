package com.myc.email.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;   
import java.util.List;
import java.util.stream.Stream;

public interface ReadEmailStrategy {
    public void printFileName() throws IOException;
    public List<File> getFileList() throws IOException;
    public boolean filterFilenameExtention(File file);
    public boolean checkModifiedFile(Path path) throws IOException;
    public HashMap<String, Stream<String>> getFileMap() throws IOException;
}
