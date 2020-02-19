package com.tp.challenge.restapi.familym;

import java.util.Comparator;


public class TreeMapComparator  implements Comparator<FamilyM> {
    public int compare(FamilyM a, FamilyM b)
    {
        //return Integer.valueOf(a.getId()) - Integer.valueOf(b.getId());       
    	return a.getBirthYear() - b.getBirthYear();
    }
}




