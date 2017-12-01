package com.embassy.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Burt Cox (e41887)
 *
 * Description: Collect file names that match the search criteria specified by EmbassySearchCriteria.
 * EmbassySearchCriteria and EmbassyFileList are essentially convenience class for use with 
 * org.apache.commons.io library.
 * 
 * Create Date: Nov 30, 2017
 *
 * Copyright 2017 Southwest Airlines. All rights reserved.
 */
public class EmbassyFileList {
   Logger _logger = LogManager.getLogger();

   public Collection<File> listFiles(EmbassySearchCriteria embassySearchCriteria) {
      List<File> sourceDirectories = embassySearchCriteria.getSourceDirectories();
      IOFileFilter fileFilter = embassySearchCriteria.getFilters();
      IOFileFilter recurseDirectories = FalseFileFilter.INSTANCE;
      
      if (embassySearchCriteria.isRecurseDirectories()) {
         recurseDirectories = TrueFileFilter.INSTANCE;
      }
      
      List<File> fileList = new ArrayList<>();
      
      for (File sourceDirectory : sourceDirectories) {
         _logger.info("Searching for files in {}", sourceDirectory);
         Collection<File> files = FileUtils.listFiles(sourceDirectory, fileFilter, recurseDirectories);
         _logger.info("Found {} files", files.size());
         fileList.addAll(files);
      }

      return fileList;
   }

}
