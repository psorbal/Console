package com.console.commands.filters;

import java.io.File;
import java.io.FilenameFilter;

public class RegFilter implements FilenameFilter{
    private String regf;

    public RegFilter(String ext){
        this.regf = ext;
    }

    public boolean accept(File file, String name) {
        name = name.substring(0, name.length()-1);
        return name.startsWith(regf);
    }
}
