package com.tp.challenge.restapi.familym;

import java.util.Collection;
import java.util.TreeMap;

public interface FamilyMCollectionRepository {

	  public abstract Collection<TreeMap<FamilyM, Long>> getMTreeFamiliesM(); 
}
