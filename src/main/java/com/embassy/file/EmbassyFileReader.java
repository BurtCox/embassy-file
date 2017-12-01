package com.embassy.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

/**
 * @author Burt Cox (e41887)
 *
 * Description: Creates a BufferedReader that will read either normal or compressed files, and allows records 
 * to be read from the file.
 * 
 * Create Date: Nov 30, 2017
 *
 * Copyright 2017 Southwest Airlines. All rights reserved.
 */
public class EmbassyFileReader {
   private File _file;
   private BufferedReader _bufferedReader;

   public EmbassyFileReader(File file) throws FileNotFoundException, IOException {
      super();
      _file = file;
      if (file.getName().endsWith(".gz")) {
         GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(file));
         Reader reader = new InputStreamReader(gzipInputStream);
         _bufferedReader = new BufferedReader(reader);
     } else {
         _bufferedReader = new BufferedReader(new FileReader(file));
     }
   }
   
   public String getRecord() throws IOException {
      return _bufferedReader.readLine();
   }
   
   public void close() throws IOException {
      _bufferedReader.close();
   }
   
   public File getFile() {
      return _file;
   }
}
