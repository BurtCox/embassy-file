package com.embassy.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * @author Burt Cox (e41887)
 *
 * Description: Used to specify search criteria, used by EmbassyFileList. EmbassySearchCriteria and
 * EmbassyFileList are essentially convenience class for use with org.apache.commons.io library.
 * 
 * Create Date: Nov 30, 2017
 *
 * Copyright 2017 Southwest Airlines. All rights reserved.
 */
public class EmbassySearchCriteria {
   private List<File> _sourceDirectories = new ArrayList<>();
   private List<String> _includeSuffixes = new ArrayList<>();
   private List<String> _wildcardFileSpecs = new ArrayList<>();
   private boolean _recurseDirectories = false;
   
   public void addSourceDirectory(String sourceDirectory) {
      Path sourceDirectoryPath = Paths.get(sourceDirectory);
      File sourceDirectoryFile = sourceDirectoryPath.toFile();
      _sourceDirectories.add(sourceDirectoryFile);
   }
   
   public void addSourceDirectories(List<String> sourceDirectories) {
      for (String sourceDirectory : sourceDirectories) {
         addSourceDirectory(sourceDirectory);
      }
   }
   
   public void addIncludeSuffix(String includeSuffix) {
      _includeSuffixes.add(includeSuffix);
   }
   
   public void addWildcardFileSpec(String wildcardFileSpec) {
      _wildcardFileSpecs.add(wildcardFileSpec);
   }
   
   public IOFileFilter getFilters() {
      List<IOFileFilter> suffixFileFilters = new ArrayList<>();
      List<IOFileFilter> wildcardFileFilters = new ArrayList<>();
      
      for (String includeSuffix : _includeSuffixes) {
         suffixFileFilters.add(new SuffixFileFilter(includeSuffix));
      }
      
      for (String wildcardFileSpec : _wildcardFileSpecs) {
         wildcardFileFilters.add(new WildcardFileFilter(wildcardFileSpec));
      }
      
      
      return new AndFileFilter(new OrFileFilter(suffixFileFilters), new OrFileFilter(wildcardFileFilters));
   }

   public List<File> getSourceDirectories() {
      return _sourceDirectories;
   }

   public void setRecurseDirectories(boolean recurseDirectories) {
      _recurseDirectories = recurseDirectories;
   }
   
   public boolean isRecurseDirectories() {
      return _recurseDirectories;
   }
}
