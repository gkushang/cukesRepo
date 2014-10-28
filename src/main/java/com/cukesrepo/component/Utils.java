package com.cukesrepo.component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Utils
{

    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static List<File> search(File directory, String fileExtension)
    {
        List<File> files = new ArrayList<File>();

        if (directory.isDirectory())
        {
            if (directory.canRead())
            {

                for (File rFile : directory.listFiles())
                {

                    if (rFile.getName().endsWith(fileExtension))
                    {
                        files.add(rFile);
                    }
                }
            }
            else
            {
                LOG.warn("You do not have permission to access the directory '{}'", directory.getAbsolutePath());
            }
        }

        return files;
    }
}
